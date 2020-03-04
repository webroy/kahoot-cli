// ----------------------------------------------------------------------------
// KahootLernen.java
// ----------------------------------------------------------------------------
// Funktion:   
// 
// SW:         Apache NetBeans IDE 11.1 ; JDK-11
// Datum:      18.02.2020
// Autor:      A. Albisser; PSI; WBWA /038; Tel. ++41563103450
// ----------------------------------------------------------------------------
package ch.edualb.presentation;

import ch.edualb.kahoot.Antwort;
import ch.edualb.kahoot.Frage;
import ch.edualb.kahoot.StatistikKahootSet;
import ch.edualb.logic.LernPlattform;
import ch.edualb.logic.Logic;
import ch.edualb.logic.Zeit;
import static ch.edualb.presentation.Hauptmenu.sprache;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

public class KahootLernen {

  public void beantworteFragen(LinkedHashMap<String, String> mapInfo, List<String> keys, List<Frage> fragen, String spielerName) {
    StatistikKahootSet statistik = new StatistikKahootSet();
    kopiereMapInfoInStatistik(mapInfo, keys, statistik);    
    statistik.setUser(spielerName);
    Zeit zeit = new Zeit();    
    statistik.setDatum(zeit.getDatumUhrzeit());           
    int anzahlFragen = fragen.size();    
    int zufallsReihenfolge [] = generiereEinmaligeZufallszahlen(anzahlFragen);
    Frage frage;
    boolean stopp;
    int zaehlerFragen = 0;
    do {
      frage = fragen.get(zufallsReihenfolge[zaehlerFragen]);
      zeigeFrageLernen(frage, (zaehlerFragen + 1), anzahlFragen);
      stopp = stelleFragePruefeAntwort(frage, statistik);
      if (stopp == false) { // bei Abbruch Zähler nicht mehr erhöhen
        zaehlerFragen++;
      }
    } while ((zaehlerFragen < anzahlFragen) && (stopp == false));
    if (zaehlerFragen > 0) { // nur, wenn überhaupt eine Frage beantwortet wurde
      getStatistikNachFragerunde(statistik, anzahlFragen, zaehlerFragen);
      statistik.zeigeKahootSetStatistikUser();
      zeigeLösungZuFalscheAntworten(statistik, fragen);
      Tastatur tastatur = new Tastatur();    
      tastatur.warteBisGelesen();    
      Logic logic = new LernPlattform();      // Logic ist Interface & LernPlattform Implementierung
      logic.speichereStatistik(spielerName, getStringStatistikUser(statistik, fragen));      
    }
  }

  public boolean stelleFragePruefeAntwort(Frage frage, StatistikKahootSet statistik) {  
    Zeit zeit = new Zeit();
    zeit.starteStoppuhrMs();    
    boolean antwortRichtig;
    Tastatur tastatur = new Tastatur();
    int eingabeAntwort = tastatur.pruefeEingabe(0, 5);
    //System.out.println("Antwort = : " +antwort); // Testausgabe    
    boolean stopp;
    if (eingabeAntwort == 0) {
      stopp = true;
    } else {    
      if (pruefeAntwort(frage, eingabeAntwort) == true) {
        statistik.setAnzahlAufgabenRichtig((statistik.getAnzahlAufgabenRichtig() + 1));
        antwortRichtig = true;
      } else {
        statistik.setAnzahlAufgabenFalsch((statistik.getAnzahlAufgabenFalsch() + 1));
        antwortRichtig = false;
      }
      long dauer = zeit.stoppeStoppuhrMs();
      //System.out.println("Dauer = : " +dauer); // Testausgabe
      Antwort antwortNeu = new Antwort(frage.getId(), frage.getLevel(), dauer, eingabeAntwort, antwortRichtig);
      statistik.addAntwort(antwortNeu);
      stopp = false;
    }
    return stopp;
  }

  public boolean pruefeAntwort(Frage frage, int antwort) {
    boolean korrekt = false;
    switch (antwort) {
      case 1: if (frage.getA1Korrekt() == 1) korrekt = true; break;
      case 2: if (frage.getA2Korrekt() == 1) korrekt = true; break;
      case 3: if (frage.getA3Korrekt() == 1) korrekt = true; break;
      case 4: if (frage.getA4Korrekt() == 1) korrekt = true; break;      
      default: System.out.println("Error input answer"); break;
    }
    return korrekt;
  }    

