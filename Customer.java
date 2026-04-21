import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

class Customer
{
    public static void main(String args[])
    {
        JFrame f = new JFrame("Customer");

        JLabel head = new JLabel("Customer Detail's");
        head.setBounds(140, 50, 150, 20);
        f.add(head);

        JLabel custno = new JLabel("Customer Number");
        custno.setBounds(10, 80, 150, 20);
        f.add(custno);

        JTextField txt_custno = new JTextField();
        txt_custno.setBounds(150, 80, 150, 20);
        f.add(txt_custno);

        JLabel cname = new JLabel("Customer Name");
        cname.setBounds(10, 110, 150, 20);
        f.add(cname);

        JTextField txt_cname = new JTextField();
        txt_cname.setBounds(150, 110, 150, 20);
        f.add(txt_cname);

        JLabel city = new JLabel("City");
        city.setBounds(10, 140, 150, 20);
        f.add(city);

        JTextField txt_city = new JTextField();
        txt_city.setBounds(150, 140, 150, 20);
        f.add(txt_city);

        JButton b = new JButton("Submit");
        b.setBounds(150, 180, 100, 40);
        f.add(b);

        f.setSize(400, 400);
        f.setLayout(null);
        f.setVisible(true);

        // Button Action
        b.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Button clicked"); // Debug

                String cno = txt_custno.getText();
                String cname = txt_cname.getText();
                String city = txt_city.getText();

                try
                {
                    // Load driver
                    Class.forName("oracle.jdbc.driver.OracleDriver");

                    // Create connection
                    Connection con = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:XE",
                        "ord",
                        "ord"
                    );

                    // Prepared Statement (BEST WAY)
                    PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO CUSTOMER VALUES (?, ?, ?)"
                    );

                    ps.setInt(1, Integer.parseInt(cno)); // number column
                    ps.setString(2, cname);
                    ps.setString(3, city);

                    int x = ps.executeUpdate();

                    if (x > 0)
                    {
                        JOptionPane.showMessageDialog(b,
                            "Record Inserted Successfully");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(b,
                            "Insertion Failed");
                    }

                    con.close();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace(); // VERY IMPORTANT
                    JOptionPane.showMessageDialog(b,
                        "Error: " + ex.getMessage());
                }
            }
        });
    }
}