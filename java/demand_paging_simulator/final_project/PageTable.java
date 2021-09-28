/**
 * David Mejia
 * CMSC412 Final Project
 * Simulator for Demand Paging
 * */

package final_project;

import java.util.*;

public class PageTable {
    private int faultCount = 0;
    private String[][] demandPageTable;
    private int referenceListSize;
    private int physicalFrames;
    ArrayList<Integer> referenceLists = new ArrayList<Integer>();
    ArrayList<VirtualFrame> victimFrames;
    ArrayList<VirtualFrame> listVirtualFrames;


    public PageTable(ArrayList<Integer> referenceList, int numPhysicalFrames){
        listVirtualFrames = new ArrayList<VirtualFrame>();
        victimFrames = new ArrayList<VirtualFrame>();
        demandPageTable = new String[numPhysicalFrames][1];
        referenceListSize = referenceList.size();
        physicalFrames = numPhysicalFrames;
        referenceLists = referenceList;
        clearTable();

    }

    /***
     * function that simulates FIFO using a modulus operator to
     * decide which row to insert new virtual frame.
     */
    public void simulateFIFO(){
        Scanner fifosc = new Scanner(System.in);
        int column = 0;
            for(int row = 0; row<referenceListSize; row++){
                int temp = referenceLists.get(row);
                int actualRow = column%physicalFrames;

                if(isFrameEqual(demandPageTable, String.valueOf(temp))){
                    //do nothing, actualRow is already present in physical memory
                }
                else if(isFramePresent(demandPageTable, String.valueOf(temp))) {
                    //starting frames will always be empty so we just add the number to the table
                    if(column < physicalFrames) {
                        demandPageTable[column][0] = String.valueOf(temp);
                        column++;
                    }else {
                        //if everything goes right, victim frames are added so they can be display later
                        victimFrames.add(new VirtualFrame(Integer.parseInt(demandPageTable[actualRow][0])));
                        //populate table
                        demandPageTable[actualRow][0] = String.valueOf(temp);
                        column++;
                    }
                }
                displaySimulationStep(temp);
                System.out.println("Press any key then enter to continue");
                fifosc.next();
            }
    }


    /***
     * Function to simulate Optimum Algorithm
     */
    public void simulateOPT(){
        Scanner optSc = new Scanner(System.in);
        int column = 0;
        for(int row = 0; row<referenceListSize; row++){
            int temp = referenceLists.get(row);

            if(isFrameEqual(demandPageTable, String.valueOf(temp))){
                //do nothing, frame is already present in physical memory
            }

            else if(isFramePresent(demandPageTable, String.valueOf(temp))) {
               //starting frames will always be empty so we just add the number to the table
                if(column < physicalFrames){
                    demandPageTable[column][0] = String.valueOf(temp);
                    column++;
                }else{
                    //to find the correct index in the table we use get optimal function
                    //we also add the victim frame before swapping values
                    int optimumIndex = getOptimal(referenceLists,column,demandPageTable);
                    addVirtualFrames(victimFrames,demandPageTable[optimumIndex][0]);
                    demandPageTable[optimumIndex][0] = String.valueOf(temp);
                    column++;
                }

            }
            displaySimulationStep(temp);
            System.out.println("Press any key then enter to continue");
            optSc.next();
        }
    }

    /***
     * Function to Simulate LRU
     */
    public void simulateLRU(){
        Scanner lruSc = new Scanner(System.in);
        int column = 0;
        for(int row = 0; row<referenceListSize; row++){
            int temp = referenceLists.get(row);

            if(isFrameEqual(demandPageTable, String.valueOf(temp))){
                //do nothing, frame is already present in physical memory
                column++;
            }
            else if(isFramePresent(demandPageTable, String.valueOf(temp))) {
                if(column < physicalFrames){
                    //starting frames will always be empty so we just add the number to the table
                    demandPageTable[column][0] = String.valueOf(temp);
                    column++;
                }else{
                    // if not empty then the program finds the LRU index
                    int lruIndex = getLRU(referenceLists,column,demandPageTable);
                    addVirtualFrames(victimFrames,demandPageTable[lruIndex][0]);
                    demandPageTable[lruIndex][0] = String.valueOf(temp);
                    column++;
                }

            }
            displaySimulationStep(temp);
            System.out.println("Press any key then enter to continue");
            lruSc.next();
        }

    }

    /***
     * Function to simulate LFU
     */
    public void simulateLFU(){
        Scanner lfuSc = new Scanner(System.in);
        int column = 0;
        for(int row = 0; row<referenceListSize; row++){
            int temp = referenceLists.get(row);

            if(isFrameEqual(demandPageTable, String.valueOf(temp))){
                //do nothing, frame is already present in physical memory
                createFramesList(listVirtualFrames, String.valueOf(temp));
                column++;
            }
            else if(isFramePresent(demandPageTable, String.valueOf(temp))) {
                if(column < physicalFrames){
                    //starting frames will always be empty so we just add the number to the table
                    //and add to virtual frames list to find the LFU later
                    demandPageTable[column][0] = String.valueOf(temp);
                    createFramesList(listVirtualFrames, String.valueOf(temp));
                    column++;
                }else{
                    //find the best location to swap
                    int lfuIndex = getLFU(demandPageTable);
                    addVirtualFrames(victimFrames,demandPageTable[lfuIndex][0]);
                    createFramesList(listVirtualFrames, String.valueOf(temp));
                    demandPageTable[lfuIndex][0] = String.valueOf(temp);
                    column++;
                }

            }
            displaySimulationStep(temp);
            System.out.println("Press any key then enter to continue");
            lfuSc.next();
        }
    }

