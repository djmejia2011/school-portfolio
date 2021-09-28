//David Mejia
//CMSC451
//Project 1

import java.util.Formatter;

public class FileCreator {
    private Formatter i;


    public void openFile(String fileName){
        try{
            i = new Formatter(fileName);
        }catch(Exception e){
            System.out.println("Error occured when opening file");
        }
    }
    //function responsible for formatting the output file correcly
    public void addToFile(double avgCount, long avgTime){
        i.format("%s %s ",String.valueOf(avgCount),String.valueOf(avgTime));
    }

    public void addToFile(int size){
        i.format("%s ",String.valueOf(size));
    }

    public void addToFile(String s){
        i.format("%s",s);
    }

    public void closeFiles(){
        i.close();

    }
}
