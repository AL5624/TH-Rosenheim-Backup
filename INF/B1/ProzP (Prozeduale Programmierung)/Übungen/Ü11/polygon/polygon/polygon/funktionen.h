#ifndef _POLY_H_
#define _POLY_H_


typedef enum
{
	CL_NOERROR,			/* Parameter sind gültig */
	CL_EMINOUTOFRANGE,	/* emin ist kleiner/gleich 2 */
	CL_EMAXOUTOFRANGE,	/* emax ist kleiner als emin */
	CL_RADIUSINVALID,	/* Radius ist kleiner/gleich 0.0 */
	CL_PARAMMISSING,	/* einer der Parameter emin/emax/r fehlt */
	CL_UNKOWNPARAM		/* ein nicht definierter String wurde in der Kommandozeile verwendet */
} cl_errors_t;

typedef enum
{
	FALSE,
	TRUE
} boolean_t;

/* Prototypen der benötigten Funktionen */
void strich (int n, char c); /* gibt n mal das Zeichen c aus  */

typedef struct 
{
	int minecken;				//Mindestanzahl an Ecken
	int maxecken;				//maximale Anzahl an Ecken
	double umkreisradius;		//Radius des Umkreises des Polygons
}mainParameter_t;

//typedef struct mainParameter_s mainParameter_t;

struct polygon_s
{
	int anzahlecken;			//Anzahl der Ecken
	double flaeche;				//Fläche des Polygons
	double seitenlaenge;		//Seitenlänge des Polygons
	double abweichung;			//Abweichung zur Kreisfläche
};

typedef struct polygon_s polygon_t;


void berechnePolygone(polygon_t data[], mainParameter_t params);

double rad2deg(double w_rad);

double berecheSeitenlaenge(double radius, int n);

double berechneFlaeche(int n, double l);

double Kreisflaeche(double radius);

void AusgabePolygone(polygon_t daten[], int anzZeilen);
#endif
