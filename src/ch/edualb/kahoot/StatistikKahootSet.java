// ----------------------------------------------------------------------------
// StatistikLerneinheit.java
// ----------------------------------------------------------------------------
// Funktion:   .
//             Zusätzlich gibt es noch die Methoden:
//             - public int getSizeInfo()
//             - public void zeigeKahootstatistik()
// 
// SW:         Apache NetBeans IDE 11.1 ; JDK-11
// Datum:      23.02.2020
// Autor:      A. Albisser; PSI; WBWA /038; Tel. ++41563103450
// ----------------------------------------------------------------------------
package ch.edualb.kahoot;

import ch.edualb.presentation.Hauptmenu;
import static ch.edualb.presentation.Hauptmenu.sprache;
import java.util.List;

public class StatistikKahootSet {
	// Effektive Werte für die Statistik des Lernsets
  private	String spiel; 
  private	String typ; 
	private	String hauptKategorie; 
  private	String unterKategorie; 
	private	String thema;
  private	String schwierigkeitsgrad; 
	private	String user; 
  private String datum;
  private long dauerGesamt;
  private long dauerDurchschnitt;  
  private long dauerSchnellsteAntwort;  
  private long dauerLangsamsteAntwort;  
  private int anzahlAufgabenGesamt;  
  private int anzahlAufgabenGeloest;
  private int anzahlAufgabenRichtig;
  private int anzahlAufgabenFalsch;
  private int anzahlSchnellerAlsDurchschnitt;    
  private int anzahlSchnellerAlsDurchschnittRichtig;    
  private double quoteTotal;
  private double quoteRichtig;
  private double quoteSchnellRichtig;
  private double quoteNachLevel;  
  private final Antwort antwort;
  private final AlleAntworten antworten;
  private int anzahlAntworten;
  
  // -------------------------------------------------------------------------- 
  // Konstruktor
  // Initialisiert alle Membervariablen bei der Objekterzeugung
  // -------------------------------------------------------------------------- 
  public StatistikKahootSet() {
    this.spiel = "";
    this.typ = "";
    this.hauptKategorie = "";
    this.unterKategorie = "";
    this.thema = "";
    this.schwierigkeitsgrad = "";
    this.user = "";
    this.datum = "";
    this.dauerGesamt = 0;
    this.dauerDurchschnitt = 0;
    this.dauerSchnellsteAntwort = 0;
    this.dauerLangsamsteAntwort = 0;
    this.anzahlAufgabenGesamt = 0; 
    this.anzahlAufgabenGeloest = 0;
    this.anzahlAufgabenRichtig = 0;
    this.anzahlAufgabenFalsch = 0;
    this.anzahlSchnellerAlsDurchschnitt = 0;    
    this.anzahlSchnellerAlsDurchschnittRichtig = 0;        
    this.quoteTotal = 0;
    this.quoteRichtig = 0;
    this.quoteSchnellRichtig = 0;
    this.quoteNachLevel = 0;
    this.antwort = new Antwort();
    this.antworten = new AlleAntworten();
  }    
  
//  public StatistikKahootSet(String spiel, String typ, String hauptKategorie, String unterKategorie, String thema, String schwierigkeitsgrad, String user, String datum, long dauerGesamt, long dauerDurchschnitt, int anzahlAufgabenGesamt, int anzahlAufgabenGeloest, int anzahlAufgabenRichtig, int anzahlAufgabenfalsch, double quoteTotal, double quoteNachZeit, double quoteNachLevel, Antwort antwort, AlleAntworten antworten) {
//    this.spiel = spiel;
//    this.typ = typ;
//    this.hauptKategorie = hauptKategorie;
//    this.unterKategorie = unterKategorie;
//    this.thema = thema;
//    this.schwierigkeitsgrad = schwierigkeitsgrad;
//    this.user = user;
//    this.datum = datum;
//    this.dauerGesamt = dauerGesamt;
//    this.dauerDurchschnitt = dauerDurchschnitt;
//    this.anzahlAufgabenGesamt = anzahlAufgabenGesamt; 
//    this.anzahlAufgabenGeloest = anzahlAufgabenGeloest;
//    this.anzahlAufgabenRichtig = anzahlAufgabenRichtig;
//    this.anzahlAufgabenFalsch = anzahlAufgabenfalsch;
//    this.quoteTotal = quoteTotal;
//    this.quoteNachZeit = quoteNachZeit;
//    this.quoteNachLevel = quoteNachLevel;
//    this.antwort = antwort;
//    this.antworten = antworten;
//
//  }    


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

  public void setThema(String thema) {
    this.thema = thema;
  }

