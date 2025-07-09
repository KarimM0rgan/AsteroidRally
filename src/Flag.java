public class Flag {

    private Extent extent;

    private boolean hitByShip1;

    private boolean hitByShip2;

    private boolean isScoredByShip1; // new: Checks a flag out for ship 1

    private boolean isScoredByShip2; // new: Checks a flag out for ship 2

    public Flag(double x, double y) {
        this.extent = new Extent(x, y, 0.01); // Small square flags
        this.hitByShip1 = false;
        this.hitByShip2 = false;
        this.isScoredByShip1 = false;
        this.isScoredByShip2 = false;
    }

    // Getters
    public Extent getExtent() {
        return extent;
    }

    public boolean hasBeenHitByShip1() {
        return hitByShip1;
    }

    public boolean hasBeenHitByShip2() {
        return hitByShip2;
    }

    // Mark as hit by a ship
    public void setHitByShip1() {
        hitByShip1 = true;
    }

    public void setHitByShip2() {
        hitByShip2 = true;
    }

    public boolean awardShip1(){
        if (hitByShip1 && !isScoredByShip1){
            isScoredByShip1 = true;
            return true;
        }
        return false;
    }

    public boolean awardShip2(){
        if (hitByShip2 && !isScoredByShip2){
            isScoredByShip2 = true;
            return true;
        }
        return false;
    }
}
