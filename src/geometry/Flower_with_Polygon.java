package geometry;

import javafx.scene.shape.Shape;


public class Flower_with_Polygon extends GeometricObject {
    protected double length;
    protected double radius;
    protected Ellipse[] petals;
    protected Polygon polygon;
    protected Ellipse centre;



    protected void calculateCentre() {
        centre = new Ellipse(new Point((points[0].x - radius), (points[0].y - radius)), radius, radius);
    }


    protected void calculateFigures() {
        /*if (petals.length == 4) {
            Point[] quadranglePoints = new Point[4];
            quadranglePoints[0] = new Point(points[0].x - length, points[0].y);
            quadranglePoints[1] = new Point(points[0].x, points[0].y + length);
            quadranglePoints[2] = new Point(points[0].x + length, points[0].y);
            quadranglePoints[3] = new Point(points[0].x, points[0].y - length);

            quadrangle = new Quadrangle(quadranglePoints[0], quadranglePoints[1], quadranglePoints[2], quadranglePoints[3]);

            Point[] centres = new Point[4];
            centres[0] = new Point((quadranglePoints[0].x + quadranglePoints[1].x)/2,(quadranglePoints[0].y + quadranglePoints[1].y)/2);
            centres[1] = new Point((quadranglePoints[1].x + quadranglePoints[2].x)/2,(quadranglePoints[1].y + quadranglePoints[2].y)/2);
            centres[2] = new Point((quadranglePoints[2].x + quadranglePoints[3].x)/2,(quadranglePoints[2].y + quadranglePoints[3].y)/2);
            centres[3] = new Point((quadranglePoints[3].x + quadranglePoints[0].x)/2,(quadranglePoints[3].y + quadranglePoints[0].y)/2);

            double ellipseSide = quadrangle.sides[0] / 2;
            petals[0] = new Ellipse(new Point(centres[0].x - ellipseSide, centres[0].y - ellipseSide), ellipseSide, ellipseSide);
            petals[1] = new Ellipse(new Point(centres[1].x - ellipseSide, centres[1].y + ellipseSide), ellipseSide, ellipseSide);
            petals[2] = new Ellipse(new Point(centres[2].x + ellipseSide, centres[2].y + ellipseSide), ellipseSide, ellipseSide);
            petals[3] = new Ellipse(new Point(centres[3].x + ellipseSide, centres[3].y - ellipseSide), ellipseSide, ellipseSide);
        } else if (petals.length == 6) {
            polygon = new Polygon(6, 6, points[0], length);

        }*/
        polygon = new Polygon(petals.length, petals.length, points[0], length);
        calculateCentre();
    }

    public Flower_with_Polygon() {
        super("Flower", 1, 0);
        points[0] = new Point(5,5);
        length = 2;
        radius = 1;
        petals = new Ellipse[4];
        calculateFigures();
    }

    public Flower_with_Polygon(Point startPoint, double length, double radius, Ellipse[] petals) {
        super("Flower", 1, 0);
        this.points[0] = startPoint;
        this.length = length;
        this.radius = radius;
        this.petals = petals;
        calculateFigures();
    }

    public Flower_with_Polygon(Flower_with_Polygon otherFlower) {
        super("Flower", 1, 0);
        points[0] = new Point(otherFlower.points[0]);
        length = otherFlower.length;
        radius = otherFlower.radius;
        petals = otherFlower.petals;
        calculateFigures();
    }

    @Override
    public boolean isValid() {
        return length > 0 && radius > 0 && length > radius && petals.length > 2;
    }

    @Override
    public void initialize() {
        do {
            System.out.println("Anfangspunkt: ");
            points[0].initialize();
            System.out.println("Länge: ");
            this.length = Help.INPUT.nextDouble();
            System.out.println("Radius: ");
            this.radius = Help.INPUT.nextDouble();
            System.out.println("Anzahl der Blütenblätter: ");
            Ellipse[] petals = new Ellipse[Help.INPUT.nextInt()];
        } while (!isValid());
    }

    @Override
    public double calculatePerimeter() {
        double perimeter = 0;
        for (int i = 0; i < petals.length; i++) {
            perimeter = petals[i].calculatePerimeter();
        }
        return perimeter / 2;
    }

    @Override
    public double calculateArea() {
        double area = 0;
        for(int i = 0; i < petals.length; i++) {
            area += petals[i].calculateArea();
        }
        area = area / 2;
        area += polygon.calculateArea();

        return area;
    }

    @Override
    public String getType() {
        return petals.length + "-seitige Blume";
    }

    @Override
    public String toString() {
        return   points[0] + "-{" + length + ", " + radius + "}-[" + petals.length +"]";
    }

    @Override
    public boolean equal(GeometricObject otherGeometricObject) {
        if(otherGeometricObject instanceof Flower_with_Polygon) {
            Flower_with_Polygon otherFlower = (Flower_with_Polygon) otherGeometricObject;
            return this.petals.length == otherFlower.petals.length && this.radius == otherFlower.radius && this.length == otherFlower.length;
        } else {
            return false;
        }
    }

    @Override
    public Shape createShape(int scale) {
        Shape[] shape = new Shape[petals.length];
        int index = 0;
        for (int i = 0; i < petals.length; i++) {
            shape[index] = petals[i].createShape(scale);
            index++;
        }
            return null;
    }


    @Override
    public boolean contains(double x, double y) {
        Point clickPoint = new Point(x, y);
        boolean isTrue = false;
            for (int i = 0; i < petals.length; i++) {
                isTrue = petals[i].contains(x,y);
                if (isTrue) {
                    break;
                }
            }
            return isTrue || polygon.contains(x, y);
    }
}
