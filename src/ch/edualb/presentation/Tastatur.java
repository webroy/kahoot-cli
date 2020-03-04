// ----------------------------------------------------------------------------
// Tastatur.java
// ----------------------------------------------------------------------------
// Funktion:   Stellt Eingabe-Methoden für die unterschiedlichen Menüs zur 
//             Verfügung. 
// 
// SW:         Apache NetBeans IDE 11.1 ; JDK-11
// Datum:      30.01.2020
// Autor:      A. Albisser; PSI; WBWA /038; Tel. ++41563103450
// ----------------------------------------------------------------------------
package ch.edualb.presentation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tastatur {

  private final String trennZeichen = "=";
  
  public int pruefeEingabe(int min, int max) {
    BufferedReader inputTastatur = new BufferedReader(new InputStreamReader(System.in));
    int tastaturEingabe;
    String tmp;
    String tmp1;
    boolean stopp = false;

    // Eingabe für Moduswahl auf Gültigkeit prüfen
    do {
      tastaturEingabe = -1;               // Eingabe vor Prüfung als ungültig erklären
      try {  
        tmp = inputTastatur.readLine();   // Als String einlesen (alles bis Enter)
        
        // Prüfen ob es eine gültige Eingabe ist => Int zwischen =min .. < max
        // Egal ob Zahl, Buchstaben oder Wörter eingegeben werden, soll Programm
        // funktionieren, es kommt einfach Meldung "Ungültige Eingabe".
        for (int i = min; i < max; i++) { // Für gültige Menüs prüfen
          tmp1 = Integer.toString(i);     // Zähler zum Vergleich in String umwandeln
          if (tmp1.equals(tmp)) {         // Bei Übereinstimmung beider Strings
            tastaturEingabe = i;          // Eingabe akzeptieren
            stopp = true;
          }
        }
        if (stopp == false) {
          zeigeUngueltigeEingabe();
        }
        //inputTastatur.close();        
      }
      catch (IOException e) {
        System.out.println("IOException bei der Tastatureingabe");
      }
    } while (stopp == false);
    return tastaturEingabe;
  }

  public void warteBisGelesen() {
    BufferedReader inputTastatur = new BufferedReader(new InputStreamReader(System.in));
    String tmp;

    zeigeFertigGelesen();
    try {  
      tmp = inputTastatur.readLine();
      //inputTastatur.close();
    }
    catch (IOException e) {
      System.out.println("IOException bei der Tastatureingabe");
    }
  }

  public boolean istTrennzeichenGueltig(String eingabe) {
    boolean antwort = false;
    if (eingabe.contains(trennZeichen)) {
      int index = eingabe.indexOf(trennZeichen,0);
      if ((index > 0) && (index < (eingabe.length() - 1))) { // Nur wenn vor & nach = etwas eingegeben wurde
        antwort = true;
      }
    }
    return antwort;
  }

  public int getEingabeSchluesselZahl(String eingabe, int min, int max) {
    int wert = -1;
    String [] elemente;        
    elemente = eingabe.split(trennZeichen);
    String tmp;
    for (int i = min; i < max; i++) {       // Für gültige Zahl prüfen
      tmp = Integer.toString(i);            // Zähler zum Vergleich in String umwandeln
      if (tmp.equals(elemente[0].trim())) { // Bei Übereinstimmung beider Strings
        wert = i;                           // Eingabe akzeptieren
      }
    }
    return wert;
  }  

  public String getEingabeSchluesselText(String eingabe) {
    String [] elemente;        
    elemente = eingabe.split(trennZeichen);
    String wert = elemente[0].trim();    
    return wert;
  }  

  public int getEingabeWertZahl(String eingabe, int min, int max) {
    int wert = 0;
    String [] elemente;        
    elemente = eingabe.split(trennZeichen);
    String tmp;
    for (int i = min; i < max; i++) {       // Für gültige Zahl prüfen
      tmp = Integer.toString(i);            // Zähler zum Vergleich in String umwandeln
      if (tmp.equals(elemente[1].trim())) { // Bei Übereinstimmung beider Strings
        wert = i;                           // Eingabe akzeptieren
      }
    }    
    return wert;
  }  
  
  public String getEingabeWertText(String eingabe) {
    String [] elemente;        
    elemente = eingabe.split(trennZeichen);
    int length = elemente[1].length();
    if (length > 0) {         // Nur wenn nach = etwas eingegeben wurde
      StringBuilder wert = new StringBuilder(length);
      for (char c: elemente[1].toCharArray()) {
        if (!((c == '\\') || (c == '{') || (c == '}') || (c == '[') || (c == ']') || (c == '\"'))) {     // Zeichen, die ignoriert werden        
          wert.append(c);
        } else {
          zeigeUnerlaubtesZeichen(c);
          //System.out.println(c +"ist ein unerlaubtes Zeichen und wurde ignoriert");
        }
      }
      //System.out.println(wert);  //Testausgabe
      return wert.toString();
    }
    return "";
  }  
  
  public void zeigeUngueltigeEingabe() {  
    if (Hauptmenu.sprache == Hauptmenu.Sprache.DEUTSCH) {
      MenuAllgemeinTextDeutsch deutsch = new MenuAllgemeinTextDeutsch();
      deutsch.zeigeUngueltigeEingabe();
    } else {
      MenuAllgemeinTextEnglisch englisch = new MenuAllgemeinTextEnglisch();
      englisch.zeigeUngueltigeEingabe();
    }
  }

  public void zeigeFertigGelesen() {  
    if (Hauptmenu.sprache == Hauptmenu.Sprache.DEUTSCH) {
      MenuAllgemeinTextDeutsch deutsch = new MenuAllgemeinTextDeutsch();
      deutsch.zeigeFertigGelesen();
    } else {
      MenuAllgemeinTextEnglisch englisch = new MenuAllgemeinTextEnglisch();
      englisch.zeigeFertigGelesen();
    }
  }

  public void zeigeUnerlaubtesZeichen(char c) {  
    if (Hauptmenu.sprache == Hauptmenu.Sprache.DEUTSCH) {
      MenuAllgemeinTextDeutsch deutsch = new MenuAllgemeinTextDeutsch();
      deutsch.zeigeUnerlaubtesZeichen(c);
    } else {
      MenuAllgemeinTextEnglisch englisch = new MenuAllgemeinTextEnglisch();
      englisch.zeigeUnerlaubtesZeichen(c);
    }
  }  
}
