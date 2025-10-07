package demoTestUnitaire.demo2;

import org.example.demoTestUnitaire.Demo2.De;
import org.example.demoTestUnitaire.Demo2.Jeu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class JeuTest {

    private Jeu jeu;
    @Mock
    private De de ;
   //private IDe de = Mockito.mock(De.class);

    @BeforeEach
    public void setup(){
        jeu = new Jeu(de);
    }

    @Test
    public void TestJeu_WhenJouer_IfDeLancerReturn15_ThenResult_True(){
        //Arrange
        Mockito.when(de.lancer()).thenReturn(15);

        //Act
        boolean result = jeu.jouer();

        //Assert
        Assertions.assertTrue(result);
    }

    @Test
    public void TestJeu_WhenJouer_IfDeLancerReturn19_ThenResult_False(){
        //Arrange
        Mockito.when(de.lancer()).thenReturn(19);

        //Act
        boolean result = jeu.jouer();

        //Assert
        Assertions.assertFalse(result);
    }



}
