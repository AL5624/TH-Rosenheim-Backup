#ifndef _POLY_H_
#define _POLY_H_

typedef struct
{
	int emin;		/* minimale Anzahl Ecken */
	int emax;		/* minimale Anzahl Ecken */
	double radius;		/* Umkreisradius */
} mainParameter_t;

typedef struct
{
	unsigned int anzEcken;
	double flaeche;
	double laenge;
	double abweichungKreis;
} polygon_t;

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
cl_errors_t scanCommandLine(int argc, char **argv, mainParameter_t *params);
void printCLError(cl_errors_t cl_error);
void berechnePolygone(polygon_t daten[], mainParameter_t params);
void AusgabePolygone(polygon_t daten[], int anzZeilen);

#endif
