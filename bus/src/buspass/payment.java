package buspass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class payment extends JPanel {
    private AppUI parentFrame;

    public payment(AppUI parentFrame) {
        this.parentFrame = parentFrame;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        JPanel paymentPanel = createPayment();
        add(paymentPanel);
    }

    private JPanel createPayment() {

        JPanel paymentPanel = new JPanel(new GridBagLayout());
        paymentPanel.setBackground(new Color(255,240,219));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel title= new JLabel("Scan this QR for payment");
        title.setFont(new Font("Cambria", Font.PLAIN, 20));;
        constraints.gridx=0;
        constraints.gridy=0;
        paymentPanel.add(title,constraints);


        ImageIcon image = new ImageIcon("bus/src/Bus Ticket QR.jpg");
        image.setImage(image.getImage().getScaledInstance(300, 280, Image.SCALE_DEFAULT));
        JLabel picture = new JLabel(image);
        constraints.gridx=0;
        constraints.gridy=2;
        paymentPanel.add(picture,constraints);

        constraints.gridx = 0;
        constraints.gridy = 9;
        constraints.gridwidth = 2; // Span across two columns
        JButton confirmBookingButton = new JButton("Confirm Booking");
        confirmBookingButton.setBackground(new Color(255,182,64));
        confirmBookingButton.setForeground(Color.WHITE);
        confirmBookingButton.setFocusPainted(false);
        confirmBookingButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        confirmBookingButton.setOpaque(true);
        confirmBookingButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        paymentPanel.add(confirmBookingButton, constraints);

        confirmBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    java.sql.Connection con = jdbc.getConnection();
                    java.sql.PreparedStatement ps1 = con.prepareStatement("SELECT bus_id FROM buss WHERE bus_no = ?");
                    ps1.setString(1, booking.busNo);
                    java.sql.ResultSet rs = ps1.executeQuery();
                    
                    if(rs.next()) {
                        String busId = rs.getString("bus_id");
                        String userId = parentFrame.id; 
                        
                        java.sql.PreparedStatement ps2 = con.prepareStatement(
                            "INSERT INTO ticket(total_pasngr, tick_type, travel_date, user_id, bus_id) VALUES (?, ?, CURDATE(), ?, ?)",
                            java.sql.Statement.RETURN_GENERATED_KEYS
                        );
                        ps2.setString(1, booking.getnumber());
                        ps2.setString(2, booking.getType());
                        ps2.setString(3, userId);
                        ps2.setString(4, busId);
                        ps2.executeUpdate();
                        
                        java.sql.ResultSet rsKeys = ps2.getGeneratedKeys();
                        if (rsKeys.next()) {
                            long tickId = rsKeys.getLong(1);
                            
                            java.sql.PreparedStatement ps3 = con.prepareStatement(
                                "INSERT INTO booking(bus_id, user_id, tick_id, date_time, status) VALUES (?, ?, ?, CURDATE(), 'Confirmed')"
                            );
                            ps3.setString(1, busId);
                            ps3.setString(2, userId);
                            ps3.setLong(3, tickId);
                            ps3.executeUpdate();
                        }
                    }
                    JOptionPane.showMessageDialog(payment.this, "Booking Successful!");
               } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(payment.this, "Database Error: " + ex.getMessage());
               }
               parentFrame.showHomeScreen();
            }
        });
        return paymentPanel;
    }

}