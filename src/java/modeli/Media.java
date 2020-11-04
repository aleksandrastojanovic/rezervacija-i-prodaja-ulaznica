/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import klase.Model;
import org.apache.commons.fileupload.FileItem;

/**
 *
 * @author iq skola
 */
public class Media implements Model {

    public static final String TIP_FOTOGRAFIJA = "fotografija";
    public static final String TIP_VIDEO = "video";

    private int id = -1;
    private final String table = "media";
    private FileItem fajl;
    private boolean glavna;
    private String tip;
    private String putanja;
    private int dogadjajId;
    private String ime;

    public Media() {
    }

    public Media(FileItem fajl, boolean glavna, String tip, String putanja, int dogadjajId, String ime) {
        this.fajl = fajl;
        this.glavna = glavna;
        this.tip = tip;
        this.putanja = putanja;
        this.dogadjajId = dogadjajId;
        this.ime = ime;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getTable() {
        return table;
    }

    public FileItem getFajl() {
        return fajl;
    }

    public void setFajl(FileItem fajl) {
        this.fajl = fajl;
    }

    public boolean isGlavna() {
        return glavna;
    }

    public void setGlavna(boolean glavna) {
        this.glavna = glavna;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getPutanja() {
        return putanja;
    }

    public void setPutanja(String putanja) {
        this.putanja = putanja;
    }

    public int getDogadjajId() {
        return dogadjajId;
    }

    public void setDogadjajId(int dogadjajId) {
        this.dogadjajId = dogadjajId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

}

