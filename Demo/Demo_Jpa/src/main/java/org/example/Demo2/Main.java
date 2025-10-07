package org.example.Demo2;

import org.example.Demo2.Entity.Student;
import org.example.Demo2.Entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo_jpa");
        EntityManager em = emf.createEntityManager();

        Student student = Student.builder().classeroom("classromm").age(12).name("toto").build();
        Teacher teacher = Teacher.builder().subject("Maths").age(35).name("tutu").build();

        em.getTransaction().begin();
        em.persist(student);
        em.persist(teacher);
        em.getTransaction().commit();
    }
}
