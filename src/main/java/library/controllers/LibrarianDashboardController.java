// src/main/java/library/controllers/LibrarianDashboardController.java
package library.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.Main;
import library.models.Book;

import java.io.IOException;

public class LibrarianDashboardController {

    /** Called automatically after FXML is loaded. */
    @FXML
    private void initialize() {
    }

    /** Log out back to the Home screen. */
    @FXML
    private void handleLogout(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
            Stage st = Main.getPrimaryStage();
            st.setScene(new Scene(root, 640, 480));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** View selected book content. */
    @FXML
    private void handleViewBook(ActionEvent event) {
        // Book selected = pendingBooksTable.getSelectionModel().getSelectedItem();
        // if (selected != null) {
        //     // TODO: open reader window with book content
        //     System.out.println("Viewing book: " + selected.getTitle());
        // }
    }

    /** Approve selected book. */
    @FXML
    private void handleApproveBook(ActionEvent event) {
        // Book selected = pendingBooksTable.getSelectionModel().getSelectedItem();
        // if (selected != null) {
        //     // TODO: update status in DB, notify author
        //     System.out.println("Approved book: " + selected.getTitle());
        // }
    }

    /** Reject selected book. */
    @FXML
    private void handleRejectBook(ActionEvent event) {
        // Book selected = pendingBooksTable.getSelectionModel().getSelectedItem();
        // if (selected != null) {
        //     // TODO: update status in DB, notify author
        //     System.out.println("Rejected book: " + selected.getTitle());
        // }
    
    }
}
