package geometry;

import javafx.scene.shape.Shape;

public class FlowerVl_2 extends GeometricObject {
    private Point startPoint;
    protected double length;
    private double radius;
    private Ellipse[] petals;
    private Ellipse center;
    private int petalsCount;
    //private Point[] ellipsesPoints;
    //private Point[] trianglePoints;
    //private double centerAngle;
    //private double startAngle;
    //private Triangle[] triangles;


    /*private void calculateAngles() {
        centerAngle = 360 * 1.0 / petals.length; //централният ъгъл между две length-та
        startAngle = (180 - centerAngle) * 1.0 / 2; //втория и третия ъгъл в един триъгълник - във всички триъгълници са еднакви
    }

    private void calculateCentre() {
        center = new Ellipse(new Point((startPoint.x - radius), (startPoint.y - radius)), radius, radius);
    }

    protected void calculateTriangles() { //пресмятане на триъгълниците и точките им
        calculateAngles(); // викаме метода, за да ни изчисли ъглите

        triangles = new Triangle[petals.length];
        trianglePoints = new Point[petals.length];

        for (int i = 0; i < trianglePoints.length; i++) { // запълваме масива с точки за триъгълниците
            trianglePoints[i] = new Point();
        }

        for (int i = 0; i < trianglePoints.length; i++) { // пресмятане на точките на триъгълниците
            trianglePoints[i].x = startPoint.x - length * Math.sin(centerAngle / 2);
            trianglePoints[i].y = startPoint.y - length * Math.cos(centerAngle / 2);

            if (i == trianglePoints.length - 1) {
                int nextI = 0;
                trianglePoints[nextI].x = startPoint.x + length * Math.sin(centerAngle / 2);
                trianglePoints[nextI].y = startPoint.y - length * Math.cos(centerAngle / 2);
            } else {
                int nextI = i + 1;
                trianglePoints[nextI].x = startPoint.x + length * Math.sin(centerAngle / 2);
                trianglePoints[nextI].y = startPoint.y - length * Math.cos(centerAngle / 2);
            }
            centerAngle += centerAngle;
        }

        for (int i = 0; i < trianglePoints.length; i++) { //пресмятане на триъгълниците
            if (i == trianglePoints.length - 1) {
                int nextPoint = 0;
                triangles[i] = new Triangle(new Point(startPoint), new Point(trianglePoints[nextPoint]), new Point(trianglePoints[i]));
            } else {
                int nextPoint = i + 1;
                triangles[i] = new Triangle(new Point(startPoint), new Point(trianglePoints[nextPoint]), new Point(trianglePoints[i]));
            }
        }
    }*/

    /*protected void calculateFigures() { // пресмятане на венчелистчетата + обединяване на фигурите
        calculateCentre();
        calculateTriangles();

        double thirdSide = Math.sqrt(2 * (length * length - length * length * Math.cos(centerAngle))); //диаметър на окръжностите(венчелистчетата) - за всички еднакъв
        double ellipsesRadius = thirdSide / 2;  //радиус на окръжностите - за всички еднакъв

        ellipsesPoints = new Point[triangles.length];
        for (int i = 0; i < ellipsesPoints.length; i++) { //пълним масива с нови точки
            ellipsesPoints[i] = new Point();
        }

        for (int i = 0; i < ellipsesPoints.length; i++) { //изчисляваме точките на елипсите
            double ang = startAngle + (i * centerAngle);
            ellipsesPoints[i].x = startPoint.x + (length + ellipsesRadius) * Math.cos(ang);
            ellipsesPoints[i].y = startPoint.y + (length + ellipsesRadius) * Math.sin(ang);
            ellipsesPoints[i].x = startPoint.x - (length + ellipsesRadius) * Math.sin(centerAngle / 2);
            ellipsesPoints[i].y = startPoint.y - (length + ellipsesRadius) * Math.cos(centerAngle / 2);

            if (i == ellipsesPoints.length - 1) {
                int nextI = 0;
                ellipsesPoints[nextI].x = startPoint.x + (length + ellipsesRadius) * Math.sin(centerAngle / 2);
                ellipsesPoints[nextI].y = startPoint.y - (length + ellipsesRadius) * Math.cos(centerAngle / 2);
            } else {
                int nextI = i + 1;
                ellipsesPoints[nextI].x = startPoint.x + (length + ellipsesRadius) * Math.sin(centerAngle / 2);
                ellipsesPoints[nextI].y = startPoint.y - (length + ellipsesRadius) * Math.cos(centerAngle / 2);
            }
            centerAngle += centerAngle;
        }

        for (int i = 0; i < ellipsesPoints.length; i++) { //пресмятаме елипсите - венчелистчетата
            petals[i] = new Ellipse(new Point(ellipsesPoints[i]), ellipsesRadius, ellipsesRadius);
        }
    }*/

