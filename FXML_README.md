# JavaFX FXML Application

This JavaFX application demonstrates the use of XML UI definitions (FXML) with Java 21 LTS.

## Features

### Simple FXML Example (`main.fxml`)
- Basic FXML layout with VBox container
- Label and Button components
- Controller binding with event handling
- Accessibility support

### Advanced FXML Example (`advanced.fxml`)
- Complex layout using BorderPane
- Menu bar with File and Help menus
- Multiple UI components:
  - TextField with prompt text
  - TextArea for output
  - CheckBox for options
  - RadioButtons with ToggleGroup
  - ComboBox with language selection
  - ProgressBar for visual feedback
- Status bar at the bottom
- Internationalization support (English, Spanish, French, German)

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/example/javafx/
│   │       ├── App.java              # Original simple application
│   │       ├── MainController.java   # Controller for simple FXML
│   │       ├── AdvancedController.java # Controller for advanced FXML
│   │       └── FXMLLauncher.java     # Main launcher with example selection
│   └── resources/
│       ├── main.fxml                 # Simple FXML layout
│       ├── advanced.fxml             # Advanced FXML layout
│       └── styles.css                # CSS styling
```

## How to Run

1. **Build the project:**
   ```
   ./gradlew build
   ```

2. **Run the application:**
   ```
   ./gradlew run
   ```

3. **Choose your example:**
   - Select "Simple Example" for basic FXML demonstration
   - Select "Advanced Example" for comprehensive UI components

## FXML Advantages

### 1. **Separation of Concerns**
- UI layout defined in XML files
- Business logic in controller classes
- Clear separation between view and logic

### 2. **Designer-Developer Workflow**
- FXML files can be created with Scene Builder
- Designers can work on UI without touching Java code
- Developers focus on functionality

### 3. **Maintainability**
- Easy to modify layouts without recompiling
- Clear structure and hierarchy
- Reusable components

### 4. **Features Demonstrated**

#### Controller Binding
```java
@FXML
private Button myButton;

@FXML
private void handleButtonClick() {
    // Event handling logic
}
```

#### FXML Layout
```xml
<Button fx:id="myButton" 
        text="Click Me!" 
        onAction="#handleButtonClick" />
```

#### Accessibility Support
- AccessibleText attributes
- AccessibleRole definitions
- AccessibleHelp descriptions

## Technologies Used

- **Java 21 LTS** - Latest long-term support version
- **JavaFX 17.0.2** - UI framework
- **FXML** - XML-based UI definitions
- **CSS** - Styling and themes
- **Gradle** - Build automation

## Key Components

### Controllers
- `MainController` - Simple example with basic components
- `AdvancedController` - Complex example with multiple UI elements

### FXML Files
- Declarative UI definitions
- fx:controller binding
- fx:id for component references
- Event handler binding with onAction

### Features
- Menu systems
- Form controls
- Progress indicators
- Internationalization
- Accessibility support
- Custom styling

This implementation provides a solid foundation for building JavaFX applications with FXML, demonstrating both simple and advanced use cases while maintaining clean architecture and accessibility standards.