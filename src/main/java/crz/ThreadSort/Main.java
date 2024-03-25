
package crz.ThreadSort;

import java.util.Random;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class Main 
{

	private static void displayHelp() {
        System.out.println("Usage: java -jar MultiThreadSort.jar <Thread Count> <ArraySize> <RNG-MIN> <RNG-MAX>");
        System.out.println("Options:");
		System.out.println("\t <Thread Count> -> Number of threads to use on the array. The array will be partioned as evenly as possible among all threads.");
		System.out.println("\t <ArraySize> -> Size of the Random Data Array");
		System.out.println("\t <RNG-MIN> -> Minimum value for random values in data array.");
		System.out.println("\t <RNG-MAX> -> Minimum value for random values in data array.");

    }

    public static void main( String[] args )
    { 

        if(args.length < 4)
        {
			displayHelp();
            return;
        }
        int threadCount = 0;
        int arraySize = 0;
        int min = 0;
        int max = 0;
        try 
        {
            threadCount = Integer.parseInt(args[0]);
            arraySize = Integer.parseInt(args[1]);
            min = Integer.parseInt(args[2]);
            max = Integer.parseInt(args[3]);
        } 
        catch (NumberFormatException e) 
        {
            System.err.println("args were not integers.");
			System.err.println( e.getLocalizedMessage() );
			System.err.println();
			displayHelp();
            return;
        }
        
        Random rng = new Random(System.currentTimeMillis());
        Integer[] testData= new Integer[arraySize];
        for(int i=0; i< testData.length; i++)
            testData[i] = rng.nextInt(min, max);

        System.out.println("\nGlobal Array Unsorted: " + Arrays.toString(testData) + "\n");


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

        System.out.println("\nGlobal Array Indiv Sort " + Arrays.toString(testData) + "\n");

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
