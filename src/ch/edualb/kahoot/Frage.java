// ----------------------------------------------------------------------------
// Frage.java
// ----------------------------------------------------------------------------
// Funktion:   Beinhaltet die Informationen im Zusammenhang mit einer einzelnen 
//             Frage, sowie den 4 Antworten zum Karten-Set Kahoot.
//             Über die Setter- und Getter-Methoden können auf die einzelnen
//             Informationen zugegriffen werden.
//             Zusätzlich gibt es noch die Methoden:
//             - public void zeigeKahootSetFrage()
// 
// SW:         Apache NetBeans IDE 11.1 ; JDK-11
// Datum:      26.01.2020
// Autor:      A. Albisser; PSI; WBWA /038; Tel. ++41563103450
// ----------------------------------------------------------------------------
package ch.edualb.kahoot;

public class Frage {

  // Effektive Werte für eine Frage im Lernset
  private int id = 0;
  private int level = 0;
	private	String frage; 
	private	String nameFoto; 
	private	String pfadFoto; 
	private	String copyright; 
	private	String antwort1; 
	private	String antwort2; 
	private	String antwort3; 
	private	String antwort4;
  private int korrekt1 = 0;
  private int korrekt2 = 0;
  private int korrekt3 = 0;
  private int korrekt4 = 0;
  private final int maxTextLaenge = 150;

  // -------------------------------------------------------------------------- 
  // Konstruktor
  // Initialisiert alle Membervariablen bei der Objekterzeugung
  // -------------------------------------------------------------------------- 
  // Empty Konstruktor
  public Frage() {    
    this.id = 1;
    this.level = 0;
    this.frage = "";
    this.nameFoto = "";
    this.pfadFoto = "";
    this.copyright = "";
    this.antwort1 = "";
    this.antwort2 = "";
    this.antwort3 = "";
    this.antwort4 = "";    
    this.korrekt1 = 0;    
    this.korrekt2 = 0;    
    this.korrekt3 = 0;    
    this.korrekt4 = 0;        
  }

  // Copy-Konstruktor
  public Frage(Frage copy) {    
    this.id = copy.id;
    this.level = copy.level;
    this.frage = copy.frage;
    this.nameFoto = copy.nameFoto;
    this.pfadFoto = copy.pfadFoto;
    this.copyright = copy.copyright;
    this.antwort1 = copy.antwort1;
    this.antwort2 = copy.antwort2;
    this.antwort3 = copy.antwort3;
    this.antwort4 = copy.antwort4;    
    this.korrekt1 = copy.korrekt1;    
    this.korrekt2 = copy.korrekt2;    
    this.korrekt3 = copy.korrekt3;    
    this.korrekt4 = copy.korrekt4;            
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

  public void setFrage(String frage) {
    if (frage.length() > maxTextLaenge) {
      this.frage = frage.substring(0, maxTextLaenge);
      System.out.println("Text hat mehr als " +maxTextLaenge +" Zeichen und wurde daher begrenzt:  => " +this.frage);
    } else {
      this.frage = frage;
    }
  }

  public void setNameFoto(String nameFoto) {
    if (nameFoto.length() > maxTextLaenge) {
      this.nameFoto = nameFoto.substring(0, maxTextLaenge);
      System.out.println("Text hat mehr als " +maxTextLaenge +" Zeichen und wurde daher begrenzt:  => " +this.nameFoto);
    } else {
      this.nameFoto = nameFoto;
    }
  }

  public void setPfadFoto(String pfadFoto) {
    this.pfadFoto = pfadFoto;
  }

  public void setCopyright(String copyrigth) {
    if (copyrigth.length() > maxTextLaenge) {
      this.copyright = copyrigth.substring(0, maxTextLaenge);
      System.out.println("Text hat mehr als " +maxTextLaenge +" Zeichen und wurde daher begrenzt:  => " +this.copyright);
    } else {
      this.copyright = copyrigth;
    }
  }

  public void setAntwort1(String antwort1) {
    if (antwort1.length() > maxTextLaenge) {
      this.antwort1 = antwort1.substring(0, maxTextLaenge);
      System.out.println("Text hat mehr als " +maxTextLaenge +" Zeichen und wurde daher begrenzt:  => " +this.antwort1);
    } else {
      this.antwort1 = antwort1;
    }
  }

  public void setAntwort2(String antwort2) {
    if (antwort2.length() > maxTextLaenge) {
      this.antwort2 = antwort2.substring(0, maxTextLaenge);
      System.out.println("Text hat mehr als " +maxTextLaenge +" Zeichen und wurde daher begrenzt:  => " +this.antwort2);
    } else {
      this.antwort2 = antwort2;
    }
  }

  public void setAntwort3(String antwort3) {
    if (antwort3.length() > maxTextLaenge) {
      this.antwort3 = antwort3.substring(0, maxTextLaenge);
      System.out.println("Text hat mehr als " +maxTextLaenge +" Zeichen und wurde daher begrenzt:  => " +this.antwort3);
    } else {
      this.antwort3 = antwort3;
    }
  }

  public void setAntwort4(String antwort4) {
    if (antwort4.length() > maxTextLaenge) {
      this.antwort4 = antwort4.substring(0, maxTextLaenge);
      System.out.println("Text hat mehr als " +maxTextLaenge +" Zeichen und wurde daher begrenzt:  => " +this.antwort4);
    } else {
      this.antwort4 = antwort4;
    }
  }

  public void setA1Korrekt(int korrekt1) {
    this.korrekt1 = korrekt1;
  }

  public void setA2Korrekt(int korrekt2) {
    this.korrekt2 = korrekt2;
  }

  public void setA3Korrekt(int korrekt3) {
    this.korrekt3 = korrekt3;
  }

  public void setA4Korrekt(int korrekt4) {
    this.korrekt4 = korrekt4;
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

  public String getFrage() {
    return frage;
  }

  public String getNameFoto() {
    return nameFoto;
  }

  public String getPfadFoto() {
    return pfadFoto;
  }

  public String getCopyright() {
    return copyright;
  }

  public String getAntwort1() {
    return antwort1;
  }

  public String getAntwort2() {
    return antwort2;
  }

  public String getAntwort3() {
    return antwort3;
  }

  public String getAntwort4() {
    return antwort4;
  }

  public int getA1Korrekt() {
    return korrekt1;
  }

  public int getA2Korrekt() {
    return korrekt2;
  }

  public int getA3Korrekt() {
    return korrekt3;
  }

  public int getA4Korrekt() {
    return korrekt4;
  }

  public int getTextLaenge() {
    return maxTextLaenge;
  }

  // -------------------------------------------------------------------------- 
  // Zusätzliche Methoden 
  // -------------------------------------------------------------------------- 
  public void zeigeFrage() {
    System.out.println("\nID:                  " +id);
    System.out.println("Level:               " +level);
    System.out.println("Frage:               " +frage); 
    System.out.println("Dateiname Foto:      " +nameFoto); 
    System.out.println("Pfad zu Foto:        " +pfadFoto); 
    System.out.println("Copyrigt:            " +copyright); 
    System.out.println("Antwort 1:           " +korrekt1 +"; " +antwort1); 
    System.out.println("Antwort 2:           " +korrekt2 +"; " +antwort2); 
    System.out.println("Antwort 3:           " +korrekt3 +"; " +antwort3); 
    System.out.println("Antwort 4:           " +korrekt4 +"; " +antwort4); 
    System.out.println("Maximale Textlänge:  " +maxTextLaenge);     
  }    
}
