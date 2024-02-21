package crz.ThreadSort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class MergingThread implements Runnable
{

    private Integer[] toSortArray;
    private int[] startIndexes;
    private int[] indexPointers;
    private int partitionSize;

    private PriorityQueue<Integer> queue;

    public MergingThread(Integer[] ref, Integer[] sortRef, int[] starts)
    {
        toSortArray = ref;
        startIndexes = starts;

        indexPointers = Arrays.copyOf(starts, starts.length);

        if(starts.length > 1)
            partitionSize = starts[1] - starts[0];
        else
            partitionSize = ref.length;

        queue = new PriorityQueue<>(Comparator.naturalOrder());
    }

    // TODO:: Given n SORTED partitions and the start index of each partion, merge into a single sorted list.
    @Override
    public void run()
    {        

        int i = 0;

        for (int j = 0; j < startIndexes.length; j++) 
        {
            queue.add(toSortArray[startIndexes[i]]);
            
        }

        while (!queue.isEmpty()) 
        {
            
        }


    }

}
