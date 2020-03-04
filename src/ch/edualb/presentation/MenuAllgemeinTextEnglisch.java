// ----------------------------------------------------------------------------
// MenuAllgemeinTextEnglisch.java
// ----------------------------------------------------------------------------
// Funktion:   Benutzerführung im Hauptmenu sowie bei allgemeinen Texten
//             in Englisch
// 
// SW:         Apache NetBeans IDE 11.1 ; JDK-11
// Datum:      02.02.2020
// Autor:      A. Albisser; PSI; WBWA /038; Tel. ++41563103450
// ----------------------------------------------------------------------------
package ch.edualb.presentation;

public class MenuAllgemeinTextEnglisch {

  public void zeigeHauptmenuStart() {
    System.out.println("\n--- Main menu (enter a number) ---------------------------------");
    System.out.println("< 1 > Principle of question/answer (q/a)");     
    System.out.println("< 2 > Principle of quartett");     
    System.out.println("< 3 > Storage room File/Database");    
    System.out.println("< 4 > Language D/E");
    System.out.println("< 5 > Enter your name for learning or as author");    
    System.out.println("< 6 > Information to the learn principle itself");
    System.out.println("< 7 > Information to this application");
    System.out.println("----------------------------------------------------------------"); 
    System.out.println("< 0 > Exit"); 
    System.out.println("----------------------------------------------------------------"); 
  }  

  public void zeigeEinstellungSpeichermedien() {
    System.out.println("\n--- Main menu > Storage room -----------------------------------");
    System.out.println("< 1 > File");     
    System.out.println("< 2 > Database");     
    System.out.println("----------------------------------------------------------------"); 
    System.out.println("< 0 > Exit"); 
    System.out.println("----------------------------------------------------------------"); 
  }  

  public void zeigeEinstellungSprache() {
    System.out.println("\n--- Main menu > Language ---------------------------------------");
    System.out.println("< 1 > German");     
    System.out.println("< 2 > English");     
    System.out.println("----------------------------------------------------------------"); 
    System.out.println("< 0 > Exit"); 
    System.out.println("----------------------------------------------------------------"); 
  }  
  
  public void zeigeNameSpielerEingeben() {
    System.out.println("\n--- Main menu > Name -------------------------------------------");
    System.out.println("Enter your name for learning or editing data");
  }    

  public void zeigeInfoLernsysteme() {
    System.out.println("\n--- Main menu > Information to the learning principles ---------------");
    System.out.println(">>> Principle of question and answer (Q/A) <<<");
    System.out.println("You can write your own questions and answers and put it together to a");
    System.out.println("new set. You can use eigther your own as other sets for learning.");
    System.out.println("Every set contains some information to the set itself and an amount of");
    System.out.println("different questions-set. Every question-set has some information like");    
    System.out.println("author, level, etc., the question itself and 4 answers. Each answer");    
    System.out.println("has the information if its true or false.");    
    System.out.println("If you are in the learning mode, the questions appears in a random order.");
    System.out.println("At the end, you will get a feedback");
    System.out.println("");
    System.out.println(">>> Principle of Quartett <<<");
    System.out.println("Soon come");
  }    

  public void zeigeInfoProgramm() {
    System.out.println("\n--- Main menu > Information to the program ---------------------------");
    System.out.println("The idea for this application came from a teacher. He wanted a software");
    System.out.println("application, where his students could learn by themselve and find out");
    System.out.println("what they really know. This program was written while an education");
    System.out.println("module called \"Nachdiplomstudium SW-Engineering\" by https://abbts.ch/");
    System.out.println("To the project team belong Samira Derokhar, Roy Stamm & Alfred Albisser");
    System.out.println("This is an education project from Java Beginners. We apologize us, if");
    System.out.println("there occure problems. We assume no liability for this application and");
    System.out.println("for results of other users");
    System.out.println("");
    System.out.println("You can generate your own questions and answers by the application");
    System.out.println("itsself. Also you can edit the text files written in json-format, but"); 
    System.out.println("take care to follow strictly structure and the seperation signs. "); 
    System.out.println("This programm is for free (2020-03-06)"); 
  }    

  public void zeigeUngueltigeEingabe() {
    System.out.println("No valid input");
  }

  public void zeigeUnerlaubtesZeichen(char c) {
    System.out.println(c +" is not allowed and ignored");
  }

  public void zeigeFertigGelesen() {
    System.out.println("----------------------------------------------------------------"); 
    System.out.println("\nPress enter if you read it");
    System.out.println("----------------------------------------------------------------"); 
  }
}