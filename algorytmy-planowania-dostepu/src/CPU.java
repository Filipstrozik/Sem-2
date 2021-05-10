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
        menadzer.przydzielProces();
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
            if(menadzer.statListFCFS.size()>=100000 ||menadzer.statListSJF.size()>=100000 ||menadzer.statListRR.size()>=100000){
                aktywny=false;
                System.out.println("koniec");
                System.out.println("stop CPU ....");
            }
        }
    }
    public void start(){
        t = new Thread(this,"CPUSym");
        t.start();
    }
}
