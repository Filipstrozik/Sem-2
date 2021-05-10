import java.util.ArrayList;

public class Menadzer {
    public FCFS AlgorytmFCFS;
    public SJF AlgorytmSJF;
    public RoundRobin AlgorytmRR;

    public ArrayList<Proces> procesListFCFS;
    public ArrayList<Proces> procesListSJF;
    public ArrayList<Proces> procesListRR;

    public ArrayList<Proces> statListFCFS;
    public ArrayList<Proces> statListSJF;
    public ArrayList<Proces> statListRR;

    public ArrayList<Proces> zaglodzoneFCFS;
    public ArrayList<Proces> zaglodzoneSJF;
    public ArrayList<Proces> zaglodzoneRR;

    private Generator gen;

    int czasDzialania=0;

    public Menadzer(Generator g){
        procesListFCFS = new ArrayList<Proces>();
        statListFCFS = new ArrayList<Proces>();
        zaglodzoneFCFS = new ArrayList<Proces>();
        AlgorytmFCFS = new FCFS(procesListFCFS);

        procesListSJF = new ArrayList<Proces>();
        statListSJF = new ArrayList<Proces>();
        zaglodzoneSJF = new ArrayList<Proces>();
        AlgorytmSJF = new SJF(procesListSJF);

        procesListRR = new ArrayList<Proces>();
        statListRR = new ArrayList<Proces>();
        zaglodzoneRR = new ArrayList<Proces>();
        AlgorytmRR = new RoundRobin(procesListRR);

        gen = g;
    }


    public void przydzielProces() throws InterruptedException, CloneNotSupportedException {
        czasDzialania++;
        sprawdzGenerator();

        innerPrzydzielProces(procesListFCFS,statListFCFS,AlgorytmFCFS, zaglodzoneFCFS);
        innerPrzydzielProces(procesListSJF,statListSJF,AlgorytmSJF,zaglodzoneSJF);
        innerPrzydzielProces(procesListRR,statListRR,AlgorytmRR,zaglodzoneRR);
    }



    public void innerPrzydzielProces(ArrayList<Proces> procesList, ArrayList<Proces> statList, Algorytm aktywnyAlgorytm, ArrayList<Proces> zaglodzone) throws InterruptedException {
        for(int i=0; i<procesList.size();i++)
        {
            if(procesList.get(i).czasOczekiwania>2000 && procesList.get(i)!=null){
                zaglodzone.add(procesList.get(i));
                procesList.remove(i);
                i--;
            }
            else if(procesList.get(i).isDone() && procesList.get(i)!=null)
            {
                statList.add(procesList.get(i));
                procesList.remove(i);
                i--;
            }
            else
            {
                procesList.get(i).czasOczekiwania++;
            }
        }
        aktywnyAlgorytm.updateList(procesList);
        if(!procesList.isEmpty()){
            Proces p = aktywnyAlgorytm.aktywnyProces();
            if(aktywnyAlgorytm.currentproces.getId() != p.getId()) {
                aktywnyAlgorytm.liczbaPrzelaczen++;
            }
            aktywnyAlgorytm.currentproces = p;
            p.doOnce();
            p.czasOczekiwania--;
        }
    }


    public void sprawdzGenerator() throws CloneNotSupportedException {
        while(Generator.isAktywny() && gen.isGotowy()){
            Proces p = gen.getNext();
            Proces pfcfs = (Proces) p.clone();
            Proces psjf = (Proces) p.clone();
            Proces prr = (Proces) p.clone();
            procesListFCFS.add(pfcfs);
            procesListSJF.add(psjf);
            procesListRR.add(prr);
        }
    }


    public void clearList(){
        procesListFCFS.clear();
        statListFCFS.clear();
        procesListSJF.clear();
        statListSJF.clear();
        procesListRR.clear();
        statListRR.clear();
    }
}
