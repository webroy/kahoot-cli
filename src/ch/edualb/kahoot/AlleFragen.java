// ----------------------------------------------------------------------------
// AlleFragen.java
// ----------------------------------------------------------------------------
// Funktion:   Erstellt aus den einzelnen Fragen zum Karten-Set Kahoot einen
//             Fragen-Container (Liste mit Fragen).
//             Über die Setter- und Getter-Methoden können auf die 
//             Informationen zugegriffen werden.
//             - public Frage getEinzelneFrage(int index)
//             - public List<Frage> getListeAlleFragen()
//             - public void add(Frage kahootFrage)
//             Zusätzlich gibt es noch die Methoden:
//             - add(Frage kahootFrage)
//             - loescheEinzelneFrage(int index)
//             - public int size()
// 
// SW:         Apache NetBeans IDE 11.1 ; JDK-11
// Datum:      26.01.2020
// Autor:      A. Albisser; PSI; WBWA /038; Tel. ++41563103450
// ----------------------------------------------------------------------------
package ch.edualb.kahoot;

import java.util.ArrayList;
import java.util.List;

public class AlleFragen {

  public List<Frage> fragenSet;

  // -------------------------------------------------------------------------- 
  // Konstruktor
  // Initialisiert alle Membervariablen bei der Objekterzeugung
  // --------------------------------------------------------------------------    
  public AlleFragen() {
    fragenSet = new ArrayList<>();
  }

  public AlleFragen(AlleFragen copy) {
    this.fragenSet = copy.fragenSet;
  }


  // -------------------------------------------------------------------------- 
  // Setter-Methoden 
  // Übergabewert wenn nötig prüfen und in Membervariable speichern
  // --------------------------------------------------------------------------   
  public void setListeAlleFragen(List<Frage> fragenSet) {
    this.fragenSet = fragenSet;
  }
  
  
  // -------------------------------------------------------------------------- 
  // Getter-Methoden 
  // Aktueller Wert aus den privaten Membervariablen lesen und dem Aufrufer 
  // zurückgeben
  // --------------------------------------------------------------------------    
  public Frage getEinzelneFrage(int index) {
    return fragenSet.get(index);
  }
  
  public List<Frage> getListeAlleFragen() {
    return fragenSet;
  }

  // -------------------------------------------------------------------------- 
  // Zusätzliche Methoden 
  // --------------------------------------------------------------------------  
  public void add(Frage kahootFrage) {
    fragenSet.add(kahootFrage);
  }

  public void loescheEinzelneFrage(int index) {
    fragenSet.remove(index);
  }
    
  public int size() {
    return fragenSet.size();
  }
}