package geometry;

public class EllipseDemo {
    public static void main(String[] args) {

        Ellipse ellipse1 = new Ellipse();
        Point startPoint2 = new Point(2,3);
        Ellipse ellipse2 = new Ellipse(startPoint2, 5,6);
        Ellipse ellipse3 = new Ellipse(ellipse2);
        Ellipse ellipse4 = new Ellipse();

        ellipse4.initialize();
        ellipse1.print();
        ellipse2.print();
        ellipse3.print();
        ellipse4.print();

        System.out.println("Ellipse 2 gleich Ellipse 1: " + ellipse2.equal(ellipse1));
        System.out.println("Ellipse 2 gleich Ellipse 3: " + ellipse2.equal(ellipse3));
    }
}
