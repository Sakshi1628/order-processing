import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

class Orderitem
{
    public static void main(String args[])
    {
        JFrame f = new JFrame("Order Items");

        JLabel head = new JLabel("Order Item Details");
        head.setBounds(140, 50, 200, 20);
        f.add(head);

        JLabel ordno = new JLabel("Order Number");
        ordno.setBounds(10, 80, 150, 20);
        f.add(ordno);

        JTextField txt_ordno = new JTextField();
        txt_ordno.setBounds(150, 80, 150, 20);
        f.add(txt_ordno);

        JLabel itemno = new JLabel("Item Number");
        itemno.setBounds(10, 110, 150, 20);
        f.add(itemno);

        JTextField txt_itemno = new JTextField();
        txt_itemno.setBounds(150, 110, 150, 20);
        f.add(txt_itemno);

        JLabel qty = new JLabel("Quantity");
        qty.setBounds(10, 140, 150, 20);
        f.add(qty);

        JTextField txt_qty = new JTextField();
        txt_qty.setBounds(150, 140, 150, 20);
        f.add(txt_qty);

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
                String itemno = txt_itemno.getText();
                String quantity = txt_qty.getText();

                try
                {
                    Class.forName("oracle.jdbc.driver.OracleDriver");

                    Connection con = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:XE",
                        "ord",
                        "ord"
                    );

                    PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO ORDER_ITEM VALUES (?, ?, ?)"
                    );

                    ps.setInt(1, Integer.parseInt(ordno));
                    ps.setInt(2, Integer.parseInt(itemno));
                    ps.setInt(3, Integer.parseInt(quantity));

                    int x = ps.executeUpdate();

                    if (x > 0)
                    {
                        JOptionPane.showMessageDialog(b,
                            "Order Item Inserted Successfully");
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