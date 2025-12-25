package plateau.role;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FighterTest {

    @Test
    public void testAttackWithBonus(){
        Fighter fighter = new Fighter();
        int res = fighter.attackWithBonus();
        assertTrue(res>= 1 && res <= 6);

    }
    
}
