package geometry;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class MouseClickHandler implements EventHandler<MouseEvent> {
    private GeometricObject[] drawings;
    private Shape[] shapes;
    private Label informationLabel;
    private double scale;

    public MouseClickHandler(GeometricObject[] drawings, Shape[] shapes, Label informationLabel, double scale) {
        this.drawings = drawings;
        this.shapes = shapes;
        this.informationLabel = informationLabel;
        this.scale = scale;
    }

    @Override
    public void handle(MouseEvent event) {
        informationLabel.setText("");

        for (Shape shape : shapes) {
            shape.setFill(Color.HOTPINK);
        }

        double clickX = event.getX() / scale;
        double clickY = event.getY() / scale;
        for (int index = 0; index < drawings.length; index++) {
            GeometricObject drawing = drawings[index];
            if (drawing.contains(clickX, clickY)) {
                String name = drawing.name;
                String perimeter = "U = " + drawing.calculatePerimeter();
                String area = "F = " + drawing.calculateArea();
                String information = String.join("\n", name, perimeter, area);
                informationLabel.setText(information);
                shapes[index].setFill(Color.PINK);
                break;
            }
        }
    }
}
