package plateau;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An enumeration representing directions (Right, Left, Up, Down).
 */
public enum Direction {
    Right ,Left ,Up ,Down ;


  /**
  * Retrieves the enum values as a list.
  *
  * @return A list containing all the enum values.
  */
  public static List<Direction> valuesAsList () {
		return new ArrayList<Direction>(Arrays.asList(Direction.values()));
	}

  
  public String toString() {
      switch (this) {
          case Right:
              return "Right";
          case Left:
              return "Left";
          case Up:
              return "Up";
          case Down:
              return "Down";
          default:
              return "Unknown";
      }
  }

}
