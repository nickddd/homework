package Q3.OODesign;

public class TestOODesign {
    public static void main(String[] args) {
        Circle circle = new Circle();
        Square square = new Square();
        ProxyDrawing proxyDrawing = new ProxyDrawing(circle);
        proxyDrawing.draw();
        proxyDrawing = new ProxyDrawing(square);
        proxyDrawing.draw();
    }
}
