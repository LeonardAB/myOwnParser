/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edictreader;

import com.google.common.base.CharMatcher;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dipta Mahardhika
 */
public class OneLema {

    String fullOri;
    String kanjiEnt, lemaEnt, entCode;
    boolean commonEnt;
    int kanjiLimPos, lemaEndPos, noOfLemas;
    int[] lemasPos = new int[20];
    int[] lemasFullStartPos = new int[20];
    int[] lemasFullEndPos = new int[20];
    SubGloss[] subGloss = new SubGloss[20];

    public OneLema(String fullOri) {
        this.fullOri = fullOri;
        this.kanjiLimPos = fullOri.indexOf("/");  // http://stackoverflow.com/questions/2615749/java-method-to-get-position-of-a-match-in-a-string
        //System.out.println("kanjiLimPos = " + kanjiLimPos);
        this.kanjiEnt = fullOri.substring(0, Math.min(fullOri.length(), this.kanjiLimPos)); //http://stackoverflow.com/questions/1583940/up-to-first-n-characters
//        System.out.println("kanjiEnt = " + this.kanjiEnt);
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
                    this.noOfLemas = i - 1;
                    break;  // kalau udah terakhir keluar
                }
                this.lemasPos[i - 1] = fullOri.indexOf("(" + iText + ")");  //mencari posisi angka pembuka gloss (1),(2), etc
                this.lemasFullStartPos[i - 1] = fullOri.lastIndexOf("/", this.lemasPos[i - 1]);//j; //assign posisi slash tersebut ke array
                //    System.out.println("Arti ke  " + i + "= " +fullOri.substring( lemasFullStartPos[i-1]+1,  lemasFullStartPos[i-1]+20) );

            }

        } else {
            this.noOfLemas = 1;

        }
//         System.out.println("this.noOfLemas = " + this.noOfLemas);
        for (int i = 1; i < this.noOfLemas + 1; i++) {
            String iText = Integer.toString(i);
            //      System.out.println("lemasFullStartPos[i-1] = " + lemasFullStartPos[i-1]);
            if (i != this.noOfLemas) {
                this.lemasFullEndPos[i - 1] = this.lemasFullStartPos[i];
            } else {
                this.lemasFullEndPos[i - 1] = this.lemaEndPos;
            }
                         //System.out.println("Arti ke  " + i + "= " + fullOri.substring(this.lemasFullStartPos[i - 1] + 1, this.lemasFullEndPos[i - 1]));
            //strip the number tag
            String unstripped = new String(fullOri.substring(this.lemasFullStartPos[i - 1] + 1, this.lemasFullEndPos[i - 1]));
            this.subGloss[i - 1] = new SubGloss(unstripped.replace("(" + iText + ")", "")); //http://stackoverflow.com/questions/10456681/how-to-initialize-array-in-java-when-the-class-constructor-has-parameters
            System.out.println("Stripped ke  " + i + "= " + this.subGloss[i - 1].fullGloss);
        }

    }

    private class SubGloss {

        String fullGloss;
        String[] ptOfSpch = new String[7];
        String[] mark = new String[7];
        String field, dialect, othNotes;
        String[] gaiRai = new String[5];

        public SubGloss(String fullGloss) {
            this.fullGloss = fullGloss;
            String[] notes = findNotes(fullGloss);
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
            System.out.println("notesArrX = " + notesArrX);
        }
        return notesArr;

    }

    private String findNoteType(String note) {
        String noteType;
        
        if (note.substring(0).equals("{")) { //http://stackoverflow.com/questions/513832/how-do-i-compare-strings-in-java
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
        
        //TODO
        //POS
        //mark
        //dialect / lang
        // } else if (???)) {
            
            
        } else {
            noteType = "meaning note";
        }
        
        return noteType;
    }

    public void fullOriPrint() {
        System.out.println(this.fullOri);
    }

    public static boolean checkJap(String matcher, String target) {
        //http://stackoverflow.com/questions/3826918/how-to-classify-japanese-characters-as-either-kanji-or-kana    
        //character list: http://www.rikai.com/library/kanjitables/kanji_codes.unicode.shtml
        final CharMatcher containsNormKanji = CharMatcher.inRange((char) 0x4e00, (char) 0x9faf);
        final CharMatcher containsRareKanji = CharMatcher.inRange((char) 0x3400, (char) 0x4dbf);
        final CharMatcher containsKanji = containsNormKanji.or(containsRareKanji);
        final CharMatcher containsHiragana = CharMatcher.inRange((char) 0x3040, (char) 0x309f);
        final CharMatcher containsKatakana = CharMatcher.inRange((char) 0x30a0, (char) 0x30ff)
                .or(CharMatcher.inRange((char) 0xff66, (char) 0xff9d));
        final CharMatcher containsKana = containsKatakana.or(containsHiragana);
        final CharMatcher containsJapPunct = CharMatcher.inRange((char) 0x3001, (char) 0x303f);
        final CharMatcher containsJapanese = containsKanji.or(containsKana).or(containsJapPunct);

        CharMatcher chosenMatcher = null;

        switch (matcher) {
            case "normkanji":
                chosenMatcher = containsNormKanji;
                break;
            case "rarekanji":
                chosenMatcher = containsRareKanji;
                break;
            case "kanji":
                chosenMatcher = containsKanji;
                break;
            case "hiragana":
                chosenMatcher = containsHiragana;
                break;
            case "katakana":
                chosenMatcher = containsKatakana;
                break;
            case "kana":
                chosenMatcher = containsKana;
                break;
            case "punct":
                chosenMatcher = containsJapPunct;
                break;
            case "japanese":
                chosenMatcher = containsJapanese;
                break;
            default:
                throw new AssertionError("matcher not recognized");

        }

        boolean contentTest = chosenMatcher.matchesAnyOf(target);
        return contentTest;
    }
}
