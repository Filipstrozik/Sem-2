public class Zadanie implements Cloneable{
    public int id;
    public int pozycja;
    public int czasOczekiwania;
    boolean realTime;
    int deadline;
    int kwant;

    public Zadanie(){
        id=-1;
        pozycja=-1;
        czasOczekiwania=0;
        realTime=false;
        deadline=0;
    }

    public Zadanie(int id, int poz, boolean real, int deadline){
        this.id = id;
        this.pozycja = poz;
        this.realTime = real;
        this.deadline = deadline;
        czasOczekiwania=0;
    }
    public int getPozycja()
    {
        return pozycja;
    }

    public String toString()
    {
        return id + " " + pozycja + " " + czasOczekiwania + " "+ realTime+ " " + deadline+ " "+ kwant;
    }
    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
