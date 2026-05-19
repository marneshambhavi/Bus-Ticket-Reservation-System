package buspass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class adminlogin extends JPanel {
    private AdminUI parentFrame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public adminlogin(AdminUI parentFrame) {

        this.parentFrame=parentFrame;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        JPanel loginPanel = createLogin();
        add(loginPanel, BorderLayout.CENTER);
    }

    private JPanel createLogin() {
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(new Color(255,240,219));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(8, 8, 8, 8);

        ImageIcon image = new ImageIcon("C:\\Users\\User\\Downloads\\TYBUSS_FINAL1\\bus\\src\\busimage.jpg");
        image.setImage(image.getImage().getScaledInstance(380, 250, Image.SCALE_DEFAULT));
        JLabel picture = new JLabel(image);
        constraints.gridy = 0;
        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        loginPanel.add(picture, constraints);

        JLabel username = new JLabel("USERNAME :");
        constraints.gridx = 0;
        constraints.gridy = 1;
        loginPanel.add(username, constraints);

        usernameField = new JTextField(15);
        constraints.gridx = 1;
        loginPanel.add(usernameField, constraints);

        JLabel passwordLabel = new JLabel("PASSWORD:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        loginPanel.add(passwordLabel, constraints);

        passwordField = new JPasswordField(15);
        constraints.gridx = 1;
        constraints.gridy = 2;
        loginPanel.add(passwordField, constraints);

        JButton loginButton=createStyledButton("LOGIN");
        constraints.gridx=0;
        constraints.gridy=3;
        constraints.gridwidth=1;
        constraints.anchor=GridBagConstraints.CENTER;
        loginPanel.add(loginButton,constraints);

        JButton cancelButton=createStyledButton("CANCEL");
        constraints.gridx=1;
        constraints.gridy=3;
        constraints.gridwidth=1;
        constraints.anchor=GridBagConstraints.CENTER;
        loginPanel.add(cancelButton,constraints);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.equals("isha")&& password.equals("isha@16")) {
                    parentFrame.AdminScreen();
                } else if (username.equals("shambhavi") && password.equals("sham@31")) {
                    parentFrame.AdminScreen();
                } else{
                    JOptionPane.showMessageDialog(adminlogin.this,"Invalid username or password.Try again",
                            "login failed",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.showadminMain();
            }
        });

        return loginPanel;
    }


    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(255,128,64));
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
                button.setBackground(new Color(255,182,64));
            }
        });

        return button;
    }
}
