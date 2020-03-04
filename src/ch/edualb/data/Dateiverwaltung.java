// ----------------------------------------------------------------------------
// Dateiverwaltung.java
// ----------------------------------------------------------------------------
// Funktion:   Es kann eine Datei ausgewählt werden:
//             Die Auswahl beschränkt sich auf den folgenden Pfad: 
//             ..\Lerneinheiten\Kahoot\..
//             Es werden nur *.json Dateien angezeigt
//             Die Dateiauswahl erfolgt der einfachheitshalber über die 
//             vorangestellt Zahl (kein abtippen des Dateinamens notwendig)
// 
// SW:         Apache NetBeans IDE 11.1 ; JDK-11
// Datum:      30.01.2020
// Autor:      A. Albisser; PSI; WBWA /038; Tel. ++41563103450
// ----------------------------------------------------------------------------
package ch.edualb.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Dateiverwaltung implements Data {
  private String relPfad;
  private String ordnerName;
  private String dateiName;  
  private final String dateiNameStart;
  private final String dateiNameEnd;  
  private final String unterOrdner = "Data";    

  // -------------------------------------------------------------------------- 
  // Konstruktor
  // Initialisiert alle Membervariablen bei der Objekterzeugung
  // -------------------------------------------------------------------------- 
  public Dateiverwaltung(String ordnerName, String dateiName) {
    this.relPfad = System.getProperty("user.dir") +File.separator +unterOrdner;
    this.ordnerName = ordnerName;
    this.dateiNameStart = null;
    this.dateiNameEnd = null;
    this.dateiName = dateiName;
  }

  public Dateiverwaltung(String ordnerName, String dateiNameStart, String dateiNameEnd) {
    this.relPfad = System.getProperty("user.dir") +File.separator +unterOrdner;
    this.ordnerName = ordnerName;
    this.dateiNameStart = dateiNameStart;
    this.dateiNameEnd = dateiNameEnd;
    this.dateiName = null;
  }

  // -------------------------------------------------------------------------- 
  // Setter-Methoden
  // Den privaten Membervariablen gültigen Wert zuweisen, sonst Fehler ausgeben
  // -------------------------------------------------------------------------- 
  public boolean setRelPfad(String pfadName) {
    File ordner = new File(pfadName);

    if(ordner.exists()) {         // Nur wenn Ordner existiert Pfad akzeptieren
      this.relPfad = pfadName;
      return true;
    } else {                      // sonst Fehlermeldung ausgeben
      System.out.println("Ordner nicht gefunden: " +pfadName);
      return false;
    }
  }

  public boolean setOrdnerName(String ordnerName) {
    File ordner = new File(relPfad, ordnerName);

    if(ordner.exists()) {         // Nur wenn Ordner existiert Pfad akzeptieren
      this.ordnerName = ordnerName;
      return true;
    } else {                      // sonst Fehlermeldung ausgeben
      System.out.println("Ordner nicht gefunden: " +ordnerName);
      return false;
    }
  }

  public boolean setDateiName(String dateiName) {
    File datei = new File(getRelPfad() +File.separator + getOrdnerName(), dateiName);

    if(datei.exists()) {         // Nur wenn Ordner existiert Pfad akzeptieren
      this.dateiName = dateiName;
      return true;
    } else {                      // sonst Fehlermeldung ausgeben
      System.out.println("Datei nicht gefunden");
      return false;
    }
  } 
  
  // -------------------------------------------------------------------------- 
  // Getter-Methoden 
  // Aktueller Wert aus den privaten Membervariablen lesen und dem Aufrufer 
  // zurückgeben
  // -------------------------------------------------------------------------- 
  public String getRelPfad() {
    return relPfad;
  }

  public String getOrdnerName() {
    return ordnerName;
  }

  public String getDateiName() {
    return dateiName;
  }

  
  // **************************************************************************
  // **************************************************************************
  // Interface-Methoden 
  // **************************************************************************
  // **************************************************************************
  @Override
  public List<String> holeAuswahlLernset() {
    List <String> kahootDateien = new ArrayList<>(); 
    boolean found = false;
    // relativer Pfad und Ordnerstruktur um Ordner-Inhalt lesen zu können
    String pfad = relPfad +File.separator +ordnerName;
    File ordner = new File(pfad);
    if(ordner.exists()) {       // Nur wenn Ordner existiert Pfad akzeptieren

			// Speichere den ganzen Ordnerinhalt in einer Liste 
			// Die Liste enthält sowohl Unterordner wie auch Dateien
			String[] alleOrdnerUndDateien = ordner.list(); 

      // Nur bestimmte Dateien, welche im Dateinamen mit ...*... beginnen/enden
      for (String name : alleOrdnerUndDateien) {
        // Jeweils alle Inhalte prüfen
        File f1 = new File(pfad, name);
        if (f1.isFile()) {
          if (name.startsWith(dateiNameStart) && name.endsWith(dateiNameEnd)) {
            //System.out.println(name);
            kahootDateien.add(name);
            found = true;
          }
        }
      }            
    } else {
      System.out.println("Kontrollieren sie ob der Unterordner im angegebenen Pfad vorhanden und richtig benennt ist");
      System.out.println("und es Dateien \"kahoot_*.json\" hat. Ansonsten erstellen Sie die Ordner-Struktur und eine neue Datei");
      System.out.println(relPfad +File.separator +ordnerName);
    }
    if (found == false) {
      kahootDateien.add("Keine Dateien gefunden");
    }
    return kahootDateien;
  }

  @Override
  public void speichereLernset(String nameLernset, String outputStream) {
    String source = relPfad +File.separator +ordnerName +File.separator +nameLernset;
    try (PrintWriter writer = new PrintWriter( new File(source))) {
      writer.println(outputStream);
    }
    catch (IOException ex) {
      System.out.println("Fehler beim Schreiben Kahoot");
    }
  }

  @Override
  public void speichereStatistik(String nameUser, String outputStream) {
    String DateiNameStatistik = dateiNameStart +nameUser +dateiNameEnd; 
    String source = relPfad +File.separator +ordnerName +File.separator +DateiNameStatistik;
//    try (PrintWriter writer = new PrintWriter( new File(source))) {
//      writer.println(outputStream);
//    }      
    try (FileWriter fileWriter = new FileWriter(source, true)) { //Set true for append mode
      try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
        printWriter.println(outputStream);      
        printWriter.close();
      }      
    }
    catch (IOException ex) {
      System.out.println("Fehler beim Schreiben Statistik");
    }
  }
  
  @Override
  public String holeLernset(String nameLernset) {
    String source = relPfad +File.separator +ordnerName +File.separator +nameLernset;
    //System.out.println(source); // Testausgabe
    try (BufferedReader reader = new BufferedReader( new FileReader(source) )) {
      String zeile;    
      StringBuilder inputStream = new StringBuilder();
      while ((zeile = reader.readLine()) != null) {
        inputStream.append(zeile).append("\n");
        //System.out.println(zeile);  // Testausgabe
      }
      //System.out.println(inputStream.toString());  // Testausgabe
      return inputStream.toString();
    }
    catch (IOException ex) {
      System.out.println("Fehler beim Lesen");
      return "?";
    }    
  }
  
  @Override
  public String holeAsciiString() {
    String source = relPfad +File.separator +ordnerName +File.separator +dateiName;
    //System.out.println(source); // Testausgabe
    try (BufferedReader reader = new BufferedReader( new FileReader(source) )) {
      String zeile;    
      StringBuilder inputStream = new StringBuilder();
      while ((zeile = reader.readLine()) != null) {
        inputStream.append(zeile).append("\n");
        //System.out.println(zeile);  // Testausgabe
      }
      //System.out.println(inputStream.toString());  // Testausgabe
      return inputStream.toString();
    }
    catch (IOException ex) {
      System.out.println("Fehler beim Lesen");
      return "?";
    }      
  }  
  
  
  // -------------------------------------------------------------------------- 
  // Zeige Membervariablen Pfad, Ordner & Dateiname 
  // -------------------------------------------------------------------------- 
  public void zeigePfadOrdnerDatei() {
    System.out.println("Pfad:   " +relPfad);
    System.out.println("Ordner: " +ordnerName);
    System.out.println("Datei:  " +dateiName);
  }  
}
