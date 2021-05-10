public class Proces implements Cloneable{

    private int id;
    private int czasProcesu;
    public  int czasOczekiwania;
    private int czasPozostały;

    public Proces() {
        id=-1;
        czasProcesu=0;
        czasPozostały=0;
        czasOczekiwania=0;
    }


    public Proces (int id, int czasProcesu){
        this.id = id;
        this.czasProcesu = czasProcesu;
        this.czasPozostały = czasProcesu;
        czasOczekiwania=0;
    }

    public void doOnce(){
        czasPozostały--;
    }

    public boolean isDone(){
        if(czasPozostały<1)
            return true;
        else
            return false;
    }
    //gettery
    public int getId(){
        return id;
    }

    public int getCzasProcesu() {
        return czasProcesu;
    }

    public int getCzasPozostały(){
        return czasPozostały;
    }

    public String toString(){
        return id+" "+czasPozostały+" "+czasProcesu+" "+czasOczekiwania;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
