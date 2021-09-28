/*********************************************************************************************************************
 * CMSC 335
 * Project 3
 * This class will be responsible for creating different cars with random speeds
 * *******************************************************************************************************************/
package project3;

import java.util.Random;

// An enumeration of the state of a car.
enum CarState {
    STOPPED, RUNNING
}

public class CarPositionTask implements Runnable{

    private CarState currentCarState = CarState.RUNNING;
    private int currentCarPosition = 0;
    private int speedMeterPerSecond; // meter/second
    private boolean stop = false; // set to true to stop the task
    private Random randomCarSpeed = new Random();//to create random car speeds

    /**
     * This constructor will create a car with a random
     * speed to better simulate real world cars better
     */
    public CarPositionTask() {
        speedMeterPerSecond = randomSpeedRange(20,34);
    }

    /**
     * when thread start() method is called the run method
     * will be called, as long as the stop bool is not true it will
     * loop and add its speed every second.
     */
    public void run() {
        while (!stop) {
            try {
                switch(currentCarState) {
                    case RUNNING:
                        Thread.sleep(1000); // run for 1 seconds

                        break;
                    case STOPPED:
                        break;
                }
            } catch(InterruptedException exc) {
                System.out.println(exc);
            }
            updatePosition();
        }
    }

    /**
     * this method will allow the position of the car to
     * add up every second.
     */
    synchronized void updatePosition() {
        if (currentCarState == CarState.RUNNING)
            currentCarPosition += speedMeterPerSecond;

    }

    /**
     * this method will be used to stop the car when
     * the light is read or if the user clicks on pause
     */
    synchronized void stopCar() {
        currentCarState = CarState.STOPPED;
    }

    /**
     * This method will be used to start the car when the light turns
     * to green or when the user clicks resume
     */
    synchronized void startCar() {
        currentCarState = CarState.RUNNING;
    }

    /**
     * this method will be used when the user clicks on stop button
     */
    synchronized void stopTask() {
        stop = true;
    }

    /**
     * getters for current position and state
     * and speed
     */
    synchronized int getCurrentCarPosition() {
        return currentCarPosition;
    }
    synchronized CarState getCurrentCarState() {
        return currentCarState;
    }
    public synchronized int getSpeedMeterPerSecond(){
        return speedMeterPerSecond;
    }

    /**this method allows the speed of each car to be random to simulate real traffic*/
    synchronized int randomSpeedRange(int min, int max){
        int randomResult;
        while(true){
            randomResult = randomCarSpeed.nextInt(max);
            if (randomResult >= min){
                return randomResult;
            }
        }
    }


}
