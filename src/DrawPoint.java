
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

public class DrawPoint extends Tool {


    public DrawPoint (MiniPaintPaintingArea miniPaintPaintingArea) {
        super(miniPaintPaintingArea);
    }
    int originX = 0;
    int originY = 0;

    @Override
    public void mousePressed(MouseEvent e) {
        if ( e.getButton() == MouseEvent.BUTTON1 ) {
            originX = e.getX();
            originY = e.getY();
            Line2D point =  new Line2D.Float(originX, originY, originX, originY);
            miniPaintPaintingArea.addShapeToShapesList(point);
            miniPaintPaintingArea.addColorToColorList();
            miniPaintPaintingArea.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

}
