import java.awt.*;
import java.awt.event.MouseEvent;

public class ObjectTransformation extends Tool {
    Shape objectToTransform;
    public ObjectTransformation(MiniPaintPaintingArea minipaintPaintingArea) {
        super(minipaintPaintingArea);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if ( e.getButton() == MouseEvent.BUTTON1 ) {
            for (Shape shape : miniPaintPaintingArea.shapesList) {
                if (shape.contains(e.getX(),e.getY())){
                    objectToTransform = shape;
                }
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
