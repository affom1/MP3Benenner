package sample;

import com.mpatric.mp3agic.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class MyMp3 extends Mp3File {

    private String status;
    private Color color;
    private String titel;
    private String interpret;


    public MyMp3(Path path) throws IOException, UnsupportedTagException, InvalidDataException {

        super(path);

    }

    public String getStatus() {
        return status;
    }

    public Color getColor() {
        return color;
    }

    public String getTitel() {
        return titel;
    }

    public String getInterpret() {
        return interpret;
    }



    public void save(MyMp3 myMp3, String speicherOrt) throws IOException, NotSupportedException {
        //save muss in Try und catch sein
        MyMp3 mymp3 = null;


        try {
            //als erstes unter Dummy-Namen _2 speichern, weil es nicht geklappt hat, wenn zuerst das alte File gelöscht wurde.
            super.save(speicherOrt+"_2"+".mp3");
            // danach löschen
            File file = new File(speicherOrt);
            file.delete();
            // zwei leere Files erzeugen mit dem "alten" und "neuem" Namen
            File oldName = new File(speicherOrt+"_2"+".mp3");
            File newName = new File(speicherOrt);
            // und überschreiben.
            if (newName.exists())
                throw new java.io.IOException("file exists");
            boolean success = oldName.renameTo(newName);
            if (!success) {
                System.out.println("umbenennen hat nicht geklappt.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Speicher schiefgegangen");
        } catch (NotSupportedException e) {
            e.printStackTrace();
            System.out.println("Speicher schiefgegangen");
        }


    }
}
