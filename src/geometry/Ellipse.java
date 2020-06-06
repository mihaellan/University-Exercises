package geometry;

import javafx.scene.shape.Shape;

public class Ellipse extends GeometricObject implements GeometricDrawing {
    private double a;
    private double b;

    public Ellipse() {
        super("Ellipse", 1, 0);
        points[0] = new Point(0, 0);
        a = 1;
        b = 1;
    }

    public Ellipse(Point startPoint, double a, double b) {
        super("Ellipse", 1, 0);
        this.points[0] = startPoint;
        this.a = a;
        this.b = b;
    }

    public Ellipse(Ellipse otherEllipse) {
        super("Ellipse", 1, 0);
        points[0] = new Point(otherEllipse.points[0]);
        a = otherEllipse.a;
        b = otherEllipse.b;
    }

    @Override
    public boolean isValid() {
        return a > 0 && b > 0;
    }

    @Override
    public void initialize() {
        do {
            System.out.println("Anfangspunkt: ");
            points[0].initialize();
            System.out.println("Halbachse a: ");
            this.a = Help.INPUT.nextDouble();
            System.out.println("Halbachse b: ");
            this.b = Help.INPUT.nextDouble();
        } while (!isValid());
    }

    @Override
    public double calculatePerimeter() {
        double sqrt = Math.sqrt((3 * this.a + this.b) * (this.a + 3 * this.b));
        double Perimeter = Math.PI * (3 * (this.a + this.b) - sqrt);
        return Perimeter;
    }

    @Override
    public double calculateArea() {
        double Area = Math.PI * this.a * this.b;
        return Area;
    }

    @Override
    public String getType() {

        if (a == b) {
            return "Kreis";
        } else {
            return "Ellipse";
        }
    }

    @Override
    public String toString() {
        return points[0] + "-[" + a + ", " + b + "]";
    }

    @Override
    public void print() {
        System.out.format("%s, %s, U=%s\n, F=%s\n", this, getType(), calculatePerimeter(), calculateArea());
    }

    @Override
    public boolean equal(GeometricObject otherGeometricObject) {
        if (otherGeometricObject instanceof Ellipse) {
            Ellipse otherEllipse = (Ellipse) otherGeometricObject;
            return ((this.a == otherEllipse.a && this.b == otherEllipse.b) || (this.a == otherEllipse.b && this.b == otherEllipse.a));
        } else {
            return false;
        }
    }

    @Override
    public Shape createShape(int scale) {
        double scaledCenterX = (points[0].x + a) * scale;
        double scaledCenterY = (points[0].y + b) * scale;
        double scaledA = a * scale;
        double scaledB = b * scale;
        return new javafx.scene.shape.Ellipse(scaledCenterX, scaledCenterY, scaledA, scaledB);
    }

    @Override
    public boolean contains(double x, double y) {
        double Xce = points[0].x + a;
        double Yce = points[0].y + b;

        return (((x - Xce)*(x - Xce))/(a*a)) + (((y - Yce)*(y-Yce))/(b*b)) <= 1;
    }
}