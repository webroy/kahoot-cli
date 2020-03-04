// ----------------------------------------------------------------------------
// LernPlattform.java
// ----------------------------------------------------------------------------
// Funktion:   
// 
// SW:         Apache NetBeans IDE 11.1 ; JDK-11
// Datum:      08.02.2020
// Autor:      A. Albisser; PSI; WBWA /038; Tel. ++41563103450
// ----------------------------------------------------------------------------
package ch.edualb.logic;

import ch.edualb.data.Data;
import ch.edualb.data.Dateiverwaltung;
import ch.edualb.kahoot.KahootJsonFormat;
import ch.edualb.kahoot.KahootSet;
import ch.edualb.presentation.Hauptmenu;
import java.util.List;

public class LernPlattform implements Logic{

  public enum Lernsystem {KAHOOT, QUARTETT};
  static public Lernsystem lernsystem = Lernsystem.KAHOOT;
  public enum Speichersystem {DATEI, DATENBANK};
  static public Speichersystem speichersystem = Speichersystem.DATEI;
  Hauptmenu hauptmenu = new Hauptmenu();
  KahootSet kahootset = new KahootSet();
  KahootJsonFormat json = new KahootJsonFormat();

  public void start() {    
    hauptmenu.start();
  }

  // **************************************************************************
  // **************************************************************************
  // Interface-Methoden 
  // **************************************************************************
  // **************************************************************************  
  @Override
  public List<String> holeListeLernset() {
    if (speichersystem == Speichersystem.DATEI) {
      if (lernsystem == Lernsystem.KAHOOT) {
        Data data = new Dateiverwaltung("Kahoot", "kahoot_", ".json");
        return data.holeAuswahlLernset();
      } else {
        Data data = new Dateiverwaltung("Quartett", "quartett_", ".json");
        return data.holeAuswahlLernset();
      }
    // Datenbank => noch anpassen !!!!
    } else { 
      if (lernsystem == Lernsystem.KAHOOT) {
        Data data = new Dateiverwaltung("Kahoot", "kahoot_", ".json");
        return data.holeAuswahlLernset();
      } else {
        Data data = new Dateiverwaltung("Quartett", "quartett_", ".json");
        return data.holeAuswahlLernset();
      }
    }
  }

  @Override
  public KahootSet holeLernset(String nameLernset) {
    if (speichersystem == Speichersystem.DATEI) {
      Data data = new Dateiverwaltung("Kahoot", "kahoot_", ".json");
      kahootset = json.getKahootSetFromJson(data.holeLernset(nameLernset));
      //kahootset.zeigeKahootInhalt();  // Testausgabe
    // Datenbank => noch anpassen !!!!
//    } else { 
//      Data data = new Dateiverwaltung("Lerneinheit\\Kahoot", "kahoot_", ".json");
    }
    return kahootset;
  }

  @Override
  public String holeText(String ort, String name) {
    String text ="";
    if (speichersystem == Speichersystem.DATEI) {
      Data data = new Dateiverwaltung(ort, name);
      text = data.holeAsciiString();
    // Datenbank => noch anpassen !!!!
    } else { 
      Data data = new Dateiverwaltung(ort, name);
      text = data.holeAsciiString();
    }
    return text;
  }
  
  @Override
  public void speichereLernset(String nameLernset, KahootSet kahootset) {
    //kahootset.zeigeKahootInhalt();  // Testausgabe    
    Data data = new Dateiverwaltung("Kahoot", "kahoot_", ".json");
    String sout = json.getJsonFromKahootSet(kahootset);
    //System.out.println(nameLernset); // Testausgabe
    System.out.println(sout); // Testausgabe
    data.speichereLernset(nameLernset, sout);
  }  

  @Override
  public void speichereStatistik(String nameUser, String statistik) {
    Data data = new Dateiverwaltung("statistik", "statistik_", ".txt");
    //System.out.println(statistik); // Testausgabe
    data.speichereStatistik(nameUser, statistik);
  }  
}