  public void kopiereMapInfoInStatistik(LinkedHashMap<String, String> mapInfo, List<String> keys, StatistikKahootSet statistik) {
    statistik.setSpiel(mapInfo.get(keys.get(0)));
    statistik.setTyp(mapInfo.get(keys.get(1)));
    statistik.setHauptKategorie(mapInfo.get(keys.get(2)));
    statistik.setUnterKategorie(mapInfo.get(keys.get(3)));    
    statistik.setThema(mapInfo.get(keys.get(4)));    
    statistik.setSchwierigkeitsgrad(mapInfo.get(keys.get(5)));    
  }    

  public void getStatistikNachFragerunde(StatistikKahootSet statistik, int anzahlFragen, int zaehlerFragen) {
    // Achtung Reihenfolge beachten, da z.T Berechnungen von Resultaten aus 
    // vorangegangenen Berechnungen (v.a. Quaoten)abhängig sind
    statistik.setAnzahlAufgabenGesamt(anzahlFragen);               
    statistik.setAnzahlAufgabenGeloest(zaehlerFragen);
    statistik.setQuoteTotal((zaehlerFragen * 100) / anzahlFragen);
    if (zaehlerFragen > 0) { //Division durch 0 verhindern
      statistik.setQuoteRichtig((statistik.getAnzahlAufgabenRichtig() * 100) / zaehlerFragen);
    } else {
      statistik.setQuoteRichtig(0);
    }
    getDauerGesamtUndDurchschnitt(statistik, zaehlerFragen);
    getDauerMinUndMax(statistik); 
    ermittleEffizienz(statistik);
//    System.out.println("Quote nach Level:    " +quoteNachLevel);   
  }
  
  public void getDauerGesamtUndDurchschnitt(StatistikKahootSet statistik, int zaehlerFragen) {
    long dauerGesamt = 0;
    for (int i = 0; i < statistik.getAnzahlAntworten(); i++) {
      dauerGesamt += statistik.getEinzelneAntwort(i).getDauer();
    }
    statistik.setDauerGesamt(dauerGesamt);
    if (zaehlerFragen > 0) { //Division durch 0 verhindern
      statistik.setDauerDurchschnitt((long) dauerGesamt / zaehlerFragen);
    } else {
      statistik.setDauerDurchschnitt(0);      
    }      
  }
  
  public void getDauerMinUndMax(StatistikKahootSet statistik) {
    int anzahlAntworten = statistik.getAnzahlAntworten();
    long dauerSchnellsteAntwort = statistik.getEinzelneAntwort(0).getDauer();
    long dauerLangsamsteAntwort = dauerSchnellsteAntwort;
    for (int i = 1; i < anzahlAntworten; i++) {
      if (statistik.getEinzelneAntwort(i).getDauer() < dauerSchnellsteAntwort) {
        dauerSchnellsteAntwort = statistik.getEinzelneAntwort(i).getDauer();
      }
      if (statistik.getEinzelneAntwort(i).getDauer() > dauerLangsamsteAntwort) {
        dauerLangsamsteAntwort = statistik.getEinzelneAntwort(i).getDauer();
      }
    }
    statistik.setDauerSchnellsteAntwort(dauerSchnellsteAntwort);
    statistik.setDauerLangsamsteAntwort(dauerLangsamsteAntwort);
  }
  
