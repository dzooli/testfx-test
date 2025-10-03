package com.example.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
            Parent root = loader.load();
            
            // Create scene
            Scene scene = new Scene(root, 400, 200);
            scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
            
            primaryStage.setTitle("JavaFX FXML Application");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            
            // Set accessibility properties for the stage/window
            primaryStage.getScene().getRoot().setAccessibleText("JavaFX FXML application main window");
            
            primaryStage.show();
            
            // Ensure accessibility is enabled
            enableAccessibility();
            
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load FXML file: " + e.getMessage());
        }
    }
    
    /**
     * Enable Java accessibility features
     */
    private void enableAccessibility() {
        // Force accessibility to be enabled
        System.setProperty("javax.accessibility.assistive_technologies", 
                          "com.sun.java.accessibility.util.AWTEventMonitor");
        
        // Additional accessibility setup if needed
        try {
            // This helps ensure the Java Access Bridge is available
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