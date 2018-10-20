package sample;

import com.mpatric.mp3agic.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

import java.nio.file.Path;
import java.nio.file.Paths;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static sample.Main.primaryStage;

public class Controller implements Initializable {

    Button renameButton;
    ArrayList<File> nurMp3undM4afiles;
    private String ordnerPfad;

    public TextField interpretTF;

    public TextField titelTF;
    public Label dateiLabel;
    User me;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        DO A BUNCH OF OTHER STUFF BEFORE

//        String suchen="C:/Users/marc_/Music/Beispielmusik/The Beatles - Yesterday.mp3";
//
//        MyMp3 mymp3 = null;
//        Path path = Paths.get(suchen);
//
//        try {
//            mymp3 = new MyMp3(path);
//            System.out.println(mymp3.getFilename());
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("File lässt sich nicht in MP3 umwandeln 1");
//        } catch (UnsupportedTagException e) {
//            e.printStackTrace();
//            System.out.println("File lässt sich nicht in MP3 umwandeln 2");
//        } catch (InvalidDataException e) {
//            e.printStackTrace();
//            System.out.println("File lässt sich nicht in MP3 umwandeln 3 - Evtl. weil M4a?");
//        }
//
//        if (mymp3.hasId3v1Tag()) {
//            ID3v1 id3v1Tag = mymp3.getId3v1Tag();
//            System.out.println("Track: " + id3v1Tag.getTrack());
//            System.out.println("Artist: " + id3v1Tag.getArtist());
//            System.out.println("Title: " + id3v1Tag.getTitle());
//            System.out.println("Album: " + id3v1Tag.getAlbum());
//            System.out.println("Year: " + id3v1Tag.getYear());
//            System.out.println("Genre: " + id3v1Tag.getGenre() + " (" + id3v1Tag.getGenreDescription() + ")");
//            System.out.println("Comment: " + id3v1Tag.getComment());
//        }
//
//        ID3v1 id3v1Tag;
//        if (mymp3.hasId3v1Tag()) {
//            id3v1Tag =  mymp3.getId3v1Tag();
//        } else {
//            // mp3 does not have an ID3v1 tag, let's create one..
//            id3v1Tag = new ID3v1Tag();
//            mymp3.setId3v1Tag(id3v1Tag);
//        }
//        id3v1Tag.setArtist("The Beatles");
//        id3v1Tag.setTitle("Yesterday");
//
//        try {
//            mymp3.save(mymp3, suchen);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (NotSupportedException e) {
//            e.printStackTrace();
//        }


//        try {
//            File file = new File("C:/Users/marc_/Music/Beispielmusik/Mother's Little Helper The Rolling Stones.mp3");
//            file.delete();
//            mymp3.save("C:/Users/marc_/Music/Beispielmusik/Mother's Little Helper The Rolling Stones");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (NotSupportedException e) {
//            e.printStackTrace();
//        }


        renameButton = new Button();
        nurMp3undM4afiles = new ArrayList<>();
        renameButton.setDefaultButton(true);

