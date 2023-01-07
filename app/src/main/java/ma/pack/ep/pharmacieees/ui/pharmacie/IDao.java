package ma.pack.ep.pharmacieees.ui.pharmacie;

import java.util.List;

public interface IDao<T>{
    T findById(int id);
    List<T> findAll();
}
