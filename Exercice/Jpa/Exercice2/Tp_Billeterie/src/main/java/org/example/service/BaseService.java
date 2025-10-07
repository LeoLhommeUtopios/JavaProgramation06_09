package org.example.service;

import java.util.List;

public interface BaseService<T> {

    // public : accesibilité de la function //  boolean : type de retour //
    // delete : nom de la methode
    // (int id) parametre de la methode ici l'id de l'objet que l'on veux supprimer

    public boolean delete (long id);
    public T findById (long id);
    public List<T> getAll ();

}
