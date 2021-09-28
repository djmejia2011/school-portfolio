/*********************************************************************************************************************
 * CMSC 335
 * Project 3
 * This class will be responsible for creating the time that will be displayed in the GUI
 * *******************************************************************************************************************/
package project3;


import java.util.Date;

public class TimeTask implements Runnable {

    //create new time
    private Date currentTimeAsDate = new Date();

    private boolean stop = false; // set to true to stop the task

    public void run() {
        while (!stop) {
            //this will update the time every second
            try {
                Thread.sleep(1000);
                updateTime();

            } catch(InterruptedException exc) {
                System.out.println(exc);
            }
        }
    }

    /**
     * this method will update time while looping in the
     * run method
     */
    synchronized void updateTime() {
        currentTimeAsDate = new Date();
    }

    /**
     * this method will be used whenever the Stop
     * button is pressed
     */
    public synchronized void stopTask() {
        stop = true;
    }

    /**
     * getter for the current time, this will be used to call current
     * date inside the GUI
     * @return
     */
    public synchronized Date getCurrentTimeAsDate() {
        return currentTimeAsDate;
    }
}

