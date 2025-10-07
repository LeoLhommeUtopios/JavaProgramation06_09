package org.example.Demo1;

import org.example.Demo1.entity.Arrosoir;
import org.example.Demo1.entity.Plante;

import javax.persistence.*;
import java.util.List;

public class Main {

    private static  EntityManager em;

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo_jpa");
        em = emf.createEntityManager();




// One to many
//        Fleur fleur1 = Fleur.builder().color("rose").build();
//        Fleur fleur2 = Fleur.builder().color("bleu").build();

//        em.getTransaction().begin();
//
//
//
//        em.persist(fleur1);
//        em.persist(fleur2);
//
//        em.getTransaction().commit();

//        em.getTransaction().begin();
//
//        Plante plante = em.find(Plante.class,1);
//        Fleur fleur = em.find(Fleur.class,1);
//        Fleur fleur2 = em.find(Fleur.class,2);
//
//        fleur.setPlante(plante);
//        fleur2.setPlante(plante);
//
//        em.getTransaction().commit();



// one to one  / composant
//        em.getTransaction().begin();
//        Plante plante = Plante.builder().name("Plante").color("red").build();
//        Pot pot = Pot.builder().size(12).build();
//
//        plante.setPot(pot);
//        em.persist(plante);
//        em.getTransaction().commit();
//
//        edit(2,"plante2","vert");


        //One to many
//        Arrosoir arrosoir = Arrosoir.builder().contenance(20).build();

//
//        em.getTransaction().begin();
//        em.persist(arrosoir);
//        em.getTransaction().commit();

        em.getTransaction().begin();
        Plante plante = em.find(Plante.class,1);
        Arrosoir arrosoir = em.find(Arrosoir.class,1);
        plante.addArrosoir(arrosoir);
        arrosoir.addPlante(plante);
        em.getTransaction().commit();

    }


    public static void add (Plante plante) {
//        EntityTransaction transaction =  em.getTransaction();
//        transaction.begin();
        em.getTransaction().begin();
        em.persist(plante);
        em.getTransaction().commit();
    }


    public static void getAll (){
        TypedQuery<Plante> query = em.createQuery("select p from Plante p ",Plante.class);
        List<Plante> plantes = query.getResultList();

//        plantes.forEach(System.out::println);

        for (Plante plante : plantes){
            System.out.println(plante);
        }
    }

    public static void getById (int id){
        Plante plante = em.find(Plante.class,id);
        if(plante != null){
            System.out.println(plante);
        }else{
            System.out.println("aucune plante trouvé");
        }
    }

    public static Plante getByReference (int id){
        try{
            return em.getReference(Plante.class,id);
        }catch (EntityNotFoundException ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static void edit (int id , String name, String color){
        Plante plante = getByReference(id);
        em.getTransaction().begin();
        plante.setName(name);
        plante.setColor(color);

        em.getTransaction().commit();
    }

    public static void delete (int id){
        Plante plante = getByReference(id);
        if(plante == null){
            System.out.println("no plant found");
            return ;
        }
        em.getTransaction().begin();
        em.remove(plante);
        em.getTransaction().commit();
    }

}