import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class DrawEllipse extends Tool {

    public DrawEllipse (MiniPaintPaintingArea miniPaintPaintingArea) {
        super(miniPaintPaintingArea);
    }
    Ellipse2D ellipse;
    int originX = 0;
    int originY = 0;

    @Override
    public void mousePressed(MouseEvent e) {
        if ( e.getButton() == MouseEvent.BUTTON1 ) {
            originX = e.getX();
            originY = e.getY();
            ellipse = new Ellipse2D.Float(originX, originY, 0, 0);
            miniPaintPaintingArea.addShapeToShapesList(ellipse);
            miniPaintPaintingArea.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(ellipse != null) {
            if((e.getY() < originY) && (e.getX() > originX)) {
                ellipse.setFrame(originX, e.getY(),e.getX() - originX,originY - e.getY());
                miniPaintPaintingArea.repaint();
            }
            else if((e.getY() < originY) && (e.getX() < originX)) {
                ellipse.setFrame(e.getX(), e.getY(),originX - e.getX(),originY - e.getY());
                miniPaintPaintingArea.repaint();
            }
            else if((e.getY() > originY) && (e.getX() < originX)) {
                ellipse.setFrame(e.getX(), originY,originX - e.getX(),e.getY() - originY);
                miniPaintPaintingArea.repaint();
            }
            else {
                ellipse.setFrame(originX, originY, e.getX() - originX, e.getY() - originY);
                miniPaintPaintingArea.repaint();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if ( e.getButton() == MouseEvent.BUTTON1 ) {
            ellipse = null;
        }
    }
}
