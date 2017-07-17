package game.bases;

/**
 * Created by SNOW on 7/16/2017.
 */
public class FrameCounter {
    int count;
    int countMax;

    public FrameCounter(int countMax){
        this.countMax = countMax;
    }

    public void reset(){
        count = 0;
    }

    public boolean run(){
        if (count < countMax){
            count++;
            return false;
        }
        return true;
    }

}
