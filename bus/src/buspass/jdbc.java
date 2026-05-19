package buspass;

    import java.sql.*;
    import java.sql.ResultSet;

    public class jdbc {

        private static Connection con=null;
        ResultSet rs=null;

        public static Connection getConnection() {
            try {

                if (con == null) {
                    //driver class load
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    //create a connection..
                    con = DriverManager.
                            getConnection("jdbc:mysql://localhost:3306/Bus_system", "root", "shambhavi");

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return con;
        }

        public ResultSet getBusData() {
            con = new jdbc().getConnection();
            try {
                Statement s = con.createStatement();
                rs = s.executeQuery("select buss.bus_no,route.source,route.destination,buss.timing,route.price from buss,route where buss.rid=route.rid");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return rs;
        }
    }


