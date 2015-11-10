/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 http://stackoverflow.com/questions/14918188/reading-text-file-with-utf-8-encoding-using-java
 http://stackoverflow.com/questions/1975973/how-to-use-jar-files-in-netbeans
 */
package edictreader;

import java.io.*;
import java.util.Scanner;

/**
 * @author Dipta Mahardhika
 */

public class EdictReader {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        Scanner task = new Scanner(System.in);
        String taskNum;
        System.out.println("Task list");
        System.out.println("1. experiment ground");
        System.out.println("2. test with 1 line");
        System.out.println("3. implemented +  with full sample");
        System.out.print("which task do you want to do?");
        taskNum = task.next();

        if (taskNum.equals("1")) {
            System.out.println("this is the test ground");
          
          //TODO hitung dan pilih kode bahasa mana saja yang muncul di file yg penuh. list code : https://en.wikipedia.org/wiki/List_of_ISO_639-2_codes
                       
            
            
            
        } else {
            String fileName = "testEDICT.txt";// The name of the file to open.
            System.out.println("Working Directory = " + System.getProperty("user.dir"));

            try {
                File fileDir = new File(fileName);
                BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "EUC-JP"));

                String str;
                switch (taskNum) {
                    case "2":
                        str = in.readLine();
                        System.out.println(str);
                        
                        //OneLema satuLema = new OneLema(str);
                       
                        
                        
                        break;
                    case "3":
                        while ((str = in.readLine()) != null) {
                       // str = in.readLine();
                        System.out.println(str);
                        OneLema satuLema = new OneLema(str);
                            //System.out.println("\n" + str);

                            // TODO
                            // 1. cek apakah tidak ada lema yang memiliki lebih dari 20 arti
                            // 2. cek apakah tidak ada entry kanji (combined with the reading) lebih dari 7
                           // OneLema satuLema = new OneLema(str);

                        }
                        break;
                    default:
                        System.out.println("Sorry your choice is outside our options");
                        break;
                }
                
                in.close();
            } catch (UnsupportedEncodingException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
