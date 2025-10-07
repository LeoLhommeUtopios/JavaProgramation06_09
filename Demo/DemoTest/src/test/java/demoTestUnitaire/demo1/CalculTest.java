package demoTestUnitaire.demo1;

import org.example.demoTestUnitaire.demo1.Calcul;
import org.junit.jupiter.api.*;

public class CalculTest {

    private Calcul calcul;

    @BeforeAll
    public static  void setupAll(){
        System.out.println("Une fois avant tout les tests");
    }
    @BeforeEach
    public  void setupEach(){
        System.out.println("Une fois avant chaque test");
        calcul = new Calcul();
    }
    @AfterAll
    public static  void setupAfterAll(){
        System.out.println("Une fois apres tout les test");
    }
    @AfterEach
    public  void setupAfterEach(){
        System.out.println("Une fois apres chaque test");
        calcul = null;
    }

    @Test
    public void GivenTestCalcul_whenAddition10_20_ThenResult30(){
        //Arrange
        double x = 10;
        double y = 20;
        double resultAwait = 30;

        //Act
        double result = calcul.addition(x,y);

        //Assert
        Assertions.assertEquals(resultAwait,result);

    }

    @Test
    public void TestCalul_WhenDivision10_5_ThenResult2(){
        //Arrange
        int x = 10;
        int y = 5;
        double resultAwait = 2;

        //Act
        double result = calcul.division(x,y);

        //Assert
        Assertions.assertEquals(resultAwait,result);
    }

    @Test
    public void TestCalul_WhenDivision10_0_ThenThrowArithmetiqueException(){
        //Arrange
        int x = 10;
        int y = 0;

        //Assert
        Assertions.assertThrows(ArithmeticException.class,()->calcul.division(x,y));
    }

}
