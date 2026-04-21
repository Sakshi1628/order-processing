import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class Shipment
{
    public static void main(String args[])
    {
        JFrame f = new JFrame("Shipment");

        JLabel head = new JLabel("Shipment Details");
        head.setBounds(140, 50, 150, 20);
        f.add(head);

        JLabel ordno = new JLabel("Order Number");
        ordno.setBounds(10, 80, 150, 20);
        f.add(ordno);

        JTextField txt_ordno = new JTextField();
        txt_ordno.setBounds(150, 80, 150, 20);
        f.add(txt_ordno);

        JLabel wareno = new JLabel("Warehouse Number");
        wareno.setBounds(10, 110, 150, 20);
        f.add(wareno);

        JTextField txt_wareno = new JTextField();
        txt_wareno.setBounds(150, 110, 150, 20);
        f.add(txt_wareno);

        JLabel shipdate = new JLabel("Shipment Date (YYYY-MM-DD)");
        shipdate.setBounds(10, 140, 200, 20);
        f.add(shipdate);

        JTextField txt_shipdate = new JTextField();
        txt_shipdate.setBounds(150, 140, 150, 20);
        f.add(txt_shipdate);

        JButton b = new JButton("Submit");
        b.setBounds(150, 180, 100, 40);
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

                String ordno = txt_ordno.getText();
                String wareno = txt_wareno.getText();
                String shipdate = txt_shipdate.getText();

                try
                {
                    Class.forName("oracle.jdbc.driver.OracleDriver");

                    Connection con = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:XE",
                        "ord",
                        "ord"
                    );

                    PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO SHIPMENT VALUES (?, ?, TO_DATE(?, 'YYYY-MM-DD'))"
                    );

                    ps.setInt(1, Integer.parseInt(ordno));
                    ps.setInt(2, Integer.parseInt(wareno));
                    ps.setString(3, shipdate);

                    int x = ps.executeUpdate();

                    if (x > 0)
                    {
                        JOptionPane.showMessageDialog(b,
                            "Shipment Inserted Successfully");
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