package buspass;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.*;

public class addbus extends JFrame {
    JPanel addPanel;
    JLabel busno, timing1,route_id,titleLabel;
    JTextField bus_no, tf_timing,rid;
    JButton btn_add;


    public addbus() {

        //f = new JFrame();

        addPanel = new JPanel();
        setTitle("Add Bus");
        titleLabel = new JLabel(" Add new bus ", JLabel.CENTER);
        addPanel.setBackground(new Color(255,240,219));
        addPanel.add(titleLabel);

        busno = new JLabel("Bus_no: ");
        timing1 = new JLabel("Timing: ");
        route_id=new JLabel("Route no:");

        bus_no = new JTextField();
        tf_timing= new JTextField();
        rid=new JTextField();

        btn_add = new JButton("Add Bus");
        busno.setBounds(20, 20, 100, 30);
        timing1.setBounds(20, 60, 100, 30);
        route_id.setBounds(20,100,100,30);
        bus_no.setBounds(140, 20, 100, 30);
        tf_timing.setBounds(140, 60, 100, 30);
        rid.setBounds(140,100,100,30);
        btn_add.setBounds(90, 140, 120, 30);

        addPanel.add(busno);
        addPanel.add(timing1);
        addPanel.add(route_id) ;
        addPanel.add(btn_add);
        addPanel.add(bus_no);
        addPanel.add(tf_timing);
        addPanel.add(rid);


        add(addPanel);
        addPanel.setLayout(null);
        this.setVisible(true);
        this.setSize(600,600);

        btn_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btn_add) {

                    int x = 0;

                    Connection con = jdbc.getConnection();
                    String Bus_no =bus_no.getText();
                    String Time = tf_timing.getText();
                    String Route=rid.getText();


                    try {

                        PreparedStatement ps = con.prepareStatement("insert into buss(bus_no,timing,rid) values (?,?,?)");
                        ps.setString(1,Bus_no);
                        ps.setString(2, Time);
                        ps.setString(3,Route);
                        ps.executeUpdate();
                        x++;

                        if (x > 0) {
                            JOptionPane.showMessageDialog(btn_add, "Bus  Added Successfully");
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