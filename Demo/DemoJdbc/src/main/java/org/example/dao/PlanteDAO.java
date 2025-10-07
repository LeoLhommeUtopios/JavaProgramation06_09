package org.example.dao;

import com.mysql.cj.protocol.Resultset;
import org.example.entity.Plante;
import org.example.util.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanteDAO {

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultset;
    private String query;

    public Plante save (Plante plante) throws SQLException {
        try {
            connection = DatabaseManager.getConnection();
            String request = "INSERT INTO plante (name,age,color) VALUE (?,?,?)";
            statement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, plante.getName());
            statement.setInt(2, plante.getAge());
            statement.setString(3, plante.getColor());

            int nbrRow = statement.executeUpdate();

            if (nbrRow != 1) {
                connection.rollback();
                return null;
            }

            resultset = statement.getGeneratedKeys();

            if (resultset.next()) {
                plante.setId(resultset.getInt(1));
            }

            connection.commit();
            return plante;
        }catch (SQLException e){
            connection.rollback();
            return null;

        }finally {
            connection.close();
            statement.close();
            resultset.close();
        }
    }

    public List<Plante> getAll () throws SQLException {
        try{
            connection = DatabaseManager.getConnection();
            query = "SELECT * FROM plante";
            statement = connection.prepareStatement(query);
            resultset = statement.executeQuery();

            List<Plante> plantes =new ArrayList<>();
            while (resultset.next()){
                plantes.add(Plante.builder().id(resultset.getInt("id")).name(resultset.getString("name")).age(resultset.getInt("age")).color(resultset.getString("color")).build());
            }

            return plantes;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
        finally {
            connection.close();
            statement.close();
            resultset.close();
        }

    }
}
