import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class Orders
{
    public static void main(String args[])
    {
        JFrame f = new JFrame("Orders");

        JLabel head = new JLabel("Order Details");
        head.setBounds(140, 50, 150, 20);
        f.add(head);

        JLabel ordno = new JLabel("Order Number");
        ordno.setBounds(10, 80, 150, 20);
        f.add(ordno);

        JTextField txt_ordno = new JTextField();
        txt_ordno.setBounds(150, 80, 150, 20);
        f.add(txt_ordno);

        JLabel odate = new JLabel("Order Date (YYYY-MM-DD)");
        odate.setBounds(10, 110, 180, 20);
        f.add(odate);

        JTextField txt_odate = new JTextField();
        txt_odate.setBounds(150, 110, 150, 20);
        f.add(txt_odate);

        JLabel custno = new JLabel("Customer Number");
        custno.setBounds(10, 140, 150, 20);
        f.add(custno);

        JTextField txt_custno = new JTextField();
        txt_custno.setBounds(150, 140, 150, 20);
        f.add(txt_custno);

        JLabel amount = new JLabel("Amount");
        amount.setBounds(10, 170, 150, 20);
        f.add(amount);

        JTextField txt_amount = new JTextField();
        txt_amount.setBounds(150, 170, 150, 20);
        f.add(txt_amount);

        JButton b = new JButton("Submit");
        b.setBounds(150, 220, 100, 40);
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

                String ono = txt_ordno.getText();
                String odate = txt_odate.getText();
                String cust_no = txt_custno.getText();
                String amt = txt_amount.getText();

                try
                {
                    Class.forName("oracle.jdbc.driver.OracleDriver");

                    Connection con = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:XE",
                        "ord",
                        "ord"
                    );

                    PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO ORDERS VALUES (?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?)"
                    );

                    ps.setInt(1, Integer.parseInt(ono));
                    ps.setString(2, odate); // date handled in SQL
                    ps.setInt(3, Integer.parseInt(cust_no));
                    ps.setDouble(4, Double.parseDouble(amt));

                    int x = ps.executeUpdate();

                    if (x > 0)
                    {
                        JOptionPane.showMessageDialog(b,
                            "Order Inserted Successfully");
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