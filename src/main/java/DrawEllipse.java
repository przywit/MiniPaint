import

import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class DrawEllipse extends Tool {

    public DrawEllipse (MiniPaintPaintingArea miniPaintPaintingArea) {
        super(miniPaintPaintingArea);
    }
    Ellipse2D ellipse;
    private int originX = 0;
    private int originY = 0;
    private int width = 0;
    private int height = 0;

    /**
     * method saves place where user clicked and creates a new shape, which has active color
     * @param e ...
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if ( e.getButton() == MouseEvent.BUTTON1 ) {
            originX = e.getX();
            originY = e.getY();
            ellipse = new Ellipse2D.Float(originX, originY, 0, 0);
            miniPaintPaintingArea.addShapeToShapesList(ellipse);
            miniPaintPaintingArea.addColorToColorList();
            miniPaintPaintingArea.repaint();
        }
    }

    /**
     * method responsible for changes made on current shape while drawing
     * @param e ...
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if(ellipse != null) {
            if((e.getY() < originY) && (e.getX() > originX)) { //I quarter
                width = e.getX() - originX;
                height = originY - e.getY();
                ellipse.setFrame(originX, e.getY(),width,height);
                miniPaintPaintingArea.repaint();
            }
            else if((e.getY() < originY) && (e.getX() < originX)) { // II quarter
                width = originX - e.getX();
                height = originY - e.getY();
                ellipse.setFrame(e.getX(), e.getY(),width,height);
                miniPaintPaintingArea.repaint();
            }
            else if((e.getY() > originY) && (e.getX() < originX)) { // III quarter
                width = originX - e.getX();
                height = e.getY() - originY;
                ellipse.setFrame(e.getX(), originY,width,height);
                miniPaintPaintingArea.repaint();
            }
            else { // IV quarter
                width = e.getX() - originX;
                height = e.getY() - originY;
                ellipse.setFrame(originX, originY, width, height);
                miniPaintPaintingArea.repaint();
            }
        }
    }

    /**
     * we calculate the center cords of already drown shape
     * @param e ...
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if ( e.getButton() == MouseEvent.BUTTON1 ) {
            miniPaintPaintingArea.addCenterXCordToCenterXCordsList(originX + (width / 2));
            miniPaintPaintingArea.addCenterYCordToCenterYCordsList(originY + (height / 2));
            ellipse = null;
        }
    }
}
