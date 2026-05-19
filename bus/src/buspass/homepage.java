package buspass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class homepage extends JPanel {
    private AppUI parentFrame;

    public homepage(AppUI parentFrame) {
        this.parentFrame = parentFrame;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        JPanel homePanel = createhomepage();
        add(homePanel, BorderLayout.CENTER);
    }

    private JPanel createhomepage() {
        JPanel homePanel = new JPanel(new GridBagLayout());
        homePanel.setBackground(new Color(255, 240, 219));
        GridBagConstraints constraints=new GridBagConstraints();
        constraints.insets=new Insets(8,8,8,8);

        JLabel title = new JLabel("Bus Ticket Reservation System");
        title.setFont(new Font("Cambria", Font.PLAIN, 30));
        title.setForeground(new Color(9, 87, 134));
        constraints.gridx=3;
        constraints.gridy=0;
        homePanel.add(title,constraints);

        JLabel title2 = new JLabel("Welcome");
        title2.setFont(new Font("Cambria", Font.PLAIN, 22));
        title2.setForeground(new Color(9, 87, 134));
        constraints.gridx=3;
        constraints.gridy=1;
        homePanel.add(title2,constraints);


        ImageIcon image = new ImageIcon("bus/src/busimage.jpg");
        image.setImage(image.getImage().getScaledInstance(380,250,Image.SCALE_DEFAULT));
        JLabel picture = new JLabel(image);
        constraints.gridx=3;
        constraints.gridy=2;
        homePanel.add(picture,constraints);

        JButton book =createStyledButton("Book Ticket");
        constraints.gridx=3;
        constraints.gridy=3;
        homePanel.add(book,constraints);

        book.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.showDetailsScreen();
            }
        });


        return homePanel;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(255, 182, 64));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorderPainted(false);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {

                button.setBackground(new Color(255, 207, 128));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(255, 182, 64));
            }
        });
        return button;
    }
}
