package buspass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {
    private AppUI parentFrame;
    private String name;


    public MainPanel(AppUI parentFrame) {
        this.parentFrame = parentFrame;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        JPanel buttonPanel = createButtonPanel();
        JLabel title=new JLabel("   Bus Reservation System   ",JLabel.CENTER);
        title.setFont(new Font("Cambria", Font.PLAIN, 30));
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        title.setBackground(new Color(9, 87, 134));
        add(title,BorderLayout.NORTH);
        add(buttonPanel);

    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(new Color(255,240,219));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets=new Insets(8,8,8,8);

        ImageIcon image = new ImageIcon("C:\\Users\\User\\Downloads\\TYBUSS_FINAL1\\bus\\src\\busimage.jpg");
        image.setImage(image.getImage().getScaledInstance(380,250,Image.SCALE_DEFAULT));
        JLabel picture = new JLabel(image);
        constraints.gridx=0;
        constraints.gridy=1;
        constraints.anchor=GridBagConstraints.CENTER;
        buttonPanel.add(picture,constraints);

        JButton loginButton = createStyledButton("Login");
        constraints.gridx=0;
        constraints.gridy=2;
        buttonPanel.add(loginButton,constraints);

        JButton registerButton = createStyledButton("Register");
        constraints.gridx=0;
        constraints.gridy=3;
        buttonPanel.add(registerButton, constraints);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.showLoginScreen();
            }
        });

        registerButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               parentFrame.showRegisterScreen();
           }
        });

        return buttonPanel;
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
