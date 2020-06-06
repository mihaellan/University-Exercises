package geometry;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class FlowerDesktopApplication extends Application {
    private static final int SCALE = 50;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GeometricObject[] flowers = new GeometricObject[1];

        flowers[0] = new Flower(new Point(5, 5), 6, 1, 7);

        Shape[] shapes = new Shape[1];

        for (int index = 0; index < shapes.length; index++) {
            Shape shape = flowers[index].createShape(SCALE);
            shape.setStroke(Color.BLACK);
            shape.setFill(Color.HOTPINK);

            shapes[index] = shape;
        }

        Label informationLabel = new Label();
        informationLabel.setLayoutX(300);

        Pane root = new Pane();
        root.getChildren().addAll(shapes);
        root.getChildren().add(informationLabel);
        root.setOnMouseClicked(new MouseClickHandler(flowers, shapes, informationLabel, SCALE));

        Scene scene = new Scene(root, 1500, 750);
        primaryStage.setTitle("Flower:");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
