package com.example.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleRole;

public class App extends Application {
    
    private Label displayLabel;
    private int clickCount = 0;
    
    @Override
    public void start(Stage primaryStage) {
        // Create the main label that will display changing text
        displayLabel = new Label("Welcome to JavaFX Application!");
        displayLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        // Set accessibility properties for the label
        displayLabel.setAccessibleText("Display label showing welcome message");
        displayLabel.setAccessibleRole(javafx.scene.AccessibleRole.TEXT);
        displayLabel.setAccessibleHelp("This label shows text that changes when the button is clicked");
        
        // Create the button that will change the text
        Button changeTextButton = new Button("Click Me!");
        changeTextButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px;");
        
        // Set accessibility properties for the button
        changeTextButton.setAccessibleText("Change text button");
        changeTextButton.setAccessibleRole(javafx.scene.AccessibleRole.BUTTON);
        changeTextButton.setAccessibleHelp("Click this button to change the text displayed above");
        
        // Set button action
        changeTextButton.setOnAction(e -> {
            clickCount++;
            String newText = "Button clicked " + clickCount + " time" + (clickCount == 1 ? "" : "s") + "!";
            displayLabel.setText(newText);
            displayLabel.setAccessibleText("Display label now showing: " + newText);
        });
        
        // Create layout
        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(displayLabel, changeTextButton);
        
        // Set accessibility properties for the root container
        root.setAccessibleText("Main application window");
        root.setAccessibleRole(javafx.scene.AccessibleRole.PARENT);
        
        // Create scene and stage
        Scene scene = new Scene(root, 400, 200);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        
        primaryStage.setTitle("JavaFX Accessibility Test Application");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        
        // Set accessibility properties for the stage/window
        primaryStage.getScene().getRoot().setAccessibleText("JavaFX test application main window");
        
        primaryStage.show();
        
        // Ensure accessibility is enabled
        enableAccessibility();
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