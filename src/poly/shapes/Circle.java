package poly.shapes;

public class Circle implements Shape {
    @Override
    public double getArea() {
        return Math.PI * Math.pow(getRadius(), 2);
    }

    private int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }


}
