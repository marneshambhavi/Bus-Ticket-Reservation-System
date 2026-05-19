package buspass;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.*;
import javax.swing.*;


public class updatebus extends JFrame {
    private AppUI parentFrame;
    JFrame f;
    JPanel updatePanel;
    JLabel bus_id,busno, time,route_id,Input;
    JTextField bus_no, time_field,rno;
    JButton btn_update;
    JTextField tf_id;
    JButton btn_search;

    public updatebus() {
        updatePanel=new JPanel();
        //f = new JFrame();
        setTitle("Update Bus");
        updatePanel.setBackground(new Color(255,240,219));
        JLabel titleLabel = new JLabel(" Update bus ", JLabel.CENTER);
        updatePanel.add(titleLabel);

        bus_id = new JLabel("Bus id : ");
        Input=new JLabel("Input your Updates :");
        busno=new JLabel("Bus no. :");
        time = new JLabel("Time : ");
        route_id=new JLabel("Route no:");
        bus_no = new JTextField();
        time_field = new JTextField();
        rno=new JTextField();
        btn_update = new JButton("Update Bus");
        tf_id = new JTextField();
        btn_search = new JButton("Search Bus");

        bus_id.setBounds(20, 20, 100, 30);
        tf_id.setBounds(140, 20, 100, 30);
        btn_search.setBounds(80, 55, 90, 30);
        Input.setBounds(30,95,140,40);
        busno.setBounds(20,140,100,30);
        bus_no.setBounds(140, 150, 100, 30);
        time.setBounds(20, 170, 100, 30);
          time_field.setBounds(140, 180, 100, 30);
          route_id.setBounds(20,200,100,30);
          rno.setBounds(140,210,100,30);
        btn_update.setBounds(80, 250, 90, 30);


        updatePanel.add(bus_id);
        updatePanel.add(tf_id);
        updatePanel.add(btn_search);
        updatePanel.add(Input);
        updatePanel.add(time);
        updatePanel.add(busno);
        updatePanel.add(route_id);
        updatePanel.add(bus_no);
        updatePanel.add(time_field);
        updatePanel.add(rno);
        updatePanel.add(btn_update);
        updatePanel.add(btn_search);

        add(updatePanel);
        updatePanel.setLayout(null);
        setVisible(true);
        setSize(600,600);

        btn_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btn_search) {
                    int x = 0;
                    Connection con = jdbc.getConnection();
                    String busID = tf_id.getText();
                    try {
                        PreparedStatement ps = con.prepareStatement("select * from buss where bus_id = ?");
                        ps.setString(1, busID);
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                            bus_no.setText(rs.getString("bus_no"));
                            time_field.setText(rs.getString("timing"));
                            rno.setText(rs.getString("rid"));
                        }

                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }
        });


        btn_update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == btn_update) {

                    int x = 0;

                    Connection con = jdbc.getConnection();

                    String BusNo = bus_no.getText();
                    String Time = time_field.getText();
                    String Routeno= rno.getText();
                    String BusID=tf_id.getText();

                    try {

                        PreparedStatement ps = con.prepareStatement("UPDATE buss SET bus_no = ?, timing = ?, rid = ? WHERE bus_id = ?");
                        ps.setString(1,BusNo);
                        ps.setString(2, Time);
                        ps.setString(3, Routeno);
                        ps.setString(4,BusID);
                        int rowsAffected=ps.executeUpdate();


                        if (rowsAffected>0) {
                            JOptionPane.showMessageDialog(btn_update, "Bus Updated Successfully");
                        }

                    } catch (Exception o) {
                        JOptionPane.showMessageDialog(btn_update, "Database Error: " + o.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        o.printStackTrace();
                    }
                }else {
                    JOptionPane.showMessageDialog(btn_update, "Something went Wrong");
                }
            }
        });

    }
}