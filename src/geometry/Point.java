package geometry;

public class Point {
    public double x;
    public double y;

    public Point() {
        x = 0;
        y = 0;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point otherPoint) {
        x = otherPoint.x;
        y = otherPoint.y;
    }

    public void initialize() {
        System.out.println("Eingabe x: ");
        x = Help.INPUT.nextDouble();
        System.out.println("Eingabe y: ");
        y = Help.INPUT.nextDouble();
    }

    public void move(double xDelta, double yDelta) {
        x = x + xDelta;
        y = y + yDelta;
    }

    public Point createNewMoved(double xDelta, double yDelta) {
        double newX = x + xDelta;
        double newY = y + yDelta;

        return new Point(newX, newY);
    }

    public boolean equal(Point otherPoint) {
        boolean xEqual = Help.equal(x, otherPoint.x);
        boolean yEqual = Help.equal(y, otherPoint.y);

        return xEqual && yEqual;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public void print() {
        System.out.println(this);
    }
}

