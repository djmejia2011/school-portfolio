//David Mejia
//CMSC412
//Homework 4

package homeworks4;

import java.io.File;
import java.util.Scanner;

public class BankerAlgorithm {
    //variables needed for the algorithms
    private static int allocation[][];
    private static int max[][];
    private static int need[][];
    private static int available[][];
    private static int numOfProcesses;
    private static int numOfResources;
    private static Scanner scanner;
    private static String filePath;
    static BankerAlgorithm ba;
    private static int count = 0;


    public BankerAlgorithm(int numOfProcesses, int numOfResources){
        allocation = new int[numOfProcesses][numOfResources];
        max = new int[numOfProcesses][numOfResources];
        need = new int[numOfProcesses][numOfResources];
        available = new int[1][numOfResources];
    }
    //helps to open the file based on the name of the file
    public static void openFile(String file){
        try{
            scanner = new Scanner(new File(file));
        }catch(Exception e){
            System.out.println("There was an error opening the file");
        }
    }
    //this function read the first two numbers at the top of the file
    public static void readProcessAndResource(){
        numOfProcesses = scanner.nextInt();
        numOfResources = scanner.nextInt();
    }

    //fill the passed array based on the file given
    //only works for allocation, and max matrix files
    public static void readFile(int[][] array){
       int i = 0;
       scanner.next();
       while(scanner.hasNext() && i < numOfProcesses){
          for(int j = 0; j<numOfResources; j++){
                array[i][j]=scanner.nextInt();
          }
          i++;
       }
    }
    // this function is needed to fill available matrix
    public static void readAvailableFile(int[][] availArray){
        scanner.next();
        for(int i = 0; i<numOfResources;i++){
            availArray[0][i] = scanner.nextInt();
        }
        scanner.close();
    }
    // function calculates the need matrix
    public static int[][] calculateNeed(){
        for(int i=0; i<numOfProcesses; i++){
            for(int j=0; j<numOfResources; j++){
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
        return need;
    }


    public static boolean allocationCheck(int process){
        for(int i = 0; i<numOfResources; i++){
            if(available[0][i] < need[process][i]){
                return false;
            }
        }
        return true;
    }

    //displays prompt to user
    private static void askForInformation() {
        Scanner response = new Scanner(System.in);
        filePath = "src/homeworks4/";
        System.out.println("Please enter name of data file: ");
        filePath += response.next();

    }

    public static void isSafeState(){
        boolean isDone[] = new boolean[numOfProcesses];
        int i = 0;
        while(i<numOfProcesses){
            boolean isProcessAllocated = false;
            for(int j = 0; j < numOfProcesses; j++)
                if(!isDone[j] && allocationCheck(j)){
                    for(int k = 0; k < numOfResources; k++) {
                        available[0][k] = available[0][k] - need[j][k] + max[j][k];
                    }
                    System.out.print("P"+j+" ");
                    isProcessAllocated = true;
                    isDone[j] = true;
                    i++;
                    }
                //no allocation found
                if(!isProcessAllocated){
                    System.out.println("No safe state was found");
                    break;
                }

        }

    }
    // my attempt at backtracking
    public static String safeStateRecursive(int i){
        String processString = "";
        if( i == numOfProcesses*numOfResources){
            return processString;
        }
        else{
            boolean isDone[] = new boolean[numOfProcesses];
            boolean isProcessAllocated = false;
            for(int j = 0; j < numOfProcesses; j++)
                if(!isDone[j] && allocationCheck(j)){
                    for(int k = 0; k < numOfResources; k++) {
                        available[0][k] = available[0][k] - need[j][k] + max[j][k];
                    }
                    processString +="P"+j+" ";
                    System.out.print("P"+j+" ");
                    isProcessAllocated = true;
                    isDone[j] = true;
                }
            System.out.println();
            //no allocation found
            if(!isProcessAllocated){
                return "No safe state was found";

            }
            return safeStateRecursive(i+1);
        }
    }

    public static void main(String[] args){
        askForInformation();            //display prompt to user
        openFile(filePath);             //opens the file using input from user
        readProcessAndResource();       //adds the processes and resources from the file
        ba = new BankerAlgorithm(numOfProcesses, numOfResources);
        readFile(allocation);           //fills 2D arrays with allocation numbers
        readFile(max);                  //fills 2D arrays with max numbers
        readAvailableFile(available);   //fills array with available numbers
        calculateNeed();
        isSafeState();
//        safeStateRecursive(0); my attempt at backtracking failed
    }

}
