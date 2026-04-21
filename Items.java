import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

class Items
{
    public static void main(String args[])
    {
        JFrame f = new JFrame("Items");

        JLabel head = new JLabel("Item Details");
        head.setBounds(140, 50, 150, 20);
        f.add(head);

        JLabel itemno = new JLabel("Item Number");
        itemno.setBounds(10, 80, 150, 20);
        f.add(itemno);

        JTextField txt_itemno = new JTextField();
        txt_itemno.setBounds(150, 80, 150, 20);
        f.add(txt_itemno);

        JLabel price = new JLabel("Price");
        price.setBounds(10, 110, 150, 20);
        f.add(price);

        JTextField txt_price = new JTextField();
        txt_price.setBounds(150, 110, 150, 20);
        f.add(txt_price);

        JButton b = new JButton("Submit");
        b.setBounds(150, 150, 100, 40);
        f.add(b);

        f.setSize(400, 400);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Button Action
        b.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Button clicked");

                String itemno = txt_itemno.getText();
                String price = txt_price.getText();

                try
                {
                    Class.forName("oracle.jdbc.driver.OracleDriver");

                    Connection con = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:XE",
                        "ord",
                        "ord"
                    );

                    PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO ITEMS VALUES (?, ?)"
                    );

                    ps.setInt(1, Integer.parseInt(itemno)); // NUMBER
                    ps.setDouble(2, Double.parseDouble(price)); // NUMBER

                    int x = ps.executeUpdate();

                    if (x > 0)
                    {
                        JOptionPane.showMessageDialog(b,
                            "Item Inserted Successfully");
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