package Practice1;

public class Circle extends Shape {
    public static final double PI = 3.14;

    public Circle() {
        super();
    }

    // Khởi tạo hình tròn với bán kính r (đường kính d = width = height = 2 * r)
    public Circle(double radius) {
        super(radius * 2, radius * 2);
    }

    public double getRadius() {
        return getWidth() / 2.0;
    }

    public double getDiameter() {
        return getWidth();
    }

    // getArea() [πr^2]
    public double getArea() {
        double r = getRadius();
        return PI * r * r;
    }

    // getCircumference() [d * 3.14]
    public double getCircumference() {
        return getDiameter() * PI;
    }

    @Override
    public void displayInfo() {
        System.out.println("Circle: Radius = " + getRadius()
                + ", Diameter = " + getDiameter()
                + ", Area = " + getArea()
                + ", Circumference = " + getCircumference());
    }
}

