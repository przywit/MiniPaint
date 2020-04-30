import java.awt.*;
import java.awt.event.MouseEvent;


public class DrawTriangle extends Tool {

    public DrawTriangle (MiniPaintPaintingArea miniPaintPaintingArea) {
        super(miniPaintPaintingArea);
    }
    Polygon triangle =  new Polygon();
    int [] xStartingCoordinates = new int[] {0,0,0};
    int [] yStartingCoordinates = new int[] {0,0,0};
    int originX = 0;
    int originY = 0;
    int triangleIndex = 0;

    @Override
    public void mousePressed(MouseEvent e) {
        if ( e.getButton() == MouseEvent.BUTTON1 ) {
            int originX = e.getX();
            int originY = e.getY();
            xStartingCoordinates = new int[]{originX, originX, originX};
            yStartingCoordinates = new int[]{originY, originY, originY};
            triangle = new Polygon(xStartingCoordinates, yStartingCoordinates,3);
            triangleIndex = miniPaintPaintingArea.addShapeToShapesList(triangle);
            miniPaintPaintingArea.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(triangle != null) {
            if((e.getY() < originY) && (e.getX() > originX)) {// I quarter
                yStartingCoordinates[0] = yStartingCoordinates[0] + e.getY();
                //xStartingCoordinates[0] stay as origin
                //xStartingCoordinates[1] stay as origin
                //yStartingCoordinates[1] stay as origin
                xStartingCoordinates[2] = xStartingCoordinates[2] + e.getX();
                //yStartingCoordinates[2] stay as origin
                triangle = new Polygon(xStartingCoordinates,yStartingCoordinates,3);
            }
            else if((e.getY() < originY) && (e.getX() < originX)) { // II quarter
                //xStartingCoordinates[0] stays as origin
                yStartingCoordinates[0] = yStartingCoordinates[0] + e.getY();
                xStartingCoordinates[1] = xStartingCoordinates[1] - e.getX();
                //yStartingCoordinates[1] stays as origin
                //xStartingCoordinates[2] stays as origin
                //yStartingCoordinates[2] stays as origin
                triangle = new Polygon(xStartingCoordinates,yStartingCoordinates,3);
            }
            else if((e.getY() > originY) && (e.getX() < originX)) {// III quarter
                //xStartingCoordinates[0] stays as origin
                //yStartingCoordinates[0] stays as origin
                xStartingCoordinates[1] = xStartingCoordinates[1] - e.getX();
                //yStartingCoordinates[1] stay as origin
                //xStartingCoordinates[2] stay as origin
                yStartingCoordinates[2] = yStartingCoordinates[2] + e.getY();
                triangle = new Polygon(xStartingCoordinates,yStartingCoordinates,3);
            }
            else { //IV quarter
                //xStartingCoordinates[0] stays as origin
                //yStartingCoordinates[0] stays as origin
                xStartingCoordinates[1] = xStartingCoordinates[1] - e.getX();
                yStartingCoordinates[1] = yStartingCoordinates[1] - e.getY();
                xStartingCoordinates[2] = e.getX();
                yStartingCoordinates[2] = e.getY();
                triangle = new Polygon(xStartingCoordinates,yStartingCoordinates,3);
            }
            miniPaintPaintingArea.setShape(triangle, triangleIndex);
            miniPaintPaintingArea.repaint();
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if ( e.getButton() == MouseEvent.BUTTON1 ) {
            triangle = null;
        }

    }
}
