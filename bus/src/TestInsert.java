import buspass.jdbc;
import java.sql.Statement;

public class TestInsert {
    public static void main(String[] args) {
        try {
            System.out.println("Connecting...");
            Statement s = jdbc.getConnection().createStatement();
            System.out.println("Connected. Executing insert...");
            int rows = s.executeUpdate("INSERT INTO user(fullname,email,gender,dob,username,password,address) " +
                    "VAlUES('TestName','test@test.com','M','01-01-2000','testuser','pwd123','123 Test St')");
            System.out.println("Inserted rows: " + rows);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
