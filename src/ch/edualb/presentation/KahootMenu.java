// ----------------------------------------------------------------------------
// KahootMenu.java
// ----------------------------------------------------------------------------
// Funktion:   Im Menü Kahoot werden die verschiedene Untermenüs aufgeführt. Der 
//             Benutzer kann zwischen den folgenden Menüs wählen:
//             - Lernen mit den bestehenden Lerneinheiten
//             - Neue Lerneinheiten erstellen oder bestehende verändern
//             - Lern-Statistik anschauen
// 
// SW:         Apache NetBeans IDE 11.1 ; JDK-11
// Datum:      08.02.2020
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
import java.util.Random;

public class KahootMenu {
  public enum State {AUSWAHL_LERNEN_BEARBEITEN, AUSWAHL_LERNEINHEIT, 
                     LERNEN_AUSWAHL_AKTION, BEARBEITEN_AUSWAHL_AKTION, 
                     LERNEN_INFO_ANZEIGEN, LERNEN_FRAGEN_BEANTWORTEN, 
                     LERNSTATISTIK_ANZEIGEN, ZURUECK, ERROR, INITIALISIEREN};
  static public State state = State.INITIALISIEREN;
  private String dateiName;
  private final Logic logic = new LernPlattform();      // Logic ist Interface & LernPlattform Implementierung
  private KahootSet kahootset = new KahootSet();
  private List<String> keysInfo = new ArrayList<>();
  private List<String> keysFrage = new ArrayList<>();
  private LinkedHashMap<String, String> mapInfo = null;
  private List<Frage> fragen = null;
  private final Frage frage = null;  
  KahootBearbeiten bearbeiten = new KahootBearbeiten();
  KahootLernen lernen = new KahootLernen();
  Random randomno = new Random();
  
  public void startMenuKahoot(String spielerName) {  
    boolean exit = false;
    boolean nurBeimErstenMal = true;
    boolean neu = false;    
    
    do {      
      switch(state) {

        case INITIALISIEREN:
          exit = false;
          nurBeimErstenMal = true;
          neu = false;
          state = State.AUSWAHL_LERNEN_BEARBEITEN;
          break;

        case AUSWAHL_LERNEN_BEARBEITEN:
          state = waehleLernenBearbeiten();
          break;

        case AUSWAHL_LERNEINHEIT: 
          state = waehleLerneinheit();
          break;

        case LERNEN_AUSWAHL_AKTION: 
          state = waehleAktionLerneinheit();
          break;

        case LERNEN_INFO_ANZEIGEN:
          zeigeInfo(mapInfo, keysInfo);                
          state = State.LERNEN_AUSWAHL_AKTION;
          break;

        case LERNEN_FRAGEN_BEANTWORTEN:
          if (nurBeimErstenMal == true) {
            nurBeimErstenMal = false;
            if (sprache == Hauptmenu.Sprache.DEUTSCH) {
              System.out.println("\n\nHallo " +spielerName);
              System.out.println("Es sind " +kahootset.getAnzahlFragen() +" Fragen im Lern-Set \"" +dateiName +"\" enthalten");
              System.out.println("Die Fragen gehören zum Themengebiet: " +mapInfo.get(keysInfo.get(2)) +">" +mapInfo.get(keysInfo.get(3)) +">" +mapInfo.get(keysInfo.get(4)));              
            } else {
              System.out.println("\n\nHello " +spielerName);
              System.out.println("There are " +kahootset.getAnzahlFragen() +" questions in the set \"" +dateiName);
              System.out.println("The questions belong to the theme: " +mapInfo.get(keysInfo.get(2)) +">" +mapInfo.get(keysInfo.get(3)) +">" +mapInfo.get(keysInfo.get(4)));                            
            }
          }
          lernen.beantworteFragen(mapInfo, keysInfo, fragen, spielerName);
          state = State.LERNEN_AUSWAHL_AKTION;
          break;
                  
        case BEARBEITEN_AUSWAHL_AKTION: 
          switch (waehleBestehendNeu()) {
            case 1: neu = false; 
                    state = waehleLerneinheit(); // Rückgabe irrelevant
                    break;
            case 2: neu = true;
                    break;
            default: break;                     
          }
          kahootset = bearbeiten.start(kahootset, neu, dateiName);
          state =  State.AUSWAHL_LERNEN_BEARBEITEN;
          break;
          
        case ERROR:
          System.out.println("Error input menu q/a");
          break;

        case ZURUECK:
          exit = true;
          break;
          
        default:// Fehlermeldung bei ungültiger Meüwahl
          System.out.println("Error state menu q/a");
          break;
      }
    } while (exit == false);
  }   

