import java.sql.*;

public class Connect {

    static int error;
    static Connection dbConnection = null;
    static String treiber = "oracle.jdbc.driver.OracleDriver";


    public static void init() throws SQLException{

        if (dbConnection == null) {
            oracle.jdbc.pool.OracleDataSource ods =
                    new oracle.jdbc.pool.OracleDataSource();

            try {
                Class.forName(treiber).newInstance();
            } catch (Exception e) {
                System.out.println("Error while loading ODBC driver "
                        + e.getMessage());
                return;
            }

            try {
                ods.setURL("jdbc:oracle:thin:" +
                        "dbprak01/dbprak01@//" +
                        "schelling.nt.fh-koeln.de:1521/xe");

                dbConnection = ods.getConnection();
            } catch (SQLException e) {
                System.out.println("Error connecting to database.");
                System.out.println(e.getMessage());
                return;
            }
        } else {
            System.out.println("Connection already given");
            return;
        }

        error = 0;
    }

    public static Connection getInstance() throws SQLException{

        if (dbConnection != null)
            return dbConnection;
        else System.out.println("No connection given.");

        return null;
    }

    public static boolean terminate() throws SQLException{

        dbConnection.close();

        if (dbConnection.isClosed())
            return true;
        else if ( error <=3 ) {
            error++;
            terminate();
        }

        return false;
    }

}
