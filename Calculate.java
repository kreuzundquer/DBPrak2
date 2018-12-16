import java.sql.*;

public class Calculate {

    String BSTNR;
    Connection connection;

    public Calculate(String BSTNR, Connection connection){

        this.BSTNR = BSTNR;
        this.connection = connection;

    }

    public void start() throws SQLException{

        double sum = 0;

        Statement mainstmt = connection.createStatement();
        ResultSet rs = mainstmt.executeQuery(
                "SELECT * FROM BPOS WHERE BSTNR =" + BSTNR);



        while (rs.next()) {

            double preis;

            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(
                    "SELECT PREIS FROM ARTIKEL WHERE ARTNR ="
                            + rs.getInt("ARTNR"));

            res.next();
            preis = res.getDouble("PREIS");

            stmt.close();


            stmt = connection.createStatement();
            stmt.executeUpdate("UPDATE  BPOS SET WERT = "
                    + (preis * ((double)rs.getInt("MENGE")))
                    + "WHERE BSTNR =" + rs.getInt("BSTNR")
                    + "AND POSNR =" + rs.getInt("POSNR"));

            res.close();


        }


        rs.close();
        mainstmt.close();

        mainstmt = connection.createStatement();
        rs = mainstmt.executeQuery(
                "SELECT WERT FROM BPOS WHERE BSTNR =" + BSTNR);

        while (rs.next())
            sum += rs.getDouble("WERT");

        rs.close();
        mainstmt.close();

        mainstmt = connection.createStatement();
        mainstmt.executeUpdate("UPDATE  BESTELLUNG SET RSUM = "
                                + sum +", STATUS=1 WHERE BSTNR =" + BSTNR );
    }
}
