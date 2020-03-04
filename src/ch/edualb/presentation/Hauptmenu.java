// ----------------------------------------------------------------------------
// Hauptmenu.java
// ----------------------------------------------------------------------------
// Funktion:   Im Hauptmenü werden die verschiedene Untermenüs aufgeführt. Der 
//             Benutzer kann zwischen den folgenden Menüs wählen:
//             - Lernen à la Kahoot
//             - Lernen/Spielen à la Quartett
//             - Informationen zum Programm selbst 
//             - Sprachauswahl Deutsch / Englisch
// 
// SW:         Apache NetBeans IDE 11.1 ; JDK-11
// Datum:      02.02.2020
// Autor:      A. Albisser; PSI; WBWA /038; Tel. ++41563103450
// ----------------------------------------------------------------------------
package ch.edualb.presentation;

import ch.edualb.logic.LernPlattform;
import ch.edualb.logic.Logic;
import static ch.edualb.presentation.KahootMenu.state;
import java.util.Scanner;

public class Hauptmenu {

  public enum Speicher {DATEI, DATENBANK};  
  static public Speicher speicher = Speicher.DATEI;
  public enum Sprache {DEUTSCH, ENGLISCH};  
  static public Sprache sprache = Sprache.DEUTSCH;  
  private String spielerName;  
  
  public void start() {
    Logic logic = new LernPlattform();      // Logic ist Interface & LernPlattform Implementierung
    final int anzahlMenu = 8;
    int subMenu;

    Tastatur tastatur = new Tastatur();
    KahootMenu kahoot = new KahootMenu();
    
    frageBeimProgrammstartSpracheUndName();

    if (sprache == Hauptmenu.Sprache.DEUTSCH) {
      System.out.println(logic.holeText("ascii_pictures", "ascii_willkommen.txt"));
    } else {
      System.out.println(logic.holeText("ascii_pictures", "ascii_welcome.txt"));
    }
    System.out.println(spielerName);

    zeigeHauptmenuStart();    
    do {
      subMenu = tastatur.pruefeEingabe(0, anzahlMenu);
      switch(subMenu) {

        case 0: // Programm beenden
                if (sprache == Hauptmenu.Sprache.DEUTSCH) {
                  System.out.println(logic.holeText("ascii_pictures", "ascii_aufwiedersehen.txt"));
                } else {
                  System.out.println(logic.holeText("ascii_pictures", "ascii_goodbye.txt"));
                }
                System.out.println(spielerName);    
                break;

        case 1: // Kahoot
                state = KahootMenu.State.INITIALISIEREN;
                kahoot.startMenuKahoot(spielerName);
                zeigeHauptmenuStart();
                break;

        case 2: // Quartett
                System.out.println("Soon come");
                zeigeHauptmenuStart();
                break;

        case 3: // Spracheinstellung D/E 
                zeigeSpeichermedienEinstellung();
                zeigeHauptmenuStart();
                break;

        case 4: // Spracheinstellung D/E 
                zeigeEinstellungSprache();
                zeigeHauptmenuStart();
                break;

        case 5: // Name eingeben
                bearbeiteSpielerName();
                zeigeHauptmenuStart();
                break;

        case 6: // Informationen zu den einezlnen Lerneinheiten
                zeigeInfoLernsysteme();
                tastatur.warteBisGelesen();
                zeigeHauptmenuStart();
                break;

        case 7: // Informationen zum Programm selbst
                zeigeInfoProgramm();
                tastatur.warteBisGelesen();
                zeigeHauptmenuStart();
                break;

        default:// Fehlermeldung bei ungültiger Meüwahl
                System.out.println("Unexpectet input main menu");
                tastatur.warteBisGelesen();
                zeigeHauptmenuStart();                
                break;
      }
    } while (subMenu != 0);    
  }   
  
  public void zeigeHauptmenuStart() {  
    if (sprache == Sprache.DEUTSCH) {
      MenuAllgemeinTextDeutsch deutsch = new MenuAllgemeinTextDeutsch();      
      deutsch.zeigeHauptmenuStart();
    } else {
      MenuAllgemeinTextEnglisch englisch = new MenuAllgemeinTextEnglisch();      
      englisch.zeigeHauptmenuStart();
    }
  }
  
