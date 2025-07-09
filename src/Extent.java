public class Extent {

    private double x;

    private double y;

    private double radius;

    public Extent(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void move(double dx, double dy) {
        this.x += dx;
        this.y += dy;

        if (this.x > 1){
            this.x -= 1;
        }
        if (this.y > 1){
            this.y -= 1;
        }
        if (this.y < 0){
            this.y += 1;
        }
        if (this.x < 0){
            this.x += 1;
        }
    }

    public double distanceTo(Extent other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx*dx + dy*dy);
    }

    // Getters
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }

    // Check collision with another extent object
    public boolean overlaps(Extent other) {
        return distanceTo(other) < (this.radius + other.radius);
    }
}