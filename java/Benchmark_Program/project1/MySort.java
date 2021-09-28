//David Mejia
//CMSC451
//Project 1

import java.util.Random;

public class MySort implements SortInterface{
    private int countRecursive;
    private int countIterative;
    private long timeRecursive;
    private long timeIterative;


    @Override
    public int[] recursiveSort(int[] list) {
        RecursiveHeapSort.setCountToZero();
        timeRecursive = 0;
        long startTime = System.nanoTime(); // begins to count time in nanoseconds
        RecursiveHeapSort.sort(list);
        timeRecursive = System.nanoTime() - startTime;
        try {
            checksSort(list);
        } catch (UnsortedException e) {
            e.printStackTrace();
        }
        countRecursive = RecursiveHeapSort.getCount();
        RecursiveHeapSort.setCountToZero();
        return list;
    }

    @Override
    public int[] iterativeSort(int[] list) {
        IterativeHeapSort.setCountToZero();
        timeIterative = 0;
        long startTime = System.nanoTime();  // begins to count time in nanoseconds
        IterativeHeapSort.heapSort(list, list.length);
        timeIterative = System.nanoTime() - startTime;
        IterativeHeapSort.getCount();
        try {
            checksSort(list);
        } catch (UnsortedException e) {
            e.printStackTrace();
        }
        countIterative = IterativeHeapSort.getCount();
        IterativeHeapSort.setCountToZero();
        return list;
    }

    @Override
    public int getCount() {
        return countRecursive;
    }

    public int getCountIterative() {
        return countIterative;
    }

    @Override
    public long getTime() {
        return timeRecursive;
    }

    public long getTimeIterative() {
        return timeIterative;
    }

    //this function will generate the sets based on the number of n required
    //every integer will random
    public int[] generateSets(int n){
        int rangeOfRandomNums = 1000;
        int[] finalArray = new int[n];
        Random random = new Random();
        for(int i =0; i<n; i++){
            finalArray[i] = random.nextInt(rangeOfRandomNums);
        }
        return finalArray;
    }
    //this function will check that the arrays are sorted if not
    //an unsortedexception will be thrown
    public static void checksSort(int[] arrayToTest) throws UnsortedException{
        for(int i=0; i<arrayToTest.length; i++){
            if(arrayToTest.length!=1){
                if(i>0){
                    if(arrayToTest[i-1] > arrayToTest[i]){
                        throw new UnsortedException();
                    }
                }
            }
        }
    }


    /*********************************************************************************************/
    /****************************Functions for Coefficient of Variance****************************/
    /*********************************************************************************************/
    public static double findMean(double[] array, int size){
        double total = 0;

        for(int i = 0; i < size; i++){
            total += array[i];
        }
        double mean = total/size;
        return mean;
    }

    public static double findMean(long[] array, int size){
        double total = 0;

        for(int i = 0; i < size; i++){
            total += array[i];
        }
        double mean = total/size;
        return mean;
    }

    public static double findSD(double[] array, int size){
        double total = 0;
        for(int i = 0; i < size; i++){
            total += (array[i] - findMean(array, size)) * (array[i] - findMean(array, size));
        }
        double SD = Math.sqrt(total/(size-1));
        return SD;
    }
    public static double findSD(long[] array, int size){
        double total = 0;
        for(int i = 0; i < size; i++){
            total += (array[i] - findMean(array, size)) * (array[i] - findMean(array, size));
        }
        double SD = Math.sqrt(total/(size-1));
        return SD;
    }

    public static String coefVariance(double[] array, int size){
        double coef = ((findSD(array, size) / findMean(array, size)))*100;
        String results = String.format("%.2g%n", coef);
        return results;
    }

    public static String coefVariance(long[] array, int size){
        double coef = (findSD(array, size) / findMean(array, size))*100;
        String results = String.format("%.2g%n", coef);
        return results;
    }
    /*********************************************************************************************/
    /****************************Functions for Coefficient of Variance****************************/
    /*********************************************************************************************/
}
