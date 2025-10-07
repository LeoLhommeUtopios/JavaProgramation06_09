package org.example;

import org.example.dao.PlanteDAO;
import org.example.entity.Plante;

import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Plante plante = Plante.builder().age(12).color("Rouge").build();
        PlanteDAO planteDAO = new PlanteDAO();
        try{
            planteDAO.save(plante);
            planteDAO.getAll().stream().forEach(System.out::println);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}