public abstract class JednDanych {
    public int adres;
    public boolean wPamieci;
    public int czasDodaniaWPamieci;
    public int czasOstatniegoUzycia;
    public int szansa;

    public JednDanych(int addr){
        adres = addr;
        wPamieci = false;
        czasDodaniaWPamieci = 0;
        czasOstatniegoUzycia = 0;
        szansa = 1;
    }

    @Override
    public String toString() {
        return "adr: "+adres+" t1: "+czasDodaniaWPamieci+" t2: "+czasOstatniegoUzycia+" C: "+szansa;
    }
}
