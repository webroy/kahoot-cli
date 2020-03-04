// ----------------------------------------------------------------------------
// KahootMenuTextDeutsch.java
// ----------------------------------------------------------------------------
// Funktion:   Benutzerführung im Menü Kahoot in Deutsch
// 
// SW:         Apache NetBeans IDE 11.1 ; JDK-11
// Datum:      02.02.2020
// Autor:      A. Albisser; PSI; WBWA /038; Tel. ++41563103450
// ----------------------------------------------------------------------------
package ch.edualb.presentation;

import ch.edualb.kahoot.Frage;
import java.util.LinkedHashMap;
import java.util.List;

public class KahootMenuTextDeutsch {

  public void zeigeAuswahlLernenOderBearbeiten() {
    System.out.println("\n--- Menueauswahl > F/A -----------------------------------------------");
    System.out.println("< 1 > Lernen");
    System.out.println("< 2 > Lerneinheiten bearbeiten");
    System.out.println("----------------------------------------------------------------------"); 
    System.out.println("< 0 > Zurueck"); 
    System.out.println("----------------------------------------------------------------------"); 
  }  
  
  public void zeigeAuswahlBestehendOderNeu() {
    System.out.println("\n--- Menueauswahl > F/A > Bearbeiten ----------------------------------");
    System.out.println("< 1 > Bestehende Lerneinheit bearbeiten");
    System.out.println("< 2 > Neue Lerneinheit erstellen");
    System.out.println("----------------------------------------------------------------------"); 
    System.out.println("< 0 > Zurueck"); 
    System.out.println("----------------------------------------------------------------------"); 
  }  

  public void zeigeAuswahlTitelStart() {
    System.out.println("\n--- Menueauswahl > F/A > Titelauswahl --------------------------------");
    System.out.println("Waehlen sie eine Kahoot-Lerneinheiten aus:");
  }  

  public void zeigeAuswahlTitelStopp() {
    System.out.println("----------------------------------------------------------------------"); 
    System.out.println("< 0 > Zurueck"); 
    System.out.println("----------------------------------------------------------------------"); 
  }  

  public void zeigeAuswahlInfoLernenNeuZurueck() {
    System.out.println("\n--- Menueauswahl > F/A > Lernen --------------------------------------");
    System.out.println("< 1 > Informationen zur Lerneinheit anzeigen");
    System.out.println("< 2 > Fragen beantworten");
    System.out.println("< 3 > Neue Lerneinheit waehlen");    
    System.out.println("----------------------------------------------------------------------"); 
    System.out.println("< 0 > Zurueck"); 
    System.out.println("----------------------------------------------------------------------"); 
  }  

  public void zeigeAuswahlBearbeitenInfoFrageZurueck() {
    System.out.println("\n--- Menueauswahl > F/A > Bearbeiten ----------------------------------");
    System.out.println("< 1 > Informationen zur Lerneinheit bearbeiten");
    System.out.println("< 2 > Fragen zur Lerneinheit bearbeiten");
    System.out.println("< 3 > Lerneinheit speichern");    
    System.out.println("----------------------------------------------------------------------"); 
    System.out.println("< 0 > Zurueck und bei vorgenommenen Aenderungen speichern"); 
    System.out.println("----------------------------------------------------------------------"); 
  }  
  
  public void zeigeInfo(LinkedHashMap<String, String> mapInfo, List<String> keys) {
    System.out.println("\n--- Menueauswahl > F/A > Lernen > Information --------------------------");
    System.out.println("Spiel:              " +mapInfo.get(keys.get(0)));
    System.out.println("Typ:                " +mapInfo.get(keys.get(1)));
    System.out.println("Hauptkategorie:     " +mapInfo.get(keys.get(2)));
    System.out.println("Unterkategorie:     " +mapInfo.get(keys.get(3)));
    System.out.println("Thema:              " +mapInfo.get(keys.get(4)));
    System.out.println("Schwierigkeitsgrad: " +mapInfo.get(keys.get(5)));
    System.out.println("Autor:              " +mapInfo.get(keys.get(6)));
    System.out.println("Datum:              " +mapInfo.get(keys.get(7)));
  }  
  
  public void zeigeInfoBearbeiten(LinkedHashMap<String, String> mapInfo, List<String> keys) {
    System.out.println("\n--- Menueauswahl > F/A > Bearbeiten > Informationen --------------------");
    System.out.println("Geben Sie die Zahl, gefolgt von einem = und dem neuen Wert ein <7=Peter>");
    System.out.println("< 1 > Spiel:              " +mapInfo.get(keys.get(0)));
    System.out.println("< 2 > Typ:                " +mapInfo.get(keys.get(1)));
    System.out.println("< 3 > Hauptkategorie:     " +mapInfo.get(keys.get(2)));
    System.out.println("< 4 > Unterkategorie:     " +mapInfo.get(keys.get(3)));
    System.out.println("< 5 > Thema:              " +mapInfo.get(keys.get(4)));
    System.out.println("< 6 > Schwierigkeitsgrad: " +mapInfo.get(keys.get(5)));
    System.out.println("< 7 > Autor:              " +mapInfo.get(keys.get(6)));
    System.out.println("< 8 > Datum:              " +mapInfo.get(keys.get(7)));
    System.out.println("----------------------------------------------------------------------"); 
    System.out.println("< 0 > Zurueck"); 
    System.out.println("----------------------------------------------------------------------"); 
  }  
  
