import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ButtonZoomEffect extends MouseAdapter {
    private JButton button;
    private Font originalFont;
    private Font zoomedFont;

    public ButtonZoomEffect(JButton button) {
        this.button = button;
        this.originalFont = button.getFont();
        this.zoomedFont = originalFont.deriveFont(originalFont.getSize() + 2.0f);
    }

    public void mouseEntered(MouseEvent e) {
        button.setFont(zoomedFont);
    }

    public void mouseExited(MouseEvent e) {
        button.setFont(originalFont);
    }
}
