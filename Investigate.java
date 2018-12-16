
import java.sql.*;
import java.util.LinkedList;

public class Investigate {

    Type type;
    Connection connection;
    String[] stmtstring;

    public Investigate
            (Type type, Connection connection, String param){

        this.type = type;
        this.connection = connection;

        this.stmtstring = new String[2];

        switch (type){
            case ARTIKEL:
                stmtstring[0] =
                        "SELECT * FROM ARTIKEL WHERE ARTNR =" + param;
                stmtstring[1]
                        = "SELECT * FROM BPOS WHERE ARTNR =" + param;

                break;

            case BESTELLUNG:
                stmtstring[0] =
                        "SELECT * FROM BESTELLUNG WHERE BSTNR =" + param;
                stmtstring[1] =
                        "SELECT * FROM BPOS WHERE BSTNR =" + param;

                break;
        	default: break;
        }
    }

    public LinkedList<String> start()
            throws SQLException {

        LinkedList<String> param = new LinkedList<>();

        Statement substmt;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(stmtstring[0]);

        ResultSet res;

        while (rs.next()) {

            switch (type) {
                case ARTIKEL:

                    param.add("\n\nARTNR  "
                            + rs.getInt("ARTNR"));
                    param.add("\nARTBEZ  "
                            + rs.getString("ARTBEZ"));
                    param.add("\nMGE  "
                            + rs.getString("MGE"));
                    param.add("\nPREIS  "
                            + rs.getDouble("PREIS"));
                    param.add("\nARTNR  "
                            + rs.getString("KUEHL"));
                    param.add("\nANZBO  "
                            + rs.getInt("ANZBO"));


                    substmt = connection.createStatement();

                    res = substmt.executeQuery(stmtstring[1]);

                    param.add("\n\nBPOS:");

                    while (res.next())
                        param.add("\nBSTNR "
                                + res.getInt("BSTNR")
                                + "\nBPOS " + res.getInt("POSNR"));

                    break;

                case BESTELLUNG:

                    param.add("\n\nBSTNR "
                            + rs.getInt("BSTNR"));
                    param.add("\nKNR "
                            + rs.getInt("KNR"));
                    param.add("\nSTATUS"
                            + rs.getInt("STATUS"));
                    param.add("\nRSUM"
                            + rs.getDouble("RSUM"));


                    substmt = connection.createStatement();

                    res = substmt.executeQuery(stmtstring[1]);

                    param.add("\n\nBPOS:");

                    while (res.next()) {
                        param.add("\nBSTNR "
                                + res.getInt("BSTNR")
                                + "\nBPOS " + res.getInt("POSNR"));


                        Statement help = connection.createStatement();

                        ResultSet x = help.executeQuery(
                                "SELECT ARTBEZ FROM ARTIKEL WHERE ARTNR = "
                                        + res.getInt("ARTNR"));

                        x.next();

                        param.add("\nARTBEZ "
                                + x.getString("ARTBEZ"));


                    }
                    break;
                default: break;
            }
        }
        return param;
    }
}