        renameButton.setOnAction((ActionEvent e) -> {
            renameSong();
        });
    }

    public void renameSong() {
        System.out.println("renameGo");



        // Erhöhe den Zähler um 1, damit das nächste File im Ordner gewählt werden kann.
        me.setAnzahlBenenner(me.getAnzahlBenenner()+1);
        benenneFelder();
    }

    public void chooseFolder() {
        System.out.println("Ordner auswählen");
        final DirectoryChooser directoryChooser =
                new DirectoryChooser();
        final File selectedDirectory =
                directoryChooser.showDialog(primaryStage);
        if (selectedDirectory != null) {
            ordnerPfad = selectedDirectory.getAbsolutePath();
            File file = selectedDirectory.getAbsoluteFile();
            System.out.println("Der Ordnerpfad erst einmal ist: "+ordnerPfad);
            //listDir(file);
            System.out.println("Versuche eine schöne Arrayliste mit Files zu erhalten.");
            createSchönesFileArray(file);
        }
        me = new User();
        me.setMeineSongs(nurMp3undM4afiles);
        //test
        System.out.println("User erzeugt, zeige sein schöne Liste");
        inhaltDerListeAnsehen(me.getMeineSongs());


        benenneFelder();

    }
    // listet nur auf
    public void listDir(File dir) {


        File[] files = dir.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                System.out.print(files[i].getAbsolutePath());
                if (files[i].isDirectory()) {
                    System.out.print(" (Ordner)\n");
                    System.out.println("Salami");
                    listDir(files[i]); // ruft sich selbst mit dem
                    // Unterverzeichnis als Parameter auf
                }
                else {
                    System.out.print(" (Datei)\n");
                }
            }
        }
    }

    public void benenneFelder() {

        Path path = Paths.get(me.getMeineSongs().get(me.getAnzahlBenenner()).getAbsolutePath());
        MyMp3 mp3_2 = null;
        try {
            mp3_2 = new MyMp3(path);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File lässt sich nicht in MP3 umwandeln 1");
        } catch (UnsupportedTagException e) {
            e.printStackTrace();
            System.out.println("File lässt sich nicht in MP3 umwandeln 2");
        } catch (InvalidDataException e) {
            e.printStackTrace();
            System.out.println("File lässt sich nicht in MP3 umwandeln 3 - Evtl. weil M4a?");
        }


        ID3v1 id3v1Tag;
        if (mp3_2.hasId3v1Tag()) {
            id3v1Tag =  mp3_2.getId3v1Tag();
        } else {
            // mp3 does not have an ID3v1 tag, let's create one..
            id3v1Tag = new ID3v1Tag();
            mp3_2.setId3v1Tag(id3v1Tag);
        }
        if (mp3_2.hasId3v1Tag()) {
            ID3v1 id3v1Tag_test = mp3_2.getId3v1Tag();
            System.out.println("Track: " + id3v1Tag_test.getTrack());
            System.out.println("Artist: " + id3v1Tag_test.getArtist());
            System.out.println("Title: " + id3v1Tag_test.getTitle());
            System.out.println("Album: " + id3v1Tag_test.getAlbum());
            System.out.println("Year: " + id3v1Tag_test.getYear());
            System.out.println("Genre: " + id3v1Tag_test.getGenre() + " (" + id3v1Tag.getGenreDescription() + ")");
            System.out.println("Comment: " + id3v1Tag_test.getComment());
        }
        dateiLabel.setText(mp3_2.getFilename());
        titelTF.setText(mp3_2.getId3v1Tag().getTitle());
//        if (titelTF.getText()=="") titelTF.setText("void");
        interpretTF.setText(mp3_2.getId3v2Tag().getArtist());
//        if (interpretTF.getText()=="") interpretTF.setText("void");

    }

    public void createSchönesFileArray(File dir) {
        String mp3 = ".mp3";
        String m4a = ".m4a";

        File[] files = dir.listFiles();


        //File[] filesAberSchön = new File[(int)dir.length()];

        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if(files[i].getAbsolutePath().contains(m4a) || files[i].getAbsolutePath().contains(mp3)) {
                    System.out.println(files[i].getAbsolutePath() + " ist ein Mp3 File!");
                    nurMp3undM4afiles.add(files[i]);
                } else {
                    System.out.print(files[i].getAbsolutePath()+ " existiert aber kein Mp3 File!");
                }
                // Druck alle

                if (files[i].isDirectory()) {
                    System.out.print(" (Ordner)\n");
                    createSchönesFileArray(files[i]); // ruft sich selbst mit dem
                    // Unterverzeichnis als Parameter auf
                }
                else {
                    System.out.print(" (Datei)\n");
                }
            }
        }
    }

    public void inhaltDerListeAnsehen (ArrayList<File> liste) {
        System.out.println("/n/n/n/n--------------------------------------------------------------------");
        System.out.println("Liste durchgehen");

        System.out.println(liste.size());
        for (int i = 0; i<liste.size();i++) {
            System.out.println(liste.get(i).getAbsolutePath());
        }
        System.out.println("Liste erfolgreich durchgegangen");
        System.out.println("Sie haben "+ liste.size()+ " Mp3-Song in der Liste");
    }


}
