
package crz.ThreadSort;

import java.lang.Thread;
import java.util.Stack;

/**
 * 
 */
class SortingThread implements Runnable
{

    private Integer[] toSortArray;
    private int startIndex;
    private int endIndex;

    /**
     * 
     * @param ref
     * @param start
     * @param end
     */
    public SortingThread(Integer[] ref, int start, int end)
    {
        toSortArray = ref;
        startIndex = start;
        endIndex = end;
    }

    /**
     * 
     */
    @Override
    public void run()
    {

        for(int i=startIndex; i<endIndex; i++)
            System.out.println("ThreadID: " + String.valueOf( Thread.currentThread().getName() ) + " - " + toSortArray[i]);        

        quickSort(toSortArray, startIndex, endIndex-1);
        System.out.println("ThreadID: " + String.valueOf( Thread.currentThread().getName() ) + " SORTED " );    

        for(int i=startIndex; i<endIndex; i++)
            System.out.println("ThreadID: " + String.valueOf( Thread.currentThread().getName() ) + " - " + (i - startIndex) + " : " + toSortArray[i]);
        
    }

    /**
     * 
     * @param ref
     * @param low
     * @param high
     * @return
     */
    private int selectPivot(Integer[] ref, int low, int high)
    {
        int midPoint = (low + high) / 2;
        
        if(ref[low] > ref[midPoint])
            swap(ref, low, midPoint);
        
        if(ref[low] > ref[high])
            swap(ref, low, high);

        if(ref[midPoint] > ref[high])
            swap(ref, midPoint, high);

        swap(ref, midPoint, high);
        return ref[high];
    }

    /**
     * 
     * @param ref
     * @param low
     * @param high
     * @return
     */
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

    /**
     * 
     * @param ref
     * @param low
     * @param high
     */
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

    public void swap(Integer[] intArray, int index1, int index2) 
    {
        int temp = intArray[index1];
        intArray[index1] = intArray[index2];
        intArray[index2] = temp;
    }

}
