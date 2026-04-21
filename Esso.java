import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Esso
{
    public static void main(String args[])
    {
        JFrame f = new JFrame("ORD");
        f.setSize(500, 350);
        f.setLayout(null);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.getContentPane().setBackground(new Color(230, 240, 255));

        JLabel head = new JLabel("Order Processing System", JLabel.CENTER);
        head.setFont(new Font("Arial", Font.BOLD, 20));
        head.setBounds(100, 30, 300, 30);
        head.setForeground(Color.DARK_GRAY);
        f.add(head);

        JButton cust = new JButton("Customer");
        cust.setBounds(35, 100, 100, 40);
        cust.addActionListener(e -> Customer.main(new String[0]));
        f.add(cust);

        JButton ord = new JButton("Orders");
        ord.setBounds(195, 100, 100, 40);
        ord.addActionListener(e -> Orders.main(new String[0]));
        f.add(ord);

        JButton ordi = new JButton("Order-Item");
        ordi.setBounds(355, 100, 100, 40);
        ordi.addActionListener(e -> Orderitem.main(new String[0]));
        f.add(ordi);

        JButton item = new JButton("Items");
        item.setBounds(35, 170, 100, 40);
        item.addActionListener(e -> Items.main(new String[0]));
        f.add(item);

        JButton ship = new JButton("Shipment");
        ship.setBounds(195, 170, 100, 40);
        ship.addActionListener(e -> Shipment.main(new String[0]));
        f.add(ship);

        JButton ware = new JButton("Warehouse");
        ware.setBounds(355, 170, 100, 40);
        ware.addActionListener(e -> Warehouse.main(new String[0]));
        f.add(ware);
    }
}