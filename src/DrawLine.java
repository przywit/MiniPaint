import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

public class DrawLine extends Tool {

    public DrawLine (MiniPaintPaintingArea miniPaintPaintingArea) {
        super(miniPaintPaintingArea);
    }
    Line2D line;
    int originX = 0;
    int originY = 0;

    @Override
    public void mousePressed(MouseEvent e) {
        if ( e.getButton() == MouseEvent.BUTTON1 ) {
            originX = e.getX();
            originY = e.getY();
            line = new Line2D.Float(originX,originY,originX,originY);
            miniPaintPaintingArea.addShapeToShapesList(line);
            miniPaintPaintingArea.repaint();
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(line != null) {
            line.setLine(originX, originY, e.getX(), e.getY());
            miniPaintPaintingArea.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if ( e.getButton() == MouseEvent.BUTTON1 ) {
            line = null;
        }
    }
}
