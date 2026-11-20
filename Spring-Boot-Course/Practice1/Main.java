package Practice1;

public class Main {
    public static void main(String[] args) {
        // 1. Khởi tạo đối tượng Shape
        Shape shape = new Shape(10, 20);

        // 2. Khởi tạo đối tượng Rectangle (chiều rộng = 5, chiều dài/cao = 8)
        Rectangle rectangle = new Rectangle(5, 8);

        // 3. Khởi tạo đối tượng Circle (bán kính r = 7 -> đường kính d = 14)
        Circle circle = new Circle(7);

        // Hiển thị thông tin các đối tượng
        System.out.println("=== THÔNG TIN CÁC HÌNH ===");
        shape.displayInfo();
        rectangle.displayInfo();
        circle.displayInfo();
    }
}

