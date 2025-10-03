package com.example.javafx.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Simple working TestFX test for JavaFX Advanced Application.
 * 
 * This test demonstrates basic TestFX functionality without complex matchers.
 */
@ExtendWith(ApplicationExtension.class)
public class WorkingAdvancedApplicationTest {

    /**
     * Configures system properties for running JavaFX tests in headless mode.
     * Sets relevant properties if "testfx.headless" is enabled.
     */
    @BeforeAll
    public static void setupHeadlessMode() {

        // Ensure headless mode is properly configured
        if (Boolean.getBoolean("testfx.headless")) {
            System.setProperty("testfx.robot", "glass");
            System.setProperty("testfx.headless", "true");
            System.setProperty("prism.order", "sw");
            System.setProperty("prism.text", "t2k");
            System.setProperty("java.awt.headless", "true");
        }
    }

    @Start
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/advanced.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root, 600, 450);
        // Only add stylesheet if it exists
        try {
            scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        } catch (Exception e) {
            // Stylesheet not found, continue without it
            System.out.println("Warning: styles.css not found, continuing without stylesheet");
        }
        
        stage.setTitle("JavaFX Advanced FXML Example - Test");
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }

    @Test
    public void testBasicGreeting(FxRobot robot) {
        // Wait a moment for the UI to be fully loaded
        robot.sleep(500);
        
        // Enter a name in the input field
        robot.clickOn("#nameTextField");
        robot.write("TestFX User");
        
        // Click the greet button
        robot.clickOn("#greetButton");
        
        // Wait for the action to complete
        robot.sleep(500);
        
        // Get the output text area and verify content
        TextArea outputArea = robot.lookup("#outputTextArea").queryAs(TextArea.class);
        String outputText = outputArea.getText();
        
        // Verify the output contains our name and is not empty
        assertFalse(outputText.trim().isEmpty(), "Output should not be empty");
        assertTrue(outputText.contains("TestFX User"), 
                  "Output should contain the input name 'TestFX User'");
        
        System.out.println("Test output: " + outputText);
    }

    @Test
    public void testClearButton(FxRobot robot) {
        // Wait for UI to be ready
        robot.sleep(500);
        
        // Enter some data first
        robot.clickOn("#nameTextField");
        robot.write("Test Data");
        robot.clickOn("#greetButton");
        
        // Wait for action to complete
        robot.sleep(500);
        
        // Verify there is content
        TextArea outputArea = robot.lookup("#outputTextArea").queryAs(TextArea.class);
        assertFalse(outputArea.getText().trim().isEmpty(), "Should have content before clearing");
        
        // Click clear button
        robot.clickOn("#clearButton");
        
        // Wait for clear action
        robot.sleep(300);
        
        // Verify fields are cleared
        TextField nameField = robot.lookup("#nameTextField").queryAs(TextField.class);
        TextArea outputAreaAfterClear = robot.lookup("#outputTextArea").queryAs(TextArea.class);
        
        assertTrue(nameField.getText().isEmpty(), "Name field should be empty after clear");
        assertTrue(outputAreaAfterClear.getText().isEmpty(), "Output area should be empty after clear");
    }

    @Test
    public void testLanguageSelection(FxRobot robot) {
        // Click on language combo box and select Spanish
        robot.clickOn("#languageComboBox");
        robot.clickOn("Spanish");
        
        // Enter a name and greet
        robot.clickOn("#nameTextField");
        robot.write("Español User");
        robot.clickOn("#greetButton");
        
        // Verify Spanish greeting
        TextArea outputArea = robot.lookup("#outputTextArea").queryAs(TextArea.class);
        String outputText = outputArea.getText();
        
        assertTrue(outputText.contains("Español User"), 
                  "Output should contain the input name");
        assertTrue(outputText.contains("Hola"), 
                  "Output should contain Spanish greeting 'Hola'");
    }

    @Test
    public void testGreetingStyles(FxRobot robot) {
        // Clear any existing content first
        robot.clickOn("#clearButton");
        robot.sleep(500);
        
        // Test Formal style
        robot.clickOn("#formalRadio");
        robot.sleep(300); // Wait for radio button to be selected
        
        robot.clickOn("#nameTextField");
        robot.write("Formal User");
        robot.clickOn("#greetButton");
        robot.sleep(500); // Wait for greeting to be generated
        
        TextArea outputArea = robot.lookup("#outputTextArea").queryAs(TextArea.class);
        String formalOutput = outputArea.getText();
        System.out.println("Formal output: '" + formalOutput + "'");
        assertTrue(formalOutput.contains("Formal User"), 
                  "Formal output should contain the input name. Actual output: " + formalOutput);
        assertTrue(formalOutput.contains("Good day"), 
                  "Formal output should contain formal greeting. Actual output: " + formalOutput);
        
        // Clear and test Casual style
        robot.clickOn("#clearButton");
        robot.sleep(500);
        
        robot.clickOn("#casualRadio");
        robot.sleep(300); // Wait for radio button to be selected
        
        robot.clickOn("#nameTextField");
        robot.write("Casual User");
        robot.clickOn("#greetButton");
        robot.sleep(500); // Wait for greeting to be generated
        
        // Re-query the text area after clear and new input
        TextArea outputAreaAfterClear = robot.lookup("#outputTextArea").queryAs(TextArea.class);
        String casualOutput = outputAreaAfterClear.getText();
        System.out.println("Casual output: '" + casualOutput + "'");
        assertTrue(casualOutput.contains("Casual User"), 
                  "Casual output should contain the input name. Actual output: " + casualOutput);
        assertTrue(casualOutput.contains("Hello"), 
                  "Casual output should contain casual greeting. Actual output: " + casualOutput);
    }
}