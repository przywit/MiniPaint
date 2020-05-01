
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class DrawRectangle extends Tool {

    public DrawRectangle (MiniPaintPaintingArea miniPaintPaintingArea) {
        super(miniPaintPaintingArea);
    }

    Rectangle2D rectangle;
    int originX = 0;
    int originY = 0;

    @Override
    public void mousePressed(MouseEvent e) {
        if ( e.getButton() == MouseEvent.BUTTON1 ) {
            originX = e.getX();
            originY = e.getY();
            rectangle = new Rectangle2D.Float(originX, originY, 0, 0);
            miniPaintPaintingArea.addShapeToShapesList(rectangle);
            miniPaintPaintingArea.addColorToColorList();
            miniPaintPaintingArea.repaint();
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(rectangle != null) {
            if((e.getY() < originY) && (e.getX() > originX)) {
                rectangle.setRect(originX, e.getY(),e.getX() - originX,originY - e.getY());
                miniPaintPaintingArea.repaint();
            }
            else if((e.getY() < originY) && (e.getX() < originX)) {
                rectangle.setRect(e.getX(), e.getY(),originX - e.getX(),originY - e.getY());
                miniPaintPaintingArea.repaint();
            }
            else if((e.getY() > originY) && (e.getX() < originX)) {
                rectangle.setRect(e.getX(), originY,originX - e.getX(),e.getY() - originY);
                miniPaintPaintingArea.repaint();
            }
            else {
                rectangle.setRect(originX, originY, e.getX() - originX, e.getY() - originY);
                miniPaintPaintingArea.repaint();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if ( e.getButton() == MouseEvent.BUTTON1 ) {
            rectangle = null;
        }
    }
}
