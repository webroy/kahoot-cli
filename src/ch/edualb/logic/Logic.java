// ----------------------------------------------------------------------------
// Logic.java
// ----------------------------------------------------------------------------
// Funktion:   Interface zwischen der Presentation- & Logic-Schicht
//             Die generelle Aufgabe eines Interfaces ist es, eine definierte 
//             Schnittstelle zwischen Ebenen/Blöcken/Schichten zu erreichen, so
//             dass Änderungen/Erweiterungen einfacher möglich sind.
//             In diesem Interface ist die Presentation-Schicht die Bezüger-
//             Klasse (von oben nach unten) und die Logic-Schicht die Anbieter- 
//             Klasse, wo die Methoden effektiv programmiert werden.
//             In der Logic-Schicht werden die Requests vom Benutzer 
//             entsprechend den aktuellen Einstellungen bearbeitet.
//
// SW:         Apache NetBeans IDE 11.1 ; JDK-11
// Datum:      09.02.2020
// Autor:      A. Albisser; PSI; WBWA /038; Tel. ++41563103450
// ----------------------------------------------------------------------------
package ch.edualb.logic;

import ch.edualb.kahoot.KahootSet;
import java.util.List;

public interface Logic {
  List<String> holeListeLernset();
  KahootSet holeLernset(String nameLernset);
  String holeText(String ort, String name);  
  void speichereLernset(String nameLernset, KahootSet kahootset);
  void speichereStatistik(String nameUser, String statistik);  
}
