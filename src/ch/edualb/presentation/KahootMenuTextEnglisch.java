// ----------------------------------------------------------------------------
// KahootMenuTextEnglisch.java
// ----------------------------------------------------------------------------
// Funktion:   Benutzerführung im Menü Kahoot in Englisch
// 
// SW:         Apache NetBeans IDE 11.1 ; JDK-11
// Datum:      02.02.2020
// Autor:      A. Albisser; PSI; WBWA /038; Tel. ++41563103450
// ----------------------------------------------------------------------------
package ch.edualb.presentation;

import ch.edualb.kahoot.Frage;
import java.util.LinkedHashMap;
import java.util.List;

public class KahootMenuTextEnglisch {

  public void zeigeAuswahlLernenOderBearbeiten() {
    System.out.println("\n--- Menu selection > Q/A ---------------------------------------------");
    System.out.println("< 1 > Study");
    System.out.println("< 2 > Edit learning units");
    System.out.println("----------------------------------------------------------------------"); 
    System.out.println("< 0 > Back"); 
    System.out.println("----------------------------------------------------------------------"); 
  }  
  
  public void zeigeAuswahlBestehendOderNeu() {
    System.out.println("\n--- Menu selection > Q/A > Edit --------------------------------------");
    System.out.println("< 1 > Edit existing learning unit");
    System.out.println("< 2 > Create new learning unit");
    System.out.println("----------------------------------------------------------------------"); 
    System.out.println("< 0 > Back"); 
    System.out.println("----------------------------------------------------------------------"); 
  }  

  public void zeigeAuswahlTitelStart() {
    System.out.println("\n--- Menu selection > Q/A > Select title ------------------------------");
    System.out.println("Select a kahoot learning unit:");
  }  

  public void zeigeAuswahlTitelStopp() {
    System.out.println("----------------------------------------------------------------------"); 
    System.out.println("< 0 > Back"); 
    System.out.println("----------------------------------------------------------------------"); 
  }  

  public void zeigeAuswahlInfoLernenNeuZurueck() {
    System.out.println("\n--- Menu selection > Q/A > > Study -----------------------------------");
    System.out.println("< 1 > Show information on learning unit");
    System.out.println("< 2 > Answer questions");
    System.out.println("< 3 > Select new learning unit");    
    System.out.println("----------------------------------------------------------------------"); 
    System.out.println("< 0 > Back"); 
    System.out.println("----------------------------------------------------------------------"); 
  }  

  public void zeigeAuswahlBearbeitenInfoFrageZurueck() {
    System.out.println("\n--- Menu selection > Q/A > Edit --------------------------------------");
    System.out.println("< 1 > Edit information on learning unit");
    System.out.println("< 2 > Edit questions about learning unit");
    System.out.println("< 3 > Save learning unit");    
    System.out.println("----------------------------------------------------------------------"); 
    System.out.println("< 0 > Back and save any changes made"); 
    System.out.println("----------------------------------------------------------------------"); 
  }  
  
  public void zeigeInfo(LinkedHashMap<String, String> mapInfo, List<String> keys) {
    System.out.println("\n--- Menu selection > Q/A > > Study > Information ---------------------");
    System.out.println("Game:                " +mapInfo.get(keys.get(0)));
    System.out.println("Type:                " +mapInfo.get(keys.get(1)));
    System.out.println("Main category:       " +mapInfo.get(keys.get(2)));
    System.out.println("Subcategory:         " +mapInfo.get(keys.get(3)));
    System.out.println("Subject:             " +mapInfo.get(keys.get(4)));
    System.out.println("Level of difficulty: " +mapInfo.get(keys.get(5)));
    System.out.println("Author:              " +mapInfo.get(keys.get(6)));
    System.out.println("Date:                " +mapInfo.get(keys.get(7)));
  }  
  
  public void zeigeInfoBearbeiten(LinkedHashMap<String, String> mapInfo, List<String> keys) {
    System.out.println("\n--- Menu selection > Q/A > Edit > Information ------------------------");
    System.out.println("Enter the number, followed by a = and the new value, such as <7=Peter>");
    System.out.println("< 1 > Game:                " +mapInfo.get(keys.get(0)));
    System.out.println("< 2 > Type:                " +mapInfo.get(keys.get(1)));
    System.out.println("< 3 > Main category:       " +mapInfo.get(keys.get(2)));
    System.out.println("< 4 > Subcategory:         " +mapInfo.get(keys.get(3)));
    System.out.println("< 5 > Subject:             " +mapInfo.get(keys.get(4)));
    System.out.println("< 6 > Level of difficulty: " +mapInfo.get(keys.get(5)));
    System.out.println("< 7 > Author:              " +mapInfo.get(keys.get(6)));
    System.out.println("< 8 > Date:                " +mapInfo.get(keys.get(7)));
    System.out.println("----------------------------------------------------------------------"); 
    System.out.println("< 0 > Back"); 
    System.out.println("----------------------------------------------------------------------"); 
  }  
  
