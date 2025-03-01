
# Turtle Panel

This project demonstrates a basic GUI application that visualizes turtle movement using Java Swing.

## Prerequisites

- Java Development Kit (JDK) 8 or later
- Maven or Gradle for dependency management (optional if you're using them)

## Steps to Run

1. **Clone the repository:**

   ```bash
   git clone https://github.com/unicodeee/tutleGraphics.git
   cd Turtle
   ```

2. **Compile the Java files:**

   If you're using `javac` (the Java compiler), run the following command from the `src` directory:

   ```bash
   cd src
   javac -d ../bin main/java/org/run/TurtlePanel.java
   ```

   This will compile your Java files into the `bin` directory.

3. **Run the program:**

   After compilation, you can run the program using the `java` command:

   ```bash
   java -cp ../bin org.run.TurtleControllerPanel
   ```

4. **Optional - Build with Maven or Gradle:**

    - **Using Maven:**

      If you have a `pom.xml` file, you can build and run the project with Maven:

      ```bash
      mvn clean package
      java -cp target/<your-jar>.jar org.run.TurtleControllerPanel
      ```

    - **Using Gradle:**

      If you have a `build.gradle` file, you can build and run the project with Gradle:

      ```bash
      gradle build
      gradle run
      ```

## License

This project is licensed under the MIT License.