  public void zeigeSpeichermedienEinstellung() {  
    int eingabe;
    Tastatur tastatur = new Tastatur();
    do {
      if (sprache == Sprache.DEUTSCH) {
        MenuAllgemeinTextDeutsch deutsch = new MenuAllgemeinTextDeutsch();        
        deutsch.zeigeEinstellungSpeichermedien();
      } else {
        MenuAllgemeinTextEnglisch englisch = new MenuAllgemeinTextEnglisch();        
        englisch.zeigeEinstellungSpeichermedien();
      }
      eingabe = tastatur.pruefeEingabe(0, 3);
      if (eingabe == 1) {
        speicher = Speicher.DATEI;
      } else if (eingabe == 2){
        speicher = Speicher.DATENBANK;        
      }
    } while (eingabe != 0);
  }
  
  public void zeigeEinstellungSprache() {  
    int eingabe;
    Tastatur tastatur = new Tastatur();
    do {
      if (sprache == Sprache.DEUTSCH) {
        MenuAllgemeinTextDeutsch deutsch = new MenuAllgemeinTextDeutsch();        
        deutsch.zeigeEinstellungSprache();
      } else {
        MenuAllgemeinTextEnglisch englisch = new MenuAllgemeinTextEnglisch();        
        englisch.zeigeEinstellungSprache();
      }
      eingabe = tastatur.pruefeEingabe(0, 3);
      if (eingabe == 1) {
        sprache = Sprache.DEUTSCH;
      } else if (eingabe == 2){
        sprache = Sprache.ENGLISCH;        
      }
    } while (eingabe != 0);
  }

  public void zeigeInfoLernsysteme() {  
    if (sprache == Sprache.DEUTSCH) {
      MenuAllgemeinTextDeutsch deutsch = new MenuAllgemeinTextDeutsch();      
      deutsch.zeigeInfoLernsysteme();
    } else {
      MenuAllgemeinTextEnglisch englisch = new MenuAllgemeinTextEnglisch();      
      englisch.zeigeInfoLernsysteme();
    }
  }
  
  public void zeigeInfoProgramm() {  
    if (sprache == Sprache.DEUTSCH) {
      MenuAllgemeinTextDeutsch deutsch = new MenuAllgemeinTextDeutsch();      
      deutsch.zeigeInfoProgramm();
    } else {
      MenuAllgemeinTextEnglisch englisch = new MenuAllgemeinTextEnglisch();      
      englisch.zeigeInfoProgramm();
    }
  }

  public void frageBeimProgrammstartSpracheUndName() {
    System.out.println("Bitte waehlen Sie ihr bevorzugte Sprache (Zahl eingeben) / please choose your preferred language (enter the number)");
    System.out.println("< 1 > Deutsch");     
    System.out.println("< 2 > English");     
    Tastatur tastaturZahl = new Tastatur();
    int eingabe = tastaturZahl.pruefeEingabe(1, 3);
    if (eingabe == 1) {
      sprache = Sprache.DEUTSCH;
      System.out.println("Bitte geben Sie ihren Namen ein");     
    } else if (eingabe == 2){
      sprache = Sprache.ENGLISCH;        
      System.out.println("Please enter your name");           
    }    
    Scanner tastatur = new Scanner(System.in);
    String input = tastatur.nextLine();
    if (input.length() > 0) {         // Nur wenn etwas eingegeben wurde
      spielerName = input;
    } else {
      spielerName = "Anonymous";
    }
//    if (sprache == Sprache.DEUTSCH) {
//      System.out.println("\nHallo " +spielerName);      
//    } else {
//      System.out.println("\nHello " +spielerName);      
//    }    
  }      
  
  public void bearbeiteSpielerName() {
    if (sprache == Hauptmenu.Sprache.DEUTSCH) {
      MenuAllgemeinTextDeutsch deutsch = new MenuAllgemeinTextDeutsch();
      deutsch.zeigeNameSpielerEingeben();
    } else {
      MenuAllgemeinTextEnglisch englisch = new MenuAllgemeinTextEnglisch();    
      englisch.zeigeNameSpielerEingeben();
    }
    Scanner tastatur = new Scanner(System.in);
    String input = tastatur.nextLine();
    if (input.length() > 0) {         // Nur wenn etwas eingegeben wurde
      spielerName = input;
    } else {
      spielerName = "Anonymous";
    }
    //System.out.println("\n Hallo " +spielerName);  //Testausgabe
  }      
}
  