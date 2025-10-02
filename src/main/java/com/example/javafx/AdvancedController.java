package com.example.javafx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AdvancedController implements Initializable {
    
    @FXML private BorderPane rootPane;
    @FXML private MenuBar menuBar;
    @FXML private Menu fileMenu, helpMenu;
    @FXML private MenuItem newMenuItem, openMenuItem, exitMenuItem, aboutMenuItem;
    
    @FXML private VBox centerVBox;
    @FXML private Label titleLabel;
    @FXML private HBox inputHBox;
    @FXML private TextField nameTextField;
    @FXML private Button greetButton;
    @FXML private TextArea outputTextArea;
    
    @FXML private HBox controlsHBox;
    @FXML private CheckBox uppercaseCheckBox;
    @FXML private RadioButton formalRadio, casualRadio;
    @FXML private ToggleGroup greetingStyle;
    @FXML private ComboBox<String> languageComboBox;
    @FXML private Button clearButton;
    
    @FXML private HBox statusHBox;
    @FXML private Label statusLabel;
    @FXML private ProgressBar progressBar;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the language combo box
        languageComboBox.getItems().addAll("English", "Spanish", "French", "German");
        languageComboBox.setValue("English");
        
        // Set initial status
        updateStatus("Application loaded successfully");
        
        // Set focus to the name text field
        nameTextField.requestFocus();
    }
    
    @FXML
    private void handleGreet() {
        String name = nameTextField.getText().trim();
        if (name.isEmpty()) {
            updateStatus("Please enter a name");
            return;
        }
        
        // Simulate some processing
        progressBar.setProgress(0.3);
        
        String greeting = generateGreeting(name);
        outputTextArea.appendText(greeting + "\n");
        
        progressBar.setProgress(1.0);
        updateStatus("Greeting generated for " + name);
        
        // Reset progress bar after a moment
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                progressBar.setProgress(0.0);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
    
    private String generateGreeting(String name) {
        String greeting;
        String language = languageComboBox.getValue();
        boolean isFormal = formalRadio.isSelected();
        
        // Generate greeting based on language and style
        switch (language) {
            case "Spanish":
                greeting = isFormal ? "Buenos días, " + name : "¡Hola, " + name + "!";
                break;
            case "French":
                greeting = isFormal ? "Bonjour, " + name : "Salut, " + name + "!";
                break;
            case "German":
                greeting = isFormal ? "Guten Tag, " + name : "Hallo, " + name + "!";
                break;
            default: // English
                greeting = isFormal ? "Good day, " + name : "Hello, " + name + "!";
                break;
        }
        
        if (uppercaseCheckBox.isSelected()) {
            greeting = greeting.toUpperCase();
        }
        
        return greeting;
    }
    
    @FXML
    private void handleClear() {
        nameTextField.clear();
        outputTextArea.clear();
        uppercaseCheckBox.setSelected(false);
        casualRadio.setSelected(true);
        languageComboBox.setValue("English");
        progressBar.setProgress(0.0);
        updateStatus("All fields cleared");
        nameTextField.requestFocus();
    }
    
    @FXML
    private void handleNew() {
        handleClear();
        updateStatus("New session started");
    }
    
    @FXML
    private void handleOpen() {
        updateStatus("Open functionality not implemented yet");
    }
    
    @FXML
    private void handleExit() {
        System.exit(0);
    }
    
    @FXML
    private void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("JavaFX FXML Example");
        alert.setContentText("This is an advanced example of JavaFX with FXML UI definitions.\n\n" +
                             "Features demonstrated:\n" +
                             "• FXML UI layout\n" +
                             "• Controller binding\n" +
                             "• Event handling\n" +
                             "• Multiple UI components\n" +
                             "• Internationalization support");
        alert.showAndWait();
    }
    
    private void updateStatus(String message) {
        statusLabel.setText(message);
    }
}