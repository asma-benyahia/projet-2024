package plateau;


import plateau.room.*;
import plateau.street.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class CellTest{

    private Cell room ;
    private Cell street ;
    private Cell pharmacy ;
    private Cell continental ;

    @BeforeEach
    public void initialization(){
        this.room = new Room(5,6);
        this.street = new Street(3,4);
        this.pharmacy = new Pharmacy(3 ,3);
        this.continental = new Continental(2, 4);

    }
    @Test
    void testCellInitialization() {
		//Room
        assertNotNull(room);
        
        //Street
        assertNotNull(street);
        
        //Pharmacy
        assertNotNull(pharmacy);
        
        //Continental
        assertNotNull(continental);
    }
    @Test
    void testGetDoor() {
        Door door = new Door();
        room.addDoor(Direction.Up, door);
        assertEquals(door, room.getDoor(Direction.Up));
    }
    @Test
    void testAddDoor() {
        Door door = new Door();
        street.addDoor(Direction.Right, door);
        assertEquals(door, street.getDoor(Direction.Right));
    }
    @Test
    void testGetX() {
        assertEquals(5, room.getX());
    }
    @Test
    void testGetY() {
        assertEquals(6, room.getY());
    }
    @Test
    void testGetNbDoors() {
        assertEquals(4, room.getNbDoors());
    }
    /* 
    @Test
    void testEquals() {
        Cell room2 = new Room(5, 6);
        assertTrue(room.equals(room2));

        assertFalse(room.equals(street));
    }

    */





   
  



  


}