package modeli;
import klase.Model;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author iq skola
 * @param <T>
 */
public interface Baza<T extends Model> {
    public T save(T model);

    public boolean delete(T model);

    //public boolean delete(int id);

    public T find(int id);
    
    public ArrayList<T> all();
    
    
}
