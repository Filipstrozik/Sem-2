import java.util.ArrayList;

public class Menadzer {

    public int poz=100;
    public int size=200;
    public FCFS AlgorytmFCFS;
    public SSTF AlgorytmSSTF;
    public SCAN AlgorytmSCAN;
    public CSCAN AlgorytmCSCAN;
    public EDF StrategiaEDF;
    public FDSCAN StrategiaFDSCAN;

    public ArrayList<Zadanie> zadanieListFCFS;
    public ArrayList<Zadanie> zadanieListSSTF;
    public ArrayList<Zadanie> zadanieListSCAN;
    public ArrayList<Zadanie> zadanieListCSCAN;
    public ArrayList<Zadanie> zadanieListEDF;
    public ArrayList<Zadanie> zadanieListFDSCAN;

    public ArrayList<Zadanie> zadanieListRTEDF;
    public ArrayList<Zadanie> zadanieListRTFDSCAN;


    public ArrayList<Zadanie> statListFCFS;
    public ArrayList<Zadanie> statListSSTF;
    public ArrayList<Zadanie> statListSCAN;
    public ArrayList<Zadanie> statListCSCAN;
    public ArrayList<Zadanie> statListEDF;
    public ArrayList<Zadanie> statListFDSCAN;


    public ArrayList<Zadanie> zaglodzoneFCFS;
    public ArrayList<Zadanie> zaglodzoneSSTF;
    public ArrayList<Zadanie> zaglodzoneSCAN;
    public ArrayList<Zadanie> zaglodzoneCSCAN;
    public ArrayList<Zadanie> zaglodzoneEDF;
    public ArrayList<Zadanie> zaglodzoneFDSCAN;

    private Generator gen;

    int czasDzialania=0;

    public Menadzer(Generator g){
        zadanieListFCFS = new ArrayList<Zadanie>();
        statListFCFS = new ArrayList<Zadanie>();
        zaglodzoneFCFS = new ArrayList<Zadanie>();
        AlgorytmFCFS = new FCFS(zadanieListFCFS, poz, size);

        zadanieListSSTF = new ArrayList<Zadanie>();
        statListSSTF = new ArrayList<Zadanie>();
        zaglodzoneSSTF = new ArrayList<Zadanie>();
        AlgorytmSSTF = new SSTF(zadanieListSSTF, poz, size);

        zadanieListSCAN = new ArrayList<Zadanie>();
        statListSCAN = new ArrayList<Zadanie>();
        zaglodzoneSCAN = new ArrayList<Zadanie>();
        AlgorytmSCAN = new SCAN(zadanieListSCAN, poz, size);

        zadanieListCSCAN = new ArrayList<Zadanie>();
        statListCSCAN = new ArrayList<Zadanie>();
        zaglodzoneCSCAN = new ArrayList<Zadanie>();
        AlgorytmCSCAN = new CSCAN(zadanieListCSCAN, poz, size);

        zadanieListEDF = new ArrayList<Zadanie>();
        zadanieListRTEDF = new ArrayList<Zadanie>();
        statListEDF = new ArrayList<Zadanie>();
        zaglodzoneEDF = new ArrayList<Zadanie>();
        StrategiaEDF = new EDF(zadanieListEDF,poz, size);

        zadanieListFDSCAN = new ArrayList<Zadanie>();
        zadanieListRTFDSCAN = new ArrayList<Zadanie>();
        statListFDSCAN = new ArrayList<Zadanie>();
        zaglodzoneFDSCAN = new ArrayList<Zadanie>();
        StrategiaFDSCAN = new FDSCAN(zadanieListFDSCAN, poz, size);


        gen = g;
    }


    public void przydzielZadanie() throws InterruptedException, CloneNotSupportedException {
        czasDzialania++;
        sprawdzGenerator();

        innerPrzydzielZadanie(zadanieListFCFS,statListFCFS,AlgorytmFCFS, zaglodzoneFCFS);
        innerPrzydzielZadanie(zadanieListSSTF,statListSSTF,AlgorytmSSTF,zaglodzoneSSTF);
        innerPrzydzielZadanie(zadanieListSCAN,statListSCAN,AlgorytmSCAN,zaglodzoneSCAN);
        innerPrzydzielZadanie(zadanieListCSCAN,statListCSCAN,AlgorytmCSCAN,zaglodzoneCSCAN);

        innerPrzydzielZadanieRT(zadanieListEDF,zadanieListRTEDF,statListEDF,StrategiaEDF,zaglodzoneEDF);
        innerPrzydzielZadanieRT(zadanieListFDSCAN,zadanieListRTFDSCAN,statListFDSCAN,StrategiaFDSCAN,zaglodzoneFDSCAN);


    }



