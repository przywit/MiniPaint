import java.awt.*;
import java.awt.event.MouseEvent;

public class ObjectTransformation extends Tool {
    int indexOfObjectToTransform = -1;
    int originX = 0;
    int originY = 0;
    public ObjectTransformation(MiniPaintPaintingArea minipaintPaintingArea) {
        super(minipaintPaintingArea);
        minipaintPaintingArea.setObjectToTransform(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if ( e.getButton() == MouseEvent.BUTTON1 ) {
            originX = e.getX();
            originY = e.getY();
            indexOfObjectToTransform = miniPaintPaintingArea.getIndexOfObjectByMousePosition(originX,originY);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(indexOfObjectToTransform != -1) {
            if((e.getY() < originY) && (e.getX() > originX)) { // I quarter
                miniPaintPaintingArea.translateObject(e.getX() - originX,e.getY() - originY, indexOfObjectToTransform);
            }
            else if((e.getY() < originY) && (e.getX() < originX)) { // II quarter
                miniPaintPaintingArea.translateObject(e.getX() - originX,e.getY() - originY, indexOfObjectToTransform);
            }
            else if((e.getY() > originY) && (e.getX() < originX)) { // III quarter
                miniPaintPaintingArea.translateObject(e.getX() - originX,e.getY() - originY, indexOfObjectToTransform);
            }
            else { // IV quarter
                miniPaintPaintingArea.translateObject(e.getX() - originX,e.getY() - originY, indexOfObjectToTransform);

            }
            miniPaintPaintingArea.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
}