  public void zeigeFrageLernen(Frage frage, int nr, int total) {
    System.out.println("\n--- Menu selection > Q/A > > Study > Answer Questions ----------------");
    System.out.println("Please answer question no. " +nr +" of " +total +",");
    System.out.println("by entering the number of the correct answer:");
    System.out.println(frage.getFrage());
    System.out.println("< 1 > " +frage.getAntwort1());
    System.out.println("< 2 > " +frage.getAntwort2());
    System.out.println("< 3 > " +frage.getAntwort3());
    System.out.println("< 4 > " +frage.getAntwort4());
    System.out.println("----------------------------------------------------------------------"); 
    System.out.println("< 0 > Back"); 
    System.out.println("----------------------------------------------------------------------"); 
  }  

  public void zeigeFrageBearbeiten(Frage frage, int nr, int total) {
    System.out.println("\n--- Menu selection > Q/A > Edit > Questions --------------------------");
    System.out.println("You are editting question no. " +(nr + 1) +" out of " +total +",");    
    System.out.println("Enter the number, followed by a = and the new value -> 2=How are you?");
    System.out.println("ID is numbered automatically => no input possible.");
    System.out.println("(*) Photos only make sense in combination with a GUI,");
    System.out.println("    with CLI you can save yourself the effort.");
    System.out.println("<  1 > ID (auto):                       " +frage.getId());
    System.out.println("<  2 > Question:                        " +frage.getFrage());
    System.out.println("<  3 > Level:                           " +frage.getLevel());
    System.out.println("<  4 > Name of photo (*):               " +frage.getNameFoto());
    System.out.println("<  5 > Path to photo (*):               " +frage.getPfadFoto());
    System.out.println("<  6 > Copyright by:                    " +frage.getCopyright());
    System.out.println("<  7 > Question A1 (Text):              " +frage.getAntwort1());
    System.out.println("<  8 > A1 correct (1), incorrect (0):   " +frage.getA1Korrekt());
    System.out.println("<  9 > Question A2 (Text):              " +frage.getAntwort2());
    System.out.println("< 10 > A2 correct (1), incorrect (0):   " +frage.getA2Korrekt());
    System.out.println("< 11 > Question A3 (Text):              " +frage.getAntwort3());
    System.out.println("< 12 > A3 correct (1), incorrect (0):   " +frage.getA3Korrekt());
    System.out.println("< 13 > Question A4 (Text):              " +frage.getAntwort4());
    System.out.println("< 14 > A4 correct (1), incorrect (0):   " +frage.getA4Korrekt());
    System.out.println("----------------------------------------------------------------------"); 
    System.out.println("< Delete > Delete this question.");
    System.out.println("< Copy=Nr > Copy this question and add it to the position no. (1 - " +(total + 1) +")"); 
    System.out.println("< Insert=Nr > Add a new, empty question at position no. (1 - " +(total + 1) +")"); 
    System.out.println("< Edit=Nr > Edit the question with the number (1 - " +total +")"); 
    System.out.println("----------------------------------------------------------------------"); 
    System.out.println("< 0 > Back"); 
    System.out.println("----------------------------------------------------------------------"); 
  }  
  
  public void zeigeSpeichernJaNein(String dateiName) {
    System.out.println("\n--- Menu selection > Q/A > Edit > Save -------------------------------");
    System.out.println("Would you like to save?");
    System.out.println("< 0 > No.");
    System.out.println("< 1 > Yes, under the same name: " +dateiName);
    System.out.println("< 2 > Yes, under a different name");    
    System.out.println("----------------------------------------------------------------------"); 
  }    
 
  public void zeigeNameDateiEingeben() {
    System.out.println("\n--- Menu selection > Q/A > Edit > Save > File name -------------------");
    System.out.println("Enter a file name (for files, kahoot_ is automatically");
    System.out.println("prefixed and the file extension .json is appended).");    
    System.out.println("----------------------------------------------------------------------");     
  }
}
