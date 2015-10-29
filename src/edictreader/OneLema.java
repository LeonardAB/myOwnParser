/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edictreader;

import com.google.common.base.CharMatcher; //http://www.tutorialspoint.com/guava/guava_overview.htm  //https://github.com/google/guava/wiki/Release19
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Dipta Mahardhika
 */
public class OneLema {

        //http://stackoverflow.com/questions/3826918/how-to-classify-japanese-characters-as-either-kanji-or-kana    
        //character list: http://www.rikai.com/library/kanjitables/kanji_codes.unicode.shtml
        public static final CharMatcher CONTAINS_NORM_KANJI = CharMatcher.inRange((char) 0x4e00, (char) 0x9faf);
        public static final CharMatcher CONTAINS_RARE_KANJI = CharMatcher.inRange((char) 0x3400, (char) 0x4dbf);
        public static final CharMatcher CONTAINS_KANJI = CONTAINS_NORM_KANJI.or(CONTAINS_RARE_KANJI);
        public static final CharMatcher CONTAINS_HIRAGANA = CharMatcher.inRange((char) 0x3040, (char) 0x309f);
        public static final CharMatcher CONTAINS_KATAKANA = CharMatcher.inRange((char) 0x30a0, (char) 0x30ff)
                .or(CharMatcher.inRange((char) 0xff66, (char) 0xff9d));
        public static final CharMatcher CONTAINS_KANA = CONTAINS_KATAKANA.or(CONTAINS_HIRAGANA);
        public static final CharMatcher CONTAINS_JAP_PUNCT = CharMatcher.inRange((char) 0x3001, (char) 0x303f);
        public static final CharMatcher CONTAINS_JAPANESE = CONTAINS_KANJI.or(CONTAINS_KANA).or(CONTAINS_JAP_PUNCT);
    
    String fullOri;
    String kanjiEnt, lemaEnt, entCode;
    String fullKanji, fullReading;
    boolean commonEnt;
    int kanjiLimPos, lemaEndPos, noOfGloss;
    int[] lemasPos = new int[20];
    int[] lemasFullStartPos = new int[20];
    int[] lemasFullEndPos = new int[20];
    SubGloss[] subGloss = new SubGloss[20];
    SubKanji[] subKanji = new SubKanji[7];
    boolean hasKanji;
    SubReading[] subReading = new SubReading[7];

    public OneLema(String fullOri) {
        this.fullOri = fullOri;
        this.kanjiLimPos = fullOri.indexOf("/");  // http://stackoverflow.com/questions/2615749/java-method-to-get-position-of-a-match-in-a-string
        //System.out.println("kanjiLimPos = " + kanjiLimPos);
        this.kanjiEnt = fullOri.substring(0, Math.min(fullOri.length(), this.kanjiLimPos)); //http://stackoverflow.com/questions/1583940/up-to-first-n-characters
//        System.out.println("kanjiEnt = " + this.kanjiEnt);
       
        this.hasKanji = kanjiEnt.contains("[");
        if (hasKanji) {
            this.fullReading = kanjiEnt.substring(kanjiEnt.indexOf("[")+1, kanjiEnt.indexOf("]"));
            System.out.println("fullReading = " + fullReading);
        }
        if (fullOri.contains("(P)")) {
            this.lemaEndPos = fullOri.lastIndexOf("/(P)");
            this.commonEnt = true;
        } else {
            this.lemaEndPos = fullOri.lastIndexOf("/Ent");
            this.commonEnt = false;
        }
        //System.out.println("lemaEndPos = " + lemaEndPos);
        this.lemaEnt = fullOri.substring(kanjiLimPos + 1, lemaEndPos);  //http://stackoverflow.com/questions/4570037/java-substring-index-range
        this.entCode = fullOri.substring(fullOri.lastIndexOf("Ent"), fullOri.length() - 1);

        if (fullOri.contains("(1)") && fullOri.contains("(2)")) {
            for (int i = 1; i < 30; i++) {
                String iText = Integer.toString(i);

                if (!(fullOri.contains("(" + iText + ")"))) {   //cek apakah sudah gloss terakhir apa belom
                    this.noOfGloss = i - 1;
                    break;  // kalau udah terakhir keluar
                }
                this.lemasPos[i - 1] = fullOri.indexOf("(" + iText + ")");  //mencari posisi angka pembuka gloss (1),(2), etc
                this.lemasFullStartPos[i - 1] = fullOri.lastIndexOf("/", this.lemasPos[i - 1]);//j; //assign posisi slash tersebut ke array
                //    System.out.println("Arti ke  " + i + "= " +fullOri.substring( lemasFullStartPos[i-1]+1,  lemasFullStartPos[i-1]+20) );

            }

        } else {
            this.noOfGloss = 1;

        }
//         System.out.println("this.noOfGloss = " + this.noOfGloss);
        for (int i = 1; i < this.noOfGloss + 1; i++) {
            String iText = Integer.toString(i);
            //      System.out.println("lemasFullStartPos[i-1] = " + lemasFullStartPos[i-1]);
            if (i != this.noOfGloss) {
                this.lemasFullEndPos[i - 1] = this.lemasFullStartPos[i];
            } else {
                this.lemasFullEndPos[i - 1] = this.lemaEndPos;
            }
                         //System.out.println("Arti ke  " + i + "= " + fullOri.substring(this.lemasFullStartPos[i - 1] + 1, this.lemasFullEndPos[i - 1]));
            //strip the number tag
            String unstripped = new String(fullOri.substring(this.lemasFullStartPos[i - 1] + 1, this.lemasFullEndPos[i - 1]));
            this.subGloss[i - 1] = new SubGloss(unstripped.replace("(" + iText + ")", "")); //http://stackoverflow.com/questions/10456681/how-to-initialize-array-in-java-when-the-class-constructor-has-parameters
            System.out.println("Stripped ke  " + i + "= " + this.subGloss[i - 1].fullGloss);
            System.out.println("\n");
        }

    }

