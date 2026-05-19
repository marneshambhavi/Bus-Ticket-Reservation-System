package buspass;

import java.awt.event.KeyListener;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.*;
import java.sql.*;

public class register extends JPanel {
    private AppUI parentFrame;
    // Components of the Form
    private Container c;
    private JLabel title;
    private JLabel name;
    private JLabel name2;
    private JTextField tname;
    private JTextField tname2;
    private JLabel age;
    private JTextField tage;
    private JLabel gender;
    private JTextField genderfield;
    private ButtonGroup gengp;
    private JLabel add;
    private JCheckBox term;
    private JLabel display;
    private JTextArea tadd;
    private JButton sub;
    private JButton reset;
    private JTextArea tout;
    private JLabel res;
    private JTextArea resadd;
    private JLabel username;
    private JLabel pwd;
    private JLabel confrimpwd;
    private JTextField userfield;
    private JTextField pwdfield;
    private JTextField confirmfield;
    private JLabel alt;
    private JLabel e_mail;
    private JTextField email;
    private JLabel text1;
    private JLabel text2;

    Connection conn=null;
    ResultSet rs=null;
    PreparedStatement ps=null;

    public register(AppUI parentFrame) {
        this.parentFrame = parentFrame;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        JPanel registerPanel = createRegister();
        add(registerPanel, BorderLayout.CENTER);
    }

