package plateau;

import plateau.street.CarrefourP;
import plateau.Board;
import plateau.action.UseEquipementAction;
import plateau.equipment.tool.HealingVial;
import plateau.gamecharacter.Survivor;
import plateau.gamecharacter.zombie.Runner;

public class AttackMain {

    public static void main(String[] args) {
        Runner z;
        Survivor s;
        Board board;
        CarrefourP carf;
        HealingVial h;
        carf = new CarrefourP(2, 5);
        board = new Board(5, 5);
        z = new Runner(board);
        s = new Survivor(board);
        h = new HealingVial(0);

        /*
         * z.setCurrentCell(carf);
         * s.setCurrentCell(carf);
         * System.out.println(s.getLifePoints());
         * z.attack();
         * System.out.println(s.getLifePoints());
         */

        s.setCurrentEquipement(h);
        s.subLifePoint(1);
        System.out.println(s.getLifePoints());
        s.setTarget(z);
        UseEquipementAction use = new UseEquipementAction();
        use.execute(s);
        System.out.println(s.getLifePoints());
        System.out.println(s.getActionPoint());

    }
}