    private class SubKanji {

        public SubKanji() {
        }
    }
    
    private class SubReading {

        public SubReading() {
        }
    }

    private class SubGloss {

        String fullGloss = "";
        String ptOfSpch= ""; //multivalue
        String mark = "" ; //multivalue
        String gaiRai= ""; //multivalue
        String field= "", dialect= "", jump= "", japNote= ""; //single value
        String pureMeaning= "";
        
        public SubGloss(String fullGloss) {
            this.fullGloss = fullGloss;
            this.pureMeaning = fullGloss;
            String[] notes = findNotes(fullGloss);
            
            for (String note: notes) {
                
                String noteType = OneLema.findNoteType(note);
                if (noteType.equals("field")) {
                    pureMeaning = pureMeaning.replace(note, "");
                    this.field = note.substring(1, note.length() - 1);
                }
                if (noteType.equals("jump")) {
                    pureMeaning = pureMeaning.replace("(" + note + ")", "");
                    this.jump = note;
                }
                if (noteType.equals("jap note")) {
                    pureMeaning = pureMeaning.replace("(" + note + ")", "");
                    this.japNote = note;
                }
                if (noteType.equals("dialect")) {
                    pureMeaning = pureMeaning.replace("(" + note + ")", "");
                    this.dialect = note;
                }
                if (noteType.equals("POS")) {
                    pureMeaning = pureMeaning.replace("(" + note + ")", "");
                    this.ptOfSpch = note;
                }
                if (noteType.equals("mark")) {
                    pureMeaning = pureMeaning.replace("(" + note + ")", "");
                    this.mark += note;
                    this.mark += ",";
                }
                if (noteType.equals("gairai")) {
                    pureMeaning = pureMeaning.replace("(" + note + ")", "");
                    this.gaiRai = note;
                }
                
            }
            
            pureMeaning = pureMeaning.trim().replaceAll(" +", " "); http://stackoverflow.com/questions/2932392/java-how-to-replace-2-or-more-spaces-with-single-space-in-string-and-delete-lead
            if(mark != null && !mark.isEmpty()){mark = mark.substring(0,mark.length()-1);}
            
            if(jump != null && !jump.isEmpty()) System.out.println("jump = " + jump);
            if(japNote != null && !japNote.isEmpty()) System.out.println("jap note = " + japNote);
            if(field != null && !field.isEmpty()) System.out.println("field = " + field);
            if(dialect != null && !dialect.isEmpty()) System.out.println("dialect = " + dialect);
            if(ptOfSpch != null && !ptOfSpch.isEmpty()) System.out.println("POS = " + ptOfSpch);
            if(mark != null && !mark.isEmpty()) System.out.println("mark = " + mark);
            if(gaiRai != null && !gaiRai.isEmpty()) System.out.println("gairai = " + gaiRai);
            //System.out.println("pureMeaning = " + pureMeaning);
           
        }
    
    }

    private String[] findNotes(String str) {
  //  int openBrk = 1;
        //  int closeBrk = 2;

        List<String> notes = new ArrayList<>();  //http://stackoverflow.com/questions/858572/how-to-make-a-new-list-in-java
        for (int openBrk = -1; (openBrk = str.indexOf("(", openBrk + 1)) != -1;) {
            //whenever I got the value i, find the its close bracket
            int closeBrk = str.indexOf(")", openBrk);
            //System.out.println("openBrk = " + openBrk + "closeBrk = " + closeBrk);
            if (openBrk != -1 && closeBrk != -1) {
                notes.add(str.substring(openBrk + 1, closeBrk));
            }
        }

        //find curly brackets but do not remove the brackets.
        int openBrk = str.indexOf("{");
        int closeBrk = str.indexOf("}");
        if (openBrk != -1 && closeBrk != -1) {
            notes.add(str.substring(openBrk, closeBrk + 1));
        }

        //convert list to array   
        String[] notesArr = new String[notes.size()];
        for (int i = 0; i < notes.size(); i++) {
            notesArr[i] = notes.get(i);  //http://stackoverflow.com/questions/9572795/convert-list-to-array-in-java
        }
        for (int i = 0; i < notesArr.length; i++) {  //this part is only for printing
            String notesArrX = notesArr[i];
//            System.out.println("notesArrX = " + notesArrX);
        }
        return notesArr;

    }

