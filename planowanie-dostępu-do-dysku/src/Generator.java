import java.util.Random;

public class Generator {

    public static int sytaucja = 0;
    public int id=0;
    Zadanie nextzad = null;
    static boolean aktywny;
    static int czasNext = 0;
    public static int maxPozycja = 200;
    public static int maxczasNextZad = 50; //?

    public Generator(){
        aktywny=true;
    }

    public void generuj(){
        Random rnd = new Random();
        if(sytaucja==0){ // wszystkie sektory
            nextzad = new Zadanie(id,rnd.nextInt(maxPozycja),rnd.nextBoolean(), rnd.nextInt(50));
        }
        else if(sytaucja==1){ // tylko 4 sektor
            nextzad = new Zadanie(id,rnd.nextInt(50)+150,rnd.nextBoolean(), rnd.nextInt(50));
        }
        else if(sytaucja==2){ // sektor 1 i 4
            Random sek = new Random();
            if(sek.nextBoolean()){
                nextzad = new Zadanie(id,rnd.nextInt(50)+150,rnd.nextBoolean(), rnd.nextInt(50));
            }
            else {
                nextzad = new Zadanie(id,rnd.nextInt(50),rnd.nextBoolean(), rnd.nextInt(50));
            }
        }
        id++;
    }

    public Zadanie getNext(){
        generuj();
        Random rnd = new Random();
        czasNext = rnd.nextInt(maxczasNextZad);
        return nextzad;
    }

    public static boolean isAktywny(){
        return aktywny;
    }

    public static void wlacz(){
        aktywny=true;
    }

    public static void updateCzas(){
        if(czasNext>0)
            czasNext--;
    }

    public boolean isGotowy(){
        if(czasNext<=0)
            return true;
        return false;
    }
}
