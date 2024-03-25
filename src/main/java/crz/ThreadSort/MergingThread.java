package crz.ThreadSort;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Arrays;
/**
 * Class representing a thread responsible for merging sorted partitions into a single sorted list
*/
class MergingThread implements Runnable
{

    private Integer[] dataArray; // Reference to the original data array
    private Integer[] sortedArray; // Reference to the sorted array
    private int[] startIndexes; // Array storing the start indexes of partitions
    private int[] endIndexes; // Array storing the end indexes of partitions
    private int partitionSize; // Size of each partition
    private PriorityQueue<ValuePair> queue; // Priority queue to facilitate merging

    // Constructor to initialize the merging thread
    public MergingThread(Integer[] ref, Integer[] sortRef, int[] starts)
    {
        dataArray = ref;
        sortedArray = sortRef;
        startIndexes = starts;

        endIndexes = new int[startIndexes.length]; 
        if(starts.length > 1)
            partitionSize = starts[1] - starts[0];
        else
            partitionSize = ref.length;

        for (int i = 0;  i < startIndexes.length-1; i++) 
          endIndexes[i] = startIndexes[i] + partitionSize;
        endIndexes[startIndexes.length-1] = dataArray.length;


        System.out.println("Start Indexes " + Arrays.toString(startIndexes));
        System.out.println("End Indexes " + Arrays.toString(endIndexes));

        queue = new PriorityQueue<>(Comparator.naturalOrder());
    }

    // Method to merge sorted partitions into a single sorted list    
	@Override
    public void run()
    {        
        // If only one partition, no need to merge
		if(startIndexes.length == 1)
			return;

        // Add the first element from each partition to the priority queue
        for (int j = 0; j < startIndexes.length; j++) 
        {
			queue.add(new ValuePair(dataArray[startIndexes[j]], j));
			startIndexes[j] += 1;
        }
  
        int i = 0;
		// Merge elements from priority queue into the sorted array
        while (!queue.isEmpty()) 
        {

			// Retrieve the smallest element from the priority queue
			ValuePair active = queue.poll();
			sortedArray[i] = active.getValue();
			int fromIndex = active.getFromIndex();
  
        	// If there are more elements in the partition, add the next element to the queue
			if(startIndexes[fromIndex] < endIndexes[fromIndex])
			{
				queue.add(new ValuePair(dataArray[startIndexes[fromIndex]], fromIndex));
				startIndexes[fromIndex] += 1;
			}

          i += 1;
        }


    }

}
