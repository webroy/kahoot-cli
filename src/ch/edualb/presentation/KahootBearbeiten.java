// ----------------------------------------------------------------------------
// KahootBearbeiten.java
// ----------------------------------------------------------------------------
// Funktion:   
// 
// SW:         Apache NetBeans IDE 11.1 ; JDK-11
// Datum:      16.02.2020
// Autor:      A. Albisser; PSI; WBWA /038; Tel. ++41563103450
// ----------------------------------------------------------------------------
package ch.edualb.presentation;

import ch.edualb.kahoot.Frage;
import ch.edualb.kahoot.KahootSet;
import ch.edualb.logic.LernPlattform;
import ch.edualb.logic.Logic;
import static ch.edualb.presentation.Hauptmenu.sprache;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

public class KahootBearbeiten {

  private final Logic logic = new LernPlattform();      // Logic ist Interface & LernPlattform Implementierung
  private KahootSet kahootsetNeu;
  private LinkedHashMap<String, String> mapInfoNew = null;  
  private List<String> keysInfo = new ArrayList<>();
  private List<Frage> fragenNeu = null;  
  public enum State {BEARBEITEN_AUSWAHL_AKTION, BEARBEITEN_AUSWAHL_INFO_FRAGEN, 
                     BEARBEITEN_INFO, BEARBETEN_FRAGEN,
                     LERNSTATISTIK_ANZEIGEN, SPEICHERN, ZURUECK, ERROR};
  static public State state;
  private boolean frageSpeichern = false;
  private boolean speichernExit = false;


  public KahootSet start(KahootSet kahootset, boolean neu, String dateiName) {
    boolean exit = false;
    if (neu) {
      this.kahootsetNeu = new KahootSet();
    } else {
      this.kahootsetNeu = new KahootSet(kahootset);
    }
    mapInfoNew = kahootsetNeu.getMapInfo();
    keysInfo = kahootsetNeu.getMapInfoKeyNames();       
    fragenNeu = kahootsetNeu.getAlleFragen();
    state = State.BEARBEITEN_AUSWAHL_INFO_FRAGEN;
    
    do {      
      switch(state) {
        case BEARBEITEN_AUSWAHL_INFO_FRAGEN: 
          state = waehleBearbeitenInfoFrage();
          break;

        case BEARBEITEN_INFO: 
          mapInfoNew = bearbeiteInfo(mapInfoNew, keysInfo);
          state = State.BEARBEITEN_AUSWAHL_INFO_FRAGEN;
          break;

        case BEARBETEN_FRAGEN:
          fragenNeu  = bearbeiteFrage(kahootsetNeu, fragenNeu);
          state = State.BEARBEITEN_AUSWAHL_INFO_FRAGEN;
          break;

        case SPEICHERN:
          if (frageSpeichern == true) {
            switch (waehleSpeichernJaNein(dateiName)) {
              case 0: // Änderungen speichern
                System.out.println("Es wurde nichts gespeichert");          
                break;
              case 2: // Änderungen speichern unter neuem Dateiname
                dateiName = bearbeiteDateiName();
              case 1: // Änderungen speichern
                kahootsetNeu.setMapInfo(mapInfoNew);
                kahootsetNeu.setAlleFragen(fragenNeu);
                kahootsetNeu.nummeriereIdNeu();
                //kahootsetNeu.zeigeKahootInhalt();  // Testausgabe
                logic.speichereLernset(dateiName, kahootsetNeu);                                
                System.out.println("Die Daten wurden in " +dateiName +" gespeichert");
                frageSpeichern = false;
                break;
              default:// Fehlermeldung bei ungültiger Meüwahl
                System.out.println("Nicht abgefangener Speicherentscheid-Fehler?!");
                break;
            } 
          }
          if (speichernExit == true) {
            speichernExit = false;
            exit = true;
          } else {
            state = State.BEARBEITEN_AUSWAHL_INFO_FRAGEN;          
          }
          break;

        case ZURUECK:
          if (frageSpeichern == true) { // Falls Änderungen erfolgt sind, aber noch nicht gespeichert wurden
            state = State.SPEICHERN;
            speichernExit = true;
          } else {
            exit = true;
          }
          break;

        case ERROR:
          System.out.println("Nicht abgefangener Eingabefehler?!");
          break;
      }
    } while (exit == false);
    return kahootset;
  }   
  
