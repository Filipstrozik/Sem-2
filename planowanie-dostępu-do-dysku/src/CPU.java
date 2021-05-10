public class CPU implements  Runnable{
    public boolean aktywny = false;
    Menadzer menadzer;
    public Thread t;

    public CPU (Menadzer m){
        this.menadzer = m;
        Generator.wlacz();
    }

    public void doCycle() throws InterruptedException, CloneNotSupportedException {
        if(Generator.isAktywny()) Generator.updateCzas();
        menadzer.przydzielZadanie();
//        Thread.sleep(1);
    }

    @Override
    public void run() {
        while (aktywny){
            try{
                doCycle();
            } catch (InterruptedException | CloneNotSupportedException e){
                e.printStackTrace();
            }
            if(menadzer.statListFCFS.size()>=10000 || menadzer.statListSSTF.size()>=10000 || menadzer.statListSCAN.size()>=10000 || menadzer.statListCSCAN.size()>=10000  ){
                aktywny=false;
                System.out.println("koniec");
                System.out.println("stop Symulacji....");
            }
        }
    }
    public void start(){
        t = new Thread(this,"CPUSym");
        t.start();
    }
}
