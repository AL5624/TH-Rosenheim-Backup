//*******************************************************************
// include-file: inf1.h                                              
// alles Allgemeine für Übungen im Semester inf1                     
// Verfasser   : Feindor / Schmidt                                      
// Datum       : Urprogramm: 1.10.1996                                           
// letzte Änderungen: 5.11.2010
//*******************************************************************
#ifndef INF1_H
#define INF1_H

// Einbinden von nötigen Header-Dateien                              
#include <stdio.h>  // Standard Input/ Output  z.B. scanf, printf 
#include <stdlib.h>  // für Standard-Utility-Funktionen wie exit() oder rand() 


// Standarddeklarationen 
typedef unsigned char byte;
typedef enum {false=0, true=1} bool;   // in C99 schon vorhanden

// Deklaration von Prototypen, die in gesonderten Moduln (z.B.inf1.c)
// bereitgestellt werden. Diese Moduln müssen daher dazugelinkt werden 
void strich (int n, char c);      // gibt n mal das Zeichen c aus 
void dialog(char *ueberschrift);  // gibt Überschrift aus

// die folgenden Funktionen kommen später dazu 
void Assert (bool ok, char *meldung);// gibt Fehlermeldung, wenn nicht ok 

// kopiert in ziel aus quelle ab anzahl character
// void copy (char * ziel, char * quelle, short ab, short anzahl);

#endif

