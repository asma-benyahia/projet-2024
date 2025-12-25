package plateau.action;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import plateau.gamecharacter.zombie.*;
import plateau.gamecharacter.Survivor;
import plateau.equipment.tool.HealingVial;
import plateau.equipment.weapon.*;
import plateau.Board;



class UseEquipementActionTest{
    private Survivor s;
    private Axe a;
    private Runner z;
    private HealingVial h; 
    private Board board;

    @BeforeEach
    public void setUp(){
        this.board=new Board(5,5);
        this.s=new Survivor(board);
        this.a=new Axe();
        this.z=new Runner(board);
        this.h=new HealingVial(0);
    }

    @Test
    public void testIsOkIfWeapon(){
        int pnt=z.getLifePoints();
        s.setCurrentEquipement(a);
        s.setTarget(z);
        UseEquipementAction use=new UseEquipementAction();
        use.execute(s);
        assertTrue(z.getLifePoints()<pnt);
        
    }

    @Test
    public void testIsOkIfTool(){
        int actionPnt=s.getActionPoint();
        int lifePnt=s.getLifePoints();
        s.setCurrentEquipement(h);
        s.subLifePoint(1);
        UseEquipementAction use=new UseEquipementAction();
        use.execute(s);
        assertTrue(actionPnt>s.getActionPoint());
        assertEquals(lifePnt, s.getLifePoints());

    }
}
