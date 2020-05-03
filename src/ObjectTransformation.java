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

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            originX = e.getX();
            originY = e.getY();
            indexOfObjectToTransform = miniPaintPaintingArea.getIndexOfObjectByMousePosition(originX, originY);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (indexOfObjectToTransform != -1) {
            miniPaintPaintingArea.transformObject(e.getX() - originX, e.getY() - originY, indexOfObjectToTransform);
            originX = e.getX();
            originY = e.getY();
            miniPaintPaintingArea.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    public void processMouseWheelRotation(MouseWheelEvent e) {
        if (indexOfObjectToTransform != -1) {
            double deltaScale = miniPaintPaintingArea.scalesList.get(indexOfObjectToTransform) + (e.getPreciseWheelRotation() * mouseWheelSensitivity);
            miniPaintPaintingArea.changeScaleInScalesList(deltaScale, indexOfObjectToTransform);
            miniPaintPaintingArea.transformObject(0, 0, indexOfObjectToTransform);
        }
    }
}
