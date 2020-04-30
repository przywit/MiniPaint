import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class DrawTriangle extends Tool {

    public DrawTriangle (MiniPaintPaintingArea miniPaintPaintingArea) {
        super(miniPaintPaintingArea);
    }
    Polygon triangle =  new Polygon();
    int originX = 0;
    int originY = 0;

    @Override
    public void mousePressed(MouseEvent e) {
        if ( e.getButton() == MouseEvent.BUTTON1 ) {
            originX = e.getX();
            originY = e.getY();
            triangle = new Polygon(new int[]  {originX,originX,originX}, new int[] {originY,originY,originY},3);
            miniPaintPaintingArea.addShapeToShapesList(triangle);
            miniPaintPaintingArea.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(triangle != null) {
            if((e.getY() < originY) && (e.getX() > originX)) {
               // triangle.transform(originX, e.getY(),e.getX() - originX,originY - e.getY());
                miniPaintPaintingArea.repaint();
            }
            else if((e.getY() < originY) && (e.getX() < originX)) {
              //  triangle.setFrame(e.getX(), e.getY(),originX - e.getX(),originY - e.getY());
                miniPaintPaintingArea.repaint();
            }
            else if((e.getY() > originY) && (e.getX() < originX)) {
              //  triangle.setFrame(e.getX(), originY,originX - e.getX(),e.getY() - originY);
                miniPaintPaintingArea.repaint();
            }
            else {
              //  triangle.setFrame(originX, originY, e.getX() - originX, e.getY() - originY);
                miniPaintPaintingArea.repaint();
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if ( e.getButton() == MouseEvent.BUTTON1 ) {
            triangle = null;
        }

    }
}
