

import java.io.*;

public class SXXXXX {
    public static void main(String[] args) {
        System.out.println("Hello World"); //test
        ABC.param(args);


    }
}
class ABC {
    public Latacz[] Dane = new Latacz[30];
    int losujliczbe() {     //losuje liczbę od 0 do 999
        int x = (int) (999 * Math.random());
        return x;
    }

    String losujModel() {   //losuj model
        int x = (int) (Math.random() * 4);
        String y;
        if (x < 1) {
            y = "Boeing";
        } else if (x < 2) {
            y = "Airbus";
        } else if (x < 3) {
            y = "Cesna";
        } else {
            y = "Concord";
        }
        return y;


    }

    int losujzaloge() { //losuj int od 5 do 199
        int x = (int) (5 + 195 * Math.random());
        return x;
    }

    boolean losujbool() {   //losuj true lub false
        double x = Math.random();
        boolean y = false;
        if (x > 0.5) {
            y = true;
        }
        return y;
    }

    String losujwek() { //losuj kierunek
        int x = (int) (8 * Math.random());
        String y;
        if (x < 1) {
            y = "Północny";
        } else if (x < 2) {
            y = "Wschodni";
        } else if (x < 3) {
            y = "Północno-Wschodni";
        } else if (x < 4) {
            y = "Zachodni";
        } else if (x < 5) {
            y = "Północno-Zachodni";
        } else if (x < 6) {
            y = "Południowo-Wschodni";
        } else if (x < 7) {
            y = "Południowo-Zachodni";
        } else {
            y = "Południowy";
        }
        return y;
    }

    int losujmale() {   //losuje liczbę od 1 do 4
        int x = (int) (1 + Math.random() * 4);
        return x;
    }

    public static void param(String[] args) {          //metoda wczytywania parametru
       ABC abc = new ABC();
        if (args.length > 0) {
            if (args[0].equals("save"))
            {System.out.println("Argument: Save");
            abc.save();}
           else if (args[0].equals("load")) {
                System.out.println("Argument: Load " + args[1]);
                abc.load(args[1]);
            }
            else {
                System.out.println("Argument niepoprawny");

            }
        }
else {System.out.println("brak argumentu");}
    }

