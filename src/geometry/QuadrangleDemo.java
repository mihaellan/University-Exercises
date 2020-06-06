package geometry;

public class QuadrangleDemo {
    public static void main(String[] args) {

        Quadrangle quadrangle1 = new Quadrangle();

        Point p1 = new Point(2,2);
        Point p2 = new Point(8,1);
        Point p3 = new Point(6,8);
        Point p4 = new Point(3,6);
        Quadrangle quadrangle2 = new Quadrangle(p1,p2,p3,p4);

        Point[] points = new Point[4];
        points[0] = new Point(2,2);
        points[1] = new Point(13,2);
        points[2] = new Point(8,6);
        points[3] = new Point(4,6);
        Quadrangle quadrangle3 = new Quadrangle(points);

        /*
        Point[] points = {new Point(2,2); new Point(13,2); new Point(8,6); new Point(4,6)}
         */

        Quadrangle quadrangle4 = new Quadrangle(quadrangle3);

        Quadrangle quadrangle5 = new Quadrangle();
        quadrangle5.initialize();

        quadrangle1.print();
        quadrangle2.print();
        quadrangle3.print();
        quadrangle4.print();
        quadrangle5.print();

        System.out.println("Viereck 3 gleich Viereck 4: " + quadrangle3.equal(quadrangle4));
        System.out.println("Viereck 3 gleich Viereck 5: " + quadrangle3.equal(quadrangle5));

    }
}
