package it.polimi.ingsw.Model.Divinities;

import it.polimi.ingsw.AthenaException;
import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.utils.Color;
import it.polimi.ingsw.utils.Divinity;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArtemisTest {
    private GodPower test = new Artemis();

    @Test
    public void testCostruttore(){
        assertSame(test.getDivinity(), Divinity.Artemis);
    }

    @Test
    public void testExecute(){
        Game game = new Game();
        Board board = game.getBoard();
        Player player = new Player("test player", Color.Red, game);
        player.placeWorkers(board.getCell(1,1));
        Cell startPosition = board.getCell(1,1);
        Cell firstDestination = board.getCell(2,2);
        Cell secondDestination = board.getCell(1,1);
        try {
            test.execute(player, firstDestination, 0);
            test.execute(player,secondDestination,0);
            assertFalse(startPosition.equals(secondDestination));
        }
        catch(IllegalArgumentException e){
            assertTrue(startPosition.equals(secondDestination));
        }
        catch(AthenaException e){ }

    }
}
