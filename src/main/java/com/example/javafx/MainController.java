package com.example.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MainController {
    
    @FXML
    private VBox rootVBox;
    
    @FXML
    private Label displayLabel;
    
    @FXML
    private Button changeTextButton;
    
    private int clickCount = 0;
    
    /**
     * Initialize method called after FXML loading
     */
    @FXML
    private void initialize() {
        // Set accessibility properties for the root container
        rootVBox.setAccessibleText("Main application window");
        rootVBox.setAccessibleRole(javafx.scene.AccessibleRole.PARENT);
        
        // Initialize the display label
        displayLabel.setText("Welcome to JavaFX Application with FXML!");
        displayLabel.setAccessibleText("Display label showing welcome message");
    }
    
    /**
     * Handle button click event
     */
    @FXML
    private void handleButtonClick() {
        clickCount++;
        String newText = "Button clicked " + clickCount + " time" + (clickCount == 1 ? "" : "s") + "!";
        displayLabel.setText(newText);
        displayLabel.setAccessibleText("Display label now showing: " + newText);
    }
}