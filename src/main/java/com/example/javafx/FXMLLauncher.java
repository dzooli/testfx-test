package com.example.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class FXMLLauncher extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        // Ask user which example to run
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("FXML Example Selector");
        alert.setHeaderText("Choose an FXML example to run:");
        alert.setContentText("Select which JavaFX FXML example you'd like to see:");
        
        ButtonType simpleButton = new ButtonType("Simple Example");
        ButtonType advancedButton = new ButtonType("Advanced Example");
        ButtonType cancelButton = ButtonType.CANCEL;
        
        alert.getButtonTypes().setAll(simpleButton, advancedButton, cancelButton);
        
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.isPresent()) {
            if (result.get() == simpleButton) {
                loadFXMLExample("/main.fxml", "JavaFX Simple FXML Example", primaryStage, 400, 200);
            } else if (result.get() == advancedButton) {
                loadFXMLExample("/advanced.fxml", "JavaFX Advanced FXML Example", primaryStage, 600, 450);
            } else {
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
    }
    
    private void loadFXMLExample(String fxmlPath, String title, Stage stage, int width, int height) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            
            Scene scene = new Scene(root, width, height);
            scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
            
            stage.setTitle(title);
            stage.setScene(scene);
            stage.setResizable(true);
            
            // Set accessibility properties
            stage.getScene().getRoot().setAccessibleText(title + " main window");
            
            stage.show();
            
            // Enable accessibility
            enableAccessibility();
            
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load FXML file " + fxmlPath + ": " + e.getMessage());
            
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Failed to load FXML");
            errorAlert.setContentText("Could not load the FXML file: " + fxmlPath);
            errorAlert.showAndWait();
            
            System.exit(1);
        }
    }
    
    /**
     * Enable Java accessibility features
     */
    private void enableAccessibility() {
        System.setProperty("javax.accessibility.assistive_technologies", 
                          "com.sun.java.accessibility.util.AWTEventMonitor");
        
        try {
            Class.forName("com.sun.java.accessibility.util.AWTEventMonitor");
        } catch (ClassNotFoundException e) {
            System.out.println("Accessibility classes not found, but application will continue to work");
        }
    }
    
    public static void main(String[] args) {
        // Enable accessibility from the start
        System.setProperty("javax.accessibility.assistive_technologies", 
                          "com.sun.java.accessibility.util.AWTEventMonitor");
        
        launch(args);
    }
}