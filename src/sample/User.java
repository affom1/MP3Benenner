package sample;

import java.io.File;
import java.util.ArrayList;

public class User {

    private ArrayList<File> meineSongs;
    private int anzahlBenenner;
    private MyMp3 myMp3;

    public User() {
        this.anzahlBenenner = 0;
    }

    public ArrayList<File> getMeineSongs() {
        return meineSongs;
    }

    public void setMeineSongs(ArrayList<File> meineSongs) {
        this.meineSongs = meineSongs;
    }

    public int getAnzahlBenenner() {
        return anzahlBenenner;
    }

    public void setAnzahlBenenner(int anzahlBenenner) {
        this.anzahlBenenner = anzahlBenenner;
    }

    public MyMp3 getMyMp3() {
        return myMp3;
    }

    public void setMyMp3(MyMp3 myMp3) {
        this.myMp3 = myMp3;
    }
}
