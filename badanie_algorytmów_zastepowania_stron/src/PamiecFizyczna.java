import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class PamiecFizyczna {

    private int size;
    private ArrayList<JednDanych> ramkaArray;

    public Queue<JednDanych> kolejka;

    public PamiecFizyczna(int size){
        this.size = size;
        this.ramkaArray = new ArrayList<JednDanych>();
        this.kolejka = new LinkedList<>();
    }

    public boolean dodajRamkeDoPamieci(JednDanych ramkaDoDodania){
        for(int i=0; i<ramkaArray.size();i++)
        {
            if(ramkaArray.get(i).adres == ramkaDoDodania.adres)
            {
//                System.out.println("ramka w pamieci");
                ramkaArray.get(i).czasOstatniegoUzycia = Timer.getInstance().getTime();
                ramkaArray.get(i).szansa=1;
                //iteracyjnie trzeba zmienic sznace
                Iterator<JednDanych> it = kolejka.iterator();
                while (it.hasNext()){
                    JednDanych jd = it.next();
                    if(jd.adres==ramkaDoDodania.adres){
                        jd.szansa = 1;
                    }
                }
                return true;
            }
        }

        if(ramkaArray.size() >=size)
        {
            return false;
        }

        ramkaDoDodania.wPamieci = true;
        Timer timer = Timer.getInstance();
        ramkaDoDodania.czasOstatniegoUzycia = timer.getTime();
        ramkaDoDodania.czasDodaniaWPamieci = timer.getTime();
        ramkaDoDodania.szansa = 1;
        ramkaArray.add(ramkaDoDodania);
        kolejka.add(ramkaDoDodania);
        return true;
    }

    public boolean usunRamke(int addr){
        for(int i=0; i<ramkaArray.size();i++){
            JednDanych currRamka = ramkaArray.get(i);
            if(currRamka.adres == addr)
            {
                currRamka.wPamieci=false;
                ramkaArray.remove(i); //sus
                return true;
            }
        }
        return false;
    }

    public ArrayList<JednDanych> getRamkaArray()
    {
        return ramkaArray;
    }

    public boolean isFull(){
        return ramkaArray.size()==size;
    }
}
