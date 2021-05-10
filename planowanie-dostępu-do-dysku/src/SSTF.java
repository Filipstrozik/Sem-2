import java.util.ArrayList;

public class SSTF extends Algorytm{

    Dysk dysk;

    public SSTF(ArrayList<Zadanie> list,int poz, int size){
        super(list);
        dysk = new Dysk(poz, size);
    }

    @Override
    public Zadanie aktywneZadanie() throws InterruptedException, CloneNotSupportedException {

        int shortest = 0;
        if(currentzadanie.pozycja!=-1){
            if(currentzadanie.pozycja == dysk.poz){
                Zadanie zad = (Zadanie) currentzadanie.clone();
                currentzadanie = new Zadanie();
                return zad;
            } else if(currentzadanie.pozycja > dysk.poz){
                dysk.poz++;
                dysk.zmiany++;
            } else {
                dysk.poz--;
                dysk.zmiany++;
            }
            return null;
        }
        else {
            for(int i=1; i<list.size();i++){
                if(abs(list.get(shortest).pozycja - dysk.poz)> abs(list.get(i).pozycja - dysk.poz)){
                    shortest =i;
                }
            }
            Zadanie z = list.get(shortest);
            currentzadanie = z;// czy to nie musi być szycbiej?
            if(z.pozycja > dysk.poz){
                dysk.poz++;
                dysk.zmiany++;
            } else {
                dysk.poz--;
                dysk.zmiany++;
            }
            return null;
        }

    }

    @Override
    public void updateList(ArrayList<Zadanie> list) {
        this.list=list;
    }
}