  public LinkedHashMap<String, String> bearbeiteInfo(LinkedHashMap<String, String> mapInfoNew, List<String> keys) {
    Tastatur tastatur = new Tastatur();
    Scanner is = new Scanner(System.in);
    String eingabe;
    int index;
    boolean stopp = false;    
    do {
      zeigeInfoBearbeiten(mapInfoNew, keys);      
      eingabe = is.nextLine();      
      
      // Command Beenden und zurück zum Aufrufer (Wert 0) 
      if (eingabe.indexOf("0",0) == 0) {  // Zurück
        stopp = true;
        
      // Inhalt bearbeiten 
      } else {
        frageSpeichern = true;            // Flag setzen für Speicheraufforderung
        if (tastatur.istTrennzeichenGueltig(eingabe)) {
          int nr = tastatur.getEingabeSchluesselZahl(eingabe, 1, 9);
          if (nr >= 0) {  // Nur wenn eine gültige Eingabe
            String textNeu = tastatur.getEingabeWertText(eingabe);
            System.out.println(textNeu);  //Testausgabe
            mapInfoNew.put(keys.get(nr - 1), textNeu);
          }
        }
      }
    } while (stopp == false);
    return mapInfoNew;
  }    
  
  public List<Frage> bearbeiteFrage(KahootSet kahootset, List<Frage> fragen) {
    Tastatur tastatur = new Tastatur();
    Scanner is = new Scanner(System.in);
    String eingabe;
    boolean stopp = false;    
    int index;
    int aktuelleFrage = 0;
    int anzahlFragen = fragen.size();
    Frage frageEmpty = new Frage();
    if (anzahlFragen == 0) {              // Sollte es noch keine Frage geben, 1 Frage hinzufügen
      fragen.add(0, frageEmpty);
      anzahlFragen = fragen.size();
    }
    do {
      zeigeFrageBearbeiten(fragen.get(aktuelleFrage), aktuelleFrage, anzahlFragen);      
      eingabe = is.nextLine();
      Frage frageNew = new Frage();
      if (eingabe.length() >= 1){           // Überhaupt ein Zeichen eingegeben?

        // Command Beenden und zurück zum Aufrufer (Wert 0) 
        if (eingabe.indexOf("0",0) == 0) {  // Zurück
          stopp = true;
        } else {
          frageSpeichern = true;            // Flag setzen für Speicheraufforderung
          eingabe = eingabe.trim();

          // Command Delete => aktuelle Frage löschen, sofern noch eine übrig ist
          if (eingabe.toLowerCase().startsWith("delete", 0)) {
            //System.out.println("Delete"); // Testausgabe
            if (anzahlFragen > 0) {
              anzahlFragen = kahootsetNeu.loescheEinzelneFrage(aktuelleFrage);             
              if (aktuelleFrage >= anzahlFragen) { // Falls letzte Frage gelöscht 
                aktuelleFrage--;
              }
            } else {
              System.out.println("Es sind schon alle Fragen gelöscht");
            }
                      
          // Command Copy => Wenn gültige Nr., dann aktuelle Frage an neue 
          // Position kopieren
          } else if (eingabe.toLowerCase().startsWith("copy", 0)) {
            //System.out.println("Copy"); // Testausgabe
            if (tastatur.istTrennzeichenGueltig(eingabe)) {
              //System.out.println("Trennzeichen = gefunden"); // Testausgabe
              int nr = tastatur.getEingabeWertZahl(eingabe, 1, (anzahlFragen + 2));
              if (nr >= 0) {  // Nur wenn eine gültige Eingabe
                Frage frageCopy = new Frage (fragen.get(aktuelleFrage));
                if (nr < anzahlFragen) {
                  fragen.add(nr, frageCopy);
                } else {
                  fragen.add(frageCopy);
                }
                anzahlFragen++;
                aktuelleFrage = nr - 1;
                fragen.get(nr - 1).setId(nr - 1);
              } else {
                System.out.println("Keine gültiger Zahlenwert "); // Testausgabe
              }
            } else {
              System.out.println("Kein Trennzeichen = gefunden, bzw. nichts davor oder dahinter"); // Testausgabe
            }

          // Command Insert => Wenn gültige Nr., dann aktuelle neue leere Frage  
          // an neuer Position einfügen
          } else if (eingabe.toLowerCase().startsWith("insert", 0)) {
            //System.out.println("Insert"); // Testausgabe
            if (tastatur.istTrennzeichenGueltig(eingabe)) {
              //System.out.println("Trennzeichen = gefunden"); // Testausgabe
              int nr = tastatur.getEingabeWertZahl(eingabe, 1, (anzahlFragen + 2));
              if (nr >= 0) {  // Nur wenn eine gültige Eingabe
                fragen.add(frageNew);
                anzahlFragen++;
                aktuelleFrage = nr - 1;
                fragen.get(nr - 1).setId(nr - 1);
              } else {
                System.out.println("Keine gültiger Zahlenwert "); // Testausgabe
              }
            } else {
              System.out.println("Kein Trennzeichen = gefunden, bzw. nichts davor oder dahinter"); // Testausgabe
            }
            
          // Command Edit => Wenn gültige Nr., dann gewünschte Frage zum  
          // bearbeiten öffnen
          } else if (eingabe.toLowerCase().startsWith("edit", 0)) {
            //System.out.println("Edit"); // Testausgabe
            if (tastatur.istTrennzeichenGueltig(eingabe)) {
              //System.out.println("Trennzeichen = gefunden"); // Testausgabe
              int nr = tastatur.getEingabeWertZahl(eingabe, 1, (anzahlFragen + 1));
              if (nr > 0) {  // Nur wenn eine gültige Eingabe
                aktuelleFrage = nr - 1; 
              } else {
                System.out.println("Keine gültiger Zahlenwert "); // Testausgabe
              }
            } else {
              System.out.println("Kein Trennzeichen = gefunden, bzw. nichts davor oder dahinter"); // Testausgabe
            }
          
          // Inhalte nach Zahlen-Schlüssel bearbeiten
          } else {
            //System.out.println("Werte bearbeiten"); // Testausgabe
            frageNew = editiereEinzelneFrage(fragen.get(aktuelleFrage), eingabe);
            if (frageNew != null) {
              fragen.set(aktuelleFrage, frageNew);
            } else {
              System.out.println("Probleme beim Editieren der Frage => kein Wert zugewiesen"); // Testausgabe
            }
          }
        }
      } else {
        System.out.println("Mehr Input bitte");
      }
    } while (stopp == false);
    return fragen;
  }

