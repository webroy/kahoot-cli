// ----------------------------------------------------------------------------
// KahootSetInfo.java
// ----------------------------------------------------------------------------
// Funktion:   Beinhaltet die allgemeinen Informationen im Zusammenhang mit dem 
//             Karten-Set Kahoot.
//             Über den Konstruktor, oder die Setter- und Getter-Methoden können 
//             auf die Informationen zugegriffen werden.
//             Zusätzlich gibt es noch die Methoden:
//             - public int getSizeInfo()
//             - public void zeigeKahootSetInfo()
// 
// SW:         Apache NetBeans IDE 11.1 ; JDK-11
// Datum:      26.01.2020
// Autor:      A. Albisser; PSI; WBWA /038; Tel. ++41563103450
// ----------------------------------------------------------------------------
package ch.edualb.kahoot;

public class Info {
 
	// Effektive Werte für die Informationen des Lernsets
  private	String spiel; 
  private	String typ; 
	private	String hauptKategorie; 
  private	String unterKategorie; 
	private	String thema;
  private	String schwierigkeitsgrad; 
	private	String autor; 
  private String datum;
  private final int anzahlInfos = 8;
  
  // -------------------------------------------------------------------------- 
  // Konstruktor
  // Initialisiert alle Membervariablen bei der Objekterzeugung
  // -------------------------------------------------------------------------- 
  public Info() {    
    this.spiel = "";
    this.typ = "";
    this.hauptKategorie = "";
    this.unterKategorie = "";
    this.thema = "";
    this.schwierigkeitsgrad = "";
    this.autor = "";
    this.datum = "";
  }
  
  public Info(String spiel, String typ, String hauptKategorie, String unterKategorie, String thema, String schwierigkeitsgrad, String autor, String datum) {
    this.spiel = spiel;
    this.typ = typ;
    this.hauptKategorie = hauptKategorie;
    this.unterKategorie = unterKategorie;
    this.thema = thema;
    this.schwierigkeitsgrad = schwierigkeitsgrad;
    this.autor = autor;
    this.datum = datum;
  }

  // -------------------------------------------------------------------------- 
  // Setter-Methoden 
  // Übergabewert wenn nötig prüfen und in Membervariable speichern
  // -------------------------------------------------------------------------- 
  public void setSpiel(String spiel) {
    this.spiel = spiel;
  }

  public void setTyp(String typ) {
    this.typ = typ;
  }
  
  public void setHauptKategorie(String hauptKategorie) {
    this.hauptKategorie = hauptKategorie;
  }

  public void setUnterKategorie(String unterKategorie) {
    this.unterKategorie = unterKategorie;
  }

  public void setSchwierigkeitsgrad(String schwierigkeitsgrad) {
    this.schwierigkeitsgrad = schwierigkeitsgrad;
  }
  
  public void setThema(String thema) {
    this.thema = thema;
  }

  public void setAutor(String autor) {
    this.autor = autor;
  }

  public void setDatum(String datum) {
    this.datum = datum;
  }

  // -------------------------------------------------------------------------- 
  // Getter-Methoden 
  // Aktueller Wert aus den privaten Membervariablen lesen und dem Aufrufer 
  // zurückgeben
  // -------------------------------------------------------------------------- 
  public String getSpiel() {
    return spiel;
  }

  public String getTyp() {
    return typ;
  }
  
  public String getHauptKategorie() {
    return hauptKategorie;
  }

  public String getUnterKategorie() {
    return unterKategorie;
  }

  public String getSchwierigkeitsgrad() {
    return schwierigkeitsgrad;
  }

  public String getThema() {
    return thema;
  }

  public String getAutor() {
    return autor;
  }

  public String getDatum() {
    return datum;
  }
  
  // -------------------------------------------------------------------------- 
  // Zusätzliche Methoden 
  // --------------------------------------------------------------------------     
  public int getSizeInfo() {
    return anzahlInfos;
  }

  public void zeigeKahootSetInfo() {
    System.out.println("\nSpiel:               " +spiel);
    System.out.println("Typ:                 " +typ); 
    System.out.println("Haupt-Kategorie:     " +hauptKategorie); 
    System.out.println("Unter-Kategorie:     " +unterKategorie); 
    System.out.println("Thema:               " +thema); 
    System.out.println("Schwierigkeitsgrad:  " +schwierigkeitsgrad); 
    System.out.println("Autor:               " +autor); 
    System.out.println("Datum:               " +datum); 
  }  
}
