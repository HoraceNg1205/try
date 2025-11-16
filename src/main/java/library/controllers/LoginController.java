package library.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import library.Main;
import library.SessionManager;
import library.models.User;
import library.repositories.UserRepository;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class LoginController {
    @FXML private Label        headerLabel;
    @FXML private TextField    usernameField;
    @FXML private PasswordField passwordField;

    private String selectedRole;
    private UserRepository userRepo = new UserRepository();         // New: Create repository instance

    public void setRole(String role) {
        this.selectedRole = role;
        String role_name = switch (role.toLowerCase()) {
            case "student", "staff" -> "Student/Staff";
            case "author" -> "Author";
            case "librarian" -> "Librarian";
            default -> "";
        };
        headerLabel.setText(role_name + " Login");
    }

    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        Parent home = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Home.fxml")));
        Main.getPrimaryStage().setScene(new Scene(home, 640, 480));
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Login Failed", "Username and password cannot be empty.");
            return;
        }

        try {
            Optional<User> authResult = userRepo.authenticate(username, password);

            if (authResult.isEmpty()) {
                // Use case A2: Unknown username or password
                showAlert("Login Failed", "Unknown username or password!");
                return;
            }

            User user = authResult.get();
            String userRole = user.getClass().getSimpleName().toLowerCase(); // "student", "author", etc.

            // Use case A3: Wrong login user type selected
            if (!isRoleMatch(userRole, selectedRole)) {
                showAlert("Login Failed", "You are not a " + selectedRole + "! Please log in in the correct screen.");
                return;
            }

            // --- Login Success ---

            SessionManager.getInstance().setLoggedInUser(user);

            String fxml;
            switch (userRole) {
                case "student":   fxml = "/fxml/StudentDashboard.fxml";   break;
                case "author":    fxml = "/fxml/AuthorDashboard.fxml";    break;
                case "librarian": fxml = "/fxml/LibrarianDashboard.fxml"; break;
                default:          fxml = "/fxml/Home.fxml";               break;
            }

            Parent dash = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
            Main.getPrimaryStage().setScene(new Scene(dash, 1000, 700));

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while trying to log in.");
        }
    }

    /**
     * Helper to check if the authenticated user's role matches the login screen role.
     */
    private boolean isRoleMatch(String userRole, String loginRole) {
        if (loginRole.toLowerCase().equals("student") || loginRole.toLowerCase().equals("staff")) {
            return userRole.equals("student"); // Assumes "staff" is saved as "student"
        }
        return userRole.equals(loginRole.toLowerCase());
    }

    /**
     * NEW: Helper method to show alerts
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /** New: navigate to the standalone Register screen */
    @FXML
    private void handleGoToRegister(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Register.fxml"));
        Parent root = loader.load();
        RegisterController ctrl = loader.getController();
        ctrl.setRole(selectedRole);
        Main.getPrimaryStage().setScene(new Scene(root, 640, 480));
    }

    private String capitalize(String s) {
        return s.substring(0,1).toUpperCase() + s.substring(1);
    }
}
