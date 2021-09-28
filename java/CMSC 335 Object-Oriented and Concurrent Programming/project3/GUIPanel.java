
/*********************************************************************************************************************
 * CMSC 335
 * Project 3
 * David Mejia
 * This class will be responsible for creating the panel that will display the options to the user
 * the user will be able to create up to 5 cars and up to 10 interceptions
 * *******************************************************************************************************************/
package project3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class GUIPanel extends JPanel{
    private TimeTask currentTime;
    private ArrayList<CarPositionTask> carList = new ArrayList<CarPositionTask>();
    private ArrayList<TrafficLightColorTask> interList = new ArrayList<TrafficLightColorTask>();
    JTextField fldCurrentTime = new JTextField(20);

    public GUIPanel() throws Exception{
        /********************************************************************************************************
         * Components that will be used inside the panel
         ********************************************************************************************************/
        setLayout(null);
        Thread timeTaskRunnable = new Thread(currentTime = new TimeTask());
        timeTaskRunnable.start();
        JLabel lbCurrentTime = new JLabel("Current Time");
        fldCurrentTime.setEditable(false);
        //display time on the GUI
        fldCurrentTime.setText(new SimpleDateFormat("kk:mm:ss a").format(currentTime.getCurrentTimeAsDate()));
        JLabel lbCarsTable = new JLabel("Cars Information");
        DefaultTableModel model = new DefaultTableModel();
        JTable carTable = new JTable(model);
        model.addColumn("Car #");
        model.addColumn("Car Location");
        model.addColumn("Car Speed(m/s)");
        model.addColumn("Car State");
        JScrollPane carScrollPane = new JScrollPane(carTable);
        JLabel lbNumOfCars = new JLabel("Number of Cars");
        JTextField numOfCars = new JTextField(10);
        JLabel lbNumOfInterceptions = new JLabel("Number of Interceptions");
        JTextField numOfInterceptions = new JTextField(10);
        JLabel interceptionInformation = new JLabel("Interception Info");
        DefaultTableModel intModel = new DefaultTableModel();
        JTable interceptionTable = new JTable(intModel);
        intModel.addColumn("Interception #");
        intModel.addColumn("Distance");
        intModel.addColumn("Traffic Light");
        JScrollPane interceptionScrollPane = new JScrollPane(interceptionTable);
        JButton startButton = new JButton("START");
        JButton stopButton = new JButton("STOP");
        stopButton.setEnabled(false);
        JButton pauseButton = new JButton("PAUSE");
        pauseButton.setEnabled(false);
        JButton resumeButton = new JButton("RESUME");
        resumeButton.setEnabled(false);

        /********************************************************************************************************
         * Location for all the components in the GUI Panel
         ********************************************************************************************************/

        lbCurrentTime.setBounds(10,10,150,20);
        fldCurrentTime.setBounds(10,30,150,20);
        lbCarsTable.setBounds(10,50,150,20);
        carScrollPane.setBounds(10,70,400,200);
        lbNumOfCars.setBounds(10,280,200,20);
        numOfCars.setBounds(210,280,50,20);
        lbNumOfInterceptions.setBounds(10,300,200,20);
        numOfInterceptions.setBounds(210,300,50,20);
        interceptionInformation.setBounds(450,50,150,20);
        interceptionScrollPane.setBounds(450,70,300,200);
        startButton.setBounds(405,280,75,30);
        pauseButton.setBounds(490,280,75,30);
        resumeButton.setBounds(575,280,90,30);
        stopButton.setBounds(675,280,75,30);


        /********************************************************************************************************
         * GUI timer to update different fields every 1/2 second
         ********************************************************************************************************/
        ActionListener timeUpdater = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                /*UPDATE TIME*/
                fldCurrentTime.setText(new SimpleDateFormat("kk:mm:ss a").format(currentTime.getCurrentTimeAsDate()));
                /*UPDATE CAR TABLE*/
                for(int k=0; k<carList.size();k++){
                    CarPositionTask carRef = carList.get(k);
                    int carPosition = carRef.getCurrentCarPosition();
                    int carSpeed = carRef.getSpeedMeterPerSecond();
                    CarState carStatus = carRef.getCurrentCarState();
                    model.setValueAt(carPosition,k,1);
                    model.setValueAt(carSpeed,k,2);
                    model.setValueAt(carStatus,k,3);
                }
                /*UPDATE TRAFFIC LIGHT TABLE*/
                for(int j=0;j < interList.size();j++){
                    TrafficLightColorTask interRef = interList.get(j);
                    TrafficLightColor lightStatus = interRef.getCurrentTrafficLightColorAsEnum();
                    intModel.setValueAt(lightStatus,j,2);
                }
                /*THIS WILL UPDATE AND CHECK WHEN LIGHTS ARE RED AND CARS
                * MUST STOP AND WAIT FOR THE LIGHT TO CHANGE TO GREEN*/
                for(int k=0; k<carList.size();k++){
                    CarPositionTask carRef = carList.get(k);
                    int carPosition = carRef.getCurrentCarPosition();
                    /*CARS WILL CHECK FOR LIGHTS TO TURN GREEN BEFORE MOVING*/
                    for(int m=0; m<interList.size(); m++){
                        TrafficLightColorTask interRef = interList.get(m);
                        TrafficLightColor lightStatus = interRef.getCurrentTrafficLightColorAsEnum();
                        /*CARS THAT ARE GETTING 200 METERS CLOSE TO THE TRAFFIC LIGHT WILL BE STOPPED*/
                        int trafficLightLocation = m*1000;
                        int trafficLightCloseTo = (m*1000)-200;
                        if((carPosition > trafficLightCloseTo && carPosition <= trafficLightLocation) && lightStatus == TrafficLightColor.RED) {
                            carRef.stopCar();
                        }else if((carPosition > trafficLightCloseTo && carPosition <= trafficLightLocation) && lightStatus == TrafficLightColor.GREEN){
                            carRef.startCar();
                        }else if((carPosition > trafficLightCloseTo && carPosition <= trafficLightLocation) && lightStatus == TrafficLightColor.YELLOW){
                            carRef.startCar();
                        }
                    }
                }
            }
        };
        /*THE UPDATE OF THE TABLES WILL BE DONE EVERY 500 MILLISECONDS*/
        Timer timer = new Timer(500, timeUpdater);
        timer.setRepeats(true);
        timer.start();

        /*Action Listeners for the four Buttons*/
        /*************************************************************************************
         * ACTION LISTENERS FOR THE FOUR DIFFERENT BUTTONS
         *************************************************************************************/
        startButton.addActionListener(new ActionListener() {
              @Override
              synchronized public void actionPerformed(ActionEvent e) {
                  try {
                      startButton.setEnabled(false);
                      pauseButton.setEnabled(true);
                      stopButton.setEnabled(true);
                      int numberOfCars =Integer.parseInt(numOfCars.getText());
                      int numberOfInter = Integer.parseInt(numOfInterceptions.getText());
                      //There is a cap on number of cars and interceptions
                      //no more than 5 cars
                      //no more than 10 interceptions
                      if (numberOfCars < 0 || numberOfCars >5){
                          numberOfCars = 5;
                      }if(numberOfInter < 0 || numberOfInter > 10){
                          numberOfInter = 10;
                      }
                      /*THIS WILL ADD THE CARS TO AN ARRAY LIST*/
                      for(int i =0; i<numberOfCars; i++) {
                          carList.add(new CarPositionTask());
                      }
                      /*THIS WILL START THE CAR THREADS AND ADD CARS TO TABLE */
                      for(int j=0;j < carList.size();j++){
                          new Thread(carList.get(j)).start();
                          CarPositionTask carRef = carList.get(j);
                          int carPosition = carRef.getCurrentCarPosition();
                          int carSpeed = carRef.getSpeedMeterPerSecond();
                          CarState carStatus = carRef.getCurrentCarState();
                          model.addRow(new Object[]{j,carPosition,carSpeed,carStatus});
                      }
                      //THIS WILL ADD THE CARS TO AN ARRAY LIST
                      for(int i =0; i<numberOfInter; i++) {
                          interList.add(new TrafficLightColorTask());
                      }
                      /*THIS WILL START THE TRAFFIC LIGHT THREADS AND
                      * ADD THEM TO THE TABLE*/
                      for(int j=0;j < interList.size();j++){
                          new Thread(interList.get(j)).start();
                          TrafficLightColorTask interRef = interList.get(j);
                          TrafficLightColor lightStatus = interRef.getCurrentTrafficLightColorAsEnum();
                          intModel.addRow(new Object[]{j,j*1000,lightStatus});
                      }
                  } catch (NumberFormatException numberFormatException) {
                      startButton.setEnabled(true);
                      pauseButton.setEnabled(false);
                      stopButton.setEnabled(false);
                      JOptionPane.showMessageDialog(null,"Enter Integers Only");
                  }

              }
          });
        /*THIS WILL BE RESPONSIBLE OF PAUSING THE CARS AND THE
        * CHANGE OF TRAFFIC LIGHT COLORS*/
        pauseButton.addActionListener(new ActionListener() {
            @Override
            synchronized public void actionPerformed(ActionEvent e) {
                resumeButton.setEnabled(true);
                pauseButton.setEnabled(false);
                /*PAUSES CARS UNTIL RESUME BUTTON PRESSED*/
                for(int k=0; k<carList.size();k++){
                    CarPositionTask carRef = carList.get(k);
                    carRef.stopCar();
                }
                /*PAUSES TRAFFIC LIGHTS UNTIL RESUME BUTTON PRESSED*/
                for(int j=0;j < interList.size();j++){
                    TrafficLightColorTask interRef = interList.get(j);
                    interRef.pauseTask();
                }


            }
        });

        /*THIS WILL MAKE SURE THAT THE CARS AND TRAFFIC LIGHTS CONTINUE
        * WORKING AFTER A PAUSE*/
        resumeButton.addActionListener(new ActionListener() {
            @Override
            synchronized public void actionPerformed(ActionEvent e) {
                resumeButton.setEnabled(false);
                pauseButton.setEnabled(true);
                /*CONTINUE RUNNING CARS*/
                for(int k=0; k<carList.size();k++){
                    CarPositionTask carRef = carList.get(k);
                    carRef.startCar();
                }
                /*CONTINUE TRAFFIC LIGHTS*/
                for(int j=0;j < interList.size();j++){
                    TrafficLightColorTask interRef = interList.get(j);
                    interRef.resumeTask();
                }
            }
        });

        /*THIS WILL MAKE SURE TO STOP ALL TASK FROM CARS AND TRAFFIC
        * LIGHTS. IT WILL ALSO ERASE BOTH TABLES MAKE THE GUI READY
        * FOR ANOTHER SIMULATION*/
        stopButton.addActionListener(new ActionListener() {
            @Override
            synchronized public void actionPerformed(ActionEvent e) {
                startButton.setEnabled(true);
                pauseButton.setEnabled(false);
                resumeButton.setEnabled(false);
                stopButton.setEnabled(false);
                /*STOP CAR TASKS AND CLEAR CAR TABLE*/
                for(int k=0; k<carList.size();k++){
                    CarPositionTask carRef = carList.get(k);
                    carRef.stopTask();
                    model.removeRow(0);
                }
                /*STOP TRAFFIC LIGHT TASK AND CLEAR TABLE*/
                for(int j=0;j < interList.size();j++){
                    TrafficLightColorTask interRef = interList.get(j);
                    interRef.stopTask();
                    intModel.removeRow(0);
                }
                /*CLEAR BOTH ARRAY LIST*/
                carList.clear();
                interList.clear();
            }
        });

        /**
         * Add all components to the panel
         */
            add(lbCurrentTime);
            add(fldCurrentTime);
            add(lbCarsTable);
            add(carScrollPane);
            add(lbNumOfCars);
            add(numOfCars);
            add(lbNumOfInterceptions);
            add(numOfInterceptions);
            add(interceptionInformation);
            add(interceptionScrollPane);
            add(startButton);
            add(pauseButton);
            add(stopButton);
            add(resumeButton);
    }

}
