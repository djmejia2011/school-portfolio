/**
 * David Mejia
 * CMSC412 Homework 6
 *
 * */
package homework6;

import java.io.*;
import java.util.Scanner;

public class FileDirectoryProcessing {
    //displays menu to the user
    public static void displayMenu(){
        System.out.println("\n0 - Exit");
        System.out.println("1 - Select directory");
        System.out.println("2 - List directory content(first level)");
        System.out.println("3 - List directory content(all levels)");
        System.out.println("4 - Delete file");
        System.out.println("5 - Display file");
        System.out.println("6 - Encrypt file(XOR with password)");
        System.out.println("7 - Decrypt file(XOR with password)");
        System.out.print("Select Option: ");
    }
    //function responsible to cyphering the file
    public static String xorOperation(String password, String stream){
        char passwordChar;
        String encrypted = "";
        int streamLength = stream.length();
        for(int i=0; i<streamLength; i++){
            int passwordCharCount = password.length();
            passwordChar = password.charAt(i%passwordCharCount);
            if(stream.charAt(i) == ' '){
                encrypted += stream.charAt(i);
            }else{
                encrypted += Character.toString((char)(stream.charAt(i) ^ passwordChar));
            }
        }
        return encrypted;
    }


    public static void getAllLevels(File[] file){
        if (file!=null){
            //do nothing
        //for every fileN in the array file
        for(File fileN:file){
            //if its a directory scan its content
            if (fileN.isDirectory()){
                System.out.println(fileN.getAbsoluteFile());
                getAllLevels(fileN.listFiles());
            }else{
                System.out.println(fileN.getAbsoluteFile());
            }
        }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        int selection = 8;
        String directory = "";


        while (selection != 0) {
            Scanner sc = new Scanner(System.in);
            displayMenu();
            try {
                selection = sc.nextInt();

            } catch (Exception e) {
                System.out.println("Please select from the Menu;");
                continue;
            }
            switch (selection) {
                case 0:
                    System.exit(0);

                case 1:
                    //select directory
                    System.out.println("Please select directory: ");
                    directory = sc.next();
                    if (new File(directory).isDirectory()) {
                        System.out.println("Directory added successfully");
                        continue;
                    } else {
                        System.out.println(directory + " is not a directory, Try again");
                        break;
                    }

                case 2:
                    //List directory content(fist level)
                    if (!directory.isEmpty()) {
                        File file = new File(directory);
                        String[] listOfContent = file.list();
                        System.out.println(directory + " First Level ->");
                        for (int i = 0; i < listOfContent.length; i++) {
                            System.out.println(listOfContent[i]);
                        }
                        break;
                    } else {
                        System.out.println("Please select a Directory");
                        break;
                    }

                case 3:
                    //List directory content(all levels)
                    if (!directory.isEmpty()) {
                        File file2nd = new File(directory);
                        File[] fileLists = file2nd.listFiles();
                        file2nd.delete();
                        System.out.println(directory + " All Levels->");
                        getAllLevels(fileLists);
                        break;
                    } else {
                        System.out.println("Please select a Directory");
                        break;
                    }

                case 4:
                    //delete file
                    if (!directory.isEmpty()) {
                        String fileName = directory + "\\";
                        System.out.println("Please enter file name in directory:");
                        fileName += sc.next();
                        File file3rd = new File(fileName);
                        if (file3rd.delete()) {
                            System.out.println("File deleted successfully");
                        } else {
                            System.out.println("File is not found");
                        }
                        break;
                    } else {
                        System.out.println("Please select a Directory");
                        break;
                    }
                case 5:
                    //Display File in Hexadecimal view
                    if (!directory.isEmpty()) {
                        String fileNameFive = directory + "\\";
                        System.out.println("Please enter file name in directory:");
                        fileNameFive += sc.next();
                        File file5th = new File(fileNameFive);
                        try (FileInputStream fileStream = new FileInputStream(file5th.getAbsolutePath())) {
                            int dataByte = 0;
                            int counter = 0;

                            while ((dataByte = fileStream.read()) != -1) {
                                System.out.printf("%02X ", dataByte);
                                counter++;

                                if (counter == 16) {
                                    counter = 0;
                                    System.out.println();
                                }
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        ;
                        break;
                    } else {
                        System.out.println("Please select a Directory");
                        break;
                    }

                case 6:
                    //Encrypt File(XOR with password)
                    if (!directory.isEmpty()) {
                        String fileNameSize = directory + "\\";
                        String encryptedLocation = directory + "\\";
                        System.out.println("Please enter file name in directory:");
                        fileNameSize += sc.next();
                        System.out.println("Please enter password:");
                        String passwordIn = sc.next();
                        System.out.println("Please enter new file name:");
                        encryptedLocation += sc.next();
                        File file6Th = new File(fileNameSize);
                        FileOutputStream fout = new FileOutputStream(encryptedLocation);
                        try (FileInputStream fileStream = new FileInputStream(file6Th.getAbsolutePath())) {
                            int dataByte = 0;
                            int counter = 0;
                            while ((dataByte = fileStream.read()) != -1) {
                                String toEncrypt = (String.valueOf(dataByte));
                                String data = (xorOperation(passwordIn, toEncrypt));
                                byte[] arrayB = data.getBytes();
                                fout.write(arrayB);
                                counter++;

                                if (counter == 16) {
                                    counter = 0;
                                }
                            }
                            fout.close();
                            System.out.println("File created successfully");
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        ;
                        break;
                    } else {
                        System.out.println("Please select a Directory");
                        break;
                    }
                case 7:
                    //Decrypt File(XOR with password)
                    String fileNameSize = directory + "\\";
                    String decryptedLocation = directory + "\\";
                    System.out.println("Please enter file to decrypt");
                    fileNameSize += sc.next();
                    System.out.println("Please enter password:");
                    String passwordIn = sc.next();
                    System.out.println("Please enter new file name:");
                    decryptedLocation += sc.next();
                    File file7Th = new File(fileNameSize);
                    FileOutputStream fout = new FileOutputStream(decryptedLocation);
                    try (FileInputStream fileStream = new FileInputStream(file7Th.getAbsolutePath())) {
                        int dataByte = 0;
                        int counter = 0;
                        while ((dataByte = fileStream.read()) != -1) {
                            String toEncrypt = (String.valueOf(dataByte));
                            String data = (xorOperation(passwordIn, toEncrypt));
                            byte[] arrayB = data.getBytes();
                            fout.write(arrayB);
                            counter++;

                            if (counter == 16) {
                                counter = 0;
                            }
                        }
                        fout.close();
                        System.out.println("File created successfully");
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }

    }
}