    public void innerPrzydzielZadanie(ArrayList<Zadanie> ZadanieList, ArrayList<Zadanie> statList, Algorytm aktywnyAlgorytm, ArrayList<Zadanie> zaglodzone) throws InterruptedException, CloneNotSupportedException {
        for(int i=0; i<ZadanieList.size();i++)
        {
            if(ZadanieList.get(i)==null){
                ZadanieList.remove(i);
                i--;
            }
            else if(ZadanieList.get(i).czasOczekiwania>2000){
                zaglodzone.add(ZadanieList.get(i));
                ZadanieList.remove(i);
                i--;
            }
            else {
                ZadanieList.get(i).czasOczekiwania++;
            }
        }
        aktywnyAlgorytm.updateList(ZadanieList);
        if(!ZadanieList.isEmpty()){
            Zadanie z = aktywnyAlgorytm.aktywneZadanie();
            if(z!=null){
                for(int i=0;i<ZadanieList.size();i++){
                    if(z.id==ZadanieList.get(i).id){
                        ZadanieList.remove(i);
                        break;
                    }
                }
            }
            else return;
            z.czasOczekiwania--;
            statList.add(z);
        }
    }


    public void innerPrzydzielZadanieRT(ArrayList<Zadanie> ZadanieList,ArrayList<Zadanie> ZadanieListRT, ArrayList<Zadanie> statList, Strategia aktywnyStrategia, ArrayList<Zadanie> zaglodzone) throws InterruptedException, CloneNotSupportedException {
        for(int i=0; i<ZadanieListRT.size();i++)
        {
            if(ZadanieListRT.get(i)==null){
                ZadanieListRT.remove(i);
                i--;
            }
            else if(ZadanieListRT.get(i).deadline<=0){ //tutaj fdscan trzeba zmienic
                zaglodzone.add(ZadanieListRT.get(i));
                ZadanieListRT.remove(i);
                i--;
                if(aktywnyStrategia instanceof EDF){
                    aktywnyStrategia.currentRT = new Zadanie();
                }
            }
            else{
                ZadanieListRT.get(i).czasOczekiwania++;
                ZadanieListRT.get(i).deadline--; // tylko realtime zmniejszam dealine
            }
        }
        if(aktywnyStrategia instanceof FDSCAN){
            for(int i=0;i<ZadanieListRT.size();i++){
                if(ZadanieListRT.get(i).deadline < Math.abs(ZadanieListRT.get(i).pozycja - ((FDSCAN) aktywnyStrategia).dysk.poz)){
                    zaglodzone.add(ZadanieListRT.get(i));
                    ZadanieListRT.remove(i);
                    i--;
                }
            }
        }

        aktywnyStrategia.updateRealList(ZadanieListRT);
        for(int i=0; i<ZadanieList.size();i++)
        {
            if(ZadanieList.get(i)==null){
                ZadanieList.remove(i);
                i--;
            }
            ZadanieList.get(i).czasOczekiwania++;
        }
        aktywnyStrategia.updateList(ZadanieList);
        if(!ZadanieList.isEmpty() || !ZadanieListRT.isEmpty()){
            Zadanie z = aktywnyStrategia.aktywneZadanie();
            if(z!=null){
                if(z.realTime){
                    for(int i=0;i<ZadanieListRT.size();i++){
                        if(z.id==ZadanieListRT.get(i).id){
                            ZadanieListRT.remove(i);
                            break;
                        }
                    }
                }
                else{
                    for(int i=0;i<ZadanieList.size();i++){
                        if(z.id==ZadanieList.get(i).id){
                            ZadanieList.remove(i);
                            break;
                        }
                    }
                }

            }
            else return;
            z.czasOczekiwania--;
            statList.add(z);
        }
    }


    public void sprawdzGenerator() throws CloneNotSupportedException {
        while(Generator.isAktywny() && gen.isGotowy()){
            Zadanie z = gen.getNext();
            z.kwant = czasDzialania;
            if(z.realTime){
                //dodajesz do strategiaedfRT
                Zadanie zRTEDF = (Zadanie) z.clone();
                zadanieListRTEDF.add(zRTEDF);
                //dodajesz do strategiaFDSCANRT
                Zadanie zRTFDSCAN = (Zadanie) z.clone();
                zadanieListRTFDSCAN.add(zRTFDSCAN);
            }
            else {
                //nie realtime dodajesz zadanieListEDF
                Zadanie zEDF = (Zadanie) z.clone();
                zadanieListEDF.add(zEDF);
                //dodajesz do zadanieListFDSCAN
                Zadanie zFDSCAN = (Zadanie) z.clone();
                zadanieListFDSCAN.add(zFDSCAN);
            }
            Zadanie zfcfs = (Zadanie) z.clone();
            Zadanie zsstf = (Zadanie) z.clone();
            Zadanie zscan = (Zadanie) z.clone();
            Zadanie zcscan = (Zadanie) z.clone();
            zadanieListFCFS.add(zfcfs);
            zadanieListSSTF.add(zsstf);
            zadanieListSCAN.add(zscan);
            zadanieListCSCAN.add(zcscan);
        }
    }


    public void clearList(){
        zadanieListFCFS.clear();
        statListFCFS.clear();
        zadanieListSSTF.clear();
        statListSSTF.clear();
        zadanieListSCAN.clear();
        statListSCAN.clear();
    }
}