  public void ermittleEffizienz(StatistikKahootSet statistik) {
    int anzahlAntworten = statistik.getAnzahlAntworten();
    int anzahlSchnellerAlsDurchschnitt = 0;
    int anzahlSchnellerAlsDurchschnittUndRichtig = 0;
    long dauerDurchschnitt = statistik.getDauerDurchschnitt();
    for (int i = 0; i < anzahlAntworten; i++) {
      if (statistik.getEinzelneAntwort(i).getDauer() < dauerDurchschnitt) {
        anzahlSchnellerAlsDurchschnitt++;
        if (statistik.getEinzelneAntwort(i).istAntwortRichtig()) {
          anzahlSchnellerAlsDurchschnittUndRichtig++;
        }
      }
    }
    statistik.setAnzahlSchnellerAlsDurchschnitt(anzahlSchnellerAlsDurchschnitt);
    statistik.setAnzahlSchnellerAlsDurchschnittRichtig(anzahlSchnellerAlsDurchschnittUndRichtig);
    if (anzahlSchnellerAlsDurchschnitt > 0) { //Division durch 0 verhindern
      statistik.setquoteSchnellRichtig((anzahlSchnellerAlsDurchschnittUndRichtig *100) / anzahlSchnellerAlsDurchschnitt);
    } else {
      statistik.setquoteSchnellRichtig(0);
    }
  }  
  
  public int[] generiereEinmaligeZufallszahlen(int anzahlZahlen) {
    Random randomno = new Random();
    int index;
    int i, j;
    // Array erzeugen und mit -1 initialisieren => Zufallszahlen
    // sind "von" - "bis"
    int[] randomIndex = new int[anzahlZahlen];
    for (j = 0; j < anzahlZahlen; j++) {
      randomIndex[j] = -1;
    }

    // Array mit Zufallszahlen zwischen 0 und anzahlFragen füllen, die
    // aber nur einmal vorkommen dürfen
    for (i = 0; i < anzahlZahlen; i++) {
      j = 0;
      index = randomno.nextInt(anzahlZahlen);     // Zufallszahl von 0 - anzahlFragen
      
      // Alle bisherigen Zuweisungen prüfen, ob Zufallszahl schon mal verwendet wurde
      // Solange wiederholen bis eine noch nicht verwendete Zahl gefunden wird
      while (j <= i) {
        if (index == randomIndex[j]) {            // wenn ja
          index = randomno.nextInt(anzahlZahlen); // Mit neuer Zufallszahl nochmals starten
          j = 0;
        } else {
          j++;
        }
      }
      randomIndex[i] = index;                 // Neue Zufallszahl speichern
      //System.out.println("Zufallsreihenfolge: " +index +" "); // Testausgabe
    }
    return randomIndex;
  }

  public void zeigeFrageLernen(Frage frage, int nr, int total) {
    if (sprache == Hauptmenu.Sprache.DEUTSCH) {
      KahootMenuTextDeutsch deutsch = new KahootMenuTextDeutsch();
      deutsch.zeigeFrageLernen(frage, nr, total);
    } else {
      KahootMenuTextEnglisch englisch = new KahootMenuTextEnglisch();
      englisch.zeigeFrageLernen(frage, nr, total);
    }
  }

