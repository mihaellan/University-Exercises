package geometry;

import javafx.scene.shape.Shape;

public class Flower extends GeometricObject {
    private Point startPoint;
    protected double length;
    private double radius;
    private Ellipse[] petals;
    private Ellipse center;
    private int petalsCount;


    private void calculateFigures() {
        petals = new Ellipse[petalsCount];
        center = new Ellipse(new Point((startPoint.x - radius), (startPoint.y - radius)), radius, radius);

        double rotationAngleInRad = (360.0 / petalsCount) * Math.PI / 180.0; // 90 градуса
        Point currentPoint = new Point(startPoint.x, startPoint.y - length);

        for (int i = 0; i < petals.length; i++) {
            double rotatedPointX = startPoint.x + Math.cos(rotationAngleInRad) * (currentPoint.x - startPoint.x)
                    - Math.sin(rotationAngleInRad) * (currentPoint.y - startPoint.y);
            double rotatedPointY = startPoint.y + Math.sin(rotationAngleInRad) * (currentPoint.x - startPoint.x)
                    + Math.cos(rotationAngleInRad) * (currentPoint.y - startPoint.y);

            Point rotatedPoint = new Point(rotatedPointX, rotatedPointY);

            double petalRadius = Math.sqrt(Math.pow(rotatedPoint.x - currentPoint.x, 2)
                    + Math.pow(rotatedPoint.y - currentPoint.y, 2)) / 2; // радиус на венчелистчетата

            double middlePointPetalX = (rotatedPoint.x + currentPoint.x) / 2;
            double middlePointPetalY = (rotatedPoint.y + currentPoint.y) / 2;
            Point middlePoint = new Point(middlePointPetalX, middlePointPetalY);

            Point petalStartPoint = new Point(middlePoint.x - petalRadius, middlePoint.y - petalRadius);

            petals[i] = new Ellipse(petalStartPoint, petalRadius, petalRadius);

            currentPoint = rotatedPoint;
        }
    }

    public Flower() {
        super("Flower", 0, 0);
        startPoint = new Point(5, 5);
        length = 2;
        petalsCount = 4;
        radius = 1;
        calculateFigures();
    }

    public Flower(Point startPoint, double length, double radius, int petalsCount) {
        super("Flower", 0, 0);
        this.startPoint = startPoint;
        this.length = length;
        this.radius = radius;
        this.petalsCount = petalsCount;
        calculateFigures();
    }

    public Flower(Flower otherFlower) {
        super("Flower", 0, 0);
        startPoint = new Point(otherFlower.startPoint);
        length = otherFlower.length;
        radius = otherFlower.radius;
        petalsCount = otherFlower.petalsCount;
        calculateFigures();
    }

    @Override
    public boolean isValid() {
        return length > 0 && radius > 0 && length > radius && petalsCount > 2;
    }

    @Override
    public void initialize() {
        do {
            System.out.println("Anfangspunkt: ");
            startPoint.initialize();
            System.out.println("Länge: ");
            this.length = Help.INPUT.nextDouble();
            System.out.println("Radius: ");
            this.radius = Help.INPUT.nextDouble();
            System.out.println("Anzahl der Blütenblätter: ");
            petalsCount = Help.INPUT.nextInt();
            calculateFigures();
        } while (!isValid());
    }

    @Override
    public double calculatePerimeter() {
        double perimeter = 0;
        for (int i = 0; i < petals.length; i++) {
            perimeter += petals[i].calculatePerimeter();
        }
        return perimeter / 2.0;
    }

    @Override
    public double calculateArea() {
        double area = 0;
        double petalsArea = 0;
        for (int i = 0; i < petalsCount; i++) {
            petalsArea += petals[i].calculateArea();
        }

        area = petalsArea / 2.0;

        double innerApothem = Math.sqrt(length * length - petals[0].calculateArea() / Math.PI);
        double petalDiameter = Math.sqrt(petals[0].calculateArea() / Math.PI) * 2;
        double areaOfInnerPolygon = innerApothem * petalDiameter * petalsCount / 2.0;

        return area + areaOfInnerPolygon - center.calculateArea();
    }

    @Override
    public String getType() {
        return petalsCount + "-seitige Blume";
    }

    @Override
    public String toString() {
        return startPoint + "-{" + length + ", " + radius + "}-[" + petals.length + "]";
    }

    @Override
    public boolean equal(GeometricObject otherGeometricObject) {
        if (otherGeometricObject instanceof Flower) {
            Flower otherFlower = (Flower) otherGeometricObject;
            return this.petalsCount == otherFlower.petalsCount && this.radius == otherFlower.radius && this.length == otherFlower.length;
        } else {
            return false;
        }
    }

    @Override
    public Shape createShape(int scale) {
        Shape centerShape = center.createShape(scale);
        Shape finalShape = Shape.union(petals[0].createShape(scale), petals[1].createShape(scale));

        for (int i = 2; i < petalsCount; i++) {
            finalShape = Shape.union(finalShape, petals[i].createShape(scale));
        }

        return Shape.subtract(finalShape, centerShape);
    }

    @Override
    public boolean contains(double x, double y) {
        if (!center.contains(x, y)) {
            for (int i = 0; i < petalsCount; i++) {
                if (petals[i].contains(x, y)) {
                    return true;
                }
            }
        } else {
            return true;
        }
        return false;
    }
}
