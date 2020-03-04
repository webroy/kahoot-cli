// ----------------------------------------------------------------------------
// MenuAllgemeinTextDeutsch.java
// ----------------------------------------------------------------------------
// Funktion:   Benutzerführung im Hauptmenu sowie bei allgemeinen Texten
//             in Deutsch
// 
// SW:         Apache NetBeans IDE 11.1 ; JDK-11
// Datum:      02.02.2020
// Autor:      A. Albisser; PSI; WBWA /038; Tel. ++41563103450
// ----------------------------------------------------------------------------
package ch.edualb.presentation;

public class MenuAllgemeinTextDeutsch {
    
  public void zeigeHauptmenuStart() {
    System.out.println("\n--- Menueauswahl (gewuenschte Zahl eingeben) -------------------");
    System.out.println("< 1 > Frage/Antwort-Prinzip (F/A-Prinzip) anwenden");     
    System.out.println("< 2 > Quartett-Prinzip anwenden *noch nicht implementiert");     
    System.out.println("< 3 > Speichermedieneinstellung Datei/Datenbank");
    System.out.println("< 4 > Spracheinstellung D/E");
    System.out.println("< 5 > Name eingeben Spieler/Autor");    
    System.out.println("< 6 > Informationen zu den einzelnen Lern-Prinzipien");    
    System.out.println("< 7 > Informationen zum Programm selbst");    
    System.out.println("----------------------------------------------------------------"); 
    System.out.println("< 0 > Beenden"); 
    System.out.println("----------------------------------------------------------------"); 
  }  

  public void zeigeEinstellungSpeichermedien() {
    System.out.println("\n--- Menueauswahl > Speichereinstellung -------------------------");
    System.out.println("< 1 > Datei");     
    System.out.println("< 2 > Datenbank *noch nicht implementiert");     
    System.out.println("----------------------------------------------------------------"); 
    System.out.println("< 0 > Beenden"); 
    System.out.println("----------------------------------------------------------------"); 
  }  
  
  public void zeigeEinstellungSprache() {
    System.out.println("\n--- Menueauswahl > Spracheinstellung ---------------------------");
    System.out.println("< 1 > Deutsch");     
    System.out.println("< 2 > Englisch");     
    System.out.println("----------------------------------------------------------------"); 
    System.out.println("< 0 > Beenden"); 
    System.out.println("----------------------------------------------------------------"); 
  }  
  
  public void zeigeNameSpielerEingeben() {
    System.out.println("\n--- Menueauswahl > Name eingeben -------------------------------");
    System.out.println("Geben Sie ihren Namen zum Lernen, bzw. zum Bearbeiten ein");
  }    

  public void zeigeInfoLernsysteme() {
    System.out.println("\n--- Menueauswahl > Informationen zu den Lernsystemen -----------");
    System.out.println(">>> Frage/Antwort-Prinzip <<<");
    System.out.println("Mit dem Frage/Antwort-Prinzip koennen Sie eigene Lern-Sets nach");
    System.out.println("ihren eigenen inhaltlichen Beduerfnissen erstellen. Es ist");
    System.out.println("natuerlich auch moeglich diese Lern-Sets sowohl für den eigenen");    
    System.out.println("wie auch für den fremden Gebrauch zum Lernen einzusetzen.");    
    System.out.println("Jedes Lern-Set besteht aus einem Informationsteil und aus einem");
    System.out.println("beliebig grossem Frage-Set. Der Informationsteil gibt Auskunft");
    System.out.println("zum Lern-Set selbst, waehrend das Frage-Set die Fragen und");
    System.out.println("Antworten beinhaltet, sowie einige Charakterestiken zu den Fragen.");
    System.out.println("Das Lern-Prinzip funktioniert so, dass alle Fragen aus dem");
    System.out.println("Frage-Set in zufaelliger Reihenfolge gestellt werden. Zu jeder");
    System.out.println("Frage gibt es 4 Antworten zur Auswahl. Am Ende der Lerneinheit");
    System.out.println("wird ein Erfolgsbericht angezeigt");
    System.out.println("");
    System.out.println(">>> Quartett <<<");
    System.out.println("Ist zur Zeit noch nicht implementiert");
  }    

  public void zeigeInfoProgramm() {
    System.out.println("\n--- Menueauswahl > Informationen zum Programm ------------------");
    System.out.println("Die Idee fuer dieses Programm kam von einem Berufsbildner, der");
    System.out.println("sich ein Software-Programm wuenschte, womit seine Berufs-");
    System.out.println("lernenden ihr Berufswissen selbststaendig repetieren und");
    System.out.println("abfragen koennen. Das Programm entstand bei einer Modularbeit im"); 
    System.out.println("Nachdiplomstudium SW-Engineering bei der ABB-TS in Baden. Zum");
    System.out.println("Projektteam gehören Samira Derokhar, Roy Stamm & Alfred Albisser.");
    System.out.println("Es handelt sich bei diesem Programm um ein Ausbildungsprojekt");    
    System.out.println("von Java-Programmieranfaengern, weshalb wir jegliche Haftung mit");
    System.out.println("diesem Anwendungsprogramm und den damit erstellten Lerninhalten");
    System.out.println("ablehnen.");    
    System.out.println("");        
    System.out.println("Sie koennen selbst gerne eigene Fragen und Antworten generieren"); 
    System.out.println("oder weitere Fragen hinzufuegen. Falls Sie die Dateien mit einem");
    System.out.println("beliebigen Texteditor bearbeiten wollen, muessen Sie sich"); 
    System.out.println("allerdings strickt an das vorgegebene JSON-Datei-Format halten."); 
    System.out.println("Das Programm wurde am 6.3.2020 zur freien Verfuegung freigegeben.");     
  }    
  
  public void zeigeUngueltigeEingabe() {
    System.out.println("Ungueltige Eingabe");
  }

  public void zeigeUnerlaubtesZeichen(char c) {
    System.out.println(c +"ist ein unerlaubtes Zeichen und wurde ignoriert");
  }
  
  public void zeigeFertigGelesen() {
    System.out.println("----------------------------------------------------------------"); 
    System.out.println("Zurueck zum Menu mit Enter");
    System.out.println("----------------------------------------------------------------"); 
  }
}
