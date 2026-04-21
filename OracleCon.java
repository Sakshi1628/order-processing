import java.sql.*;

class OracleCon
{
    public static void main(String args[])
    {
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:XE",
                "hr",
                "hr"
            );

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from employees");

            while(rs.next())
            {
                System.out.println(rs.getInt(1) + " " + rs.getString(2));
            }

            con.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}