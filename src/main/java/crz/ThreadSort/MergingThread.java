package crz.ThreadSort;

import java.util.Comparator;
import java.util.PriorityQueue;

class MergingThread implements Runnable
{

    private Integer[] toSortArray;
    private int[] startIndexes;

    private PriorityQueue queue;

    public MergingThread(Integer[] ref, Integer[] sortRef, int[] starts)
    {
        toSortArray = ref;
        startIndexes = starts;

        queue = new PriorityQueue<>(Comparator.naturalOrder());
        

    }

    // TODO:: Given n SORTED partitions and the start index of each partion, merge into a single sorted list.
    @Override
    public void run()
    {

    }

}
