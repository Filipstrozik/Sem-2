import java.util.ArrayList;

public abstract class Strategia extends Algorytm {
    ArrayList<Zadanie> listRT;
    Zadanie currentRT =new Zadanie();
    public Strategia (ArrayList<Zadanie> list){
        super(list);
    }

    @Override
    public abstract Zadanie aktywneZadanie() throws CloneNotSupportedException;

    @Override
    public abstract void updateList(ArrayList<Zadanie> list);

    public abstract void updateRealList(ArrayList<Zadanie> list);

}
