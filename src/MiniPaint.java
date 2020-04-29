import java.awt.EventQueue;

public class MiniPaint {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var miniPaintDisplay = new MiniPaintDisplay();
            miniPaintDisplay.setVisible(true);
        });
    }
}