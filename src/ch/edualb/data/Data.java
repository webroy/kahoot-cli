// ----------------------------------------------------------------------------
// Data.java
// ----------------------------------------------------------------------------
// Funktion:   Interface zwischen der Logic- & Data-Schicht
//             Die generelle Aufgabe eines Interfaces ist es, eine definierte 
//             Schnittstelle zwischen Ebenen/Blöcken/Schichten zu erreichen, so
//             dass Änderungen/Erweiterungen einfacher möglich sind.
//             In diesem Interface ist die Logic-Schicht die Bezüger-Klasse
//             (von oben nach unten) und die Data-Schicht die Anbieter-Klasse, 
//             wo die Methoden effektiv programmiert werden.
//             In der Data-Schicht werden die Lernsets gespeichert in: 
//             - Dateien
//             - Datenbank
//             In beiden Klassen werden diese Funktionen programmiert, 
//             entsprechend ihren Anforderungen
//
// SW:         Apache NetBeans IDE 11.1 ; JDK-11
// Datum:      08.02.2020
// Autor:      A. Albisser; PSI; WBWA /038; Tel. ++41563103450
// ----------------------------------------------------------------------------
package ch.edualb.data;

import java.util.List;

public interface Data {
  void speichereLernset(String nameLernset, String outputStream);
  void speichereStatistik(String nameUser, String outputStream);  
  String holeLernset(String nameLernset);
  String holeAsciiString();  
  List<String> holeAuswahlLernset();
}
