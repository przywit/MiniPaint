import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;


public class ObjectTransformation extends Tool {
    int indexOfObjectToTransform = -1;
    int originX = 0;
    int originY = 0;
    final static float mouseWheelSensitivity = 0.1f;

    public ObjectTransformation(MiniPaintPaintingArea minipaintPaintingArea) {
        super(minipaintPaintingArea);
        minipaintPaintingArea.setObjectToTransform(this);
    }

    /**
     * if clicked on object method saves index of that object
     * @param e ...
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            originX = e.getX();
            originY = e.getY();
            indexOfObjectToTransform = miniPaintPaintingArea.getIndexOfObjectByMousePosition(originX, originY);
        }
    }

    /**
     * method responsible for translating an object , it calculates delta x and delta y made by mouse
     * @param e ...
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (indexOfObjectToTransform != -1) {
            miniPaintPaintingArea.translateObject(e.getX() - originX, e.getY() - originY, indexOfObjectToTransform);
            originX = e.getX();
            originY = e.getY();
            miniPaintPaintingArea.repaint();
        }
    }

    /**
     *
     * @param e ...
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * method responsible for receiving information about scaling an object if one had been selected.
     * @param e ...
     */
    public void processMouseWheelRotation(MouseWheelEvent e) {
        if (indexOfObjectToTransform != -1) {
            miniPaintPaintingArea.scaleObject((float) e.getPreciseWheelRotation() * mouseWheelSensitivity, indexOfObjectToTransform);
            miniPaintPaintingArea.repaint();
        }
    }
}
