import java.util.ArrayList;

public abstract class Algorytm {
    ArrayList<Zadanie> list;
    int liczbaPrzelaczen = 0;
    Zadanie currentzadanie = new Zadanie();

    public Algorytm(ArrayList<Zadanie> list){
        this.list=list;
    }
    public abstract Zadanie aktywneZadanie() throws InterruptedException, CloneNotSupportedException;

    public abstract void updateList(ArrayList<Zadanie> list);

    public int abs(int a)
    {
        return (a < 0) ? -a : a;
    }
}
