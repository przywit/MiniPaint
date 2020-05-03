import java.awt.event.*;

public abstract class Tool {

    protected MiniPaintPaintingArea miniPaintPaintingArea;

    public Tool (MiniPaintPaintingArea minipaintPaintingArea) {
        this.miniPaintPaintingArea = minipaintPaintingArea;
    }

    public abstract void mousePressed(MouseEvent e);
    public abstract void mouseDragged(MouseEvent e);
    public abstract void mouseReleased(MouseEvent e);
    public void processMouseWheelRotation(MouseWheelEvent e) {

    }

}
