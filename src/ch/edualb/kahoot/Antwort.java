// ----------------------------------------------------------------------------
// Antwort.java
// ----------------------------------------------------------------------------
// Funktion:   .
//             Zusätzlich gibt es noch die Methoden:
//             - public int getSizeInfo()
//             - public void zeigeEinzelneAntwort()
// 
// SW:         Apache NetBeans IDE 11.1 ; JDK-11
// Datum:      23.02.2020
// Autor:      A. Albisser; PSI; WBWA /038; Tel. ++41563103450
// ----------------------------------------------------------------------------
package ch.edualb.kahoot;

public class Antwort {
	// Effektive Werte für die Statistik des Lernsets
  private	int id; 
  private	int level; 
  private	long dauer; 
  private	int gewaehlteAntwort; 
  private	boolean antwortRichtig; 

  
  // -------------------------------------------------------------------------- 
  // Konstruktor
  // Initialisiert alle Membervariablen bei der Objekterzeugung
  // -------------------------------------------------------------------------- 
  public Antwort() {    
    this.id = 0;
    this.level = 0;
    this.dauer = 0;
    this.gewaehlteAntwort = 0;
    this.antwortRichtig = false;    
  }

  public Antwort(int id, int level, long dauer, int gewaehlteAntwort, boolean antwortRichtig) {
    this.id = id;
    this.level = level;
    this.dauer = dauer;
    this.gewaehlteAntwort = gewaehlteAntwort;
    this.antwortRichtig = antwortRichtig;    
  }


  // -------------------------------------------------------------------------- 
  // Setter-Methoden 
  // Übergabewert wenn nötig prüfen und in Membervariable speichern
  // -------------------------------------------------------------------------- 
  public void setId(int id) {
    this.id = id;
  }
  
  public void setLevel(int level) {
    this.level = level;
  }

  public void setDauer(long dauer) {
    this.dauer = dauer;
  }

  public void setGewaehlteAntwort(int gewaehlteAntwort) {
    this.gewaehlteAntwort = gewaehlteAntwort;
  }

  public void setAntwortRichtig(boolean antwortRichtig) {
    this.antwortRichtig = antwortRichtig;
  }

 
  // -------------------------------------------------------------------------- 
  // Getter-Methoden 
  // Aktueller Wert aus den privaten Membervariablen lesen und dem Aufrufer 
  // zurückgeben
  // -------------------------------------------------------------------------- 
  public int getId() {
    return id;
  }

  public int getLevel() {
    return level;
  }

  public long getDauer() {
    return dauer;
  }

  public int getGewaehlteAntwort() {
    return gewaehlteAntwort;
  }

  public boolean istAntwortRichtig() {
    return antwortRichtig;
  }

  
  // -------------------------------------------------------------------------- 
  // Zusätzliche Methoden 
  // --------------------------------------------------------------------------     
  public void zeigeAntwort() {
    System.out.println("\nID:                  " +id);
    System.out.println("Level:               " +level); 
    System.out.println("Dauer:               " +dauer); 
    System.out.println("Gewaehlte Antwort:   " +gewaehlteAntwort); 
    System.out.println("Antwort richtig:     " +antwortRichtig);     
  }    
}
