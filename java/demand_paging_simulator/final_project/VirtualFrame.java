/**
 * David Mejia
 * CMSC412 Final Project
 * Simulator for Demand Paging
 * */

package final_project;

public class VirtualFrame implements Comparable<VirtualFrame>{
    private int frequency;
    private int virtualID;

    /***
     * Constructor of virtual Frame
     * @param virtualID
     */
    VirtualFrame(int virtualID){
        frequency = 1;
        this.virtualID = virtualID;
    }

    /***
     * Getters and Setters
     * @return
     */
    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getVirtualID() {
        return virtualID;
    }

    public void addFrequency(){
        setFrequency(getFrequency() + 1);
    }
    public void setVirtualID(int virtualID) {
        this.virtualID = virtualID;
    }

    public String toString(){
        return String.valueOf(virtualID);
    }

    @Override
    public int compareTo(VirtualFrame o) {
        return this.getFrequency() - o.getFrequency();
    }
}
