import java.awt.event.*;

/**
 * abstract class needed to create tools.
 */
public abstract class Tool {

    protected MiniPaintPaintingArea miniPaintPaintingArea;

    /**
     * we need to have access to methods in MiniPaintPaintingArea class
     */
    public Tool (MiniPaintPaintingArea minipaintPaintingArea) {
        this.miniPaintPaintingArea = minipaintPaintingArea;
    }

    public abstract void mousePressed(MouseEvent e);
    public abstract void mouseDragged(MouseEvent e);
    public abstract void mouseReleased(MouseEvent e);
    public void processMouseWheelRotation(MouseWheelEvent e) {

    }

}