    private void calculateFigures() {
        petals = new Ellipse[petalsCount];
        center = new Ellipse(new Point((startPoint.x - radius), (startPoint.y - radius)), radius, radius);

        double rotationAngleInRad = (360.0 / petalsCount) * Math.PI / 180.0;
        Point currentPoint = new Point(startPoint.x, startPoint.y - length);

        for (int i = 0; i < petals.length; i++) {
            double rotatedPointX = startPoint.x + Math.cos(rotationAngleInRad) * (currentPoint.x - startPoint.x)
                    - Math.sin(rotationAngleInRad) * (currentPoint.y - startPoint.y);
            double rotatedPointY = startPoint.y + Math.sin(rotationAngleInRad) * (currentPoint.x - startPoint.x)
                    + Math.cos(rotationAngleInRad) * (currentPoint.y - startPoint.y);

            Point rotatedPoint = new Point(rotatedPointX, rotatedPointY);

            double petalRadius = Math.sqrt(Math.pow(rotatedPoint.x - currentPoint.x, 2)
                    + Math.pow(rotatedPoint.y - currentPoint.y, 2)) / 2;

            double middlePointPetalX = (rotatedPoint.x + currentPoint.x) / 2;
            double middlePointPetalY = (rotatedPoint.y + currentPoint.y) / 2;
            Point middlePoint = new Point(middlePointPetalX, middlePointPetalY);

            Point petalStartPoint = new Point(middlePoint.x - petalRadius, middlePoint.y - petalRadius);

            petals[i] = new Ellipse(petalStartPoint, petalRadius, petalRadius);

            currentPoint = rotatedPoint;
        }
    }

    public FlowerVl_2() {
        super("Flower", 0, 0);
        startPoint = new Point(5, 5);
        length = 2;
        petalsCount = 4;
        radius = 1;
        calculateFigures();
    }

    public FlowerVl_2(Point startPoint, double length, double radius, int petalsCount) {
        super("Flower", 0, 0);
        this.startPoint = startPoint;
        this.length = length;
        this.radius = radius;
        this.petalsCount = petalsCount;
        calculateFigures();
    }

    public FlowerVl_2(FlowerVl_2 otherFlower) {
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
            this.petalsCount = Help.INPUT.nextInt();
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
            petalsArea += petals[i].calculateArea() / 2.0;
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
        if (otherGeometricObject instanceof FlowerVl_2) {
            FlowerVl_2 otherFlower = (FlowerVl_2) otherGeometricObject;
            return this.petalsCount == otherFlower.petalsCount && this.radius == otherFlower.radius && this.length == otherFlower.length;
        } else {
            return false;
        }
    }

    @Override
    public Shape createShape(int scale) {
        /*Shape[] trianglesShape = new Shape[triangles.length];
        for (int i = 0; i < triangles.length; i++) {
            trianglesShape[i] = triangles[i].createShape(scale);
        }
        Shape firstShape = trianglesShape[0];
        for (int i = 1; i < trianglesShape.length; i++) {
            firstShape = Shape.union(firstShape, trianglesShape[i]);
        }

        Shape[] petalsShape = new Shape[petals.length];
        for (int i = 0; i < petals.length; i++) {
            petalsShape[i] = petals[i].createShape(scale);
        }
        Shape secondShape = petalsShape[0];
        for (int i = 1; i < petalsShape.length; i++) {
            secondShape = Shape.union(secondShape, petalsShape[i]);
        }
        return Shape.union(firstShape, secondShape);*/

        Shape centerShape = center.createShape(scale);
        Shape finalShape = Shape.union(petals[0].createShape(scale), petals[1].createShape(scale));

        for (int i = 2; i < petalsCount; i++) {
            finalShape = Shape.union(finalShape, petals[i].createShape(scale));
        }

        return Shape.subtract(finalShape, centerShape);
    }

    @Override
    public boolean contains(double x, double y) {
        /*Point clickPoint = new Point(x, y);
        boolean isTrue = false;
        for (int i = 0; i < petals.length; i++) {
            isTrue = petals[i].contains(x, y) || triangles[i].contains(x, y);
            if (isTrue) {
                break;
            }
        }
        return isTrue;*/
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
