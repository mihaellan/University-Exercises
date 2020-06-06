package geometry;

public abstract class GeometricObject implements GeometricDrawing {
    protected String name;
    protected Point[] points;
    protected double[] sides;

    protected GeometricObject(String name, int pointsCount, int sidesCount) {
        this.name = name;
        points = new Point[pointsCount];
        sides = new double[sidesCount];
    }

    public abstract boolean isValid();
    public abstract void initialize();
    public abstract double calculatePerimeter();
    public abstract double calculateArea();
    public abstract String getType();
    public abstract String toString();
    public abstract boolean equal(GeometricObject otherGeometricObject);

    public void print() {
        System.out.format("%s: %s, %s, U=%s, F=%s\n",
                name, getType(), this,  calculatePerimeter(), calculateArea());
    }

    protected void calculateSides() {
        for (int index = 0; index < points.length; index++) {
            int nextIndex = (index == points.length - 1) ? 0 : index + 1;
            sides[index] = Help.calculateDistance(points[index], points[nextIndex]);
        }
    }
}
