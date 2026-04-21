import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class Warehouse
{
    public static void main(String args[])
    {
        JFrame f = new JFrame("Warehouse");

        JLabel head = new JLabel("Warehouse Details");
        head.setBounds(140, 50, 150, 20);
        f.add(head);

        JLabel wareno = new JLabel("Warehouse Number");
        wareno.setBounds(10, 80, 150, 20);
        f.add(wareno);

        JTextField txt_wareno = new JTextField();
        txt_wareno.setBounds(150, 80, 150, 20);
        f.add(txt_wareno);

        JLabel city = new JLabel("City");
        city.setBounds(10, 110, 150, 20);
        f.add(city);

        JTextField txt_city = new JTextField();
        txt_city.setBounds(150, 110, 150, 20);
        f.add(txt_city);

        JButton b = new JButton("Submit");
        b.setBounds(150, 150, 100, 40);
        f.add(b);

        f.setSize(400, 400);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        b.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Button clicked");

                String wareno = txt_wareno.getText();
                String city = txt_city.getText();

                try
                {
                    Class.forName("oracle.jdbc.driver.OracleDriver");

                    Connection con = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:XE",
                        "ord",
                        "ord"
                    );

                    PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO WAREHOUSE VALUES (?, ?)"
                    );

                    ps.setInt(1, Integer.parseInt(wareno));
                    ps.setString(2, city);

                    int x = ps.executeUpdate();

                    if (x > 0)
                    {
                        JOptionPane.showMessageDialog(b,
                            "Warehouse Inserted Successfully");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(b,
                            "Insert Failed");
                    }

                    con.close();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(b,
                        "Error: " + ex.getMessage());
                }
            }
        });
    }
}