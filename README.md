# ThreadSort

ThreadSort is a Java package that demonstrates sorting of a random data array using multiple threads. This package utilizes Java's multithreading capabilities to efficiently sort large arrays of random integers.

## Usage

To use this package, follow these steps:

1. Clone this repository to your local machine.
2. Navigate to the directory containing the package.
3. Compile the Java files using `maven`.
4. Run the jar file using `java -jar` command along with the appropriate arguments.

### Command Line Arguments

```
java -jar MultiThreadSort.jar <Thread Count> <ArraySize> <RNG-MIN> <RNG-MAX>
```

#### Options:

- `<Thread Count>`: Number of threads to use on the array. The array will be partitioned as evenly as possible among all threads.
- `<ArraySize>`: Size of the Random Data Array.
- `<RNG-MIN>`: Minimum value for random values in the data array.
- `<RNG-MAX>`: Maximum value for random values in the data array.

## Example

```bash
java -jar MultiThreadSort.jar 4 100 0 1000
```

This command will sort an array of size 100 with random integers ranging from 0 to 1000 using 4 threads.

## Compiling with NO TOOLS
To compile and build the project, follow these steps:

1. **Use JavaC:**
   Open the a command terminal at the location of the source files an run
   ```bash
   javac *.java
   ```

2. **Files:**
   After running the above place the files within two folders. Create a 'crz' folder. Create a folder with the package name within the previous folder.
   Place the .class files in the final folder.

3. **Running The Application:**
   Open a terminal or travel from the previous terminal to the location of the crz folder.
   run:
    ```bash
   java crz.ThreadSort.Main
   ```

## Compile and Build Instructions
To compile and build the project, follow these steps:

1. **Install Maven:**
   Make sure you have Maven installed on your system. You can download Maven from [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi) and follow the installation instructions.

2. **Navigate to Project Directory:**
   Open a terminal or command prompt and navigate to the directory where your `pom.xml` file is located.

3. **Compile and Build:**
   Run the following command to compile and build the project:

   ```bash
   mvn clean package
   ```

   This command will download the required dependencies, compile the source code, run tests, and package the application into a JAR file.

4. **Run the Application:**
   After a successful build, you can run the application using the following command:

   ```bash
   java -jar target/JAR_FILE_NAME.jar
   ```

   Make sure to replace `target/JAR_FILE_NAME.jar args...` with the actual path to your generated JAR file.
