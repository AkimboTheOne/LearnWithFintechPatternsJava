# LearnWithFintechPatternsJava

## Description
This project implements design patterns in Java, focusing on fintech applications. It includes examples of creational, structural, and behavioral patterns.

## Project Structure
- **`app/`**: Contains the main source code and tests.
  - **`src/main/java/com/akimbotheone/pg/patterns/`**: Design pattern implementations.
  - **`src/test/java/com/akimbotheone/pg/`**: Unit tests.
- **`contex.docs/`**: Design pattern documentation.
- **`build.gradle`**: Gradle configuration.
- **`settings.gradle`**: Project settings.

## Implemented Patterns
### Creational Patterns
- **Abstract Factory**: Creation of families of related objects.
- **Singleton**: Ensures a single instance.
- **Prototype**: Efficient object cloning.
- **Object Pool**: Reuse of expensive objects.

### Structural Patterns
- **Composite**: Uniform representation of portfolios and accounts.
- **Decorator**: Dynamic addition of responsibilities.
- **Facade**: Simplification of complex subsystems.
- **Flyweight**: Memory usage minimization.

### Behavioral Patterns
- **Strategy**: Interchangeable algorithms.
- **Template Method**: Definition of algorithm templates.
- **Interpreter**: Rule evaluation.
- **Visitor**: Application of operations without modifying objects.

## Requirements
- **Java**: Version 21.
- **Gradle**: Version 8.0 or higher.

## Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/AkimboTheOne/LearnWithFintechPatternsJava.git
   ```
2. Navigate to the project directory:
   ```bash
   cd LearnWithFintechPatternsJava
   ```
3. Build the project:
   ```bash
   ./gradlew build
   ```
4. Run tests:
   ```bash
   ./gradlew test
   ```

## Execution
To run the project:
```bash
./gradlew run
```

## Contributions
Contributions are welcome. Please open an issue or pull request on GitHub.

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.
