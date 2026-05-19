package buspass;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;

   public class admin extends JFrame{

    private JMenuBar menubar;
    private JPanel adminpanel;
    private JDesktopPane desktop;
    private JButton addBus,update,route,removebus;
    private JInternalFrame currentFrame;

    public admin(AdminUI parentFrame) {
        JMenu  customer, logout, exit;
        JMenuItem viewCustomers, exit_admin, logout_admin;
        adminpanel=new JPanel();
        adminpanel.setBackground(new Color(255, 240, 219));
        desktop=new JDesktopPane();
        //f = new JFrame("Bus Reservation System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(750,500);
        this.setLocationRelativeTo(null);
        this.add(adminpanel);
        adminpanel.setLayout(null);

        //this.setContentPane(desktop);

        menubar=new JMenuBar();
        customer = new JMenu("Customers");
        logout = new JMenu("Logout");
        exit = new JMenu("Exit");

        menubar.add(customer);
        menubar.add(logout);
        menubar.add(exit);

        exit_admin = new JMenuItem("Exit");
        logout_admin = new JMenuItem("Logout");
        viewCustomers = new JMenuItem("View All Customers");

        customer.add(viewCustomers);
        exit.add(exit_admin);
        logout.add(logout_admin);

        addBus= createStyledButton("Add Bus");
        addBus.setBounds(150,30,200,40);
        addBus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addbus ad=new addbus();
            }
        });
        adminpanel.add(addBus);

        route= createStyledButton("Add Route");
        route.setBounds(150,90,200,40);
        route.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
               addroute ar=new addroute();
            }
        });
        adminpanel.add(route);

       removebus=createStyledButton("Remove Buses");
       removebus.setBounds(150,210,200,40);
        removebus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove rm=new remove();
            }
        });
        adminpanel.add(removebus);

        update= createStyledButton("Update Bus");
        update.setBounds(150,150,200,40);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatebus up=new updatebus();
            }
        });
        adminpanel.add(update);

      logout_admin.addActionListener(new ActionListener() {
         @Override
           public void actionPerformed(ActionEvent e) {
             parentFrame.showadminMain();
           }
       });

        exit_admin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.setJMenuBar(menubar);
        this.setVisible(true);
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


