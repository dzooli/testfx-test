# JavaFX Application Testing with TestFX

This project now includes comprehensive testing capabilities using TestFX, a modern testing framework specifically designed for JavaFX applications.

## Overview

TestFX provides a powerful and intuitive way to test JavaFX applications by simulating user interactions like clicks, keyboard input, and UI validation. Unlike external automation tools, TestFX integrates directly with the JavaFX framework for reliable and fast testing.

## Test Framework Setup

### Dependencies

The project includes the following testing dependencies in `build.gradle`:

```gradle
dependencies {
    // TestFX for JavaFX testing
    testImplementation 'org.testfx:testfx-core:4.0.18'
    testImplementation 'org.testfx:testfx-junit5:4.0.18'
    
    // JUnit 5 (Jupiter)
    testImplementation 'org.junit.jupiter:junit-jupiter:5.10.0'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
    
    // TestFX requires a robot implementation
    testImplementation 'org.testfx:openjfx-monocle:jdk-12.0.1+2'
}
```

### Test Configuration

TestFX tests are configured to run in headless mode for CI/CD compatibility:

```gradle
test {
    useJUnitPlatform()
    
    // TestFX system properties for headless testing
    systemProperty 'testfx.robot', 'glass'
    systemProperty 'testfx.headless', 'true'
    systemProperty 'prism.order', 'sw'
    systemProperty 'prism.text', 't2k'
    systemProperty 'java.awt.headless', 'true'
}
```

## Test Suites

### 1. AdvancedApplicationTestFX

**Location**: `src/test/java/com/example/javafx/test/AdvancedApplicationTestFX.java`

**Purpose**: Comprehensive test suite for the JavaFX Advanced application covering all major functionality.

**Test Cases**:
- `testBasicGreetingWorkflow()` - Tests the core input → greet → output workflow
- `testLanguageSelection()` - Validates language switching (English, Spanish, French, German)
- `testGreetingStyleSelection()` - Tests Formal vs Casual greeting styles
- `testClearAllFunctionality()` - Verifies the Clear All button functionality
- `testMultipleLanguageCombinations()` - Tests switching between languages
- `testEmptyNameHandling()` - Validates form handling with empty input
- `testUIStateConsistency()` - Ensures UI state remains consistent across operations

### 2. SimpleAdvancedApplicationTest

**Location**: `src/test/java/com/example/javafx/test/SimpleAdvancedApplicationTest.java`

**Purpose**: Simplified test suite demonstrating basic TestFX functionality.

**Test Cases**:
- `testBasicGreeting()` - Basic input and greeting test
- `testClearButton()` - Clear functionality test

## Running Tests

### Command Line

Run all tests:
```bash
./gradlew test
```

Run tests with detailed output:
```bash
./gradlew test --info
```

Run specific test class:
```bash
./gradlew test --tests com.example.javafx.test.AdvancedApplicationTestFX
```

Run specific test method:
```bash
./gradlew test --tests com.example.javafx.test.AdvancedApplicationTestFX.testBasicGreetingWorkflow
```

### IDE Integration

TestFX tests integrate seamlessly with IDEs:
- **IntelliJ IDEA**: Right-click on test class or method → "Run Test"
- **Eclipse**: Right-click → "Run As" → "JUnit Test"
- **VS Code**: Use Java Test Runner extension

## Test Architecture

### TestFX Components Used

1. **FxRobot**: Simulates user interactions (clicks, typing, etc.)
2. **FxAssert**: Provides JavaFX-specific assertions
3. **ApplicationExtension**: JUnit 5 extension for TestFX lifecycle management
4. **@Start method**: Initializes the JavaFX application for testing

### Key Testing Patterns

#### UI Interaction
```java
// Click on elements by fx:id
robot.clickOn("#nameField")
     .write("Test User")
     .clickOn("#greetButton");
```

#### Assertions
```java
// Verify text content
verifyThat("#outputArea", hasText("Expected Text"));

// Custom verification
verifyThat("#outputArea", (TextArea textArea) -> {
    String output = textArea.getText();
    return output.contains("Expected Content");
});
```

#### Element Lookup
```java
// Query specific control types
ComboBox<?> combo = robot.lookup("#languageComboBox").queryAs(ComboBox.class);
RadioButton radio = robot.lookup("#formalRadio").queryAs(RadioButton.class);
```

## Test Coverage

The TestFX test suite covers:

✅ **Core Functionality**
- Name input and greeting generation
- Language selection (4 languages)
- Greeting style selection (Formal/Casual)
- Clear All functionality

✅ **UI Interactions**
- Text field input
- Button clicks
- ComboBox selection
- RadioButton selection

✅ **State Management**
- UI consistency across operations
- Form validation
- Error handling

✅ **Edge Cases**
- Empty input handling
- Multiple language switching
- State persistence

## Advantages of TestFX over Robot Framework + JavaAccessBridge

1. **Native Integration**: Direct JavaFX framework integration
2. **Reliability**: No external accessibility bridge dependencies
3. **Performance**: Faster execution without external process communication
4. **Debugging**: Better IDE integration and debugging capabilities
5. **Maintenance**: Easier to maintain and update
6. **CI/CD**: Headless mode support for automated pipelines

## Continuous Integration

TestFX tests are designed to run in CI/CD environments:

```yaml
# Example GitHub Actions workflow
- name: Run Tests
  run: ./gradlew test
  
- name: Publish Test Results
  uses: dorny/test-reporter@v1
  if: success() || failure()
  with:
    name: TestFX Results
    path: build/test-results/test/*.xml
    reporter: java-junit
```

## Best Practices

1. **Use fx:id attributes** in FXML for reliable element lookup
2. **Test user workflows** rather than individual components
3. **Use Page Object pattern** for complex applications
4. **Verify both positive and negative scenarios**
5. **Keep tests independent** - each test should be able to run in isolation
6. **Use meaningful test names** that describe the behavior being tested

This TestFX implementation provides a robust, maintainable, and reliable testing solution for the JavaFX application, replacing the complex Robot Framework + JavaAccessBridge setup with a native Java solution.