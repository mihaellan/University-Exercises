package geometry;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class Quadrangle extends GeometricObject implements GeometricDrawing {

    @Override
    protected void calculateSides() {
        sides[0] = Help.calculateDistance(points[0], points[1]);
        sides[1] = Help.calculateDistance(points[1], points[2]);
        sides[2] = Help.calculateDistance(points[2], points[3]);
        sides[3] = Help.calculateDistance(points[3], points[0]);
    }

    public Quadrangle() {
        super("Quadrangle", 4, 4);
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 0);
        points[2] = new Point(1, 1);
        points[3] = new Point(0, 1);
        calculateSides();
    }

    public Quadrangle(Point point1, Point point2, Point point3, Point point4) {
        super("Quadrangle", 4, 4);
        points[0] = new Point(point1);
        points[1] = new Point(point2);
        points[2] = new Point(point3);
        points[3] = new Point(point4);
        calculateSides();
    }

    public Quadrangle(Point[] p) {
        super("Quadrangle", 4, 4);
        for (int i = 0; i < points.length; i++) {
            points[i] = p[i];
        }
        calculateSides();
    }

    public Quadrangle(Quadrangle otherQuadrangle) {
        super("Quadrangle", 4, 4);
        for (int i = 0; i < otherQuadrangle.points.length; i++) {
            points[i] = new Point(otherQuadrangle.points[i]);
        }
        calculateSides();
    }

    @Override
    public double calculatePerimeter() {
        return this.sides[0] + this.sides[1] + this.sides[2] + this.sides[3];
    }

    @Override
    public double calculateArea() {
        return Math.abs((((points[0].x * points[1].y) - (points[0].y * points[1].x)) +
                ((points[1].x * points[2].y) - (points[1].y * points[2].x)) +
                ((points[2].x * points[3].y) - (points[2].y * points[3].x)) +
                ((points[3].x * points[0].y) - (points[3].y * points[0].x))) / 2);
    }

    @Override
    public boolean isValid() {
        double quadrangleArea = calculateArea();
        Triangle[] triangles = new Triangle[4];
        triangles[0] = new Triangle(points[0], points[1], points[2]);
        triangles[1] = new Triangle(points[1], points[2], points[3]);
        triangles[2] = new Triangle(points[2], points[3], points[0]);
        triangles[3] = new Triangle(points[3], points[0], points[1]);

        for (Triangle triangle : triangles) {
            boolean triangleNotValid = !triangle.isValid();
            boolean triangleAreaBigger = triangle.calculateArea() > quadrangleArea;
            if (triangleNotValid || triangleAreaBigger) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void initialize() {
        do {
            points[0].initialize();
            points[1].initialize();
            points[2].initialize();
            points[3].initialize();
        } while (!isValid());
        calculateSides();
    }

    @Override
    public String getType() {
        double koef1 = Help.calculateLineCoefficient(points[0], points[1]);
        double koef2 = Help.calculateLineCoefficient(points[1], points[2]);
        double koef3 = Help.calculateLineCoefficient(points[2], points[3]);
        double koef4 = Help.calculateLineCoefficient(points[3], points[0]);

        if (Help.equal(koef1, koef3) && Help.equal(koef2, koef4)) {
            return "Parallelogramm";
        } else if (Help.equal(koef1, koef3) || Help.equal(koef2, koef4)) {
            return "Trapez";
        } else {
            return "gewoenliches Viereck";
        }
    }

    @Override
    public String toString() {
        return "(" + points[0].x + ", " + points[0].y + ")-(" + points[1].x + ", " + points[1].y + ")-(" +
                points[2].x + ", " + points[2].y + ")-(" + points[3].x + ", " + points[3].y + ")";
    }

    @Override
    public void print() {
        System.out.printf("%s, %s, U=%s\n, F=%s\n", this, getType(), calculatePerimeter(), calculateArea());
    }

    @Override
    public boolean equal(GeometricObject otherGeometricObject) {
        if (otherGeometricObject instanceof Quadrangle) {
            Quadrangle otherQuadrangle = (Quadrangle) otherGeometricObject;
            Triangle triangle1 = new Triangle(points[0], points[1], points[2]);
            Triangle triangle2 = new Triangle(points[0], points[2], points[3]);
            Point[] otherPoints = otherQuadrangle.points;
            Triangle otherTriangle1 = new Triangle(otherPoints[0], otherPoints[1], otherPoints[2]);
            Triangle otherTriangle2 = new Triangle(otherPoints[0], otherPoints[2], otherPoints[3]);

            return triangle1.equal(otherTriangle1) && triangle2.equal(otherTriangle2);
        } else {
            return false;
        }
    }

    @Override
    public Shape createShape(int scale) {
        double[] coordinates = {
            points[0].x, points[0].y,
            points[1].x, points[1].y,
            points[2].x, points[2].y,
            points[3].x, points[3].y
        };
        for (int index = 0; index < coordinates.length; index++) {
            coordinates[index] *= scale;
        }
        return new Polygon(coordinates);
    }

    @Override
    public boolean contains(double x, double y) {
        Point clickPoint = new Point(x, y);

        Triangle ABK = new Triangle(points[0], points[1], clickPoint);
        Triangle BCK = new Triangle(points[1], points[2], clickPoint);
        Triangle CDK = new Triangle(points[2], points[3], clickPoint);
        Triangle DAK = new Triangle(points[3], points[0], clickPoint);
        Quadrangle ABCD = new Quadrangle(points[0], points[1], points[2], points[3]);

        double areaABK = ABK.calculateArea();
        double areaBCK = BCK.calculateArea();
        double areaCDK = CDK.calculateArea();
        double areaDAK = DAK.calculateArea();
        double areaABCD = ABCD.calculateArea();

        if (Help.equal(areaABK + areaBCK + areaCDK + areaDAK, areaABCD)) {
            return true;
        } else {
            return false;
        }
    }
}
