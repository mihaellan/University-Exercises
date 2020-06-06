package geometry;

public class TriangleDemo {
    public static void main(String[] args) {
        Triangle triangle1 = new Triangle();
        Point point1 = new Point(4, 0);
        Point point2 = new Point(9, 0);
        Point point3 = new Point(1, 3);
        Triangle triangle2 = new Triangle(point1, point2, point3);
        Triangle triangle3 = new Triangle(triangle2);
        Triangle triangle4 = new Triangle();
        triangle4.initialize();
        triangle1.print();
        triangle2.print();
        triangle3.print();
        triangle4.print();

        System.out.println("Dreieck 2 gleich Dreieck 1: " + triangle2.equal(triangle1));
        System.out.println("Dreieck 2 gleich Dreieck 3: " + triangle2.equal(triangle3));
    }
}