
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class DrawRectangle extends Tool {

    public DrawRectangle (MiniPaintPaintingArea miniPaintPaintingArea) {
        super(miniPaintPaintingArea);
    }

    Rectangle2D rectangle;
    int originX = 0;
    int originY = 0;
    int width = 0;
    int height = 0;
    int xCenterCord = 0;
    int yCenterCord = 0;
    double scale = 1.0;

    @Override
    public void mousePressed(MouseEvent e) {
        if ( e.getButton() == MouseEvent.BUTTON1 ) {
            originX = e.getX();
            originY = e.getY();
            rectangle = new Rectangle2D.Float(originX, originY, 0, 0);
            miniPaintPaintingArea.addShapeToShapesList(rectangle);
            miniPaintPaintingArea.addColorToColorList();
            miniPaintPaintingArea.addScaleToScalesList(scale);
            miniPaintPaintingArea.repaint();
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(rectangle != null) {
            if((e.getY() < originY) && (e.getX() > originX)) {
                width = e.getX() - originX;
                height = originY - e.getY();
                rectangle.setRect(originX, e.getY(),width,height);
                miniPaintPaintingArea.repaint();
            }
            else if((e.getY() < originY) && (e.getX() < originX)) {
                width = originX - e.getX();
                height = originY - e.getY();
                rectangle.setRect(e.getX(), e.getY(),width,height);
                miniPaintPaintingArea.repaint();
            }
            else if((e.getY() > originY) && (e.getX() < originX)) {
                width = originX - e.getX();
                height = e.getY() - originY;
                rectangle.setRect(e.getX(), originY,width,height);
                miniPaintPaintingArea.repaint();
            }
            else {
                width = e.getX() - originX;
                height = e.getY() - originY;
                xCenterCord = originX + (width / 2);
                yCenterCord = originY + (height / 2);
                rectangle.setRect(originX, originY, width, height);
                miniPaintPaintingArea.repaint();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if ( e.getButton() == MouseEvent.BUTTON1 ) {
            miniPaintPaintingArea.addCenterXCordToCenterXCordsList(originX + (width / 2));
            miniPaintPaintingArea.addCenterYCordToCenterYCordsList(originY + (height / 2));

            rectangle = null;
        }
    }

}
