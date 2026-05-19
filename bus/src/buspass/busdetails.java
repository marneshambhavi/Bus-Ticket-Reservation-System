package buspass;

import java.awt.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.sql.*;


public class busdetails extends JFrame {

    public AppUI parentFrame;
    Connection con;
    PreparedStatement pst=null;
    ResultSet rs;
    private JTable table;
    private JLabel title;
    public busdetails(AppUI parentFrame) {
        DefaultTableModel model=new DefaultTableModel();
        model.addColumn("Bus_no");
        model.addColumn("Source");
        model.addColumn("Destination");
        model.addColumn("Time");
        model.addColumn("Price");

        rs = new jdbc().getBusData();
        if (rs == null) {
            JOptionPane.showMessageDialog(this, "No data found or database connection failed.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            while(rs.next())
            {
                model.addRow(new Object[]{
                        rs.getString("bus_no"),rs.getString("source"),
                        rs.getString("destination"),rs.getString("timing"),rs.getString("price")
                });
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        title=new JLabel("Select Row and Book Ticket");
        this.add(title,"North");
        table=new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        this.setTitle("Bus Details : Select desired Row ");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(2);
        this.setSize(700, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(scrollPane,"Center");
        JButton bookTicketButton = new JButton("Book Ticket");
        bookTicketButton.setPreferredSize(new Dimension(40,20));
        bookTicketButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                table.getSelectionModel().addListSelectionListener((ListSelectionEvent ae) -> {
                    if (!ae.getValueIsAdjusting()) {
                        int selectedRow = busdetails.this.table.getSelectedRow();
                        if (selectedRow >= 0) {
                            String busNo = busdetails.this.table.getValueAt(selectedRow, 0).toString();
                            String from = busdetails.this.table.getValueAt(selectedRow, 1).toString();
                            String to = busdetails.this.table.getValueAt(selectedRow, 2).toString();
                            String time = busdetails.this.table.getValueAt(selectedRow, 3).toString();
                            String price = busdetails.this.table.getValueAt(selectedRow, 4).toString();
                            parentFrame.ShowBookingScreen(busNo,from,to,price,time);
                        } else {
                            JOptionPane.showMessageDialog(busdetails.this, "Please select a bus to book a ticket.", "No Selection", 2);
                        }

                    }
                });
            }
        });
        this.add(bookTicketButton,"South");
       // bookTicketButton.setBounds(250,710,100,50);
        this.setVisible(true);
    }
}