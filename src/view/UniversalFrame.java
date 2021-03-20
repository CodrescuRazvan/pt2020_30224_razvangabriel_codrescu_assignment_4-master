package view;

import javax.swing.*;
import java.awt.*;

public class UniversalFrame extends JFrame {
    protected static JPanel contentPanel;
    protected Color background = new Color(39, 110, 89);
    protected Color back = new Color(115, 104, 108);
    protected Font writing = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20);

    public UniversalFrame(String title) {
        FrameStack.getInstance().push(this);
        setTitle(title);
        setSize(900, 800);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(back);
        add(contentPanel, BorderLayout.CENTER);
        //setVisible(true);

    }
}
