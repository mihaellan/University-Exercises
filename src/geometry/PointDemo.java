package geometry;

public class PointDemo {
    public static void main(String[] args) {
        Point point1 = new Point();
        Point point2 = new Point(1, 2);
        Point point3 = new Point();

        System.out.println("Eingabe Punkt 3");

        point3.initialize();
        Point point4 = new Point(point3);
        Point point5 = point2.createNewMoved(1, 2);
        Point point6 = point2;

        System.out.print("Punkt 1: ");
        point1.print();
        System.out.print("Punkt 2: ");
        point2.print();
        System.out.print("Punkt 3: ");
        point3.print();
        System.out.print("Punkt 4: ");
        point4.print();
        System.out.print("Punkt 5: ");
        point5.print();
        System.out.print("Punkt 6: ");
        point6.print();

        point2.move(4, 5);
        System.out.print("Punkt 2: ");
        point2.print();
        System.out.print("Pinkt 6: ");
        point6.print();

        System.out.println("Punkt 1 gleich Punkt 5: " + point1.equal(point5));
        point1.x = 2;
        point1.y = 4;
        System.out.println("Punkt 1 gleich Punkt 5: " + point1.equal(point5));
    }
}
