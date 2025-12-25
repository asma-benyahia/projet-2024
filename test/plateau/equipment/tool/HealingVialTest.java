package plateau.equipment.tool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import plateau.gamecharacter.Survivor;
import plateau.Board;
import static org.junit.jupiter.api.Assertions.*;

class HealingVialTest {
    private Survivor s;
    private HealingVial h;
    private Board b;
    @BeforeEach
    public void setUp() {
        s = new Survivor(b);
        h = new HealingVial(0);
        s.setCurrentEquipement(h);
    }

    @Test
    public void healingVialaddLifePoint() {
        int lifepoints = s.getLifePoints();
        s.subLifePoint(1);
        h.useBy(s);
        assertEquals(lifepoints, s.getLifePoints());
    }

    @Test
    public void healingVialSubActionPoint() {
        int actionPoints = s.getActionPoint();
        h.useBy(s);
        assertTrue(actionPoints > s.getActionPoint());
    }
}
