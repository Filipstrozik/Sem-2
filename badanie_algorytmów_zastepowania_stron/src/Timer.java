public class Timer {
    private static Timer timerInstance = null;
    private int time;

    private Timer(){
        resetTime();
    }

    public static Timer getInstance(){
        if(timerInstance==null)
        {
            timerInstance = new Timer();
        }
        return timerInstance;
    }

    public void increaseTime(int ileczasu){
        time += ileczasu;
    }

    public void resetTime(){
        time=0;
    }

    public int getTime(){
        return time;
    }

}
