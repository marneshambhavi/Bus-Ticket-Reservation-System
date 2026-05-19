package buspass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import java.awt .Window;

public class AdminUI {
    private JFrame f1;
    private JPanel currentPanel;
    public String name;
    public String id;
    private JFrame currentFrame;

    public AdminUI() {
        f1 = new JFrame("Bus Reservation System");
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setSize(750, 500);
        f1.setLocationRelativeTo(null);

        showadminMain();

    }

    public void showadminMain(){
        if (currentPanel != null) {
            f1.getContentPane().remove(currentPanel);
        }
        currentPanel = new adminMain(this);
        f1.getContentPane().add(currentPanel);
        f1.revalidate();
        f1.repaint();
    }

    public void showAdminScreen(){
        if (currentPanel!=null){
            f1.getContentPane().remove(currentPanel);
        }
        currentPanel=new adminlogin(this);
        f1.getContentPane().add(currentPanel);
        f1.revalidate();
        f1.repaint();
    }

    public void AdminScreen(){
        if (currentFrame!=null){
            f1.getContentPane().remove(currentFrame);
        }
        currentFrame=new admin(this);
        f1.getContentPane().add(currentFrame);
        f1.revalidate();
        f1.repaint();
    }


    public void Start()
    {
        f1.setVisible(true);
    }

}