  // **************************************************************************
  // **************************************************************************
  // Entscheidungs-Methoden => Auswahl & Eingabe (mit Fehlerprüfung)
  // Je nach Einstellung in Deutsch oder Englisch
  // **************************************************************************
  // **************************************************************************    
  public State waehleLerneinheit() {  
    List<String> kahootDateien = logic.holeListeLernset();
    Tastatur tastatur = new Tastatur();
    int zaehler;
    zeigeAuswahlLerneinheitenTitelStart();
    for (zaehler = 0; zaehler < kahootDateien.size(); zaehler++) {
      String dateiNameTmp = kahootDateien.get(zaehler);
      dateiNameTmp = dateiNameTmp.substring( 7, (dateiNameTmp.length() - 5)); // ohne kahoot_ und .json anzeigen
      System.out.println("< " +(zaehler + 1) +" >   " +dateiNameTmp);
    }                
    zeigeAuswahlLerneinheitenTitelStopp();
    int eingabe = tastatur.pruefeEingabe(0, (zaehler + 1));
    if (eingabe == 0) { // zurück
      return State.ZURUECK;
    } else { // Datei gewählt
      dateiName = kahootDateien.get(eingabe - 1);
      System.out.println(dateiName);  // Testausgabe
      kahootset = logic.holeLernset(dateiName);
      mapInfo = kahootset.getMapInfo();
      keysInfo = kahootset.getMapInfoKeyNames();       
      fragen = kahootset.getAlleFragen();
      keysFrage = kahootset.getFrageKeyNames();       
      return State.LERNEN_AUSWAHL_AKTION;
    }
  }

  public State waehleLernenBearbeiten() {  
    if (sprache == Hauptmenu.Sprache.DEUTSCH) {
      KahootMenuTextDeutsch deutsch = new KahootMenuTextDeutsch();
      deutsch.zeigeAuswahlLernenOderBearbeiten();
    } else {
      KahootMenuTextEnglisch englisch = new KahootMenuTextEnglisch();
      englisch.zeigeAuswahlLernenOderBearbeiten();
    }
    Tastatur tastatur = new Tastatur();
    switch (tastatur.pruefeEingabe(0, 3)) {
      case 0: return State.ZURUECK;
      case 1: return State.AUSWAHL_LERNEINHEIT;
      case 2: return State.BEARBEITEN_AUSWAHL_AKTION;
    }
    return State.ERROR;
  }
  
  public State waehleAktionLerneinheit() {  
    if (sprache == Hauptmenu.Sprache.DEUTSCH) {
      KahootMenuTextDeutsch deutsch = new KahootMenuTextDeutsch();
      deutsch.zeigeAuswahlInfoLernenNeuZurueck();
    } else {
      KahootMenuTextEnglisch englisch = new KahootMenuTextEnglisch();
      englisch.zeigeAuswahlInfoLernenNeuZurueck();
    }
    Tastatur tastatur = new Tastatur();
    switch (tastatur.pruefeEingabe(0, 4)) {
      case 0: return State.AUSWAHL_LERNEN_BEARBEITEN;
      case 1: return State.LERNEN_INFO_ANZEIGEN;
      case 2: return State.LERNEN_FRAGEN_BEANTWORTEN;
      case 3: return State.AUSWAHL_LERNEINHEIT;
    }
    return State.ERROR;
  }
 
  public int waehleBestehendNeu() {  
    if (sprache == Hauptmenu.Sprache.DEUTSCH) {
      KahootMenuTextDeutsch deutsch = new KahootMenuTextDeutsch();
      deutsch.zeigeAuswahlBestehendOderNeu();
    } else {
      KahootMenuTextEnglisch englisch = new KahootMenuTextEnglisch();
      englisch.zeigeAuswahlBestehendOderNeu();
    }
    Tastatur tastatur = new Tastatur();
    return tastatur.pruefeEingabe(0, 3);
  }  

  
  // **************************************************************************
  // **************************************************************************
  // Text-Anzeige-Methoden je nach Einstellung in Deutsch oder Englisch
  // **************************************************************************
  // **************************************************************************  
  public void zeigeAuswahlLerneinheitenTitelStart() {    
    if (sprache == Hauptmenu.Sprache.DEUTSCH) {
      KahootMenuTextDeutsch deutsch = new KahootMenuTextDeutsch();
      deutsch.zeigeAuswahlTitelStart();
    } else {
      KahootMenuTextEnglisch englisch = new KahootMenuTextEnglisch();
      englisch.zeigeAuswahlTitelStart();
    }
  }

  public void zeigeAuswahlLerneinheitenTitelStopp() {    
    if (sprache == Hauptmenu.Sprache.DEUTSCH) {
      KahootMenuTextDeutsch deutsch = new KahootMenuTextDeutsch();
      deutsch.zeigeAuswahlTitelStopp();
    } else {
      KahootMenuTextEnglisch englisch = new KahootMenuTextEnglisch();
      englisch.zeigeAuswahlTitelStopp();
    }
  }
  
  public void zeigeInfo(LinkedHashMap<String, String> mapInfo, List<String> keys) {
    if (sprache == Hauptmenu.Sprache.DEUTSCH) {
      KahootMenuTextDeutsch deutsch = new KahootMenuTextDeutsch();
      deutsch.zeigeInfo(mapInfo, keys);
    } else {
      KahootMenuTextEnglisch englisch = new KahootMenuTextEnglisch();    
      englisch.zeigeInfo(mapInfo, keys);
    }
    Tastatur tastatur = new Tastatur();
    tastatur.warteBisGelesen();
  }  
}
