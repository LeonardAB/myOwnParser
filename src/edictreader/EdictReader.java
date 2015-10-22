/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
http://stackoverflow.com/questions/14918188/reading-text-file-with-utf-8-encoding-using-java
http://stackoverflow.com/questions/1975973/how-to-use-jar-files-in-netbeans
 */
package edictreader;
import java.io.*;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;
import com.google.common.base.CharMatcher; //http://www.tutorialspoint.com/guava/guava_overview.htm  //https://github.com/google/guava/wiki/Release19

/**
 *
 * @author Dipta Mahardhika
 */

//public class EdictReader {
//  public static void main(String[] argv) throws IOException {
//    String FILENAME = "testEDICT.gz";
//
//    // Since there are 4 constructor calls here, I wrote them out in full.
//    // In real life you would probably nest these constructor calls.
//    FileInputStream fin = new FileInputStream(FILENAME);
//    GZIPInputStream gzis = new GZIPInputStream(fin);
//    InputStreamReader xover = new InputStreamReader(gzis,"EUC-JP");
//    BufferedReader is = new BufferedReader(xover);
//
//    String line;
//    // Now read lines of text: the BufferedReader puts them in lines,
//    // the InputStreamReader does Unicode conversion, and the
//    // GZipInputStream "gunzip"s the data from the FileInputStream.
//    while ((line = is.readLine()) != null)
//      System.out.println("Read: " + line);
//  }
//}

// <editor-fold defaultstate="collapsed" desc=" normal binary ">
public class EdictReader {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String fileName = "testEDICT.txt";// The name of the file to open.
        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        try {
            File fileDir = new File(fileName);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "EUC-JP"));

            String str;
//        str = in.readLine();
//             System.out.println(str);
            while ((str = in.readLine()) != null) {
                System.out.println("\n" + str);
                String romajiOnly = "gaada kanji lhoooo";
                final CharMatcher containsJapanese = 
        CharMatcher.inRange((char) 0x4e00, (char) 0x9faf);  //http://stackoverflow.com/questions/3826918/how-to-classify-japanese-characters-as-either-kanji-or-kana
                                                             //character list: http://www.rikai.com/library/kanjitables/kanji_codes.unicode.shtml
                boolean contentTest = containsJapanese.matchesAnyOf(romajiOnly);
System.out.println("containsJapanese = " + contentTest);
                 // TODO
                // 1. cek apakah tidak ada lema yang memiliki lebih dari 20 arti
               // OneLema satuLema = new OneLema(str); 
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

// </editor-fold>
