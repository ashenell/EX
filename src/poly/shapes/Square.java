package poly.shapes;

public class Square implements Shape {
    @Override
    public double getArea() {
        return Math.pow(getSide(), 2);
    }

    private int side;

    public Square(int side) {
        this.side = side;
    }

    public int getSide() {
        return side;
    }
}
