import org.example.Frame;
import org.example.IGenerateur;
import org.example.Roll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FrameTest {

    private Frame frame;
    private IGenerateur generator = Mockito.mock(IGenerateur.class);

    @Test
    public void Roll_SimpleFrame_FirstRoll_CheckScore(){
        //Arrange
        frame = new Frame(generator,false);
        Mockito.when(generator.randomPin(10)).thenReturn(4);

        // Act
        frame.makeRoll();

        //Assert
        Assertions.assertEquals(4,frame.getScore());

    }

    @Test
    public void Roll_SimpleFrame_SecondRoll_CheckScore (){
        frame = new Frame(generator,false);
        Mockito.when(generator.randomPin(6)).thenReturn(4);
        List<Roll> rolls = new ArrayList<>(Arrays.asList(new Roll(4)));
        frame.setRolls(rolls);
        frame.makeRoll();
        Assertions.assertEquals(8,frame.getScore());
    }

    @Test
    public void Roll_SimpleFrame_SecondRoll_FirstRollStrick_ReturnFalse (){
        frame = new Frame(generator,false);
        List<Roll> rolls = new ArrayList<>(Arrays.asList(new Roll(10)));
        frame.setRolls(rolls);
        boolean result = frame.makeRoll();
        Assertions.assertFalse(result);
    }

    @Test
    public void Roll_SimpleFrame_MoreRolls_ReturnFalse (){
        frame = new Frame(generator,false);
        List<Roll> rolls = new ArrayList<>(Arrays.asList(new Roll(4),new Roll(4)));
        frame.setRolls(rolls);
        boolean result = frame.makeRoll();
        Assertions.assertFalse(result);
    }

    @Test
    public void Roll_LastFrame_SecondRoll_FirstRollStrick_ReturnTrue(){
        frame = new Frame(generator,true);
        Mockito.when(generator.randomPin(10)).thenReturn(4);
        List<Roll> rolls = new ArrayList<>(Arrays.asList(new Roll(10)));
        frame.setRolls(rolls);
        boolean result = frame.makeRoll();
        Assertions.assertTrue(result);

    }

    @Test
    public void Roll_LastFrame_SecondRoll_FirstRollStrick_CheckScore(){
        frame = new Frame(generator,true);
        Mockito.when(generator.randomPin(10)).thenReturn(4);
        List<Roll> rolls = new ArrayList<>(Arrays.asList(new Roll(10)));
        frame.setRolls(rolls);
        frame.makeRoll();
        Assertions.assertEquals(14,frame.getScore());
    }

    @Test
    public void Roll_LastFrame_ThirdRoll_FirstRollStrick_ReturnTrue(){
        frame = new Frame(generator,true);
        Mockito.when(generator.randomPin(10)).thenReturn(4);
        List<Roll> rolls = new ArrayList<>(Arrays.asList(new Roll(10),new Roll(6)));
        frame.setRolls(rolls);
        boolean result = frame.makeRoll();
        Assertions.assertTrue(result);
    }

    @Test
    public void Roll_LastFrame_ThirdRoll_FirstRollStrick_CheckScore (){
        frame = new Frame(generator,true);
        Mockito.when(generator.randomPin(4)).thenReturn(4);
        List<Roll> rolls = new ArrayList<>(Arrays.asList(new Roll(10),new Roll(6)));
        frame.setRolls(rolls);
        frame.makeRoll();
        Assertions.assertEquals(20,frame.getScore());
    }

    @Test
    public void Roll_LastFrame_ThirdRoll_Spare_ReturnTrue (){
        frame = new Frame(generator,true);
        Mockito.when(generator.randomPin(10)).thenReturn(4);
        List<Roll> rolls = new ArrayList<>(Arrays.asList(new Roll(4),new Roll(6)));
        frame.setRolls(rolls);
        boolean result = frame.makeRoll();
        Assertions.assertTrue(result);
    }

    @Test
    public void Roll_LastFrame_ThirdRoll_Spare_CheckScore (){
        frame = new Frame(generator,true);
        Mockito.when(generator.randomPin(10)).thenReturn(4);
        List<Roll> rolls = new ArrayList<>(Arrays.asList(new Roll(4),new Roll(6)));
        frame.setRolls(rolls);
        frame.makeRoll();
        Assertions.assertEquals(14,frame.getScore());
    }

    @Test
    public void Roll_LastFrame_FourthRoll_ReturnFalse(){
        frame = new Frame(generator,true);
        Mockito.when(generator.randomPin(10)).thenReturn(4);
        List<Roll> rolls = new ArrayList<>(Arrays.asList(new Roll(4),new Roll(6),new Roll(5)));
        frame.setRolls(rolls);
        boolean result = frame.makeRoll();
        Assertions.assertFalse(result);
    }
}
