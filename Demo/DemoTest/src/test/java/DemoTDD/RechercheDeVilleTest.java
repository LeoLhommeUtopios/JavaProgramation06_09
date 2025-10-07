package DemoTDD;

import org.example.DemoTDD.NotFoundException;
import org.example.DemoTDD.RechercheDeVille;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RechercheDeVilleTest {

    private RechercheDeVille rechercheDeVille;

    @BeforeEach
    public void setup(){
        rechercheDeVille= new RechercheDeVille();
    }

    @Test
    public void TestRechercheVille_WhenRechercherWithLessThan_2Char_ThrowException_NotFoundException(){
        //Arrange
        String searchString = "a";

        //Act Assert
        Assertions.assertThrows(NotFoundException.class,()->rechercheDeVille.recherche(searchString));
    }

    @Test
    public void TestRechercheVille_WhenRechercherStringGreater_2Char_ReturnCitiesStartedWithChar(){
        //Arrange
        String searchString = "Va";
        List<String> expected = List.of("Valence","Vancouver");

        //Act
        List<String> result = rechercheDeVille.recherche(searchString);

        //Assert
        Assertions.assertEquals(expected,result);

    }

    @Test
    public void testRechercheVille_WhenRechercherStringGreater_2Char_NotCaseSensitive_ReturnCitiesStartedWithChar(){
        //Arrange
        String searchString = "vA";
        List<String> expected = List.of("Valence","Vancouver");

        //Act
        List<String> result = rechercheDeVille.recherche(searchString);

        //Assert
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void testRechercheVille_WhenRechercher_CharAsterisk_ReturnAllCities(){
        //Arrange
        String searchString = "*";
        List<String> expected = List.of("Paris", "Budapest", "Skopje", "Rotterdam", "Valence", "Vancouver", "Amsterdam", "Vienne", "Sydney", "New York", "Londres", "Bangkok", "Hong Kong", "Dubaï", "Rome", "Istanbul");

        //Act
        List<String> result = rechercheDeVille.recherche(searchString);

        //Assert
        Assertions.assertEquals(expected,result);
    }
}


