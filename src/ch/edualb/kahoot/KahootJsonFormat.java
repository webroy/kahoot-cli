// ----------------------------------------------------------------------------
// KahootJsonFormat.java
// ----------------------------------------------------------------------------
// Funktion:   Stellt die 2 folgenden Puplic-Methoden zur Konvertierung vom 
//             Kahoot-Lernset in einen JSON-Format-String und umgekehrt zur 
//             Verfügung:
//             - public String getJsonFromKahootSet(KahootSet kahootset)
//             - public KahootSet getKahootSetFromJson(String lernset)
//             Die einzelnen Methoden sind noch zuwenig ausgereift, um sie für 
//             beliebige Konvertierungen einsetzen zu können.
//             Da gibt es noch einiges Verbesserungspotential (oder gleich
//             eine JSON-Bibliothek verwenden).
//             Vorsicht auch bei Änderungen an Schlüssel-Bezeichnungen. Diese
//             sind hier noch hart codiert!!!
// 
// SW:         Apache NetBeans IDE 11.1 ; JDK-11
// Datum:      26.01.2020
// Autor:      A. Albisser; PSI; WBWA /038; Tel. ++41563103450
// ----------------------------------------------------------------------------
package ch.edualb.kahoot;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class KahootJsonFormat {

  // **************************************************************************
  // **************************************************************************
  // Kovertiere Kahoot-Set in JSON-String um in Datei zu schreiben
  // **************************************************************************
  // **************************************************************************
  public String getJsonFromKahootSet(KahootSet kahootset) {
    try {
      // Buffergrösse ermitteln und Buffer erzeugen
      // Aus Set-Infos & allen Fragen
      int bufferSize = 0;
      bufferSize += getInfo(kahootset.getMapInfo()).length();
      bufferSize += getAlleFragen(kahootset).length();
      System.out.println("\nBenötigte Buffergrösse für alle Informationen ist " +bufferSize +" Zeichen");
      StringBuilder text = new StringBuilder(bufferSize + 50);
      
      // Alle Schlüssel & Werte im JSON-Format zusammenfassen und zurückgeben
      text.append("{\n");    
      text.append(getInfo(kahootset.getMapInfo())).append(",");
      text.append(getAlleFragen(kahootset));
      text.append("\n}");
      //System.out.println(text);
      return text.toString();
    }
    catch (Exception e){
      System.out.println("\nProbleme bei StringBuilder Json-Set");
      return "Houston, we have problems!";
    }    
  }
  
  private String getInfo(Map<String, String> mapInfo) {
    KahootSet kahootSet = new KahootSet();    
    try {
      int anzahlKey = mapInfo.size();
      List<String> keys = new ArrayList<>(kahootSet.getMapInfoKeyNames());
      // Buffergrösse ermitteln und Buffer erzeugen
      // x * Anzahl Zeichen pro Schlüssel & Wert + 50 Zusatzzeichen je nach Anzahl
      // Einträge für die Reststruktur
      int bufferSize = 0;
      for (int key = 0; key < anzahlKey; key++) {
        bufferSize += keys.get(key).length();               // Schlüssel
        bufferSize += mapInfo.get(keys.get(key)).length();  // Werte
      }
      //System.out.println("\nBenötigte Buffergrösse für alle Informationen ist " +bufferSize +" Zeichen");
      StringBuilder text = new StringBuilder(bufferSize + 50);
      
      // Alle Schlüssel & Werte im JSON-Format zusammenfassen und zurückgeben
      text.append("  \"info\": {\n");    
      for (int key = 0; key < anzahlKey; key++) {
        text.append("    \"").append(keys.get(key)).append("\": \"").append(mapInfo.get(keys.get(key))).append("\"");
        if (key < (anzahlKey - 1)) {  // Letztes Fragenpacket benötigt kein Komma mehr
          text.append(",\n");
        }
      }
      text.append("\n  }");
      //System.out.println(text);
      return text.toString();
    }
    catch (Exception e){
      System.out.println("\nProbleme bei StringBuilder Json-Info");
      return "Houston, we have problems!";
    }    
  }

  private String getAlleFragen(KahootSet kahootSet) {
    int anzahlFragen = kahootSet.getAnzahlFragen();
    try {
     // Buffergrösse ermitteln und Buffer erzeugen
     // x * Anzahl Zeichen pro Frage + 200 Zusatzzeichen je nach Anzahl Packete
     // für die Reststruktur
     int bufferSize = 0;
     for (int nr = 0; nr < anzahlFragen; nr++) {
       bufferSize += getEinzelneFrage(kahootSet.getAlleFragen(), nr).length();
     }
     //System.out.println(bufferSize);
     StringBuilder text = new StringBuilder(bufferSize + 200);

     // Alle Fragen-Packete im JSON-Format zusammenfassen und zurückgeben
     text.append("\n  \"fragen\": [\n");
     for (int nr = 0; nr < anzahlFragen; nr++) {
       text.append(getEinzelneFrage(kahootSet.getAlleFragen(), nr));
       if (nr < (anzahlFragen - 1)) {  // Letztes Fragenpacket benötigt kein Komma mehr
         text.append(",\n");
       }
     }
     text.append("\n  ]");
     return text.toString();
    }
    catch (Exception e){
      System.out.println("\nProbleme bei StringBuilder Json-Fragen");
      return "Houston, we have problems!";
    }    
  }

  private String getEinzelneFrage(List<Frage> fragen, int nr) {
    StringBuilder text = new StringBuilder(1000);
     try {
      text.append("    {\n      \"id\": ").append(fragen.get(nr).getId()).append(",\n");
      text.append("      \"frage\": \"").append(fragen.get(nr).getFrage()).append("\",\n");
      text.append("      \"level\": ").append(fragen.get(nr).getLevel()).append(", \n");
      text.append("      \"nameFoto\": \"").append(fragen.get(nr).getNameFoto()).append("\",\n");
      text.append("      \"pfadFoto\": \"").append(fragen.get(nr).getPfadFoto()).append("\",\n");
      text.append("      \"copyright\": \"").append(fragen.get(nr).getCopyright()).append("\",\n");
      text.append("      \"antworten\": [\n");
      text.append("        {\n          \"antwort1\": \"").append(fragen.get(nr).getAntwort1()).append("\",\n");
      text.append("          \"korrekt1\": ").append(fragen.get(nr).getA1Korrekt()).append("\n        },\n");
      text.append("        {\n          \"antwort2\": \"").append(fragen.get(nr).getAntwort2()).append("\",\n");
      text.append("          \"korrekt2\": ").append(fragen.get(nr).getA2Korrekt()).append("\n        },\n");
      text.append("        {\n          \"antwort3\": \"").append(fragen.get(nr).getAntwort3()).append("\",\n");
      text.append("          \"korrekt3\": ").append(fragen.get(nr).getA3Korrekt()).append("\n        },\n");
      text.append("        {\n          \"antwort4\": \"").append(fragen.get(nr).getAntwort4()).append("\",\n");
      text.append("          \"korrekt4\": ").append(fragen.get(nr).getA4Korrekt()).append("\n        }\n");    
      text.append("      ]\n    }");
      return text.toString();
    }
    catch (Exception e){
      System.out.println("\nProbleme bei StringBuilder Json-Fragen");
      return "Houston, we have problems!";
    }    
  }        

  
  // **************************************************************************
  // **************************************************************************
  // Kovertiere JSON-String aus Datei um in Kahoot-Set zu schreiben
  // **************************************************************************
  // **************************************************************************
  public KahootSet getKahootSetFromJson(String lernset) {
    KahootSet kahootset = new KahootSet();
    KahootJsonFormat json = new KahootJsonFormat();
    String objektInfo = json.getEinzelnesObjekt(lernset, "info");   // Objekt Info in String
    //System.out.println(objektInfo); // Testausgabe "Info-String"      
    kahootset.setMapInfo(json.getMapInfo(objektInfo));
    //System.out.println(kahootset.getMapInfo()); // Testausgabe

    String arrayFragen = json.getArray(lernset, "fragen");   // Objekt Info in String
    //System.out.println(arrayFragen); // Testausgabe
    List<String> listeFragen = new ArrayList<>(json.getMehrereObjekte(arrayFragen, "id"));
    //System.out.println(listeFragen); // Testausgabe
    for (int entries = 0; entries < listeFragen.size(); entries++) {    
      //System.out.println("< " +entries +" > " +listeFragen.get(entries)); // Testausgabe
      String arrayAntworten = json.getArray(listeFragen.get(entries), "antworten");   // Objekt Info in String
      //System.out.println("Array Antworten" +arrayAntworten); // Testausgabe
      Frage Frage = new Frage();
      String tmp = "\"id\": ".concat(listeFragen.get(entries));
      Frage.setId(json.getZahlMitSchluessel(tmp, "id"));
      Frage.setLevel(json.getZahlMitSchluessel(listeFragen.get(entries), "level"));
      Frage.setFrage(json.getTextMitSchluessel(listeFragen.get(entries), "frage"));
      Frage.setNameFoto(json.getTextMitSchluessel(listeFragen.get(entries), "nameFoto"));
      Frage.setPfadFoto(json.getTextMitSchluessel(listeFragen.get(entries), "pfadFoto"));
      Frage.setCopyright(json.getTextMitSchluessel(listeFragen.get(entries), "copyright"));
      Frage.setAntwort1(json.getTextMitSchluessel(arrayAntworten, "antwort1"));
      Frage.setAntwort2(json.getTextMitSchluessel(arrayAntworten, "antwort2"));
      Frage.setAntwort3(json.getTextMitSchluessel(arrayAntworten, "antwort3"));
      Frage.setAntwort4(json.getTextMitSchluessel(arrayAntworten, "antwort4"));
      Frage.setA1Korrekt(json.getZahlMitSchluessel(arrayAntworten, "korrekt1"));
      Frage.setA2Korrekt(json.getZahlMitSchluessel(arrayAntworten, "korrekt2"));
      Frage.setA3Korrekt(json.getZahlMitSchluessel(arrayAntworten, "korrekt3"));
      Frage.setA4Korrekt(json.getZahlMitSchluessel(arrayAntworten, "korrekt4"));
      //Frage.zeigeFrage(); // Testausgabe
      kahootset.addFrage(Frage);
      //kahootset.getEinzelneFrage(entries).zeigeFrage(); // Testausgabe
    }
    return kahootset;
  }
      
  private String getEinzelnesObjekt(String json, String objektName) {
    StringBuilder text = new StringBuilder(json.length());
    int maxTrigger = objektName.length() + 4;
    StringBuilder trigger = new StringBuilder(maxTrigger);
    trigger.append("\"").append(objektName).append("\":{");
    //System.out.println(trigger +" Objekt-Name: " +objektName.length() +" Trigger: " +trigger.length() +" Zähler: " +maxTrigger);
    int zaehlerTrigger = 0;
    int zaehlerKlammer = 0;
    boolean gefunden = false;
    for (char c: json.toCharArray()) {
      if (gefunden == false) {
        if (!((c == '\n') || (c == ' '))) {     // Zeichen, die ignoriert werden        
          // Suche die Triggerbedingung        
          if (c == trigger.charAt(zaehlerTrigger)) {
            if (zaehlerTrigger < maxTrigger) {
              //System.out.println(zaehlerTrigger +" " +c);  // Testausgabe Trigger => Zeichen um Zeichen
              if (zaehlerTrigger == (maxTrigger - 1)) {
                //System.out.println("Triggerwort gefunden"); // Testausgabe
                gefunden = true; 
              } else {
                zaehlerTrigger++;                          
              }
            }
          } else {
            zaehlerTrigger = 0;
          }
        }
        // Füge Zeichen ein, bis die schliessende Klammer kommt
      } else {
        if (!(c == '\n')) {     // Zeichen, die ignoriert werden                  
//        if (!((c == '\n') || (c == ' '))) {     // Zeichen, die ignoriert werden                  
          text.append(c);
          if (c == '{') {
            zaehlerKlammer++;
          } else if (c == '}') {
            zaehlerKlammer--;            
            if (zaehlerKlammer <= 0) {
              return text.toString();
            }
          }
        }
      }
    }
    return text.toString();   
  }
  
  private List<String> getMehrereObjekte(String json, String objektName) {
    List<String> objekte = new ArrayList<>();
    StringBuilder text = new StringBuilder(json.length());
    int maxTrigger = objektName.length() + 3;
    StringBuilder trigger = new StringBuilder(maxTrigger);
    trigger.append("\"").append(objektName).append("\":");
    //System.out.println(trigger +" Objekt-Name: " +objektName.length() +" Trigger: " +trigger.length() +" Zähler: " +maxTrigger);
    int zaehlerTrigger = 0;
    int zaehlerKlammer = 1;
    boolean gefunden = false;
    for (char c: json.toCharArray()) {
      if (gefunden == false) {
        if (!((c == '\n') || (c == ' '))) {     // Zeichen, die ignoriert werden        
          // Suche die Triggerbedingung        
          if (c == trigger.charAt(zaehlerTrigger)) {
            if (zaehlerTrigger < maxTrigger) {
              //System.out.println(zaehlerTrigger +" " +c);  // Testausgabe
              if (zaehlerTrigger == (maxTrigger - 1)) {
                //System.out.println("Triggerwort gefunden"); // Testausgabe
                gefunden = true; 
              } else {
                zaehlerTrigger++;                          
              }
            }
          } else {
            zaehlerTrigger = 0;
          }
        }
        // Füge Zeichen ein, bis die schliessende Klammer kommt
      } else {
        if (!(c == '\n')) {     // Zeichen, die ignoriert werden                  
          text.append(c);
          if (c == '{') {
            zaehlerKlammer++;
          } else if (c == '}') {
            zaehlerKlammer--;            
            if (zaehlerKlammer <= 0) {
              gefunden = false;
              zaehlerKlammer = 1;
              objekte.add(text.toString());
              //System.out.println(text.toString());  // Testausgabe
              text.delete(0, text.length());
            }
          }
        }
      }
    }
    return objekte;   
  }

  private String getArray(String json, String arrayName) {
    StringBuilder text = new StringBuilder(json.length());
    int maxTrigger = arrayName.length() + 4;
    StringBuilder trigger = new StringBuilder(maxTrigger);
    trigger.append("\"").append(arrayName).append("\":[");
    //System.out.println(trigger +" Array-Name: " +arrayName.length() +" Trigger: " +trigger.length() +" Zähler: " +maxTrigger);  // Testausgabe
    int zaehlerTrigger = 0;
    int zaehlerKlammer = 1;
    boolean gefunden = false;
    for (char c: json.toCharArray()) {
      // Suche die Triggerbedingung
      if (gefunden == false) {
        if (!((c == '\n') || (c == ' '))) {     // Zeichen, die ignoriert werden        
          if (c == trigger.charAt(zaehlerTrigger)) {
            if (zaehlerTrigger < maxTrigger) {
              //System.out.println(zaehlerTrigger +" " +c);  // Testausgabe
              if (zaehlerTrigger == (maxTrigger - 1)) {
                //System.out.println("Triggerwort gefunden"); // Testausgabe
                gefunden = true; 
              } else {
                zaehlerTrigger++;                          
              }
            }
          } else {
            zaehlerTrigger = 0;
          }
        }
      // Füge Zeichen ein, bis die schliessende Klammer kommt
      } else {
        text.append(c);
        if (c == '[') {
          zaehlerKlammer++;
        } else if (c == ']') {
          zaehlerKlammer--;            
          if (zaehlerKlammer <= 0) {
            return text.toString();
          }
        }
      }
    }
    return text.toString();   
  }

  private LinkedHashMap<String, String> getMapInfo(String objektInfo) {
    LinkedHashMap<String, String> map = new LinkedHashMap<>();
    KahootSet kahootSet = new KahootSet();
    List<String> keys = new ArrayList<>(kahootSet.getMapInfoKeyNames());
    String key;
    String value;
    //System.out.println("Objekt: " +objektInfo); // Testausgabe
    for (int i = 0; i < keys.size(); i++) {
      key = keys.get(i);
      value = getTextMitSchluessel(objektInfo, key);
      //System.out.println("Schlüssel " +key +" Value: " +value); // Testausgabe
      map.put(key, value);
    }
    return map;
  }  
  
  private String getTextMitSchluessel(String json, String key) {
    StringBuilder text = new StringBuilder(json.length());
    int maxTrigger = key.length() + 3;
    StringBuilder trigger = new StringBuilder(maxTrigger);
    trigger.append("\"").append(key).append("\":");
    //System.out.println(">> " +trigger + json);  // Testausgabe
    //System.out.println(trigger +" Schlüsselwort: " +key.length() +" Trigger: " +trigger.length() +" Zähler: " +maxTrigger);  // Testausgabe
    int zaehlerTrigger = 0;
    boolean gefunden = false;
    boolean toggle = false;   
    for (char c: json.toCharArray()) {
      if (gefunden == false) {
        if (!((c == '\n') || (c == ' ') || (c == ':'))) {     // Zeichen, die ignoriert werden        
          // Suche die Triggerbedingung        
          if (c == trigger.charAt(zaehlerTrigger)) {
            if (zaehlerTrigger < maxTrigger) {
              //System.out.println(zaehlerTrigger +" " +c); // Testausgabe
              if (zaehlerTrigger == (maxTrigger - 2)) {
                //System.out.println("Triggerwort gefunden"); // Testausgabe
                gefunden = true; 
              } else {
                zaehlerTrigger++;                          
              }
            }
          } else {
            zaehlerTrigger = 0;
          }
        }
      // Füge Zeichen zwischen " xxx " ein
      } else {
        if (!((c == '\n') || (c == ':'))) {     // Zeichen, die ignoriert werden                
          if (c == '\"') {
            if (toggle == false) {
              toggle = true;
            } else {
              return text.toString();            
            }
          } else if (toggle == true) {
            text.append(c);
            //System.out.println(c); // Testausgabe
          }
        }
      }
    }
    return text.toString();   
  }
  private int getZahlMitSchluessel(String json, String key) {
    StringBuilder text = new StringBuilder(json.length());
    int maxTrigger = key.length() + 3;
    StringBuilder trigger = new StringBuilder(maxTrigger);
    trigger.append("\"").append(key).append("\":");
    //System.out.println(">> " +trigger + json);  // Testausgabe
    //System.out.println(trigger +" Schlüsselwort: " +key.length() +" Trigger: " +trigger.length() +" Zähler: " +maxTrigger);  // Testausgabe
    int zaehlerTrigger = 0;
    boolean gefunden = false;
    for (char c: json.toCharArray()) {
      if (gefunden == false) {
        if (!((c == '\n') || (c == ' ') || (c == ':'))) {     // Zeichen, die ignoriert werden        
          // Suche die Triggerbedingung        
          if (c == trigger.charAt(zaehlerTrigger)) {
            if (zaehlerTrigger < maxTrigger) {
              //System.out.println(zaehlerTrigger +" " +c); // Testausgabe
              if (zaehlerTrigger == (maxTrigger - 2)) {
                //System.out.println("Triggerwort gefunden"); // Testausgabe
                gefunden = true; 
              } else {
                zaehlerTrigger++;                          
              }
            }
          } else {
            zaehlerTrigger = 0;
          }
        }
      // Füge Zeichen ein, bis das Ende kommt
      } else {
        if (!((c == '\n') || (c == ' ') || (c == ':'))) { // Zeichen, die ignoriert werden                
          if ((c == ',') || (c == '}') || (c == ']')) {   // Zeichen, die das Ende kennzeichnen                
            return Integer.parseInt(text.toString());
          } else {
            text.append(c);
            //System.out.println(c); // Testausgabe
          }
        }
      }
    }
    return -1;   
  }  
}
