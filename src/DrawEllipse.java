import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class DrawEllipse extends Tool {

    public DrawEllipse (MiniPaintPaintingArea miniPaintPaintingArea) {
        super(miniPaintPaintingArea);
    }
    Ellipse2D ellipse;
    int originX = 0;
    int originY = 0;
    int width = 0;
    int height = 0;
    double scale = 1.0;

    @Override
    public void mousePressed(MouseEvent e) {
        if ( e.getButton() == MouseEvent.BUTTON1 ) {
            originX = e.getX();
            originY = e.getY();
            ellipse = new Ellipse2D.Float(originX, originY, 0, 0);
            miniPaintPaintingArea.addShapeToShapesList(ellipse);
            miniPaintPaintingArea.addScaleToScalesList(scale);
            miniPaintPaintingArea.addColorToColorList();
            miniPaintPaintingArea.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(ellipse != null) {
            if((e.getY() < originY) && (e.getX() > originX)) {
                width = e.getX() - originX;
                height = originY - e.getY();
                ellipse.setFrame(originX, e.getY(),width,height);
                miniPaintPaintingArea.repaint();
            }
            else if((e.getY() < originY) && (e.getX() < originX)) {
                width = originX - e.getX();
                height = originY - e.getY();
                ellipse.setFrame(e.getX(), e.getY(),width,height);
                miniPaintPaintingArea.repaint();
            }
            else if((e.getY() > originY) && (e.getX() < originX)) {
                width = originX - e.getX();
                height = e.getY() - originY;
                ellipse.setFrame(e.getX(), originY,width,height);
                miniPaintPaintingArea.repaint();
            }
            else {
                width = e.getX() - originX;
                height = e.getY() - originY;
                ellipse.setFrame(originX, originY, width, height);
                miniPaintPaintingArea.repaint();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if ( e.getButton() == MouseEvent.BUTTON1 ) {
            miniPaintPaintingArea.addCenterXCordToCenterXCordsList(width / 2);
            miniPaintPaintingArea.addCenterYCordToCenterYCordsList(height / 2);
            ellipse = null;
        }
    }
}
