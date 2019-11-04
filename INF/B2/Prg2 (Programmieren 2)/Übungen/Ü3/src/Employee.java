public class Employee {

    private String name;
    private float monatsgehalt;

    public Employee (String name){
        this.name = name;
    }

    public Employee (String name, float monatsgehalt){
        this.name = name;
        this.monatsgehalt = monatsgehalt;
    }

    public void setGehaltsänderung (float betrag){
        if(betrag > 50000f){
            System.out.println("Betrag abgelehnt!\n");
        }else{
        monatsgehalt = betrag;
        }
    }

    public String getName (){
        return name;
    }

    public float getMonatsgehalt() {
        return monatsgehalt;
    }


    public void printInfo(){
        System.out.println("Name: " + name + "\nMonatsgehalt: " + monatsgehalt + "\n");
    }


}


