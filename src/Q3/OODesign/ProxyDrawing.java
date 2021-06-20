package Q3.OODesign;

public class ProxyDrawing implements Drawing {

    private Drawing drawing;

    public ProxyDrawing(Drawing drawing) {
        this.drawing = drawing;
    }

    private void preprocessing() {
        System.out.println("preprocessing");
    }

    private void postprocessing() {
        System.out.println("postprocessing");
    }

    @Override
    public void draw() {
        preprocessing();
        drawing.draw();
        postprocessing();
    }
}
