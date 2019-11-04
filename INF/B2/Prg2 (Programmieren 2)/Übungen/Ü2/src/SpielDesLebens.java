import javax.swing.text.html.HTMLDocument;

public class SpielDesLebens {
   public static void main (String[] args){

    boolean [][] Spielfeld = new boolean [10][10];

    for(int i = 0; i < 10; i++){
        for(int j = 0; j< 10; j++){

            double zufallszahl = Math.random();
            boolean lot; // lebendig (1) oder tot (0)
            if(zufallszahl < 0.5) {
                lot = false;
            }else{
                lot = true;
            }
            Spielfeld[i][j] = lot;
        }
    }

    printCells(Spielfeld);

    System.out.println("");

    int livingNeighbors = 0;

    livingNeighbors = countLivingNeighbors(Spielfeld, 0, 0);

    System.out.println("Lebende Nachbarn = " + livingNeighbors);

    System.out.println("");

    Spielfeld = computeNextGenCells(Spielfeld);

    printCells(Spielfeld);




 }

    private static void printCells (boolean [][] c){

        for(int i = 0; i < 10; i++){
            for(int j = 0; j< 10; j++){


                if(c[i][j] == false) {

                    System.out.print("O ");

                }else{

                    System.out.print("X ");

                }

            }

            System.out.println("");

        }

    }

    private static int countLivingNeighbors (boolean [][] c, int x, int y){

       int zaehlen = 0;
       int j; //Zeile (x)
       int i; //Spalte (y)

       if(x > 0 && y > 0 && x < 9 && y < 9)/*MITTE*/ {

           for (j = x - 1; j <= x + 1; j++) {
               for (i = y - 1; i <= y + 1; i++) {

                   if (c[j][i] == true) {
                       zaehlen += 1;
                   }
               }

           }
       }

       if(x == 0 && y == 0)/*LINKS OBEN*/ {

            for (j = x; j <= x + 1; j++) {
                for (i = y; i <= y + 1; i++) {

                    if (c[j][i] == true) {
                        zaehlen += 1;
                    }
                }

            }
        }

        if(x == 0 && y > 0 && y < 9)/*OBEN*/ {

            for (j = x; j <= x + 1; j++) {
                for (i = y - 1; i <= y + 1; i++) {

                    if (c[j][i] == true) {
                        zaehlen += 1;
                    }
                }

            }
        }

        if(x == 0 && y == 9)/*RECHTS OBEN*/ {

            for (j = x; j <= x + 1; j++) {
                for (i = y - 1; i <= y; i++) {

                    if (c[j][i] == true) {
                        zaehlen += 1;
                    }
                }

            }
        }

        if(x > 0 && x < 9 && y == 9)/*RECHTS*/ {

            for (j = x - 1; j <= x + 1; j++) {
                for (i = y - 1; i <= y; i++) {

                    if (c[j][i] == true) {
                        zaehlen += 1;
                    }
                }

            }
        }

        if(x == 9 && y == 9)/*RECHTS UNTEN*/ {

            for (j = x- 1; j <= x; j++) {
                for (i = y - 1; i <= y; i++) {

                    if (c[j][i] == true) {
                        zaehlen += 1;
                    }
                }

            }
        }

        if(x == 9 && y < 0 && y > 9)/*UNTEN*/ {

            for (j = x - 1; j <= x; j++) {
                for (i = y - 1; i <= y + 1; i++) {

                    if (c[j][i] == true) {
                        zaehlen += 1;
                    }
                }

            }
        }

        if(x == 9 && y == 0)/*LINKS UNTEN*/ {

            for (j = x - 1; j <= x; j++) {
                for (i = y; i <= y + 1; i++) {

                    if (c[j][i] == true) {
                        zaehlen += 1;
                    }
                }

            }
        }

        if(x < 0 && x > 9 && y == 0)/*LINKS*/ {

            for (j = x - 1; j <= x + 1; j++) {
                for (i = y; i <= y + 1; i++) {

                    if (c[j][i] == true) {
                        zaehlen += 1;
                    }
                }

            }
        }



       if(c[x][y] == true) {
           zaehlen -= 1;
       }

       return zaehlen;

    }

    private static boolean[][] computeNextGenCells(boolean[][] cells){

        boolean [][] SpielfeldNeu = new boolean [10][10];

        for(int j = 0/*Zeile*/; j < 10; j++){
            for(int i = 0/*Spalte*/; i < 10; i++){

                int livingneighbours = 0;

                livingneighbours = countLivingNeighbors(cells, j, i);

                if(cells[j][i] == true){

                    if(livingneighbours < 2 || livingneighbours > 3){
                        SpielfeldNeu[j][i] = false;
                    }else{
                        SpielfeldNeu[j][i] = true;
                    }
                }else{
                    if(livingneighbours == 3){
                        SpielfeldNeu[j][i] = true;
                    }else{
                        SpielfeldNeu[j][i] = false;
                    }
                }

            }
        }
        return SpielfeldNeu;
    }

}
