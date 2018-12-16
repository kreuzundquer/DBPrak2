import java.util.*;
import java.sql.*;

public class Insert<T> {

Connection connection;

    public Insert(Connection connection){

        this.connection = connection;
    }

    public  void fromList(LinkedList<T> list, Type type) throws SQLException {

        switch (type) {
            case ARTIKEL:

                for (T x : list) {
                    String stmtString = "INSERT INTO ARTIKEL " +
                            "(ARTBEZ, MGE, PREIS, KUEHL, ANZBO)" +
                            " VALUES" +
                            "('" + ((Artikel) x).ARTBEZ + "','" + ((Artikel) x).MGE + "'," +
                            ((Artikel) x).PREIS + ",'" + ((Artikel) x).KUEHL + "'," +
                            ((Artikel) x).ANZBO + ")";

                    System.out.println(stmtString);

                    Statement stmt = connection.createStatement();
                    stmt.executeUpdate(stmtString);
                }
                break;
            default: break;
        }
    }


}
