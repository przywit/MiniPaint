import java.awt.*;
import java.awt.event.MouseEvent;


public class DrawTriangle extends Tool {

    public DrawTriangle (MiniPaintPaintingArea miniPaintPaintingArea) {
        super(miniPaintPaintingArea);
    }
    Polygon triangle =  new Polygon();
    int [] xCoordinates = new int[] {0,0,0};
    int [] yCoordinates = new int[] {0,0,0};
    int originX = 0;
    int originY = 0;
    int triangleIndex = 0;

    @Override
    public void mousePressed(MouseEvent e) {
        if ( e.getButton() == MouseEvent.BUTTON1 ) {
            int originX = e.getX();
            int originY = e.getY();
            xCoordinates = new int[]{originX, originX, originX};
            yCoordinates = new int[]{originY, originY, originY};
            triangle = new Polygon(xCoordinates, yCoordinates,3);
            triangleIndex = miniPaintPaintingArea.addShapeToShapesList(triangle);
            miniPaintPaintingArea.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(triangle != null) {
                xCoordinates[0] = e.getX();
                yCoordinates[0] = e.getY();
                //xStartingCoordinates[2] stays as origin
                yCoordinates[2] = yCoordinates[0];
                xCoordinates[1] = (xCoordinates[2] + xCoordinates[0] ) / 2;
                //yStartingCoordinates[1] stays as origin
                triangle = new Polygon(xCoordinates, yCoordinates,3);
            }
            miniPaintPaintingArea.setShape(triangle, triangleIndex);
            miniPaintPaintingArea.repaint();
        }


    @Override
    public void mouseReleased(MouseEvent e) {
        if ( e.getButton() == MouseEvent.BUTTON1 ) {
            triangle = null;
        }

    }
}
