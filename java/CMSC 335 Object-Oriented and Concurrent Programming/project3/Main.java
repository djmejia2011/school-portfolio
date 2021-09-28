/*********************************************************************************************************************
 * CMSC335
 * Project 3
 * David Mejia
 * This class will be responsible for creating the main frame of the GUI.
 * *******************************************************************************************************************/
package project3;

import javax.swing.*;

public class Main {

    public Main() throws Exception{
        final int WIDTH = 760;
        final int HEIGHT =400;

        //GUI JFrame creation
        JFrame frameGUI = new JFrame("Project 3 - Traffic Simulator");
        frameGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameGUI.setSize(WIDTH, HEIGHT);
        frameGUI.setResizable(false);
        frameGUI.setLocationRelativeTo(null);
        frameGUI.add(new GUIPanel()); //main panel
        frameGUI.setVisible(true); //
    }
/*********************************************************************************************************************
 *
 *                                                 MAIN METHOD
 *
 * *******************************************************************************************************************/
    static public void main(String[] args) {
            try {
                new Main();
            } catch (Exception e) {
                System.out.println("Fatal Error, Please try again");
            }
    }
}


