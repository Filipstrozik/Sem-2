import java.util.ArrayList;

public abstract class Algorytm {
    ArrayList<Proces> list;
    int liczbaPrzelaczen = 0;
    Proces currentproces = new Proces();

    public Algorytm(ArrayList<Proces> list){
        this.list=list;
    }
    public abstract Proces aktywnyProces() throws InterruptedException;

    public abstract void updateList(ArrayList<Proces> list);
}