    public void save() {        //parametr save
        Latacz[] Dane = new Latacz[30];
        for (int i = 0; i < 30; i++) {
            if (i < 10) {
                Dane[i] = new SamolotPasazerski(losujliczbe(), losujliczbe(), losujliczbe(), losujliczbe(), losujwek(), losujzaloge(), losujModel(), losujbool(), losujbool(), losujbool());
            } else if (i < 20) {
                Dane[i] = new SamolotWojskowy(losujliczbe(), losujliczbe(), losujliczbe(), losujliczbe(), losujwek(), losujmale(), losujModel(), losujbool(), losujbool(), losujbool());
            } else {
                Dane[i] = new Helikopter(losujliczbe(), losujliczbe(), losujliczbe(), (losujliczbe() / 2), losujwek(), losujbool(), losujmale());
            }
            // System.out.println(Dane[i].toString()); losuje tablicę danych
        }
        try {
            FileWriter fw = new FileWriter("latacze.txt");      //zapisuje w pliku txt
            for (int i=0; i< Dane.length;i++){
            fw.write(Dane[i].toString()+"\n");}
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(String par) {      //parametr load
        int liczba_znakow = 0;
        int liczba_linijek = 0;
        try {
            FileReader fr = new FileReader("latacze.txt");
            int ret = fr.read();
            int i = 0;
            while (ret != -1) {
                liczba_znakow++;
                if ((char) ret == '\n') {
                    liczba_linijek++;
                }
                ret = fr.read();
            }
            fr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }                                       //zliczyłem znaki i linkiji
        char[][] linijki = new char[liczba_linijek+1][200];             //tablica z tablic wszystkich znaków linijki
        try {
            FileReader fr = new FileReader("latacze.txt");
            int ret = fr.read();
            int i = 0;
            while (ret != -1) {
                for (int j=0;j<linijki.length;j++)
                {
                    for (int u=0;u<linijki[j].length;u++)
                    {
                    {linijki[j][u]=(char)ret;
                        ret = fr.read();
            if(linijki[j][u]=='\n'){j++;u=-1;}
                    }
                    }
                }
            }
            fr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        decoder(linijki);
        Sortuj_ros(Dane);
        //testtab();
    }
    void Sortuj_ros(Latacz dane[])
    {
        int n = Dane.length;
        for (int j = 1; j < n; j++) {
            Latacz key = Dane[j];
            int i = j - 1;
            while ((i > -1) && (Dane[i].dist() > key.dist())) {
                Dane[i + 1] = Dane[i];
                i--;
            }
            Dane[i + 1] = key;
        }
    }


void testtab(){
    for(int i=0; i<Dane.length; i++){
        System.out.println(Dane[i].dist());
    }
}

    public void decoder(char[][] linijki){
        String zmienna1 = ""; //rodzaj
        String zmienna2 = ""; //x
        String zmienna3 = "";  //y
        String zmienna4 = "";  //z
        String zmienna5 = "";   //v
        String zmienna6 = ""; //wektor
        String zmienna7 = ""; // pasażerowie, ratunkowy
        String zmienna8 = ""; //model, liczba śmigieł
        String zmienna9 = ""; //transponder
        String zmienna10= ""; //czy ma pasażerów czy uzbrojony
        String zmienna11= "";   // czy opóźniony, czy na misji
        int przecinki=0;
        int j = 0;
        for(int i = 0;i<linijki.length;i++){
            for(j=0; j<linijki[i].length;j++){
                if (linijki[i][j] != ',' && linijki[i][j]!= ' '&&linijki[i][j]!='\n') {
                    switch (przecinki) {
                        case 0:
                            zmienna1 = zmienna1 + linijki[i][j];
                            break;
                        case 1:
                            zmienna2 = zmienna2 + linijki[i][j];
                            break;
                        case 2:
                            zmienna3 = zmienna3 + linijki[i][j];
                            break;
                        case 3:
                            zmienna4 = zmienna4 + linijki[i][j];
                            break;
                        case 4:
                            zmienna5 = zmienna5 + linijki[i][j];
                            break;
                        case 5:
                            zmienna6 = zmienna6 + linijki[i][j];
                            break;
                        case 6:
                            zmienna7 = zmienna7 + linijki[i][j];
                            break;
                        case 7:
                            zmienna8 = zmienna8 + linijki[i][j];
                            break;
                        case 8:
                            zmienna9 = zmienna9 + linijki[i][j];
                            break;
                        case 9:
                            zmienna10 = zmienna10 + linijki[i][j];
                            break;
                        case 10:
                            zmienna11 = zmienna11 + linijki[i][j];
                            break;
                    }
                }
            if (linijki[i][j]==',' ){
                przecinki++;
            }
                if(linijki[i][j]=='\n') {
                    zapis(i, zmienna1, zmienna5, zmienna6, zmienna7, zmienna8, zmienna9, zmienna10, zmienna11);
                    przecinki=0;

                    zmienna1="";
                    zmienna2="";
                    zmienna3="";
                    zmienna4="";
                    zmienna5="";
                    zmienna6="";
                    zmienna7="";
                    zmienna8="";
                    zmienna9="";
                    zmienna10="";
                    zmienna11="";
                    break;

                }
            }

        }


    }
    void zapis(int i, String zmienna1, String zmienna5, String zmienna6, String zmienna7,String  zmienna8,String  zmienna9,String  zmienna10,String  zmienna11){
         switch (zmienna1){
            case ("SamolotPasażerski"): SamolotPasazerski pas= new SamolotPasazerski(losujliczbe(), losujliczbe(),
                    losujliczbe(), Integer.valueOf(zmienna5),  zmienna6, Integer.valueOf(zmienna7), zmienna8,
                    Boolean.parseBoolean(zmienna9), Boolean.parseBoolean(zmienna10), Boolean.parseBoolean(zmienna11));
            Dane[i]=pas;
              //  System.out.println(pas);
            break;

            case ("SamolotWojskowy"): SamolotWojskowy woj= new SamolotWojskowy(losujliczbe(), losujliczbe(),
                    losujliczbe(), Integer.valueOf(zmienna5),  zmienna6, Integer.valueOf(zmienna7), zmienna8,
                    Boolean.parseBoolean(zmienna9),Boolean.parseBoolean(zmienna10), Boolean.parseBoolean(zmienna11));
                Dane[i]=woj;
              //  System.out.println(woj);
        break;
            case"Helikopter": Helikopter hel = new Helikopter(losujliczbe(),
                    losujliczbe(), losujliczbe(), Integer.valueOf(zmienna5),  zmienna6,Boolean.parseBoolean(zmienna7), Integer.valueOf(zmienna8));
            Dane[i]=hel;
              //  System.out.println(hel);
        break;

        }

    }
 /*   public static boolean toBool(String s){ //tylko dla stringów true i false
        System.out.println("String s równe "+s);
       // boolean test=false;
//
       // if(s=="true"){
       //     test=true;
      //  }

     //   System.out.println("test"+ test);
String a = s;

        if (a=="true"){
            return true;}
        else if(a=="false") {
            return false;
        }
       else {System.out.println("zły String, Napraw błąd");
       return false;}
    } */


    abstract class Latacz { //ogólna klasa jednostek latających
        private int pozycjaX;
        private int pulap;
        private int pozycjaY;
        private int veloc;
        private String wektor;

        public Latacz(int x, int y, int z, int v, String wektor) {
            this.pozycjaX = x;
            this.pozycjaY = y;
            this.pulap = z;
            this.veloc = v;
            this.wektor = wektor;
        }

        int xGet() {
            return pozycjaX;
        }

        int yGet() {
            return pozycjaY;
        }

        int zGet() {
            return pulap;
        }

        int vGet() {
            return veloc;
        }

        String wekGet() {
            return wektor;
        }

        public abstract String rodzajGet();

        public String toString() {
            return rodzajGet() +", "+ xGet() +", "+ yGet() +", "+ zGet() +", "+ vGet() +", "+ wekGet();

        }
       public int dist(){
                int dist = (int) Math.pow((Math.pow((xGet()), 2) + Math.pow((yGet()), 2) + Math.pow((zGet()), 2)), (0.5));
                return dist;
    }
    }

    class Samolot extends Latacz {
        private int maxzalogi;
        private String model;
        private boolean czyMaTransponder;
        private String rodzaj = "Samolot";

        public Samolot(int x, int y, int z, int v, String wektor, int maxZaloga, String model, boolean transponder) {
            super(x, y, z, v, wektor);
            this.maxzalogi = maxZaloga;
            this.model = model;
            this.czyMaTransponder = transponder;
        }

        int zalogaGet() {
            return maxzalogi;
        }

        String modelGet() {
            return model;
        }

        boolean transGet() {
            return czyMaTransponder;
        }

        public String rodzajGet() {
            return rodzaj;
        }

        public String toString() {
            return super.toString() +", "+ zalogaGet() +", "+ modelGet() +", "+ transGet();
        }
    }

    class Helikopter extends Latacz {
        private boolean czyRatowniczy;
        private int liczbaSmigiel;
        private String rodzaj = "Helikopter";

        public Helikopter(int x, int y, int z, int v, String wektor, boolean ratowniczy, int smig) {
            super(x, y, z, v, wektor);
            this.czyRatowniczy = ratowniczy;
            this.liczbaSmigiel = smig;
        }

        public String rodzajGet() {
            return rodzaj;
        }

        boolean ratGet() {
            return czyRatowniczy;
        }

        int smigGet() {
            return liczbaSmigiel;
        }

        public String toString() {
            return super.toString() +", "+ ratGet() +", "+ smigGet();

        }
    }

    class SamolotPasazerski extends Samolot {
        private boolean czyMaPasazerow;
        private boolean czyOpozniony;
        private String rodzaj = "Samolot Pasażerski";

        public SamolotPasazerski(int x, int y, int z, int v, String wektor, int maxZaloga, String model, boolean transponder, boolean pasazer, boolean opoznienie) {
            super(x, y, z, v, wektor, maxZaloga, model, transponder);
            this.czyMaPasazerow = pasazer;
            this.czyOpozniony = opoznienie;
        }

        public String rodzajGet() {
            return rodzaj;
        }

        boolean pasGet() {
            return czyMaPasazerow;
        }

        boolean opozGet() {
            return czyOpozniony;
        }

        public String toString() {
            return super.toString() +", "+ pasGet() +", "+ opozGet();
        }
    }

    class SamolotWojskowy extends Samolot {
        private boolean czyUzbrojony;
        private boolean czyNaMisji;
        private String rodzaj = "Samolot Wojskowy";

        public SamolotWojskowy(int x, int y, int z, int v, String wektor, int maxZaloga, String model, boolean transponder, boolean uzbrojenie, boolean naMisji) {
            super(x, y, z, v, wektor, maxZaloga, model, transponder);
            this.czyUzbrojony = uzbrojenie;
            this.czyNaMisji = naMisji;
        }

        public String rodzajGet() {
            return rodzaj;
        }

        boolean uzbGet() {
            return czyUzbrojony;
        }

        boolean misGet() {
            return czyNaMisji;
        }

        public String toString() {
            return super.toString() +", "+ uzbGet() +", "+ misGet();
        }
    }

}
