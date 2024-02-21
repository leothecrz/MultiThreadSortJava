package crz.ThreadSort;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Arrays;

class MergingThread implements Runnable
{

    private Integer[] dataArray;
    private Integer[] sortedArray; 
    
    private int[] startIndexes;
    private int[] endIndexes;

    private int partitionSize;

    private PriorityQueue<ValuePair> queue;

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

    // TODO:: Given n SORTED partitions and the start index of each partion, merge into a single sorted list.
    // Implement sorting k sorted list.
    @Override
    public void run()
    {        

        if(startIndexes.length == 1)
          return;

        for (int j = 0; j < startIndexes.length; j++) 
        {
          queue.add(new ValuePair(dataArray[startIndexes[j]], j));
          startIndexes[j] += 1;
        }
  
        int i = 0;
        while (!queue.isEmpty()) 
        {

          ValuePair active = queue.poll();
          sortedArray[i] = active.getValue();
          int fromIndex = active.getFromIndex();
  
          if(startIndexes[fromIndex] < endIndexes[fromIndex])
          {
            queue.add(new ValuePair(dataArray[startIndexes[fromIndex]], fromIndex));
            startIndexes[fromIndex] += 1;
          }

          i += 1;
        }


    }

}
