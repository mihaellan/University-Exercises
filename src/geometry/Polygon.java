package geometry;

import javafx.scene.shape.Shape;

public class Polygon extends GeometricObject {
    private double centerAngle;
    private double startAngle;
    private double radius;
    private Point startPoint;
    private Point[] points;
    private double[] sides;
    private double[] coordinates;

    public void calculatePoints() {
        centerAngle = 2 * Math.PI / 2;
        if (sides.length % 2 == 0) {
            startAngle = Math.PI / 2;
        } else {
            startAngle = Math.PI / 2 - centerAngle / 2;
        }

        for (int i = 0; i < points.length; i++) {
            points[i] = new Point();
        }

        for (int i = 0; i < points.length; i++) {
            double ang = startAngle + (i * centerAngle);
            points[i].x = startPoint.x + radius * Math.cos(ang);
            points[i].y = startPoint.y + radius * Math.sin(ang);
        }

        for (int index = 0; index < points.length; index++) {
            int nextIndex = (index == points.length - 1) ? 0 : index + 1;
            sides[index] = Help.calculateDistance(points[index], points[nextIndex]);
        }
    }

    public Polygon(int sidesNum, int pointsNum, Point startPoint, double length) {
        super("Polygon", pointsNum, sidesNum);
        radius = length;
        sides = new double[sidesNum];
        points = new Point[pointsNum];
        this.startPoint = startPoint;
        calculatePoints();
    }

    @Override
    public boolean isValid() {
        return centerAngle > 0 && startAngle > 0;
    }

    @Override
    public void initialize() {

    }

    @Override
    public double calculatePerimeter() {
        double perimeter = 0;
        for (int i = 0; i < sides.length; i++) {
            perimeter += sides[i];
        }
        return perimeter;
    }

    @Override
    public double calculateArea() {
        double area = ((calculatePerimeter() * radius) / 2) *
                Math.sqrt(1 - ((calculatePerimeter() * calculatePerimeter()) / (4
                        * sides.length * sides.length * radius * radius)));
        return area;
    }

    @Override
    public String getType() {
        int counter = 0;
        for (int i = 0; i < sides.length; i++) {
            counter++;
        }
        return "Polygon mit " + counter + "Seite.";
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public boolean equal(GeometricObject otherGeometricObject) {
        if (otherGeometricObject instanceof Polygon) {
            Polygon otherPolygon = (Polygon) otherGeometricObject;
            double area = calculateArea();
            double otherArea = otherPolygon.calculateArea();
            return Help.equal(area, otherArea);
        } else {
            return false;
        }
    }

    public double[] calculateCoordinates(int scale) {
        coordinates = new double[2 * points.length];
        int coordinatesIndex = 0;
        for (int i = 0; i < points.length; i++) {
            coordinates[coordinatesIndex] = points[i].x;
            coordinatesIndex++;
            coordinates[coordinatesIndex] = points[i].y;
            coordinatesIndex++;
        }
        for (int i = 0; i < coordinates.length; i++) {
            coordinates[i] *= scale;
        }
        return coordinates;
    }

    @Override
    public Shape createShape(int scale) {
        return new javafx.scene.shape.Polygon(calculateCoordinates(scale));
    }

    @Override
    public boolean contains(double x, double y) {

        Triangle[] triangles = new Triangle[sides.length];
        for (int i = 0; i < points.length; i++) {
            if (i == points.length - 1) {
                int nextPoint = 0;
                triangles[i] = new Triangle(new Point(points[i]), new Point(points[nextPoint]), new Point(startPoint));
            } else {
                int nextPoint = i + 1;
                triangles[i] = new Triangle(new Point(points[i]), new Point(points[nextPoint]), new Point(startPoint));
            }
        }
        boolean contains = false;
        for (int i = 0; i < triangles.length; i++) {
            if (triangles[i].contains(x, y)) {
                contains = true;
            } else {
                contains = false;
            }
        }
        return contains;
    }
}