package Practice1;

public class Rectangle extends Shape {

    public Rectangle() {
        super();
    }

    public Rectangle(double width, double height) {
        super(width, height);
    }

    // getArea() [w * l]
    public double getArea() {
        return getWidth() * getHeight();
    }

    // getPerimeter() [2 * (l + w)]
    public double getPerimeter() {
        return 2 * (getWidth() + getHeight());
    }

    @Override
    public void displayInfo() {
        System.out.println("Rectangle: Width = " + getWidth()
                + ", Height = " + getHeight()
                + ", Area = " + getArea()
                + ", Perimeter = " + getPerimeter());
    }
}

