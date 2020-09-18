package modeli;

import java.sql.Timestamp;

public class Korisnik1 extends Model1 {
    protected String table = "korisnici";

    private String username;
    private String password;
    private String ime_prezime;
    private String email;
    private Timestamp vreme;

    public Timestamp getVreme() {
        return vreme;
    }

    public void setVreme(Timestamp vreme) {
        this.vreme = vreme;
    }

    public Korisnik1(String username, String password, String ime_prezime, String email) {
        this.username = username;
        this.password = password;
        this.ime_prezime = ime_prezime;
        this.email = email;
    }

    public Korisnik1() {

    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIme_prezime() {
        return ime_prezime;
    }

    public void setIme_prezime(String ime_prezime) {
        this.ime_prezime = ime_prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return id + ". " + ime_prezime + " - " + email;
    }
}
