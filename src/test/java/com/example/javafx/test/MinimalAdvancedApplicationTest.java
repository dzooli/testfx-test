package com.example.javafx.test;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Minimal TestFX test for JavaFX Advanced Application.
 */
@ExtendWith(ApplicationExtension.class)
public class MinimalAdvancedApplicationTest {

    @Start
    public void start(Stage stage) throws Exception {
        // Load FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/advanced.fxml"));
        Parent root = loader.load();
        
        // Create scene
        Scene scene = new Scene(root, 600, 450);
        
        // Setup stage
        stage.setTitle("TestFX Test");
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }

    @Test
    public void testApplicationLoads(FxRobot robot) {
        // Just verify the application loads and we can find basic elements
        assertNotNull(robot.lookup("#nameTextField").query(), "Name field should exist");
        assertNotNull(robot.lookup("#greetButton").query(), "Greet button should exist");
        assertNotNull(robot.lookup("#outputTextArea").query(), "Output area should exist");
        assertNotNull(robot.lookup("#clearButton").query(), "Clear button should exist");
    }

    @Test
    public void testBasicInteraction(FxRobot robot) {
        // Simple interaction test
        robot.clickOn("#nameTextField");
        robot.write("Test");
        
        TextField nameField = robot.lookup("#nameTextField").queryAs(TextField.class);
        assertEquals("Test", nameField.getText(), "Name field should contain entered text");
    }
}