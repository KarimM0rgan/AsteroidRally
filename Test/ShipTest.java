import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShipTest {

	public static final double DELTA = 0.001;

	private Ship ship;

	@BeforeEach
	public void setUp() throws Exception {
		ship = new Ship(0.5, 0.5, 0.0);
	}

	@Test
	public void returnsCorrectExtent() {
		Extent e = ship.getExtent();
		assertEquals(0.5, e.getX(), DELTA);
		assertEquals(0.5, e.getY(), DELTA);
		assertEquals(0.025, e.getRadius(), DELTA);		
	}

	@Test
	public void drifts() {
		ship.accelerate(0.1);
		ship.drift();
		Extent e = ship.getExtent();
		assertEquals(0.6, e.getX(), DELTA);
		assertEquals(0.5, e.getY(), DELTA);
	}
	
	@Test
	public void accelerationDoesNotDirectlyMoveShip() {
		ship.accelerate(0.1);
		Extent e = ship.getExtent();
		assertEquals(0.5, e.getX(), DELTA);
		assertEquals(0.5, e.getY(), DELTA);
	}

	@Test
	public void rotationDoesNotChangeDirectionOfDrift() {
		ship.accelerate(0.1);
		ship.drift();
		// Merely changing the direction the ship is facing should not change
		// the direction it is drifting
		ship.rotate(Math.PI / 2);
		ship.drift();
		Extent e = ship.getExtent();
		assertEquals(0.7, e.getX(), DELTA);
		assertEquals(0.5, e.getY(), DELTA);
	}

	@Test
	public void wrapsAroundEdgeOfPlayArea() {
		ship.rotate(Math.PI * 1.0 / 2); // Point north
		ship.accelerate(0.1);
		for (int i = 0; i < 6; i++) {
			ship.drift();
		}
		Extent e = ship.getExtent();
		assertEquals(0.5, e.getX(), DELTA);
		assertEquals(0.1, e.getY(), DELTA);
	}

	@Test
	public void driftsAtAnAngle() {
		ship.rotate(Math.atan(3.0 / 4) + Math.PI);
		ship.accelerate(0.5);
		ship.drift();
		Extent e = ship.getExtent();
		assertEquals(0.1, e.getX(), DELTA);
		assertEquals(0.2, e.getY(), DELTA);
		ship.drift();
		e = ship.getExtent();
		assertEquals(0.7, e.getX(), DELTA);
		assertEquals(0.9, e.getY(), DELTA);
	}
}