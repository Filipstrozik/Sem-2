import java.util.Random;

public class Generator {

    public int id=0;
    Proces nextproc = null;
    static boolean aktywny;
    static int czasNext = 0;
    public static int maxCzasProcesu = 27;
    public static int maxczasNextproc = 30;

    public Generator(){
        aktywny=true;
    }

    public void generuj(){
        Random rnd = new Random();
        nextproc = new Proces(id,rnd.nextInt(maxCzasProcesu)+1);
        id++;
    }

    public Proces getNext(){
        generuj();
        Random rnd = new Random();
        czasNext = rnd.nextInt(maxczasNextproc);
        return nextproc;
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
