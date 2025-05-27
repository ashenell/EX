package poly.shapes;

public class Rectangle implements Shape {
    @Override
    public double getArea() {
        return getHeight() * getWidth();
    }

    private int height;
    private int width;

    public Rectangle(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