  public String getStringStatistikUser(StatistikKahootSet statistik, List<Frage> fragen) {
    StringBuilder text = new StringBuilder(1000);
    if (sprache == Hauptmenu.Sprache.DEUTSCH) {
      text.append("***********************************************************************************************************************");
      text.append("\n***********************************************************************************************************************");    
      text.append("\nStatistik:");
      text.append("\n< 1> Anwender:                                     ").append(statistik.getUser());
      text.append("\n< 2> Datum:                                        ").append(statistik.getDatum());
      text.append("\n< 3> Lernprinzip:                                  ").append(statistik.getSpiel()).append(", ").append(statistik.getTyp());
      text.append("\n< 4> Kategorie:                                    ").append(statistik.getHauptKategorie()).append(", ").append(statistik.getUnterKategorie()).append(", ").append(statistik.getThema());
      text.append("\n< 5> Schwierigkeitsgrad:                           ").append(statistik.getSchwierigkeitsgrad());
      text.append("\n< 6> Gesamtdauer für alle Antworten:               ").append(statistik.getDauerGesamt()).append(" ms");
      text.append("\n< 7> Durchschnittliche Dauer aller Antworten:      ").append(statistik.getDauerDurchschnitt()).append(" ms");
      text.append("\n< 8> Dauer der schnellsten / langsamsten Antwort:  ").append(statistik.getDauerSchnellsteAntwort()).append(" / ").append(statistik.getDauerLangsamsteAntwort()).append(" ms");
      text.append("\n< 9> Anzahl Fragen gesamt / gelöst / Quote:        ").append(statistik.getAnzahlAufgabenGesamt()).append(" / ").append(statistik.getAnzahlAufgabenGeloest()).append(" / ").append(statistik.getQuoteTotal()).append("%");
      text.append("\n<10> richtig / falsch / Quote:                     ").append(statistik.getAnzahlAufgabenRichtig()).append(" / ").append(statistik.getAnzahlAufgabenFalsch()).append(" / ").append(statistik.getQuoteRichtig()).append("%");
      text.append("\n<11> schneller als Durchschnitt / richtig / Quote: ").append(statistik.getAnzahlSchnellerAlsDurchschnitt()).append(" / ").append(statistik.getAnzahlSchnellerAlsDurchschnittRichtig()).append(" / ").append(statistik.getquoteSchnellRichtig()).append("%");    
    } else {
      text.append("***********************************************************************************************************************");
      text.append("\n***********************************************************************************************************************");    
      text.append("\nStatistic:");
      text.append("\n< 1> User:                                         ").append(statistik.getUser());
      text.append("\n< 2> Date:                                         ").append(statistik.getDatum());
      text.append("\n< 3> Principle:                                    ").append(statistik.getSpiel()).append(", ").append(statistik.getTyp());
      text.append("\n< 4> Category:                                     ").append(statistik.getHauptKategorie()).append(", ").append(statistik.getUnterKategorie()).append(", ").append(statistik.getThema());
      text.append("\n< 5> Difficulty:                                   ").append(statistik.getSchwierigkeitsgrad());
      text.append("\n< 6> Duration for all answers:                     ").append(statistik.getDauerGesamt()).append(" ms");
      text.append("\n< 7> Average time for a question:                  ").append(statistik.getDauerDurchschnitt()).append(" ms");
      text.append("\n< 8> Duration for fastest / slowest answer:        ").append(statistik.getDauerSchnellsteAntwort()).append(" / ").append(statistik.getDauerLangsamsteAntwort()).append(" ms");
      text.append("\n< 9> Count of questions over all / solved / quote: ").append(statistik.getAnzahlAufgabenGesamt()).append(" / ").append(statistik.getAnzahlAufgabenGeloest()).append(" / ").append(statistik.getQuoteTotal()).append("%");
      text.append("\n<10> right / wrong / quote:                        ").append(statistik.getAnzahlAufgabenRichtig()).append(" / ").append(statistik.getAnzahlAufgabenFalsch()).append(" / ").append(statistik.getQuoteRichtig()).append("%");
      text.append("\n<11> Faster as average / right  / quote:           ").append(statistik.getAnzahlSchnellerAlsDurchschnitt()).append(" / ").append(statistik.getAnzahlSchnellerAlsDurchschnittRichtig()).append(" / ").append(statistik.getquoteSchnellRichtig()).append("%");          
    }
    int anzahlFalsch = statistik.getAnzahlAufgabenFalsch();
    if (anzahlFalsch > 0) {
      int idAntworten[][] = getIdUndGewaehlteAntwortVonAllenFalschenAntworten(statistik, anzahlFalsch);
      int idFragen[][] = sortiere2DimensionalesIntArrayNachErstemElement(idAntworten);
      if (sprache == Hauptmenu.Sprache.DEUTSCH) {
        text.append("\n<12> ID der falschen Antworten:                    ").append(fragen.get(idFragen[0][0]).getId());          
      } else {
        text.append("\n<12> ID from the wrong answer:                     ").append(fragen.get(idFragen[0][0]).getId());          
      }
      for (int i = 1; i < anzahlFalsch; i++) {
        text.append(", ").append(fragen.get(idFragen[i][0]).getId());
      }
    }
    if (sprache == Hauptmenu.Sprache.DEUTSCH) {
      text.append("\n***********************************************************************************************************************");   
      text.append("\n\nRoh-Daten der erfassten Antworten (Reihenfolge nach Fragestellung):");    
    } else {
      text.append("\n***********************************************************************************************************************");   
      text.append("\n\nRow data of the answers (random order):");          
    }
    List<Antwort> alleAntworten = statistik.getAlleAntworten();
    int anzahlAntworten = statistik.getAnzahlAntworten();
    text.append("\n<13> ID:          ");
    for (int i = 0; i < anzahlAntworten; i++) {
      text.append(alleAntworten.get(i).getId());
      if (i < (anzahlAntworten - 1)) {
        text.append(", ");
      }
    }
    if (sprache == Hauptmenu.Sprache.DEUTSCH) {
      text.append("\n<14> Korrekt:     ");
    } else {
      text.append("\n<14> Correct:     ");
    }
    for (int i = 0; i < anzahlAntworten; i++) {
      text.append(alleAntworten.get(i).istAntwortRichtig());
      if (i < (anzahlAntworten - 1)) {
        text.append(", ");
      }
    }
    if (sprache == Hauptmenu.Sprache.DEUTSCH) {
      text.append("\n<15> Antwort-Nr.: ");
    } else {
      text.append("\n<15> Nr. of answer: ");
    }
    for (int i = 0; i < anzahlAntworten; i++) {
      text.append(alleAntworten.get(i).getGewaehlteAntwort());
      if (i < (anzahlAntworten - 1)) {
        text.append(", ");
      }
    }
    text.append("\n<16> Level:       ");
    for (int i = 0; i < anzahlAntworten; i++) {
      text.append(alleAntworten.get(i).getLevel());
      if (i < (anzahlAntworten - 1)) {
        text.append(", ");
      }
    }
    if (sprache == Hauptmenu.Sprache.DEUTSCH) {
      text.append("\n<17> Dauer:       ");
    } else {
      text.append("\n<17> Duration:    ");
    }
    for (int i = 0; i < anzahlAntworten; i++) {
      text.append(alleAntworten.get(i).getDauer());
      if (i < (anzahlAntworten - 1)) {
        text.append(", ");
      }
    }

    // Falsche Antworten, sortiert nach ID mit entsprecehnder Fragen und der 
    // richtiger Antwort noch anzeigen
    if (anzahlFalsch > 0) {
      int idAntworten[][] = getIdUndGewaehlteAntwortVonAllenFalschenAntworten(statistik, anzahlFalsch);
      int idFragen[][] = sortiere2DimensionalesIntArrayNachErstemElement(idAntworten);
      if (sprache == Hauptmenu.Sprache.DEUTSCH) {
        text.append("\n***********************************************************************************************************************");    
        text.append("\nEs sind die folgenden ").append(anzahlFalsch).append(" Fragen falsch beantwortet worden:");
      } else {
        text.append("\n***********************************************************************************************************************");    
        text.append("\nThe following ").append(anzahlFalsch).append(" answers are wrong:");        
      }
      for (int i  = 0; i < anzahlFalsch; i++) {        
        text.append("\n-----------------------------------------------------------------------------------------------------------------------");
        /* // Testausgabe in Datei
        text.append("\nID: ").append(fragen.get(idFragen[i][0]).getId()); // Sortierte Anzeige, also get ID von Frage
        text.append(",   Korrekt: ").append(alleAntworten.get(idAntworten[i][0]).istAntwortRichtig());        
        text.append(",   Nr. der Antwort: ").append(alleAntworten.get(idAntworten[i][0]).getGewaehlteAntwort());                
        text.append(",   Level: ").append(alleAntworten.get(idAntworten[i][0]).getLevel());
        text.append(",   Dauer: ").append(alleAntworten.get(idAntworten[i][0]).getDauer()).append("ms");
        */ 
        if (sprache == Hauptmenu.Sprache.DEUTSCH) {
          text.append("\nFrage:            ").append(fragen.get(idFragen[i][0]).getFrage());        
        } else {
          text.append("\nQuestion:         ").append(fragen.get(idFragen[i][0]).getFrage());        
        }
        for (int j = 0; j < 4; j++) {
          if (sprache == Hauptmenu.Sprache.DEUTSCH) {
            switch (j) {
              case 0: if (fragen.get(idFragen[i][0]).getA1Korrekt() != 0) text.append("\nRichtige Antwort: ").append(fragen.get(idFragen[i][0]).getAntwort1()); break;
              case 1: if (fragen.get(idFragen[i][0]).getA2Korrekt() != 0) text.append("\nRichtige Antwort: ").append(fragen.get(idFragen[i][0]).getAntwort2()); break;
              case 2: if (fragen.get(idFragen[i][0]).getA3Korrekt() != 0) text.append("\nRichtige Antwort: ").append(fragen.get(idFragen[i][0]).getAntwort3()); break;
              case 3: if (fragen.get(idFragen[i][0]).getA4Korrekt() != 0) text.append("\nRichtige Antwort: ").append(fragen.get(idFragen[i][0]).getAntwort4()); break;
            }
          } else {
            switch (j) {
              case 0: if (fragen.get(idFragen[i][0]).getA1Korrekt() != 0) text.append("\nRight answer: ").append(fragen.get(idFragen[i][0]).getAntwort1()); break;
              case 1: if (fragen.get(idFragen[i][0]).getA2Korrekt() != 0) text.append("\nRight answer: ").append(fragen.get(idFragen[i][0]).getAntwort2()); break;
              case 2: if (fragen.get(idFragen[i][0]).getA3Korrekt() != 0) text.append("\nRight answer: ").append(fragen.get(idFragen[i][0]).getAntwort3()); break;
              case 3: if (fragen.get(idFragen[i][0]).getA4Korrekt() != 0) text.append("\nRight answer: ").append(fragen.get(idFragen[i][0]).getAntwort4()); break;
            }                  
          }
        }
        if (sprache == Hauptmenu.Sprache.DEUTSCH) {
          switch (idFragen[i][1]) {
            case 1: text.append("\nIhre Antwort:     ").append(fragen.get(idFragen[i][0]).getAntwort1()); break;
            case 2: text.append("\nIhre Antwort:     ").append(fragen.get(idFragen[i][0]).getAntwort2()); break;
            case 3: text.append("\nIhre Antwort:     ").append(fragen.get(idFragen[i][0]).getAntwort3()); break;
            case 4: text.append("\nIhre Antwort:     ").append(fragen.get(idFragen[i][0]).getAntwort4()); break;
            default: text.append("\n???"); break;
          }        
        } else {
          switch (idFragen[i][1]) {
            case 1: text.append("\nYour answer:      ").append(fragen.get(idFragen[i][0]).getAntwort1()); break;
            case 2: text.append("\nYour answer:      ").append(fragen.get(idFragen[i][0]).getAntwort2()); break;
            case 3: text.append("\nYour answer:      ").append(fragen.get(idFragen[i][0]).getAntwort3()); break;
            case 4: text.append("\nYour answer:      ").append(fragen.get(idFragen[i][0]).getAntwort4()); break;
            default: text.append("\n???"); break;
          }                  
        }
      }
      text.append("\n");      
    }
    return text.toString();
  }
  

