//David Mejia
//CMSC451
//Project 1

import java.io.File;
import java.util.Scanner;

public class FileReader{
    private Scanner scanner;
    static String[][] finalTableData = new String[10][5];
    static int[] size = new int[10];
    static double[][] count = new double[10][50];
    static long[][] time = new long[10][50];
    static long averageTime = 0;

    public void openFile(String file){
        try{
            scanner = new Scanner(new File(file));
        }catch(Exception e){
            System.out.println("There was an error opening the file");
        }
    }
    // this function will read and separate the program created by the benchmark
    public void readFile(){
        scanner.useDelimiter(" ");
        int i=0;
        while(scanner.hasNext() && i < 10){
            size[i] = Integer.parseInt(scanner.next());
            for(int j=0; j<50;j++){
                count[i][j]=Double.parseDouble(scanner.next());
                time[i][j]=Long.parseLong(scanner.next());
            }
            scanner.nextLine();
            i++;
        }

    }
    //needed to close file after finish reading it.
    public void closeFile(){
        scanner.close();
    }
    //will be used to find average of the arrays
    public static double findAverage(double[] array, int n){
        double averageCount = 0;
        for(int i = 0; i<n; i++){
            averageCount += array[i];
        }
        return averageCount/n;
    }
    //will be used to find average of the arrays
    public static long findAverage(long[] array, int n){
        long averageCount = 0;
        for(int i = 0; i<n; i++){
            averageCount += array[i];
        }
        return averageCount/n;
    }



    public String[][] populateTable(){
        for(int i=0;i<10;i++){
                //create 2D array to be used for the final table
                finalTableData[i][0] = String.valueOf(size[i]);
                finalTableData[i][1] = String.valueOf(findAverage(count[i],50));
                finalTableData[i][2] = (MySort.coefVariance(count[i],50))+"%";
                finalTableData[i][3] = String.valueOf(findAverage(time[i],50));
                finalTableData[i][4] = (MySort.coefVariance(time[i],50)+"%");

        }
        return finalTableData;
    }
}
