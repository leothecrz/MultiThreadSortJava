
package crz.ThreadSort;

import java.util.Stack;
import java.lang.Thread;

/**
 * Class representing a thread responsible for sorting a partition using QuickSort algorithm
 */
class SortingThread implements Runnable
{

    private Integer[] toSortArray; // Reference to the array to be sorted
    private int startIndex; // Start index of the partition to be sorted
    private int endIndex; // End index of the partition to be sorted

    // Constructor to initialize the sorting thread with the partition to be sorted
    public SortingThread(Integer[] ref, int start, int end)
    {
        toSortArray = ref;
        startIndex = start;
        endIndex = end;
    }

    // Method to execute the sorting thread
    @Override
    public void run()
    {
        quickSort(toSortArray, startIndex, endIndex-1);
        System.out.println("ThreadID: " + String.valueOf( Thread.currentThread().getName() ) + " SORTED " );    
    }

    // Method to select a pivot element for QuickSort
    private int selectPivot(Integer[] ref, int low, int high)
    {
        int midPoint = (low + high) / 2;
        
		// Ensuring the median-of-three pivot selection
        if(ref[low] > ref[midPoint])
            swap(ref, low, midPoint);
        
        if(ref[low] > ref[high])
            swap(ref, low, high);

        if(ref[midPoint] > ref[high])
            swap(ref, midPoint, high);

        swap(ref, midPoint, high);
        return ref[high];
    }

    // Method to partition the array for QuickSort
    private int partition(Integer[] ref, int low, int high)
    {

        int pivot = selectPivot(ref, low, high);
        int i = low - 1;

        for (int j = low; j <= high; j++) 
        {
            if(ref[j] < pivot)
            {
                i += 1;
                swap(ref, i, j);
            }      
        }
        
        swap(ref, i+1, high);
        return i+1;
    }

    // Method to perform QuickSort iteratively
    private void quickSort(Integer[] ref, int low, int high)
    {

        Stack<Integer> stack = new Stack<>();
        stack.push(low);
        stack.push(high);

        while (!stack.empty())
        {
            int activeHigh = stack.pop();
            int activeLow = stack.pop();

            int p = partition(ref, activeLow, activeHigh);

            if(p - 1 > activeLow)
            {
                stack.push(activeLow);
                stack.push(p-1);
            }

            if(p + 1 < activeHigh)
            {
                stack.push(p+1);
                stack.push(activeHigh);
            }

        }
    }
	
    // Method to swap elements in the array
    public void swap(Integer[] intArray, int index1, int index2) 
    {
        int temp = intArray[index1];
        intArray[index1] = intArray[index2];
        intArray[index2] = temp;
    }

}