  public void setSchwierigkeitsgrad(String schwierigkeitsgrad) {
    this.schwierigkeitsgrad = schwierigkeitsgrad;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public void setDatum(String datum) {
    this.datum = datum;
  }

  public void setDauerGesamt(long dauerGesamt) {
    this.dauerGesamt = dauerGesamt;
  }

  public void setDauerDurchschnitt(long dauerDurchschnitt) {
    this.dauerDurchschnitt = dauerDurchschnitt;
  }

  public void setDauerSchnellsteAntwort(long dauerSchnellsteAntwort) {
    this.dauerSchnellsteAntwort = dauerSchnellsteAntwort;
  }

  public void setDauerLangsamsteAntwort(long dauerLangsamsteAntwort) {
    this.dauerLangsamsteAntwort = dauerLangsamsteAntwort;
  }

  public void setAnzahlAufgabenGesamt(int anzahlAufgabenGesamt) {
    this.anzahlAufgabenGesamt = anzahlAufgabenGesamt;
  }

  public void setAnzahlAufgabenGeloest(int anzahlAufgabenGeloest) {
    this.anzahlAufgabenGeloest = anzahlAufgabenGeloest;
  }

  public void setAnzahlAufgabenRichtig(int anzahlAufgabenRichtig) {
    this.anzahlAufgabenRichtig = anzahlAufgabenRichtig;
  }

  public void setAnzahlAufgabenFalsch(int anzahlAufgabenfalsch) {
    this.anzahlAufgabenFalsch = anzahlAufgabenfalsch;
  }

  public void setAnzahlSchnellerAlsDurchschnitt(int anzahlSchnellerAlsDurchschnitt) {
    this.anzahlSchnellerAlsDurchschnitt = anzahlSchnellerAlsDurchschnitt;
  }

  public void setAnzahlSchnellerAlsDurchschnittRichtig(int anzahlSchnellerAlsDurchschnittRichtig) {
    this.anzahlSchnellerAlsDurchschnittRichtig = anzahlSchnellerAlsDurchschnittRichtig;
  }

  public void setQuoteTotal(double quoteTotal) {
    this.quoteTotal = quoteTotal;
  }

  public void setQuoteRichtig(double quoteRichtig) {
    this.quoteRichtig = quoteRichtig;
  }

  public void setquoteSchnellRichtig(double quoteSchnellRichtig) {
    this.quoteSchnellRichtig = quoteSchnellRichtig;
  }

  public void setQuoteNachLevel(double quoteNachLevel) {
    this.quoteNachLevel = quoteNachLevel;
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

  public String getThema() {
    return thema;
  }

  public String getSchwierigkeitsgrad() {
    return schwierigkeitsgrad;
  }

  public String getUser() {
    return user;
  }

  public String getDatum() {
    return datum;
  }

  public long getDauerGesamt() {
    return dauerGesamt;
  }

  public long getDauerDurchschnitt() {
    return dauerDurchschnitt;
  }

  public long getDauerSchnellsteAntwort() {
    return dauerSchnellsteAntwort;
  }

  public long getDauerLangsamsteAntwort() {
    return dauerLangsamsteAntwort;
  }
  
  public int getAnzahlAufgabenGesamt() {
    return anzahlAufgabenGesamt;
  }

  public int getAnzahlAufgabenGeloest() {
    return anzahlAufgabenGeloest;
  }

  public int getAnzahlAufgabenRichtig() {
    return anzahlAufgabenRichtig;
  }

  public int getAnzahlAufgabenFalsch() {
    return anzahlAufgabenFalsch;
  }

  public int getAnzahlSchnellerAlsDurchschnitt() {
    return anzahlSchnellerAlsDurchschnitt;
  }

  public int getAnzahlSchnellerAlsDurchschnittRichtig() {
    return anzahlSchnellerAlsDurchschnittRichtig;
  }

  public double getQuoteTotal() {
    return quoteTotal;
  }

  public double getquoteSchnellRichtig() {
    return quoteSchnellRichtig;
  }

  public double getQuoteRichtig() {
    return quoteRichtig;
  }

  public double getQuoteNachLevel() {
    return quoteNachLevel;
  }

  public Antwort getEinzelneAntwort(int index) {
    return  antworten.getEinzelneAntwort(index);
  }

  public List<Antwort> getAlleAntworten() {
    return  antworten.getListeAlleAntworten();
  }

  public int getAnzahlAntworten() {
    return  antworten.size();
  }
  
  
  // --------------------------------------------------------------------------
  // Zusätzliche Methoden
  // --------------------------------------------------------------------------
  public void addAntwort(Antwort neueAntwort) {
    antworten.add(neueAntwort);
    anzahlAntworten = antworten.size();
  }

  @Override
  public String toString() {
    return "StatistikKahootSet{" + "spiel=" + spiel + ", typ=" + typ + ", hauptKategorie=" + hauptKategorie + ", unterKategorie=" + unterKategorie + ", thema=" + thema + ", schwierigkeitsgrad=" + schwierigkeitsgrad + ", user=" + user + ", datum=" + datum + ", dauerGesamt=" + dauerGesamt + ", dauerDurchschnitt=" + dauerDurchschnitt + ", anzahlAufgabenGesamt=" + anzahlAufgabenGesamt + ", anzahlAufgabenGeloest=" + anzahlAufgabenGeloest + ", anzahlAufgabenRichtig=" + anzahlAufgabenRichtig + ", anzahlAufgabenFalsch=" + anzahlAufgabenFalsch + ", quoteTotal=" + quoteTotal + ", quoteNachZeit=" + quoteSchnellRichtig + ", quoteNachLevel=" + quoteNachLevel + ", antwort=" + antwort + ", antworten=" + antworten + '}';
  }

  public void zeigeKahootSetStatistikUser() {
    if (sprache == Hauptmenu.Sprache.DEUTSCH) {    
      System.out.println("\nAnwender:                                     " +user);
      System.out.println("Datum:                                        " +datum);
      System.out.println("Lernprinzip:                                  " +spiel);
      System.out.println("Typ:                                          " +typ);
      System.out.println("Haupt-Kategorie:                              " +hauptKategorie);
      System.out.println("Unter-Kategorie:                              " +unterKategorie);
      System.out.println("Thema:                                        " +thema);
      System.out.println("Schwierigkeitsgrad:                           " +schwierigkeitsgrad);
      System.out.println("Gesamtdauer für alle Antworten:               " +zeigeSekunden(dauerGesamt));
      System.out.println("Durchschnittliche Dauer aller Antworten:      " +zeigeSekunden(dauerDurchschnitt));
      System.out.println("Dauer der schnellsten / langsamsten Antwort:  " +zeigeSekunden(dauerSchnellsteAntwort) +" / " +zeigeSekunden(dauerLangsamsteAntwort));
      System.out.println("Anzahl Fragen gesamt / gelöst / Quote:        " +anzahlAufgabenGesamt +" / " +anzahlAufgabenGeloest +" / " +quoteTotal +"%");
      System.out.println("richtig / falsch / Quote:                     " +anzahlAufgabenRichtig +" / " +anzahlAufgabenFalsch +" / " +quoteRichtig +"%");
      System.out.println("schneller als Durchschnitt / richtig / Quote: " +anzahlSchnellerAlsDurchschnitt +" / " +anzahlSchnellerAlsDurchschnittRichtig +" / " +quoteSchnellRichtig +"%");    
    } else {
      System.out.println("\nUser:                                         " +user);
      System.out.println("Date:                                         " +datum);
      System.out.println("Principle:                                    " +spiel);
      System.out.println("Typ:                                          " +typ);
      System.out.println("Maincategory:                                 " +hauptKategorie);
      System.out.println("Subcategory:                                  " +unterKategorie);
      System.out.println("Theme:                                        " +thema);
      System.out.println("Difficulty:                                   " +schwierigkeitsgrad);
      System.out.println("Duration for all answers:                     " +zeigeSekunden(dauerGesamt));
      System.out.println("Average time for a question:                  " +zeigeSekunden(dauerDurchschnitt));
      System.out.println("Duration for fastest / slowest answer:        " +zeigeSekunden(dauerSchnellsteAntwort) +" / " +zeigeSekunden(dauerLangsamsteAntwort));
      System.out.println("Count of questions over all / solved / quote: " +anzahlAufgabenGesamt +" / " +anzahlAufgabenGeloest +" / " +quoteTotal +"%");
      System.out.println("right / wrong / quote:                        " +anzahlAufgabenRichtig +" / " +anzahlAufgabenFalsch +" / " +quoteRichtig +"%");
      System.out.println("Faster as average / right  / quote:           " +anzahlSchnellerAlsDurchschnitt +" / " +anzahlSchnellerAlsDurchschnittRichtig +" / " +quoteSchnellRichtig +"%");    
    }
//    System.out.println("Quote richtig gelöster Aufgaben nach Level:   " +quoteNachLevel);

//    List<Antwort> alleAntworten = getAlleAntworten();
//    if (alleAntworten.size() > 0) {
//      for (int i  = 0; i < anzahlAntworten; i++) {
//        alleAntworten.get(i).zeigeAntwort();
//      }          
//    }
  }

  public String zeigeSekunden(long zeit) {
    String sekunden = "0";
    String millisekunden = Long.toString((zeit % 1000) / 10);
    if (zeit >= 1000) {
      sekunden = Long.toString(zeit/1000);
    }
    return sekunden +"." +millisekunden +"s";
  }
  
  public void zeigeAntwort() {
    antwort.zeigeAntwort();
  }
}
