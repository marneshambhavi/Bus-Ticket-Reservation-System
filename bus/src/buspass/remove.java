package buspass;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.*;

public class remove extends JFrame {

    JPanel p;
    JLabel id;
    JTextField tf_id;
    JButton btn_delete;

    public remove() {
        //f =  new JFrame();
        setTitle("Remove Bus");
        p = new JPanel();
        p.setBackground(new Color(255,240,219));
        id = new JLabel("Bus No : ");
        tf_id = new JTextField();
        btn_delete = new JButton("Delete Bus");
        id.setBounds(20, 20, 100, 30);
        tf_id.setBounds(140, 20, 100, 30);
        btn_delete.setBounds(80, 60, 100, 40);
        p.add(id);
        p.add(tf_id);
        p.add(btn_delete);
        add(p);
        p.setLayout(null);
        setVisible(true);
        setSize(600, 600);

        btn_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btn_delete) {

                    Connection con = jdbc.getConnection();

                    try {

                        PreparedStatement ps = con.prepareStatement("delete from buss where bus_no =" + tf_id.getText());

                        ps.executeUpdate();

                        JOptionPane.showMessageDialog(btn_delete, "Deleted Successfully");

                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(btn_delete, "Something went Wrong");
                }

            }
        });
}
}
