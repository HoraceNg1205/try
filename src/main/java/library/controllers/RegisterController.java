package library.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import library.Main;
import library.repositories.UserRepository;

import java.io.IOException;

public class RegisterController {

    @FXML private Label  headerLabel;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField fullNameField;

    private String selectedRole; // "student", "author", or "librarian"

    public void setRole(String role) {
        this.selectedRole = role;
        headerLabel.setText(capitalize(role) + " Register");
    }

    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        Parent home = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
        Main.getPrimaryStage().setScene(new Scene(home, 640, 480));
    }

    @FXML
    private void handleGoToLogin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        Parent root = loader.load();
        LoginController ctrl = loader.getController();
        ctrl.setRole(selectedRole);
        Main.getPrimaryStage().setScene(new Scene(root, 640, 480));
    }
    @FXML
    private void handleRegister(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String fullName = fullNameField.getText();
        
        // Alternative Flow A1, empty fields
        if (username.isEmpty() || password.isEmpty() || fullName.isEmpty()) {
            showAlert("Validation Error", "All fields must be filled!");
            return;
        }
        
        UserRepository userRepo = new UserRepository();
        
        // Alternative Flow A2, username already exists
        if (userRepo.userExists(username)) {
            showAlert("Registration Failed", "Username already exists!");
            return;
        }
        
        // Save new user
        boolean success = userRepo.saveUser(username, password, fullName, selectedRole);
        if (success) {
            System.out.println("Registration successful for: " + username);
            // Navigate to login
            handleGoToLogin(event);
        } else {
            showAlert("Error", "Registration failed!");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private String capitalize(String s) {
        return s.substring(0,1).toUpperCase() + s.substring(1);
    }
}
