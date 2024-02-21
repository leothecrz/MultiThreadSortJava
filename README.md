# ThreadSort Package

The `crz.ThreadSort` package is a Java implementation of a parallelized sorting algorithm using threads. The package includes the `Main` class, which orchestrates the sorting process by creating and managing multiple threads for sorting and merging.

## Usage

To use the package, compile and run the `Main` class with the following command-line arguments:

```bash
java crz.ThreadSort.Main <arraySize> <min> <max>
```

- `arraySize`: The size of the array to be sorted.
- `min`: The minimum value for generating random integers in the array.
- `max`: The maximum value for generating random integers in the array.

Ensure that you provide valid integer arguments; otherwise, the program will terminate with an error message.

## Sorting Algorithm

The sorting process is divided into two main phases:

1. **Sorting Phase:**
   - The input array is filled with random integers within the specified range.
   - Multiple threads are spawned to sort different partitions of the array concurrently.

2. **Merging Phase:**
   - After the sorting threads complete, a merging thread is spawned to merge the sorted partitions.

## Multithreading

The `SortingThread` class is responsible for sorting individual partitions of the array. The number of threads used in the sorting phase is configurable and set to a default value of 5 in the provided example.

## Example

```java
public class Main {
    public static void main(String[] args) {
        // ... (as in the provided code)

        // Create an instance of the Main class
        Main main = new Main();

        // Run the sorting and merging process
        main.sortAndMerge(args);
    }
}
```

## Note

This is a basic example, and the number of threads and the sorting algorithm can be customized based on specific requirements. The provided code serves as a starting point for implementing parallelized sorting with threads in Java.