
package crz.ThreadSort;

import java.util.Random;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class Main 
{

    public static void main( String[] args )
    { 

        if(args.length < 3)
        {
            System.err.println("invalid arg count");
            return;
        }
        int arraySize = 0;
        int min = 0;
        int max = 0;
        try 
        {
            arraySize = Integer.parseInt(args[0]);
            min = Integer.parseInt(args[1]);
            max = Integer.parseInt(args[2]);
        } 
        catch (NumberFormatException e) 
        {
            System.err.println("args were not integers.");
            return;
        }
        
        Random rng = new Random(System.currentTimeMillis());
        Integer[] testData= new Integer[arraySize];
        for(int i=0; i< testData.length; i++)
            testData[i] = rng.nextInt(min, max);

        int threadCount = 4;
        int remainingIntegers = arraySize;
        int partitionSize = arraySize / threadCount;
        int[] startIndexes = new int[threadCount];

        Thread[] threadArray = new Thread[threadCount];
        for (int i = 0; i < threadCount-1; i++)
        {
            SortingThread st = new SortingThread(testData, partitionSize*i, (i+1)*partitionSize);
            startIndexes[i] = partitionSize*i;
            threadArray[i] = new Thread(st);
            threadArray[i].start();
            remainingIntegers -= partitionSize;
        }

        System.out.println();

        SortingThread st = new SortingThread(testData, partitionSize*(threadCount-1), partitionSize*(threadCount-1)+remainingIntegers );
        startIndexes[threadCount - 1] = partitionSize * (threadCount-1);
        threadArray[threadCount-1] = new Thread(st);
        threadArray[threadCount-1].start();
        
        try 
        {
            for (int i = 0; i < threadArray.length; i++)
                threadArray[i].join();
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
            System.err.println("FAILED TO JOIN THREADS");
            return;
        }

        System.out.println();
        System.out.println("Global Array Indiv Sort " + Arrays.toString(testData));
        System.out.println();

        Integer[] sortedArray = new Integer[arraySize];
        Thread mergingThread = new Thread( new MergingThread(testData, sortedArray, startIndexes));
        mergingThread.start();
        try 
        {
            mergingThread.join();
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
            System.err.println("FAILED TO JOIN THREADS");
            return;
        }

        System.out.println(Arrays.toString(sortedArray));

    }

}