  private Frage editiereEinzelneFrage(Frage frageEdit, String eingabe) {  
    Tastatur tastatur = new Tastatur();
    if (tastatur.istTrennzeichenGueltig(eingabe)) {
      int nr = tastatur.getEingabeSchluesselZahl(eingabe, 1, 15);
      //System.out.println("Nr = " +nr); // Testausgabe
      switch (nr) {
        case 1: break;  // ID ignorieren, da automatisch
        case 2: frageEdit.setFrage(tastatur.getEingabeWertText(eingabe)); break;
        case 3: frageEdit.setLevel(tastatur.getEingabeWertZahl(eingabe, 0, 10)); break;
        case 4: frageEdit.setNameFoto(tastatur.getEingabeWertText(eingabe)); break;
        case 5: frageEdit.setPfadFoto(tastatur.getEingabeWertText(eingabe)); break;
        case 6: frageEdit.setCopyright(tastatur.getEingabeWertText(eingabe)); break;
        case 7: frageEdit.setAntwort1(tastatur.getEingabeWertText(eingabe)); break;
        case 8: frageEdit.setA1Korrekt(tastatur.getEingabeWertZahl(eingabe, 0, 2)); break;
        case 9: frageEdit.setAntwort2(tastatur.getEingabeWertText(eingabe)); break;
        case 10: frageEdit.setA2Korrekt(tastatur.getEingabeWertZahl(eingabe, 0, 2)); break;
        case 11: frageEdit.setAntwort3(tastatur.getEingabeWertText(eingabe)); break;
        case 12: frageEdit.setA3Korrekt(tastatur.getEingabeWertZahl(eingabe, 0, 2)); break;
        case 13: frageEdit.setAntwort4(tastatur.getEingabeWertText(eingabe)); break;
        case 14: frageEdit.setA4Korrekt(tastatur.getEingabeWertZahl(eingabe, 0, 2)); break;
        default: System.out.println("Keine gültiger Schlüssel"); break; // Testausgabe
      }
      return frageEdit;
    } else {
      System.out.println("Kein Trennzeichen = gefunden, bzw. nichts davor oder dahinter"); // Testausgabe
      return null;    
    }
  }
 
