/*********************************************************************************************************************
 * CMSC 335
 * Project 3
 * This class will be responsible for creating the traffic lights that will
 * be used for the simulation
 * *******************************************************************************************************************/
package project3;

// An enumeration of the colors of a traffic light. 
enum TrafficLightColor {  
  RED, GREEN, YELLOW 
}
// enums for whenever the traffic lights have to be
// paused
enum TrafficLightState {
    RUNNING, NOT_RUNNING
}

public class TrafficLightColorTask implements Runnable{
    private TrafficLightColor currentTrafficLightColor = TrafficLightColor.GREEN;
    private TrafficLightState stateOflight = TrafficLightState.RUNNING;
    private boolean stop = false; // set to true to stop the task 
    private boolean changed = false; // true when the light has changed
    
    public void run() {
        while (!stop) {
            try {
                switch(currentTrafficLightColor) { 
                  case GREEN: 
                    Thread.sleep(10000); // green for 10 seconds 
                    break; 
                  case YELLOW: 
                    Thread.sleep(2000);  // yellow for 2 seconds 
                    break; 
                  case RED: 
                    Thread.sleep(12000); // red for 12 seconds 
                    break; 
                } 
            } catch(InterruptedException exc) { 
                System.out.println(exc); 
            }
            changeColor();
        }
        changeColor();
    }
    
    
    synchronized void changeColor() {
        if (stateOflight == TrafficLightState.RUNNING) {
            switch(currentTrafficLightColor) {
            case RED:
                currentTrafficLightColor = TrafficLightColor.GREEN;
            break;
            case YELLOW:
                currentTrafficLightColor = TrafficLightColor.RED;
            break;
            case GREEN:
                currentTrafficLightColor = TrafficLightColor.YELLOW;
            }
            changed = true;
            notify(); // signal that the light has changed
        }
    } 
 
    // Wait until a light change occurs. 
    synchronized void waitForChange() { 
        try { 
            while(!changed) 
            wait(); // wait for light to change 
            changed = false;
        } catch(InterruptedException exc) { 
            System.out.println(exc); 
        } 
    }
    /*this will be used when user clicks on the STOP button*/
    synchronized  void stopTask(){
        stop = true;
    }
    /*this will be used when user clicks on the PAUSE button*/
    synchronized void pauseTask() {
        stateOflight = TrafficLightState.NOT_RUNNING;
    }

    /*This will be used when user clicks on the RESUME button*/
    synchronized void resumeTask() {
        stateOflight = TrafficLightState.RUNNING;
    }
    /*Getter for the traffic light color*/
    synchronized TrafficLightColor getCurrentTrafficLightColorAsEnum() {
        return currentTrafficLightColor;
    }
}
