package buspass;

import javax.swing.*;
import java.awt.*;

public class history extends JFrame {
    private JTextArea bookingarea;
    private String id;
    public history(String id, String busDetails,String Ticket,String Type){
       this.id = id;
       setTitle("show bookings");
       setSize(400,300);
       setLocationRelativeTo(null);
       setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       setBackground(new Color(255,240,219));

       bookingarea=new JTextArea();
       bookingarea.setEditable(false);
       bookingarea.setText("Passenger ID: " + id + "\n" + busDetails + "\nPassengers: " + Ticket + "\nTicket: " + Type);
       JScrollPane scrollPane=new JScrollPane(bookingarea);
       add(scrollPane,BorderLayout.CENTER);
    }

}