  public State waehleBearbeitenInfoFrage() {  
    if (sprache == Hauptmenu.Sprache.DEUTSCH) {
      KahootMenuTextDeutsch deutsch = new KahootMenuTextDeutsch();
      deutsch.zeigeAuswahlBearbeitenInfoFrageZurueck();
    } else {
      KahootMenuTextEnglisch englisch = new KahootMenuTextEnglisch();
      englisch.zeigeAuswahlBearbeitenInfoFrageZurueck();
    }
    Tastatur tastatur = new Tastatur();
    switch (tastatur.pruefeEingabe(0, 4)) {
      case 0: return State.ZURUECK;
      case 1: return State.BEARBEITEN_INFO;
      case 2: return State.BEARBETEN_FRAGEN;
      case 3: return State.SPEICHERN;      
    }
    return State.ERROR;
  }  

  public String bearbeiteDateiName() {
    if (sprache == Hauptmenu.Sprache.DEUTSCH) {
      KahootMenuTextDeutsch deutsch = new KahootMenuTextDeutsch();
      deutsch.zeigeNameDateiEingeben();
    } else {
      KahootMenuTextEnglisch englisch = new KahootMenuTextEnglisch();    
      englisch.zeigeNameDateiEingeben();
    }
    String dateiName = "";
    boolean stopp = false;
    do {
      Scanner tastatur = new Scanner(System.in);
      String input = tastatur.nextLine();
      int length = input.length();
      if (length > 0) {         // Nur wenn nach = etwas eingegeben wurde
        StringBuilder value = new StringBuilder(length);
        value.append("kahoot_");
        for (char c: input.toCharArray()) {
          if (!((c == '\\') || (c == '/') || (c == '\"') || (c == ':') || (c == '*') || (c == '?') || (c == '<') || (c == '>') || (c == '|'))) {     // Zeichen, die ignoriert werden        
            value.append(c);
          } else {
            System.out.println(c +"ist ein unerlaubtes Zeichen und wurde ignoriert");
          }
        }
        value.append(".json");
        dateiName = value.toString();
        System.out.println(dateiName);  //Testausgabe
        stopp = true;
      } else {
        System.out.println("Ein Name muss Zeichen haben");
      }
    } while (stopp == false);      
    return dateiName;
  }

  public int waehleSpeichernJaNein(String dateiName) {
    if (sprache == Hauptmenu.Sprache.DEUTSCH) {
      KahootMenuTextDeutsch deutsch = new KahootMenuTextDeutsch();
      deutsch.zeigeSpeichernJaNein(dateiName);
    } else {
      KahootMenuTextEnglisch englisch = new KahootMenuTextEnglisch();    
      englisch.zeigeSpeichernJaNein(dateiName);
    }
    Tastatur tastatur = new Tastatur();
    return tastatur.pruefeEingabe(0, 3);
  }    

  public void zeigeInfoBearbeiten(LinkedHashMap<String, String> mapInfo, List<String> keys) {
    if (sprache == Hauptmenu.Sprache.DEUTSCH) {
      KahootMenuTextDeutsch deutsch = new KahootMenuTextDeutsch();
      deutsch.zeigeInfoBearbeiten(mapInfo, keys);
    } else {
      KahootMenuTextEnglisch englisch = new KahootMenuTextEnglisch();
      englisch.zeigeInfoBearbeiten(mapInfo, keys);
    }
  }  

  public void zeigeFrageBearbeiten(Frage frage, int nr, int total) {
    if (sprache == Hauptmenu.Sprache.DEUTSCH) {
      KahootMenuTextDeutsch deutsch = new KahootMenuTextDeutsch();
      deutsch.zeigeFrageBearbeiten(frage, nr, total);
    } else {
      KahootMenuTextEnglisch englisch = new KahootMenuTextEnglisch();
      englisch.zeigeFrageBearbeiten(frage, nr, total);
    }
  }  
  
}
