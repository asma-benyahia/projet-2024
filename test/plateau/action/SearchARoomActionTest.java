package plateau.action;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import plateau.equipment.tool.Card;
import plateau.equipment.tool.Tool;
import plateau.gamecharacter.Survivor;
import plateau.room.Room;

public class SearchARoomActionTest {

    private Survivor survivor;
    private SearchARoomAction searchARoomAction;

    @BeforeEach
    public void setUp() {
        //survivor = new Survivor();
        searchARoomAction = new SearchARoomAction();
    }

    @Test
    public void testFouilleWithTool() {
        Room room = new Room(0, 0);
        Tool tool1 = new Card(1);

        room.addTool(tool1);
        survivor.setCurrentCell(room);

        searchARoomAction.fouille(survivor);

        assertTrue(survivor.getAllTools().contains(tool1));

    }

}
