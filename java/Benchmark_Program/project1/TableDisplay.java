//David Mejia
//CMSC451
//Project 1

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class TableDisplay implements ActionListener {

    JFrame frame;
    JTable table;
    JButton selectFile = new JButton("Select a File");

    //display table and button
    public TableDisplay(String[][] array){
        frame = new JFrame();
        frame.setTitle("Benchmark Report");
        frame.setSize(600,400);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
        String[] columns = {"size","Avg Count", "Coef Count", "Avg Time", "Coef Time"};
        table = new JTable(array, columns);
        table.setBounds(40,50,250,400);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);
        frame.add(selectFile);
        selectFile.addActionListener(this);
    }


    public static void main(String[] args){
        //empty table
        String[][] data = {
                { "", "", "","", "" },
                { "", "", "", "", "" }
        };

        {
            new TableDisplay(data);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //when user clicks on select file
        if(e.getSource()==selectFile){
            JFileChooser selectFiles = new JFileChooser();
            int response = selectFiles.showOpenDialog(selectFile);
            if (response == JFileChooser.APPROVE_OPTION){
                File file = new File(selectFiles.getSelectedFile().getAbsolutePath());
                FileReader reader = new FileReader();
                reader.openFile(file.toString());
                reader.readFile();
                // this will populate the screen based on the information inside file.
                new TableDisplay(reader.populateTable());
                reader.closeFile();
            }
        }
    }
}
