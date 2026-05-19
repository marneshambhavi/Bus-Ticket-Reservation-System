package buspass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class login extends JPanel {
    private AppUI parentFrame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    public String busNo;
    public String from;
    public String to;
    public String time;
    public String price;
    Connection conn=null;
    ResultSet rs=null;
    PreparedStatement ps=null;

    public login(AppUI parentFrame)
    {
        this.parentFrame=parentFrame;
        initializeUI();
    }
    private void initializeUI(){
        setLayout(new BorderLayout());
        JPanel loginPanel=createLogin();
        add(loginPanel,BorderLayout.CENTER);
    }

    private JPanel createLogin(){
        conn=jdbc.getConnection();
        JPanel loginPanel=new JPanel(new GridBagLayout());
        loginPanel.setBackground(new Color(255,240,219));
        GridBagConstraints constraints=new GridBagConstraints();
        constraints.insets=new Insets(5,5,5,5);

        JLabel title=new JLabel("Bus Ticket Reservation System");
        title.setFont(new Font("Cambria", Font.PLAIN, 30));
        title.setForeground(new Color(9, 87, 134));
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        loginPanel.add(title, constraints);

        ImageIcon image = new ImageIcon("C:\\Users\\User\\Downloads\\TYBUSS_FINAL1\\bus\\src\\busimage.jpg");
        image.setImage(image.getImage().getScaledInstance(380,250,Image.SCALE_DEFAULT));
        JLabel picture = new JLabel(image);
        constraints.gridy=1;
        constraints.gridx=1;
        constraints.anchor=GridBagConstraints.CENTER;
        loginPanel.add(picture,constraints);

        JLabel username=new JLabel("USERNAME :");
        constraints.gridx=0;
        constraints.gridy=2;
        loginPanel.add(username,constraints);

        usernameField=new JTextField(15);
        constraints.gridx=1;
        constraints.gridy=2;
        loginPanel.add(usernameField,constraints);

        JLabel passwordLabel=new JLabel("PASSWORD:");
        constraints.gridx=0;
        constraints.gridy=3;
        loginPanel.add(passwordLabel,constraints);

        passwordField=new JPasswordField(15);
        constraints.gridx=1;
        constraints.gridy=3;
        loginPanel.add(passwordField,constraints);

        JButton loginButton=createStyledButton("LOGIN");
        constraints.gridx=0;
        constraints.gridy=4;
        constraints.gridwidth=1;
        constraints.anchor=GridBagConstraints.CENTER;
        loginPanel.add(loginButton,constraints);

        JButton cancelButton=createStyledButton("CANCEL");
        constraints.gridx=1;
        constraints.gridy=4;
        constraints.gridwidth=1;
        constraints.anchor=GridBagConstraints.CENTER;
        loginPanel.add(cancelButton,constraints);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Username= usernameField.getText();
                String Password = new String(passwordField.getPassword());
               /* if (username.equals("") && password.equals("")) {
                    parentFrame.showHomeScreen();
                }
                else{
                    JOptionPane.showMessageDialog(login.this,"Invalid username or password.Try again",
                            "login failed",JOptionPane.ERROR_MESSAGE);
                }
               */ try{
                    String sql="SELECT * FROM user WHERE username=? AND password=?";
                    ps=conn.prepareCall(sql);
                    ps.setString(1,Username);
                    ps.setString(2,Password);

                    rs=ps.executeQuery();

                    if(rs.next())
                    {
                        parentFrame.id = rs.getString("user_id");
                        JOptionPane.showMessageDialog(login.this,"you are logged in...");
                        parentFrame.showHomeScreen();

                    }
                    else{
                        JOptionPane.showMessageDialog(login.this,"Login failed...");

                    }

                }
                catch(Exception ae){
                   ae.printStackTrace();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.showMainScreen();
            }
        });

        return loginPanel;
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