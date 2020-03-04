// ----------------------------------------------------------------------------
// KahootSet.java
// ----------------------------------------------------------------------------
// Funktion:   Beinhaltet die kompletten Informationen zu einem Kahoot-Set
//             - Jedes Kahoot-Set besteht aus einem Informationsteil. Dieser 
//               Teil ist in Info.java definiert
//             - Jedes Kahoot-Set besteht aus beliebig vielen Fragen-Packeten.  
//               Dieser Teil ist in AlleFragen.java definiert
//             - Der Bauplan für eine einzelne Frage ist in Frage.java definiert
//             Über die Setter- und Getter-Methoden können auf die 
//             Informationen zugegriffen werden.
//             Zusätzlich gibt es noch die Methoden:
//             - int addFrage(Frage neueFrage) // gibt Anzahl Fragen zurück
//             - int loescheEinzelneFrage(int index) // gibt Anzahl Fragen zurück
//             - nummeriereIdNeu()
//             - public void zeigeKahootInhalt()
//             - public void zeigeMapInfoSchlüssel()
//             - public void zeigeFrageSchlüssel()
// 
// SW:         Apache NetBeans IDE 11.1 ; JDK-11
// Datum:      26.01.2020
// Autor:      A. Albisser; PSI; WBWA /038; Tel. ++41563103450
// ----------------------------------------------------------------------------
package ch.edualb.kahoot;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class KahootSet {

  // Schlüssel-Definition für die Informationen des Lernsets 
  // in der Map- & Json-Struktur 
  private	final String keySpiel = "spiel"; 
  private	final String keyTyp = "typ"; 
	private	final String keyHauptkategorie = "hauptkategorie"; 
  private	final String keyUnterkategorie = "unterkategorie"; 
	private	final String keyThema = "thema";
  private	final String keySchwierigkeitsgrad = "schwierigkeitsgrad"; 
	private	final String keyAutor = "autor"; 
  private final String keyDatum = "datum"; 
  // Schlüssel-Definition für die Frage des Lernsets 
  // in der Map- & Json-Struktur 
  private	final String keyId = "id"; 
  private	final String keyFrage = "frage"; 
  private	final String keyLevel = "level"; 
  private	final String keyNameFoto = "nameFoto"; 
  private	final String keyPfadFoto = "pfadFoto"; 
  private	final String keyCopyright = "copyright"; 
  private	final String keyAntworten = "antworten"; 
  private	final String keyAntwort1 = "antwort1"; 
  private	final String keyKorrekt1 = "korrekt1"; 
  private	final String keyAntwort2 = "antwort2"; 
  private	final String keyKorrekt2 = "korrekt2"; 
  private	final String keyAntwort3 = "antwort3"; 
  private	final String keyKorrekt3 = "korrekt3"; 
  private	final String keyAntwort4 = "antwort4"; 
  private	final String keyKorrekt4 = "korrekt4"; 

  private int anzahlFragen;
  private final Info info;
  private final Frage frage;
  private final AlleFragen fragen;

  // -------------------------------------------------------------------------- 
  // Konstruktor
  // Initialisiert alle notwendeigen Membervariablen bei der Objekterzeugung
  // --------------------------------------------------------------------------   
  public KahootSet() {
    anzahlFragen = 0;
    this.info = new Info();
    this.frage = new Frage();
    this.fragen = new AlleFragen();
  }
  
  // Copy-Konstruktor
  public KahootSet(KahootSet copy) {
    this. anzahlFragen = copy.anzahlFragen;
    this.info = copy.info;
    this.frage = copy.frage;
    this.fragen = copy.fragen;
  }

  
  // -------------------------------------------------------------------------- 
  // Setter-Methoden 
  // Übergabewert wenn nötig prüfen und in Membervariable speichern
  // -------------------------------------------------------------------------- 
  public void setMapInfo(LinkedHashMap<String, String> mapInfo) {    
    LinkedHashMap<String, String> map = new LinkedHashMap<>(mapInfo);
    //System.out.println(map);  // Testausgabe
    info.setSpiel(map.get(keySpiel));
    info.setTyp(map.get(keyTyp));
    info.setHauptKategorie(map.get(keyHauptkategorie));
    info.setUnterKategorie(map.get(keyUnterkategorie));
    info.setThema(map.get(keyThema));
    info.setSchwierigkeitsgrad(map.get(keySchwierigkeitsgrad));
    info.setAutor(map.get(keyAutor));
    info.setDatum(map.get(keyDatum));
  }

  public void setAlleFragen(List<Frage> listeAlleFragen) {
    fragen.setListeAlleFragen(listeAlleFragen);
    anzahlFragen = fragen.size();
    //zeigeKahootInhalt();  // Testausgabe
  }
  
  
  // -------------------------------------------------------------------------- 
  // Getter-Methoden 
  // Aktueller Wert aus den privaten Membervariablen lesen und dem Aufrufer 
  // zurückgeben
  // -------------------------------------------------------------------------- 
  public LinkedHashMap<String, String> getMapInfo() {
    LinkedHashMap<String, String> map = new LinkedHashMap<>();
    map.put(keySpiel, info.getSpiel());
    map.put(keyTyp, info.getTyp());
    map.put(keyHauptkategorie, info.getHauptKategorie());
    map.put(keyUnterkategorie, info.getUnterKategorie());
    map.put(keyThema, info.getThema());
    map.put(keySchwierigkeitsgrad, info.getSchwierigkeitsgrad());
    map.put(keyAutor, info.getAutor());    
    map.put(keyDatum, info.getDatum()); 
    //System.out.println(map);  // Testausgabe
    return map;
  }

  public List<String> getMapInfoKeyNames() {
    List<String> keys = new ArrayList<>();
    keys.add(keySpiel);
    keys.add(keyTyp);
    keys.add(keyHauptkategorie);
    keys.add(keyUnterkategorie);
    keys.add(keyThema);
    keys.add(keySchwierigkeitsgrad);
    keys.add(keyAutor);    
    keys.add(keyDatum);
    //System.out.println(keys);  // Testausgabe    
    return keys;
  }

  public LinkedHashMap<String, String> leereMapInfo() {  
    LinkedHashMap<String, String> map = new LinkedHashMap<>();
    map.put(keySpiel, "");
    map.put(keyTyp, "");
    map.put(keyHauptkategorie, "");
    map.put(keyUnterkategorie, "");
    map.put(keyThema, "");
    map.put(keySchwierigkeitsgrad, "");
    map.put(keyAutor, "");    
    map.put(keyDatum, ""); 
    //System.out.println(map);  // Testausgabe
    return map;
  }
  
  public List<String> getFrageKeyNames() {
    List<String> keys = new ArrayList<>();
    keys.add(keyId);
    keys.add(keyFrage);
    keys.add(keyLevel);
    keys.add(keyNameFoto);
    keys.add(keyPfadFoto);
    keys.add(keyCopyright);
    keys.add(keyAntworten);    
    keys.add(keyAntwort1);
    keys.add(keyKorrekt1);
    keys.add(keyAntwort2);
    keys.add(keyKorrekt2);
    keys.add(keyAntwort3);
    keys.add(keyKorrekt3);
    keys.add(keyAntwort4);
    keys.add(keyKorrekt4);
    //System.out.println(keys);  // Testausgabe    
    return keys;
  }

  public int getAnzahlFragen() {
    return anzahlFragen;
  }

  public Frage getEinzelneFrage(int index) {
    return  fragen.getEinzelneFrage(index);
  }

  public List<Frage> getAlleFragen() {
    return  fragen.getListeAlleFragen();
  }
  
  
  // -------------------------------------------------------------------------- 
  // Zusätzliche Methoden 
  // -------------------------------------------------------------------------- 
  public int addFrage(Frage neueFrage) {  
    fragen.add(neueFrage);
    anzahlFragen = fragen.size();
    nummeriereIdNeu();
    return anzahlFragen;
  }

  public int loescheEinzelneFrage(int index) {
    anzahlFragen = fragen.size();
    if (anzahlFragen > 0) {
      fragen.loescheEinzelneFrage(index); 
      anzahlFragen = fragen.size();
      nummeriereIdNeu(); 
    }
    return anzahlFragen;
  }

  public void nummeriereIdNeu() {
    List<Frage> alleFagen = getAlleFragen();
    // System.out.println("\nAnzahlFragen " +anzahlFragen);
    for (int i  = 0; i < anzahlFragen; i++) {
      alleFagen.get(i).setId(i);
      // System.out.println("\nNr." +i);
    }        
  }
  
  public void zeigeKahootInhalt() {
    System.out.println("\n\nSie sehen den aktuellen Inhalt von Kahoot: ");
    info.zeigeKahootSetInfo();
    List<Frage> alleFagen = getAlleFragen();
    for (int i  = 0; i < anzahlFragen; i++) {
      alleFagen.get(i).zeigeFrage();
    }    
  }

  public void zeigeFrage() {
    frage.zeigeFrage();
  }

  public void zeigeMapInfoSchlüssel() {
    System.out.println("\nEs werden die Schlüssel aufgelistet, um an die über getMapInfo() erstellten Werte zu kommen:");
    System.out.println(keySpiel); 
    System.out.println(keyTyp); 
    System.out.println(keyHauptkategorie); 
    System.out.println(keyUnterkategorie); 
    System.out.println(keyThema); 
    System.out.println(keySchwierigkeitsgrad); 
    System.out.println(keyAutor); 
    System.out.println(keyDatum); 
  }  

  public void zeigeFrageSchlüssel() {
    System.out.println("\nEs werden die Schlüssel aufgelistet, um an die über getEinzelneFrage() erstellten Werte zu kommen:");
    System.out.println(keyId);
    System.out.println(keyFrage);
    System.out.println(keyLevel);
    System.out.println(keyNameFoto);
    System.out.println(keyPfadFoto);
    System.out.println(keyCopyright);
    System.out.println(keyAntworten);    
    System.out.println(keyAntwort1);
    System.out.println(keyKorrekt1);
    System.out.println(keyAntwort2);
    System.out.println(keyKorrekt2);
    System.out.println(keyAntwort3);
    System.out.println(keyKorrekt3);
    System.out.println(keyAntwort4);
    System.out.println(keyKorrekt4);
  }    
}
