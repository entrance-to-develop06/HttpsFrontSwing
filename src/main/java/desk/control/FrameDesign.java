package main.java.desk.control;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FrameDesign {
    protected JPanel fPanel = new JPanel();

    protected JLabel labelsSetting(String name, String txt, int x, int y, int w, int h) {
        var label = new JLabel();
        label.setName(name);
        label.setText(txt);
        label.setBounds(x, y, w, h);
        fPanel.add(label);

        return label;
    }

    protected JComponent componentsSetting(JComponent ctl, String name, int x, int y, int w, int h) {
        ctl.setName(name);
        ctl.setBounds(x, y, w, h);
        fPanel.add(ctl);

        return ctl;
    }
}