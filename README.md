# JavaFX Accessibility Test Application

A minimal JavaFX application with accessibility support for testing with Robot Framework and Java Access Bridge.

## Features

- Simple JavaFX application with one button and one label
- Button changes the text displayed in the label when clicked
- Full accessibility support using `javax.accessibility` 
- Gradle build system
- Ready for Robot Framework testing with Java Access Bridge

## Structure

```
├── build.gradle                    # Gradle build configuration
├── settings.gradle                 # Gradle settings
├── gradlew / gradlew.bat           # Gradle wrapper scripts
├── gradle/wrapper/                 # Gradle wrapper files
├── src/main/java/com/example/javafx/
│   └── App.java                    # Main JavaFX application
└── src/main/resources/
    └── styles.css                  # CSS styles for the application
```

## Building and Running

### Prerequisites
- Java 11 or higher
- JavaFX runtime (included via Gradle dependencies)

### Build the application
```bash
./gradlew build
```

### Run the application
```bash
./gradlew run
```

## Accessibility Features

The application includes:
- Accessible text descriptions for all UI elements
- Proper accessible roles (BUTTON, TEXT, PARENT)
- Accessible help text for screen readers
- Java Access Bridge compatibility
- Dynamic accessibility updates when text changes

## Robot Framework Testing

This application is designed to be tested with Robot Framework using the Java Access Bridge. The accessibility properties ensure that:

- UI elements can be identified by their accessible names
- Button clicks can be automated
- Text changes can be verified
- The application state is accessible to testing tools

## Key Accessibility Properties

- **Button**: Accessible name "Change text button", role BUTTON
- **Label**: Accessible name updates dynamically with content, role TEXT
- **Window**: Accessible name "JavaFX test application main window"