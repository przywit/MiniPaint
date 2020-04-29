import java.awt.event.MouseEvent;


public abstract class Tool {
    public abstract void mousePressed(MouseEvent e);
    public abstract void mouseDragged(MouseEvent e);
    public abstract void mouseReleased(MouseEvent e);
}
