package buspass;

import java.sql.*;
import javax.swing.*;
import static javax.swing.SwingUtilities.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.Date;


public class booking extends JPanel{
   public AppUI parentFrame;
   public JFrame frame;
   public JPanel paymentpanel,front;
   public JLabel title;
   public  ImageIcon image;
   public JButton confirmBookingButton;
    public static String busNo;
    public static String from;
    public static String to;
    public static String time;
    public static String price;
    public String date;
    private static JComboBox options;
    private static JComboBox tickets;

    Connection conn=null;
    ResultSet rs=null;
    PreparedStatement ps=null;


    public booking(AppUI parentFrame, String busNo, String from, String to, String price, String time) {
        this.parentFrame = parentFrame;
     /*   paymentpanel=new JPanel();
        paymentpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"SIGN UP FORM"));*/
        setLayout(new GridBagLayout());
        setBackground(new Color(255, 240, 219));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(15, 0, 0, 0);

        conn = jdbc.getConnection();

        this.busNo = busNo;
        this.from = from;
        this.to = to;
        this.price = price;
        this.time = time;
        this.date = new java.text.SimpleDateFormat("dd-MM-yyyy").format(new java.util.Date());

        JLabel titleLabel = new JLabel("  Book Your Ticket  ", JLabel.CENTER);
        constraints.gridx = 1;
        constraints.gridy = 0;
        titleLabel.setFont(new Font("Cambria", Font.PLAIN, 30));
        titleLabel.setForeground(new Color(9, 87, 134));
        add(titleLabel, constraints);

        JLabel busno = new JLabel("Bus no.:");
        busno.setFont(new Font("Cambria", Font.PLAIN, 18));
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(busno, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        add(new JLabel(busNo), constraints);

        JLabel source = new JLabel("From");
        source.setFont(new Font("Cambria", Font.PLAIN, 20));
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(source, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        add(new JLabel(from), constraints);

        JLabel destination = new JLabel("To");
        destination.setFont(new Font("Cambria", Font.PLAIN, 20));
        constraints.gridx = 0;
        constraints.gridy = 3;
        add(destination, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        add(new JLabel(to), constraints);

        JLabel amount = new JLabel("Time:");
        amount.setFont(new Font("Cambria", Font.PLAIN, 18));
        constraints.gridx = 0;
        constraints.gridy = 4;
       add(amount, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
       add(new JLabel(time), constraints);

        JLabel bustime = new JLabel("Amount:");
        bustime.setFont(new Font("Cambria", Font.PLAIN, 18));
        constraints.gridx = 0;
        constraints.gridy = 5;
        add(bustime, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        add(new JLabel(price), constraints);

        JLabel number = new JLabel("Number of Tickets:");
        number.setFont(new Font("Cambria", Font.PLAIN, 18));
        constraints.gridx = 0;
        constraints.gridy = 6;
        add(number, constraints);

        String no[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

        tickets = new JComboBox(no);
        constraints.gridx = 1;
        constraints.gridy = 6;
       add(tickets, constraints);


        JLabel ttype = new JLabel("Type");
        ttype.setFont(new Font("Cambria", Font.PLAIN, 18));
        constraints.gridx = 0;
        constraints.gridy = 7;
        add(ttype, constraints);

        String option[] = {"regular", "pass"};

        options = new JComboBox(option);
        constraints.gridx = 1;
        constraints.gridy = 7;
        add(options, constraints);

        JButton confirmButton = createStyledButton("Confirm");
        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        add(confirmButton, constraints);

        JButton nextButton = createStyledButton("Payment");
        constraints.gridx = 1;
        constraints.gridy = 8;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        add(nextButton,constraints);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String type=options.getSelectedItem().toString();
                String ticket= tickets.getSelectedItem().toString();
                showReceipt(ticket,type);
            }
        });

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if (parentFrame != null) {
                    parentFrame.showPaymentScreen();
                }
            }
        });
    }
    public void showReceipt(String ticket,String type) {
        String receiptMessage = "Booking Confirmed!\n"
                + "Bus No: " + busNo + "\n"
                + "From: " + from + "\n"
                + "To: " + to + "\n"
                + "Total_amt:"+ price+"\n"
                + "Time: " + time + " \nDate: " + date + "\n"
                + "Type: " + type + "\nNo.of pg: " + ticket;

        JOptionPane.showMessageDialog(this, receiptMessage, "Receipt", JOptionPane.INFORMATION_MESSAGE);
    }


    public static String getBusDetails() {
        return "Bus No: " + busNo + ", From: " + from + ", To: " + to + ", Price: " + price + ", Time: " + time;
    }

  public static String getType(){
        return options.getSelectedItem().toString();
    }

    public static String getnumber(){
        return tickets.getSelectedItem().toString();
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