  public int[][] getIdUndGewaehlteAntwortVonAllenFalschenAntworten(StatistikKahootSet statistik, int anzahlFalsch) {
    int id[][] = new int[anzahlFalsch][2];
    int zaehlerFalsch = 0;
    List<Antwort> alleAntworten = statistik.getAlleAntworten();
    int anzahlAntworten = alleAntworten.size();    
    // Alle Antworten durchgehen und die ID, sowie die gewählte Antwort der 
    // falschen Antworten erfassen
    for (int i  = 0; i < anzahlAntworten; i++) {
      if (alleAntworten.get(i).istAntwortRichtig() == false) {
        id[zaehlerFalsch][0] = alleAntworten.get(i).getId();
        id[zaehlerFalsch][1] = alleAntworten.get(i).getGewaehlteAntwort();          
        //System.out.println("ID: " +id[zaehlerFalsch][0] +", Nr. der gewählten Antwort " +id[zaehlerFalsch][1]); // Testausgabe unsortiert
        zaehlerFalsch++;          
      }
    }      
    return id;
  }   

  public int[][] sortiere2DimensionalesIntArrayNachErstemElement(int[][] array) {
    // Bubble-Sort nach ID
    int tmp1, tmp2;
    int size = array.length;
    //System.out.println("Anzahl zu sortieren" +size);
    for (int i  = 0; i < size; i++) {
      for (int j  = 0; j < (size - i - 1); j++) {
        if (array[j][0] > array[j + 1][0]) {
          tmp1 = array[j][0];
          tmp2 = array[j][1];
          array[j][0] = array[j + 1][0];
          array[j][1] = array[j + 1][1];            
          array[j + 1][0] = tmp1;            
          array[j + 1][1] = tmp2;                        
        }
      }
    }      
    return array;
  } 
  