    /***
     * Function that helps find the LFU index to SWAP
     * @param dataTable
     * @return
     */
    public int getLFU(String[][] dataTable){
        Collections.sort(listVirtualFrames);
//        Collections.reverse(listVirtualFrames);
        for(int k = 0; k< dataTable.length; k++){
            //match lowest index and find the index on the table which virtual frame to kill
            int stTemp = listVirtualFrames.get(0).getVirtualID();
            if((dataTable[k][0].equals(String.valueOf(stTemp)))){
                listVirtualFrames.remove(0);
                return k;
            }
        }
        return 0;
    }

    /***
     * Function that helps find the Optimal index to help SWAP later
     * @param referenceString
     * @param startIndex
     * @param dataTable
     * @return
     */
    public int getOptimal(ArrayList<Integer> referenceString, int startIndex, String[][] dataTable){
        ArrayList<Integer> maxIndex = new ArrayList<Integer>();
        for(int i =0; i < dataTable.length;i++){
            for(int j = startIndex+1; j < referenceString.size(); j++){
                if(dataTable[i][0].equals(String.valueOf(referenceString.get(j)))){
                     maxIndex.add(j); //if its found later in the reference list
                    break;
                }else{
                    return i+1 ;
                }
            }
        }
        //sort the list of reference strings so get the farthest one on the list and return it
        Collections.sort(maxIndex);
        Collections.reverse(maxIndex);
        for(int k = 0; k< dataTable.length; k++){
            if( !(dataTable[k][0].equals(String.valueOf(referenceString.get(maxIndex.get(0)))))){
                return k;
            }else if(dataTable[k][0].equals(String.valueOf(referenceString.get(maxIndex.get(0))))){
                return k;
            }
        }
        return 0;
    }

    /***
     * Function that helps find the LRU
     * @param referenceString
     * @param startIndex
     * @param dataTable
     * @return
     */
    public int getLRU(ArrayList<Integer> referenceString, int startIndex, String[][] dataTable){
        ArrayList<Integer> lruIndex = new ArrayList<Integer>();
        //look for the least used
        for(int i =0; i < dataTable.length;i++){
            for(int j = startIndex; j >= startIndex- dataTable.length; j--){
                if(dataTable[i][0].equals(String.valueOf(referenceString.get(j)))){
                    lruIndex.add(j);
                    break;
                }
            }
        }
        //sort the list to find lowest index(least recently used)
        Collections.sort(lruIndex);

        for(int k = 0; k< dataTable.length; k++){
            //match lowest index and find the index on the table which virtual frame to kill
            if((dataTable[k][0].equals(String.valueOf(referenceString.get(lruIndex.get(0)))))){
                return k;
            }
        }
        return 0;
    }

    /***
     * Function that lets the program detect a frame is in the table.
     * @param dataTable
     * @param cellString
     * @return
     */
    public boolean isFramePresent(String[][] dataTable, String cellString){
        for(int i = 0; i < dataTable.length; i++){
            for(int j = 0; j < 1; j++){
                if(!(dataTable[i][0].equals(cellString))){
                    faultCount++;
                    return true;
                }
            }
        }
        return false;
    }

    /***
     * function helps the program detect if two frames are equal(already in memory)
     * @param dataTable
     * @param cellString
     * @return
     */
    public boolean isFrameEqual(String[][] dataTable, String cellString){
        for(int i = 0; i < dataTable.length; i++){
            for(int j = 0; j < dataTable[0].length; j++){
                String a = dataTable[i][j];
                String b = cellString;
                if(a.equals(b)){
                    return true;
                }
            }
        }
        return false;
    }

    /***
     * Function that allows the program to add new frames to an ArrayList of Virtual Frames
     * will be used to count frequency of virtual frames
     * @param virtualFList
     * @param victimFrame
     */
    private void addVirtualFrames(ArrayList<VirtualFrame> virtualFList, String victimFrame){
        int temp = Integer.parseInt(victimFrame);

        for(int i = 0; i < virtualFList.size(); i++){
            if(virtualFList.get(i).getVirtualID() == temp){
                virtualFList.get(i).addFrequency();
            }else{
                virtualFList.add(new VirtualFrame(temp));
                break;
            }
        }
        if(virtualFList.isEmpty()){
            virtualFList.add(new VirtualFrame(temp));
        }
    }

    /***
     * Function will be used to create the list of Victim Frames
     * @param virtualFList
     * @param victimFrame
     */
    private void createFramesList(ArrayList<VirtualFrame> virtualFList, String victimFrame){
        int temp = Integer.parseInt(victimFrame);
        boolean found = false;
        for(int i = 0; i < virtualFList.size(); i++){
            if(virtualFList.get(i).getVirtualID() == temp){
                virtualFList.get(i).addFrequency();
                found = true;
                break;
            }
        }if(!(found)){
            virtualFList.add(new VirtualFrame(temp));
        }

        if(virtualFList.isEmpty()){
            virtualFList.add(new VirtualFrame(temp));
        }
    }

    /***
     * Function that helps display the right format for the simulation
     * @param temp
     */
    public void displaySimulationStep(int temp){
        System.out.println("Virtual Frame Number: "+ temp);
        for(int i = 0; i < physicalFrames; i++){
            for(int j = 0; j < 1; j++){
                   System.out.println("Physical Frame " + i +": " + demandPageTable[i][j]);

                }
            }
        System.out.println("Number of Page Faults: "+faultCount);
        System.out.println("Victim Frames: " + victimFrames.toString());
    }

    /***
     * this function will clear the table for the next simulation
     */
    public void clearTable(){
        for(int i = 0; i<physicalFrames; i++){
            for(int j = 0; j<1; j++){
                demandPageTable[i][j]=" ";
            }
        }
    }
}