    private static String findNoteType(String note) {
        String noteType;
        
        if (note.substring(0,1).equals("{")) { //http://stackoverflow.com/questions/513832/how-do-i-compare-strings-in-java
            //curly brackets (field)
            noteType = "field";
        } else if (note.indexOf("See") == 1 && OneLema.checkJap("japanese", note)) { 
            //See (in the beginning - pos 1) && japanese ortho
            noteType = "jump";
        } else if (OneLema.checkJap("japanese", note)) {
            //esp. (in the beginning pos 1) && japanese ortho
            //only (in the end - pos length - 4(5?)) && japanese ortho
            //as (in the beginning - pos 1) && japanese ortho
            //other than that but contain japanese ortho
            noteType = "jap note";
        //Part of Speech
        //mark
        //dialect / lang
        } else if (OneLema.noteContentCheckByFile(note,"POS")) {
            noteType = "POS";
        } else if (OneLema.noteContentCheckByFile(note,"mark")) {
            noteType = "mark";
        } else if (OneLema.noteContentCheckByFile(note,"dialect")) {
            noteType = "dialect";
        } else if (note.matches("^[a-z]{3}:.*") ) {
            noteType = "gairai";
        } else {
            noteType = "meaning note";
        }
        
        return noteType;
    }

    public void entryPrint(int obj) {
        switch (obj) {
            case 1: 
                System.out.println("fullOri = " + fullOri);
                break;
            case 2: 
                System.out.println("kanjiEnt = " + kanjiEnt);
                break;
            case 3: 
                System.out.println("lemaEnt = " + lemaEnt);
                break;
            case 4: 
                System.out.println("entCode = " + entCode);
                break;
            case 5: 
            {System.out.println("fullGloss :");
                for (SubGloss gloss: subGloss) {
                    System.out.println(gloss.fullGloss);
                }
            }
                break;
            case 6: 
            {System.out.println("pureMeaning :");
                for (SubGloss gloss: subGloss) {
                    System.out.println(gloss.pureMeaning);
                }
            }
                break;
            default:
                throw new AssertionError();
        }
        
        System.out.println(this.fullOri);
    }

    public static boolean checkJap(String matcher, String target) {
        
        CharMatcher chosenMatcher = null;

        switch (matcher) {
            case "normkanji":
                chosenMatcher = CONTAINS_NORM_KANJI;
                break;
            case "rarekanji":
                chosenMatcher = CONTAINS_RARE_KANJI;
                break;
            case "kanji":
                chosenMatcher = CONTAINS_KANJI;
                break;
            case "hiragana":
                chosenMatcher = CONTAINS_HIRAGANA;
                break;
            case "katakana":
                chosenMatcher = CONTAINS_KATAKANA;
                break;
            case "kana":
                chosenMatcher = CONTAINS_KANA;
                break;
            case "punct":
                chosenMatcher = CONTAINS_JAP_PUNCT;
                break;
            case "japanese":
                chosenMatcher = CONTAINS_JAPANESE;
                break;
            default:
                throw new AssertionError("matcher not recognized");

        }

        boolean contentTest = chosenMatcher.matchesAnyOf(target);
        return contentTest;
    }
    
    private static boolean noteContentCheckByFile (String note, String comparer) {
        File comparerFile=null;
        boolean strict,colon;
        boolean doesContain = false;
        
        switch (comparer) {
            case "POS":
                comparerFile= new File("pos_list.txt");
                strict = true;
                colon = false;
                break;
            case "mark":
                comparerFile= new File("mark_list.txt");
                strict = true;
                colon = false;
                break;
            case "dialect":
                comparerFile= new File("ben_list.txt");
                strict = false;
                colon = true;
                break;
            default:
                throw new AssertionError("wrong comparer");
        }
        try {
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(comparerFile), "EUC-JP"));
        String item;
        while ((item = in.readLine()) != null) {
            if(!colon){               
            doesContain = note.equals(item) ||
                         (note.contains(item+",") && note.indexOf(item+",")==0) ||
                         (note.contains(","+item) && note.lastIndexOf(","+item)==note.length()-item.length()-1) ||
                         note.contains(","+item+",")  ;
            } else {
                doesContain = note.contains(item+":");
            }
            if (doesContain) {//System.out.println("item = " + item);
            break;}
                    }
                
        in.close();
        }
        catch (UnsupportedEncodingException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        return doesContain;
    }
}