  public void zeigeLösungZuFalscheAntworten(StatistikKahootSet statistik, List<Frage> fragen) {
    int anzahlFalsch = statistik.getAnzahlAufgabenFalsch();
    if (anzahlFalsch > 0) {
      if (sprache == Hauptmenu.Sprache.DEUTSCH) {
        System.out.println("\nEs sind insgesamt " +anzahlFalsch +" Fragen falsch beantwortet:");
      } else {
        System.out.println("\nThere are " +anzahlFalsch +" questions wrong:");
      }
      int id[][] = getIdUndGewaehlteAntwortVonAllenFalschenAntworten(statistik, anzahlFalsch);
//      id = sortiere2DimensionalesIntArrayNachErstemElement(id);
//      System.out.println("\nSortierte Ausgabe:");
      for (int i  = 0; i < anzahlFalsch; i++) {        
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
//        System.out.println("ID: " +id[i][0] +", Nr. der gewählten Antwort " +id[i][1]); // Testausgabe                
        System.out.println("Frage:            " +fragen.get(id[i][0]).getFrage()); // Testausgabe
        for (int j = 0; j < 4; j++) {
          if (sprache == Hauptmenu.Sprache.DEUTSCH) {
            switch (j) {
              case 0: if (fragen.get(id[i][0]).getA1Korrekt() != 0) System.out.println("Richtige Antwort: " +fragen.get(id[i][0]).getAntwort1()); break;
              case 1: if (fragen.get(id[i][0]).getA2Korrekt() != 0) System.out.println("Richtige Antwort: " +fragen.get(id[i][0]).getAntwort2()); break;
              case 2: if (fragen.get(id[i][0]).getA3Korrekt() != 0) System.out.println("Richtige Antwort: " +fragen.get(id[i][0]).getAntwort3()); break;
              case 3: if (fragen.get(id[i][0]).getA4Korrekt() != 0) System.out.println("Richtige Antwort: " +fragen.get(id[i][0]).getAntwort4()); break;
            }
          } else {
            switch (j) {
              case 0: if (fragen.get(id[i][0]).getA1Korrekt() != 0) System.out.println("Right answer:     " +fragen.get(id[i][0]).getAntwort1()); break;
              case 1: if (fragen.get(id[i][0]).getA2Korrekt() != 0) System.out.println("Right answer:     " +fragen.get(id[i][0]).getAntwort2()); break;
              case 2: if (fragen.get(id[i][0]).getA3Korrekt() != 0) System.out.println("Right answer:     " +fragen.get(id[i][0]).getAntwort3()); break;
              case 3: if (fragen.get(id[i][0]).getA4Korrekt() != 0) System.out.println("Right answer:     " +fragen.get(id[i][0]).getAntwort4()); break;
            }            
          }
        }
        if (sprache == Hauptmenu.Sprache.DEUTSCH) {
          switch (id[i][1]) {
            case 1: System.out.println("Ihre Antwort:     " +fragen.get(id[i][0]).getAntwort1()); break;
            case 2: System.out.println("Ihre Antwort:     " +fragen.get(id[i][0]).getAntwort2()); break;
            case 3: System.out.println("Ihre Antwort:     " +fragen.get(id[i][0]).getAntwort3()); break;
            case 4: System.out.println("Ihre Antwort:     " +fragen.get(id[i][0]).getAntwort4()); break;
            default: System.out.println("???"); break;
          }
        } else {
          switch (id[i][1]) {
            case 1: System.out.println("Your answer:      " +fragen.get(id[i][0]).getAntwort1()); break;
            case 2: System.out.println("Your answer:      " +fragen.get(id[i][0]).getAntwort2()); break;
            case 3: System.out.println("Your answer:      " +fragen.get(id[i][0]).getAntwort3()); break;
            case 4: System.out.println("Your answer:      " +fragen.get(id[i][0]).getAntwort4()); break;
            default: System.out.println("???"); break;
          }
        }
      }
      System.out.println("-----------------------------------------------------------------------------------------------------------------------\n\n");          
    }
  }  
}
