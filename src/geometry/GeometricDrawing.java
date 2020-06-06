package geometry;

import javafx.scene.shape.Shape;

public interface GeometricDrawing {
    Shape createShape(int scale);
    boolean contains(double x, double y);
}
