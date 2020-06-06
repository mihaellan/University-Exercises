package geometry;

public class FlowerDemo {
    public static void main(String[] args) {
       Flower flower1 = new Flower();

       Point startPoint2 = new Point(9,9);
       Flower flower2 = new Flower(startPoint2, 4, 2, 6);
       Flower flower3 = new Flower(flower2);

       Flower flower4 = new Flower();
       flower4.initialize();

       flower1.print();
       flower2.print();
       flower3.print();
       flower4.print();

        System.out.println("Blume 2 gleich Blume 1: " + flower2.equal(flower1));
        System.out.println("Blume 2 gleich Blume 3: " + flower2.equal(flower3));

        /*Anfangspunkt:
          Eingabe x:
          5
          Eingabe y:
          5
          Länge:
          2
          Radius:
          1
          Anzahl der Blütenblätter:
          4
          Flower: 4-seitige Blume, (5.0,5.0)-{2.0, 1.0}-[4], U=17.771531752633468, F=11.141592653589793
          Flower: 6-seitige Blume, (9.0,9.0)-{4.0, 2.0}-[6], U=37.6991118430775, F=47.85240468883261
          Flower: 6-seitige Blume, (9.0,9.0)-{4.0, 2.0}-[6], U=37.6991118430775, F=47.85240468883261
          Flower: 4-seitige Blume, (5.0,5.0)-{2.0, 1.0}-[4], U=17.771531752633468, F=11.141592653589793
          Blume 2 gleich Blume 1: false
          Blume 2 gleich Blume 3: true*/

        /*Anfangspunkt:
          Eingabe x:
          9
          Eingabe y:
          9
          Länge:
          7
          Radius:
          3
          Anzahl der Blütenblätter:
          7
          Flower: 4-seitige Blume, (5.0,5.0)-{2.0, 1.0}-[4], U=17.771531752633468, F=11.141592653589793
          Flower: 6-seitige Blume, (9.0,9.0)-{4.0, 2.0}-[6], U=37.6991118430775, F=47.85240468883261
          Flower: 6-seitige Blume, (9.0,9.0)-{4.0, 2.0}-[6], U=37.6991118430775, F=47.85240468883261
          Flower: 7-seitige Blume, (9.0,9.0)-{7.0, 3.0}-[7], U=66.79121239886577, F=156.52410206863507
          Blume 2 gleich Blume 1: false
          Blume 2 gleich Blume 3: true*/
    }
}
