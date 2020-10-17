/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klase;

import java.time.LocalDateTime;


/**
 *
 * @author iq skola
 */
public class Dogadjaj implements Model {
    private int id;
    
    private String naziv;
    private String nazivLokacije;
    //proveriti format mySql i LocalDateTime
    //dodati limit po korisniku
    private LocalDateTime datumIVreme;
    private String detalji;
    private String glavnaSlikaPutanja;
    private String videoPutanja;
    private final String table;
    //ostale slike?

    public Dogadjaj() {
        this.table = "dogadjaji";
        this.id = -1;
    }

    public Dogadjaj(String naziv, String nazivLokacije, LocalDateTime datumIVreme, String detalji, String glavnaSlikaPutanja, String videoPutanja) {
        this.naziv = naziv;
        this.nazivLokacije = nazivLokacije;
        this.datumIVreme = datumIVreme;
        this.detalji = detalji;
        this.glavnaSlikaPutanja = glavnaSlikaPutanja;
        this.videoPutanja = videoPutanja;
        this.table = "dogadjaji";
        this.id = -1;
    }


    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getNazivLokacije() {
        return nazivLokacije;
    }

    public void setNazivLokacije(String nazivLokacije) {
        this.nazivLokacije = nazivLokacije;
    }

    public LocalDateTime getDatumIVreme() {
        return datumIVreme;
    }

    public void setDatumIVreme(LocalDateTime datumIVreme) {
        this.datumIVreme = datumIVreme;
    }

    public String getDetalji() {
        return detalji;
    }

    public void setDetalji(String detalji) {
        this.detalji = detalji;
    }

    public String getGlavnaSlikaPutanja() {
        return glavnaSlikaPutanja;
    }

    public void setGlavnaSlikaPutanja(String glavnaSlikaPutanja) {
        this.glavnaSlikaPutanja = glavnaSlikaPutanja;
    }

    public String getVideoPutanja() {
        return videoPutanja;
    }

    public void setVideoPutanja(String videoPutanja) {
        this.videoPutanja = videoPutanja;
    }

    @Override
    public String getTable() {
        return table;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
    
    
    
    
    
    
    
    
}
