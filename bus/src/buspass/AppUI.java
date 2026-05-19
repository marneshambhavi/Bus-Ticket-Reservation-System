package buspass;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppUI {
    private JFrame frame;
    private JPanel currentPanel;
    private JMenuBar menuBar;
    public String name;
    public String id;
    public String busDetails;
    private JFrame currentFrame;
    public static String busNo;
    public static String from;
    public static String to;
    public static String time;
    public static String price;

    public AppUI() {

        frame = new JFrame("Bus Reservation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750,500);
        frame.setLocationRelativeTo(null);

        menuBar=new JMenuBar();
        JMenu homemenu=new JMenu("Home");
        JMenu ticketmenu=new JMenu("Ticket");
       /* JMenu busmenu=new JMenu("Bus Details");*/
        JMenu pay=new JMenu("Payment");


        menuBar.add(homemenu);
        menuBar.add(ticketmenu);
       /* menuBar.add(busmenu);*/
        menuBar.add(pay);

        JMenuItem home=new JMenuItem("Home");
        JMenuItem helpitem=new JMenuItem("Help");
        JMenuItem exititem=new JMenuItem("Logout");

        homemenu.add(home);
        homemenu.add(helpitem);

        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               showHomeScreen();
            }
        });


        helpitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame,"****TICKET BOOKING ****\n" +
                        "1.Login\n"+
                        "2.Book Ticket\n"+
                        "3.Bus Details:select bus\n"+
                        "4.Confirm Booking\n"+
                        "5.Payment");
            }
        });

        JMenuItem Payment=new JMenuItem("ticket payment");
        Payment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              showPaymentScreen();
            }
        });
        pay.add(Payment);

       /* JMenuItem details=new JMenuItem("Bus details");
        details.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDetailsScreen();
            }
        });
        busmenu.add(details);*/

        exititem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMainScreen();
            }
        });
        homemenu.add(exititem);

       JMenuItem thistory=new JMenuItem("History");
        thistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String busDetails=booking.getBusDetails();
                String Ticket=booking.getnumber();
                String Type=booking.getType();
                history showhistory=new history(AppUI.this.id, busDetails,Ticket,Type);
                showhistory.setVisible(true);
            }
        });
        ticketmenu.add(thistory);

        frame.setJMenuBar(menuBar);
        showMainScreen();
    }

    public void showMainScreen(){
        if (currentPanel != null) {
            frame.getContentPane().remove(currentPanel);
        }
        currentPanel = new MainPanel(this);
        frame.getContentPane().add(currentPanel);
        frame.revalidate();
        frame.repaint();
    }

    public void showLoginScreen(){
        if (currentPanel!=null){
            frame.getContentPane().remove(currentPanel);
        }
        currentPanel=new login(this);
        frame.getContentPane().add(currentPanel);
        frame.revalidate();
        frame.repaint();

    }

    public void showRegisterScreen(){
        if (currentPanel!=null){
            frame.getContentPane().remove(currentPanel);
        }
        currentPanel=new register(this);
        frame.getContentPane().add(currentPanel);
        frame.revalidate();
        frame.repaint();

    }

    public void showHomeScreen(){
        if (currentPanel!=null){
            frame.getContentPane().remove(currentPanel);
        }
        currentPanel=new homepage(this);
        frame.getContentPane().add(currentPanel);
        frame.revalidate();
        frame.repaint();
    }

    public void showPaymentScreen(){
      if (currentPanel!=null){
            frame.getContentPane().remove(currentPanel);
        }
        currentPanel=new payment(this);
        frame.getContentPane().add(currentPanel);
        frame.revalidate();
        frame.repaint();
    }

    public void ShowBookingScreen(String busNo, String from, String to, String price,
                                  String time){
        if (currentPanel !=null){
            frame.getContentPane().remove(currentPanel);
        }
        currentPanel=new booking(this,busNo,from,to,price,time);
        frame.getContentPane().add(currentPanel);
        frame.revalidate();
        frame.repaint();
    }

    public void showDetailsScreen(){
        if (currentFrame!=null){
            frame.getContentPane().remove(currentFrame);
        }
        currentFrame=new busdetails(this);
        frame.getContentPane().add(currentFrame);
        frame.revalidate();
        frame.repaint();
    }

    public void start()
    {
        frame.setVisible(true);
    }
}

