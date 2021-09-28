/**
 * David Mejia
 * CMSC412 Final Project
 * Simulator for Demand Paging
 * */

package final_project;

public class ReferenceStringException extends Exception{
    public String toString(){
        return "Virtual memory is of 10 frames only, please enter between 0 - 9 in reference string";
    }
}
