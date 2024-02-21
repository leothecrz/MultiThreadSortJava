package crz.ThreadSort;


class MergingThread implements Runnable
{

  private Integer[] toSortArray;
  private int[] startIndexes;

  public MergingThread(Integer[] ref, int[] starts)
  {
    toSortArray = ref;
    startIndexes = starts;
  }

  // TODO:: Given n SORTED partitions and the start index of each partion, merge into a single sorted list.
  @Override
  public void run()
  {
    
  }



}
