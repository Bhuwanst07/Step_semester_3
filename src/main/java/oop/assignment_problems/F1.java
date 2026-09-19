package oop.assignment_problems;

public class F1 {

    public static abstract class Shape {

        private static int shapeCounter = 1000;

        private final String shapeId;

        protected double scaleX;
        protected double scaleY;

        public Shape() {
            shapeCounter++;
            shapeId = "SH-" + shapeCounter;

            scaleX = 1.0;
            scaleY = 1.0;
        }

        public abstract double calculateArea();

        public void scale(double factor) {
            scaleX *= factor;
            scaleY *= factor;
        }

        public void scale(double xFactor, double yFactor) {
            scaleX *= xFactor;
            scaleY *= yFactor;
        }

        public String getShapeId() {
            return shapeId;
        }
    }

    public static class CircleShape extends Shape {

        private double radius;

        public CircleShape(double radius) {

            if (radius <= 0) {
                throw new IllegalArgumentException(
                        "Radius must be positive"
                );
            }

            this.radius = radius;
        }

        @Override
        public double calculateArea() {
            return Math.PI
                    * radius
                    * radius
                    * scaleX
                    * scaleY;
        }
    }

    public static class SquareShape extends Shape {

        private double side;

        public SquareShape(double side) {

            if (side <= 0) {
                throw new IllegalArgumentException(
                        "Side must be positive"
                );
            }

            this.side = side;
        }

        @Override
        public double calculateArea() {
            return side
                    * side
                    * scaleX
                    * scaleY;
        }
    }

    public static void printArea(Shape s) {
        System.out.println(s.calculateArea());
    }

    public static void main(String[] args) {

        CircleShape c =
                new CircleShape(5.0);

        System.out.println(
                c.calculateArea()
        );

        SquareShape sq =
                new SquareShape(4.0);

        System.out.println(
                sq.calculateArea()
        );

        sq.scale(2.0);

        System.out.println(
                sq.calculateArea()
        );

        sq.scale(0.5, 2.0);

        System.out.println(
                sq.calculateArea()
        );

        System.out.println(
                c.getShapeId()
        );

        printArea(c);

        // Shape cannot be instantiated because it is abstract.
    }
}