    // constructor, to initialize the components
    // with default values.
    private JPanel createRegister() {

        conn = jdbc.getConnection();

        JPanel registerPanel = new JPanel(new GridBagLayout());
        registerPanel.setBackground(new Color(255,240,219));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(7 ,7,7,7);

        title = new JLabel("Registration Form");
        title.setFont(new Font("Cambria", Font.PLAIN, 30));
        title.setForeground(new Color(9, 87, 134));
        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        registerPanel.add(title, constraints);

        name = new JLabel("Name: ");
        name.setFont(new Font("Cambria", Font.PLAIN, 16));
        constraints.gridx = 0;
        constraints.gridy = 1;
        registerPanel.add(name, constraints);

        tname = new JTextField(15);
        tname.setFont(new Font("Cambria", Font.PLAIN, 15));
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridwidth = 10;
        registerPanel.add(tname, constraints);

        e_mail=new JLabel("E-mail");
        e_mail.setFont(new Font("Cambria", Font.PLAIN, 16));
        constraints.gridx = 0;
        constraints.gridy = 2;
        registerPanel.add(e_mail, constraints);

        email = new JTextField(15);
        email.setFont(new Font("Cambria", Font.PLAIN, 15));
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 10;
        registerPanel.add(email, constraints);

        text2=new JLabel();
        text2.setFont(new Font("Cambria", Font.PLAIN, 15));
        text2.setForeground(Color.red);
        constraints.gridx = 2;
        constraints.gridy = 2;
        registerPanel.add(text2,constraints);

        username = new JLabel("Username :");
        username.setFont(new Font("Cambria", Font.PLAIN, 16));
        constraints.gridx = 0;
        constraints.gridy = 3;
        registerPanel.add(username, constraints);

        userfield = new JTextField(15);
        userfield.setFont(new Font("Cambria", Font.PLAIN, 15));
        constraints.gridx = 2;
        constraints.gridy = 3;
        registerPanel.add(userfield, constraints);

        alt = new JLabel("username already taken");
        alt.setFont(new Font("Cambria", Font.PLAIN, 12));
        constraints.gridx = 2;
        constraints.gridy = 3;
        alt.setVisible(false);
        registerPanel.add(alt, constraints);

        text1=new JLabel();
        text1.setFont(new Font("Cambria", Font.PLAIN, 12));
        text1.setForeground(Color.RED);
        constraints.gridx = 3;
        constraints.gridy = 3;
        registerPanel.add(text1, constraints);

        pwd = new JLabel("Password:");
        pwd.setFont(new Font("Cambria", Font.PLAIN, 16));
        constraints.gridx = 0;
        constraints.gridy = 4;
        registerPanel.add(pwd, constraints);

        pwdfield = new JTextField(15);
        pwdfield.setFont(new Font("Cambria", Font.PLAIN, 15));
        constraints.gridx = 2;
        constraints.gridy = 4;
        registerPanel.add(pwdfield, constraints);

        age = new JLabel("DOB: ");
        age.setFont(new Font("Cambria", Font.PLAIN, 16));
        constraints.gridx = 0;
        constraints.gridy = 5;
        registerPanel.add(age, constraints);

        //age text box
        tage = new JTextField(8);
        tage.setFont(new Font("Cambria", Font.PLAIN, 15));
        constraints.gridx = 2;
        constraints.gridy = 5;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        try {
            Date parsedDate = dateFormat.parse(String.valueOf(tage));
            System.out.println(dateFormat.format(parsedDate));
        } catch (ParseException e) {
            System.out.println("invalid date");
        }

        registerPanel.add(tage, constraints);

        //gender label
        gender = new JLabel("Gender:");
        gender.setFont(new Font("Cambria", Font.PLAIN, 16));
        constraints.gridx = 0;
        constraints.gridy = 6;
        registerPanel.add(gender, constraints);

        genderfield = new JTextField(10);
        genderfield.setFont(new Font("Cambria", Font.PLAIN, 15));
        constraints.gridx = 2;
        constraints.gridy = 6;
        registerPanel.add(genderfield, constraints);


        //address label
        add = new JLabel("Address");
        add.setFont(new Font("Cambria", Font.PLAIN, 16));
        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.anchor = GridBagConstraints.WEST;
        registerPanel.add(add, constraints);

        //address textarea
        tadd = new JTextArea(3, 12);
        tadd.setFont(new Font("Cambria", Font.PLAIN, 12));
        tadd.setLineWrap(true);
        constraints.gridx = 2;
        constraints.gridy = 7;
        registerPanel.add(tadd, constraints);

        term = new JCheckBox("I guarantee that all details provided stand true.");
        term.setFont(new Font("Cambria", Font.PLAIN, 15));
        constraints.gridx = 0;
        constraints.gridy = 8;
        registerPanel.add(term, constraints);

        sub = createStyledButton("Submit");
        sub.setFont(new Font("Cambria", Font.PLAIN, 15));
        sub.setSize(100, 30);
        constraints.gridx = 0;
        constraints.gridy = 10;
        constraints.gridwidth = 2; // Span across two columns
        registerPanel.add(sub, constraints);

        reset = createStyledButton("Reset");
        reset.setFont(new Font("Cambria", Font.PLAIN, 15));
        reset.setSize(100, 30);
        constraints.gridx = 3;
        constraints.gridy = 10;
        constraints.gridwidth = 2;
        registerPanel.add(reset, constraints);



        sub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Name = tname.getText();
                String Uname = userfield.getText();
                String Pwd = pwdfield.getText();
                String Age = tage.getText();
                String Gender = genderfield.getText();
                String Address = tadd.getText();
                String Email = email.getText();
                if (Name.isEmpty() || Uname.isEmpty() || Pwd.isEmpty() || Age.isEmpty() || Uname.isEmpty() || Address.isEmpty() || Gender.isEmpty()) {
                    JOptionPane.showMessageDialog(register.this, "Please fill in all fields.");
                }
                else {
                    try {
                        Statement s = jdbc.getConnection().createStatement();
                        s.executeUpdate("INSERT INTO user(fullname,email,gender,dob,username,password,address) " +
                                "VAlUES('" + Name + "','" + Email + "','" + Gender + "','" + Age + "','" + Uname + "','" + Pwd + "','" + Address + "')");

                        JOptionPane.showMessageDialog(register.this, "your account created");
                        parentFrame.showLoginScreen();
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }

                    tname.setText("");
                    userfield.setText("");
                    pwdfield.setText("");
                    tage.setText("");
                    genderfield.setText("");
                    tadd.setText("");
                    email.setText("");
                }
            }
        });

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tname.setText("");
                tname2.setText("");
                tage.setText("");
                tadd.setText("");
                term.setSelected(false);
            }
        });

       userfield.addKeyListener(new KeyListener() {
           @Override
           public void keyTyped(KeyEvent e) {
             String PATTERN="^[a-zA-Z0-9]{0,30}$";
             Pattern patt=Pattern.compile(PATTERN);
             Matcher match=patt.matcher(userfield.getText());
             if(!match.matches()){
              text1.setText("use valid user name.....!!");
             }
             else {
                 text1.setText(null);
             }
           }

           @Override
           public void keyPressed(KeyEvent e) {}

           @Override
           public void keyReleased(KeyEvent e) {
               String uname=userfield.getText();
               try{
                   Statement s=jdbc.getConnection().createStatement();
                   ResultSet rs=s.executeQuery("SELECT * FROM user WHERE username='"+uname+"'");

                   if(rs.next())
                   {
                       String yes=rs.getString("username");
                       System.out.println(yes);
                       userfield.setForeground(Color.RED);
                       alt.setVisible(true);
                   }
                   else{
                       System.out.println("no users");
                       userfield.setForeground(Color.BLACK);
                       alt.setVisible(false);
                   }
               }
               catch(Exception ae )
               {
                   System.out.println(ae);
               }
           }
       });

       email.addKeyListener(new KeyListener() {
           @Override
           public void keyTyped(KeyEvent e) {

           }

           @Override
           public void keyPressed(KeyEvent e) {

           }

           @Override
           public void keyReleased(KeyEvent e) {
               String PATTERN="^[a-zA-Z0-9]{0,30}[@][a-zA-Z0-9]{0,10}[.][a-zA-Z]{0,5}$";
               Pattern patt=Pattern.compile(PATTERN);
               Matcher match=patt.matcher(email.getText());
               if(!match.matches()){
                   text2.setText("Invalid Email ID...!!");
               }
               else{
                   text2.setText(null);
               }
           }
       });


        setVisible(true);

        return registerPanel;
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