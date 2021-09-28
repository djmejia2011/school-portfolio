/**
 * David Mejia
 * CMSC412 Final Project
 * Simulator for Demand Paging
 * */

package final_project;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Simulator {

    //displays menu to the user
    public static void displayMenu(){
        System.out.println("\n0 - Exit");
        System.out.println("1 - Read reference string");
        System.out.println("2 - Generate reference string");
        System.out.println("3 - Display current reference string");
        System.out.println("4 - Simulate FIFO");
        System.out.println("5 - Simulate OPT");
        System.out.println("6 - Simulate LRU");
        System.out.println("7 - Simulate LFU");
        System.out.print("Select Option: ");
    }

    public static void main(String[] args) throws FileNotFoundException, ReferenceStringException {

        int selection = 8;
        ArrayList<Integer> referenceStrings = new ArrayList<Integer>();


        while (selection != 0) {
            Scanner sc = new Scanner(System.in);
            displayMenu();
            try {
                selection = sc.nextInt();

            } catch (Exception e) {
                System.out.println("Please select from the Menu;");
                continue;
            }
            switch (selection) {
                case 0:
                    System.exit(0);
                case 1:
                    referenceStrings.clear();
                    boolean successfull = true;
                    Scanner casOne = new Scanner(System.in);
                    System.out.println("Please enter reference string;");
                    String referenceString = casOne.nextLine();
                    StringTokenizer st = new StringTokenizer(referenceString);
                    while(st.hasMoreTokens()){
                        int nextInt = Integer.parseInt(st.nextToken());
                        try {
                            if(nextInt < 0 || nextInt >9){
                                throw new ReferenceStringException();
                            }
                        } catch (ReferenceStringException e) {
                            successfull = false;
                            System.out.println("Virtual memory is of 10 frames only, please enter between 0 - 9 in reference string");
                        }
                        if(nextInt >= 0 && nextInt < 10){
                            referenceStrings.add(nextInt);
                        }
                    }
                    if (successfull)
                        System.out.println("Reference string successfully added!");
                    break;
                case 2:
                    referenceStrings.clear();
                    boolean isSuccess = true;
                    Scanner casTwo = new Scanner(System.in);

                    System.out.println("Please enter length of reference string required;");
                    try {
                        int lengthRequired = casTwo.nextInt();
                        Random r = new Random();
                        for(int i = 0; i<lengthRequired; i++){
//                            System.out.println(r.nextInt(10) + " ");
                            referenceStrings.add(r.nextInt(10));
                        }

                    } catch (Exception e) {
                        isSuccess = false;
                        System.out.println("Integers Only, Please try again");
                    }

                    if(isSuccess){
                        System.out.println("Reference string created successfully!");
                        break;
                    }
                case 3:
                    if(referenceStrings.isEmpty()){
                        System.out.println("Please enter or generate reference string first!");
                    }else{
                        System.out.println("Current Reference String");
                        System.out.println(referenceStrings.toString());
                    }
                    break;
                case 4:
                    if(referenceStrings.isEmpty()){
                        System.out.println("Please enter or generate reference string first!");
                    }else{
                        Scanner casFour = new Scanner(System.in);
                        System.out.println("Please enter Physical Frame number;");
                        int physicalFrame = casFour.nextInt();
                        PageTable pt = new PageTable(referenceStrings, physicalFrame);
                        pt.simulateFIFO();
                    }
                    break;
                case 5:
                    if(referenceStrings.isEmpty()){
                        System.out.println("Please enter or generate reference string first!");
                    }else{
                        Scanner casFive = new Scanner(System.in);
                        System.out.println("Please enter Physical Frame number;");
                        int physicalFrame = casFive.nextInt();
                        PageTable pt = new PageTable(referenceStrings, physicalFrame);
                        pt.simulateOPT();
                    }
                    break;
                case 6:
                    if(referenceStrings.isEmpty()){
                        System.out.println("Please enter or generate reference string first!");
                    }else{
                        Scanner casSix = new Scanner(System.in);
                        System.out.println("Please enter Physical Frame number;");
                        int physicalFrame = casSix.nextInt();
                        PageTable pt = new PageTable(referenceStrings, physicalFrame);
                        pt.simulateLRU();
                    }
                    break;
                case 7:
                    if(referenceStrings.isEmpty()){
                        System.out.println("Please enter or generate reference string first!");
                    }else{
                        Scanner casSeven = new Scanner(System.in);
                        System.out.println("Please enter Physical Frame number;");
                        int physicalFrame = casSeven.nextInt();
                        PageTable pt = new PageTable(referenceStrings, physicalFrame);
                        pt.simulateLFU();
                    }
                    break;

            }
        }

    }
}
