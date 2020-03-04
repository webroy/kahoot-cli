// ----------------------------------------------------------------------------
// Zeit.java
// ----------------------------------------------------------------------------
// Funktion:   
// 
// SW:         Apache NetBeans IDE 11.1 ; JDK-11
// Datum:      23.02.2020
// Autor:      A. Albisser; PSI; WBWA /038; Tel. ++41563103450
// ----------------------------------------------------------------------------
package ch.edualb.logic;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Zeit {
  long dauerStoppuhr;
  
  public void starteStoppuhrMs() {
    dauerStoppuhr = System.currentTimeMillis();
    //System.out.println("Zeit Stoppuhr Start: " +dauerStoppuhr);  // Testausgabe
  }

  public long stoppeStoppuhrMs() {
    long ende = System.currentTimeMillis();
    //System.out.println("Zeit Stoppuhr Stopp: " +ende);  // Testausgabe
    dauerStoppuhr = ende - dauerStoppuhr;
    //System.out.println("Zeit Stoppuhr Stopp: " +dauerStoppuhr);  // Testausgabe
    return dauerStoppuhr;
  }

  public String getDatumUhrzeit() {
    Date date = new Date(); 
    SimpleDateFormat ft = 
    new SimpleDateFormat ("E yyyy.MM.dd 'um' hh:mm:ss a zzz");
    //System.out.println("Aktuelles Datum: " + ft.format(date));
    return ft.format(date);
  }  
}
