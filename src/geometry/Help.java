package geometry;

import java.util.Scanner;

public class Help {
    public static final Scanner INPUT = new Scanner(System.in);
    public static final double EPSILON = 1e-4;

    public static boolean equal(double x, double y) {
        return Math.abs(x - y) < EPSILON;
    }

    public static double calculateDistance(Point point1, Point point2) {
        double distance = Math.sqrt(((point2.x - point1.x) * (point2.x - point1.x)) +
                ((point2.y - point1.y) * (point2.y - point1.y)));
        return distance;
    }

    public static boolean areCollinear(Point point1, Point point2, Point point3) {
        if (point1.x*(point2.y - point3.y) + point2.x*(point3.y - point1.y)
                + point3.x*(point1.y - point2.y) == 0) {
    return true;
        }
        return false;
    }

    public static double calculateAngle(double b, double c, double a) {
        double sum = (b*b + c*c - a*a) / (2*b*c);
        return Math.acos(sum);
    }

    public static double calculateLineCoefficient(Point point1, Point point2) {
        if (point2.x - point1.x == 0) {
            return Double.MIN_VALUE;
        } else {
            return (point2.y - point1.y) / (point2.x - point1.x);
        }
    }
}
