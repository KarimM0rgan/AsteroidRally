public class Ship {

    private Extent extent;

    private double angle;  // in radians

    private double velocityX;

    private double velocityY;

    private double maxSpeed = 1;

//  new: Adding Brakes!

    private double brakePower = 0.95;

    public Ship(double x, double y, double initialAngle) {
        this.extent = new Extent(x, y, 0.025); // Ship size
        this.angle = initialAngle;
        this.velocityX = 0;
        this.velocityY = 0;
    }

    // Movement methods
    public void rotate(double deltaAngle) {
        angle += deltaAngle;
    }

    public void accelerate(double amount) {
        velocityX += Math.cos(angle) * amount;
        velocityY += Math.sin(angle) * amount;

        // Limit speed
        double speed = Math.sqrt(Math.pow(velocityX,2) + Math.pow(velocityY, 2));
        if (speed > maxSpeed) {
            velocityX = (velocityX/speed) * maxSpeed;
            velocityY = (velocityY/speed) * maxSpeed;
        }
    }

    public void drift() {
        // Apply movement
        extent.move(velocityX, velocityY);
    }

    // new: brake method to slow the player down
    public void brake(){
        velocityX *= brakePower;
        velocityY *= brakePower;

        // Complete Stop
        if (Math.abs(velocityX) < 0.001){
            velocityX = 0;
        }
        if (Math.abs(velocityY) < 0.001){
            velocityY = 0;
        }
    }

    // Getters
    public Extent getExtent() {
        return extent;
    }

    public double getAngle() {
        return angle;
    }
}