  public void zeigeFrageLernen(Frage frage, int nr, int total) {
    System.out.println("\n--- Menueauswahl > F/A > Lernen > Fragen beantworten ------------------");
    System.out.println("Bitte beantworten Sie die Frage Nr. " +nr +" von " +total +",");
    System.out.println("indem Sie die Zahl vor der richtigen Antwort eingeben:");
    System.out.println(frage.getFrage());
    System.out.println("< 1 > " +frage.getAntwort1());
    System.out.println("< 2 > " +frage.getAntwort2());
    System.out.println("< 3 > " +frage.getAntwort3());
    System.out.println("< 4 > " +frage.getAntwort4());
    System.out.println("----------------------------------------------------------------------"); 
    System.out.println("< 0 > Zurueck"); 
    System.out.println("----------------------------------------------------------------------"); 
  }  

  public void zeigeFrageBearbeiten(Frage frage, int nr, int total) {
    System.out.println("\n--- Menueauswahl > F/A > Bearbeiten > Fragen -------------------------");
    System.out.println("Sie bearbeiten die Frage Nr. " +(nr + 1) +" von insgesamt " +total +",");    
    System.out.println("Geben Sie die Zahl, gefolgt von einem = und dem neuen Wert ein -> 2=Wie geht es Ihnen?");
    System.out.println("ID wird automatisch nummeriert => keine Eingabe moeglich");
    System.out.println("(*) Fotos machen nur in Verbindung mit einem GUI Sinn, bei CLI koennen Sie sich den Aufwand sparen");
    System.out.println("<  1 > ID (auto):                      " +frage.getId());
    System.out.println("<  2 > Frage:                          " +frage.getFrage());
    System.out.println("<  3 > Level:                          " +frage.getLevel());
    System.out.println("<  4 > Name des Fotos (*):             " +frage.getNameFoto());
    System.out.println("<  5 > Pfad zum Foto (*):              " +frage.getPfadFoto());
    System.out.println("<  6 > Copyright by:                   " +frage.getCopyright());
    System.out.println("<  7 > Antwort A1 (Text):              " +frage.getAntwort1());
    System.out.println("<  8 > A1 richtig (1), falsch (0):     " +frage.getA1Korrekt());
    System.out.println("<  9 > Antwort A2 (Text):              " +frage.getAntwort2());
    System.out.println("< 10 > A2 richtig (1), falsch (0):     " +frage.getA2Korrekt());
    System.out.println("< 11 > Antwort A3 (Text):              " +frage.getAntwort3());
    System.out.println("< 12 > A3 richtig (1), falsch (0):     " +frage.getA3Korrekt());
    System.out.println("< 13 > Antwort A4 (Text):              " +frage.getAntwort4());
    System.out.println("< 14 > A4 richtig (1), falsch (0):     " +frage.getA4Korrekt());
    System.out.println("----------------------------------------------------------------------"); 
    System.out.println("< Delete > Loesche diese Frage");
    System.out.println("< Copy=Nr > Kopiere diese Frage und fuege sie an der Position Nr hinzu (1 - " +(total + 1) +")"); 
    System.out.println("< Insert=Nr > Fuege eine neue, leere Frage an der Position Nr hinzu (1 - " +(total + 1) +")"); 
    System.out.println("< Edit=Nr > Bearbeite die Frage mit der Nr (1 - " +total +")"); 
    System.out.println("----------------------------------------------------------------------"); 
    System.out.println("< 0 > Zurueck"); 
    System.out.println("----------------------------------------------------------------------"); 
  }  
  
  public void zeigeSpeichernJaNein(String dateiName) {
    System.out.println("\n--- Menueauswahl > F/A > Bearbeiten > Speichern ----------------------");
    System.out.println("Moechten Sie speichern?");
    System.out.println("< 0 > Nein");
    System.out.println("< 1 > Ja, unter dem gleichen Namen: " +dateiName);
    System.out.println("< 2 > Ja, mit neuem Namen");    
    System.out.println("----------------------------------------------------------------------"); 
  }    
 
  public void zeigeNameDateiEingeben() {
    System.out.println("\n--- Menueauswahl > F/A > Bearbeiten > Speichern > Dateiname ----------");
    System.out.println("Geben Sie einen Dateinamen ein (bei Dateien wird automatisch");
    System.out.println("kahoot_ vorangestellt und die Dateiendung .json angehaengt)");    
    System.out.println("----------------------------------------------------------------------");     
  }
}
