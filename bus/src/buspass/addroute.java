package buspass;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.*;

public class addroute extends JFrame {
    JPanel routePanel;
    JLabel rno, source, destination, price,titleLabel;
    JTextField route_no,tf_source, tf_destination, total_price;
    JButton btn_add;


    public addroute() {

        //f = new JFrame();
        routePanel = new JPanel();
        setTitle("Add Route");
        titleLabel = new JLabel(" Add new route ", JLabel.CENTER);
        routePanel.setBackground(new Color(255,240,219));
        routePanel.add(titleLabel);

        rno=new JLabel("Route no");
        source = new JLabel("Source : ");
        destination = new JLabel("Destination : ");
        price = new JLabel(" Price : ");

        route_no=new JTextField();
        tf_source = new JTextField();
        tf_destination = new JTextField();
        total_price = new JTextField();

        btn_add = new JButton("Add Route");

        rno.setBounds(20, 20, 100, 30);
        source.setBounds(20, 60, 100, 30);
        destination.setBounds(20, 100, 100, 30);
        price.setBounds(20,140,100,30);

        route_no.setBounds(140, 20, 100, 30);
        tf_source.setBounds(140, 60, 100, 30);
        tf_destination.setBounds(140, 100, 100, 30);
        total_price.setBounds(140,140,100,30);
        btn_add.setBounds(80, 220, 120, 30);

        routePanel.add(rno);
        routePanel.add(source);
        routePanel.add(destination);
        routePanel.add(price);
        routePanel.add(route_no);
        routePanel.add(tf_source);
        routePanel.add(tf_destination);
        routePanel.add(total_price);
        routePanel.add(btn_add);
        add(routePanel);
        routePanel.setLayout(null);
        setVisible(true);
        setSize(600, 600);

        btn_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btn_add) {

                    int x = 0;

                    Connection con = jdbc.getConnection();
                    String RouteNo=route_no.getText();
                    String Source = tf_source.getText();
                    String Destination = tf_destination.getText();
                    String TotalPrice = total_price.getText();

                    try {

                        PreparedStatement ps = con.prepareStatement("insert into route (rno,source,destination,price) values (?,?,?,?)");
                        ps.setString(1,RouteNo);
                        ps.setString(2, Source);
                        ps.setString(3, Destination);
                        ps.setString(4, TotalPrice);
                        ps.executeUpdate();
                        x++;
                        if (x > 0) {
                            JOptionPane.showMessageDialog(btn_add, "Route  Added Successfully");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(btn_add, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println(ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(btn_add, "Something went Wrong");
                }


            }
        });

    }
}