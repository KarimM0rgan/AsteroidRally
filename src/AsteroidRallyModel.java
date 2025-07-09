/** Logical model for the Asteroid Rally game. */
public class AsteroidRallyModel {

	/** Asteroids. */
	private Extent[] asteroids;

	/** Flags. */
	private Flag[] flags;

	/** Player 1's ship. */
	private Ship ship1;

	/** Player 2's ship. */
	private Ship ship2;

	//new: Score board addition!
	/** player 1's score. **/
	private int score1 = 0;

	/** player 2's score. **/
	private int score2 = 0;

	public AsteroidRallyModel() {
		// Create two ships, pointing north.
		ship1 = new Ship(0.25, 0.5, Math.PI / 2);
		ship2 = new Ship(0.75, 0.5, Math.PI / 2);
		// Create 10 asteroids that do not overlap.
		asteroids = new Extent[10];
		for (int i = 0; i < asteroids.length; i++) {
			do {
				asteroids[i] = new Extent(StdRandom.uniform(), StdRandom.uniform(), 0.05);
			} while (isConflictingAsteroidPosition(i));
		}
		// Create 5 flags that do not overlap.
		flags = new Flag[5];
		for (int i = 0; i < flags.length; i++) {
			do {
				flags[i] = new Flag(StdRandom.uniform(), StdRandom.uniform());
			} while (isConflictingFlagPosition(i));
		}
	}

	/** Causes both ships to drift and checks for flag hits. */
	public void advance() {
		ship1.drift();
		ship2.drift();
		for (Flag f : flags) {
			if (!f.hasBeenHitByShip1() && f.getExtent().overlaps(ship1.getExtent())) {
				f.setHitByShip1();
				if (f.awardShip1()){
					score1 += 1; // score increases 1 point when getting a flag
				}
			}
			if (!f.hasBeenHitByShip2() && f.getExtent().overlaps(ship2.getExtent())) {
				f.setHitByShip2();
				if (f.awardShip2()){
					score2 += 1; // score increases one point when getting a flag
				}
			}
		}
	}

	/** Returns the asteroids. */
	public Extent[] getAsteroids() {
		return asteroids;
	}

	/** Returns the flags. */
	public Flag[] getFlags() {
		return flags;
	}

	/** Returns ship 1. */
	public Ship getShip1() {
		return ship1;
	}

	/** Returns ship 2. */
	public Ship getShip2() {
		return ship2;
	}

	// new: Score Getters
	/** Returns Score 1. **/
	public int getScore1(){
		return score1;
	}

	/** Returns Score 2. **/
	public int getScore2(){
		return score2;
	}

	/**
	 * Returns true if asteroid i overlaps with either ship or with any
	 * lower-indexed asteroid.
	 */
	public boolean isConflictingAsteroidPosition(int i) {
		if (asteroids[i].overlaps(ship1.getExtent())) {
			return true;
		}
		if (asteroids[i].overlaps(ship2.getExtent())) {
			return true;
		}
		for (int j = 0; j < i; j++) {
			if (asteroids[i].overlaps(asteroids[j])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns true if flag i overlaps with either ship, any asteroid, or any
	 * lower-indexed flag.
	 */
	public boolean isConflictingFlagPosition(int i) {
		// TODO: Implement this.
		if (flags[i].getExtent().overlaps(ship1.getExtent())){
			return true;
		}
		if (flags[i].getExtent().overlaps(ship2.getExtent())){
			return true;
		}
		for (Extent asteroid : asteroids){
			if (flags[i].getExtent().overlaps(asteroid)){
				return true;
			}
		}
		for (int j = 0; j < i; j++){
			if (flags[i].getExtent().overlaps(flags[j].getExtent())){
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns 1 if player 1 has won, 2 if player 2 has won, or 0 if neither
	 * player has won. A player wins if they hit all flags first or if the other
	 * player hits a rock.
	 */
	public int winner() {
		// TODO: Implement this.
		for (Extent asteroid : asteroids){
			if (ship1.getExtent().overlaps(asteroid)){
				return 2; // Player 2 wins
			}
			if (ship2.getExtent().overlaps(asteroid)){
				return 1; // Player 1 wins
			}
		}

		int ship1Flags = 0;
		int ship2Flags = 0;

		for (Flag flag : flags){
			if (flag.hasBeenHitByShip1()){
				ship1Flags += 1;
			}
			if (flag.hasBeenHitByShip2()){
				ship2Flags += 1;
			}
		}

		// Player 1 wins
		if (ship1Flags == flags.length && ship2Flags < flags.length){
			return 1;
		}
		// player 2 wins
		if (ship2Flags == flags.length && ship1Flags < flags.length){
			return 2;
		}
		return 0; // Tie or no winner declared
	}
}