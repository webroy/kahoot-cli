// ----------------------------------------------------------------------------
// AlleAntworten.java
// ----------------------------------------------------------------------------
// Funktion:   Erstellt aus den einzelnen Antworten zum Karten-Set Kahoot einen
//             Antworten-Container (Liste mit Antworten).
//             Über die Setter- und Getter-Methoden können auf die 
//             Informationen zugegriffen werden.
//             - public StatistikEinzelneAntwort getEinzelneAntwort(int index)
//             - public List<StatistikEinzelneAntwort> getListeAlleFragen()
//             Zusätzlich gibt es noch die Methoden:
//             - add(StatistikEinzelneFrage statistikFrage)
//             - public int size()
// 
// SW:         Apache NetBeans IDE 11.1 ; JDK-11
// Datum:      23.02.2020
// Autor:      A. Albisser; PSI; WBWA /038; Tel. ++41563103450
// ----------------------------------------------------------------------------
package ch.edualb.kahoot;

import java.util.ArrayList;
import java.util.List;

public class AlleAntworten {

  public List<Antwort> antwortenSet;

  // -------------------------------------------------------------------------- 
  // Konstruktor
  // Initialisiert alle Membervariablen bei der Objekterzeugung
  // --------------------------------------------------------------------------    
  public AlleAntworten() {
    antwortenSet = new ArrayList<>();
  }

  public AlleAntworten(AlleAntworten copy) {
    this.antwortenSet = copy.antwortenSet;
  }


  // -------------------------------------------------------------------------- 
  // Setter-Methoden 
  // Übergabewert wenn nötig prüfen und in Membervariable speichern
  // --------------------------------------------------------------------------   
  public void setListeAlleFragen(List<Antwort> fragenSet) {
    this.antwortenSet = fragenSet;
  }
  
  
  // -------------------------------------------------------------------------- 
  // Getter-Methoden 
  // Aktueller Wert aus den privaten Membervariablen lesen und dem Aufrufer 
  // zurückgeben
  // --------------------------------------------------------------------------    
  public Antwort getEinzelneAntwort(int index) {
    return antwortenSet.get(index);
  }
  
  public List<Antwort> getListeAlleAntworten() {
    return antwortenSet;
  }

  // -------------------------------------------------------------------------- 
  // Zusätzliche Methoden 
  // --------------------------------------------------------------------------  
  public void add(Antwort antwort) {
    antwortenSet.add(antwort);
  }
    
  public int size() {
    return antwortenSet.size();
  }
}