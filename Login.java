import javax.swing.*;
import java.awt.event.*;

public class Login
{
    public static void main(String args[])
    {
        JFrame f = new JFrame("Login Page");

        JLabel head = new JLabel("Welcome to Order Processing System", JLabel.CENTER);
        head.setBounds(50, 20, 300, 30);
        f.add(head);

        JLabel usr = new JLabel("Username:");
        usr.setBounds(50, 80, 100, 20);
        f.add(usr);

        JTextField txt_usr = new JTextField();
        txt_usr.setBounds(150, 80, 120, 20);
        f.add(txt_usr);

        JLabel pwd = new JLabel("Password:");
        pwd.setBounds(50, 120, 100, 20);
        f.add(pwd);

        JPasswordField txt_pwd = new JPasswordField();
        txt_pwd.setBounds(150, 120, 120, 20);
        f.add(txt_pwd);

        JButton b = new JButton("Login");
        b.setBounds(140, 180, 80, 40);
        f.add(b);

        b.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String username = txt_usr.getText();
                String password = new String(txt_pwd.getPassword());

                if(username.equals("admin") && password.equals("1234"))
                {
                    JOptionPane.showMessageDialog(f, "Login Successful");
                    Esso.main(new String[0]);
                }
                else
                {
                    JOptionPane.showMessageDialog(f,
                        "Invalid username or password",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        f.setSize(400, 300);
        f.setLayout(null);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}