import java.sql.*;

public class Place {

    Connection connection;
    public String BESTDAT;
    public String KNR;
    public String BSTNR;
    public int POSNR = 1;

    public Place(Connection connection, String KNR, String BESTDAT)
            throws SQLException{

        this.KNR = KNR;
        this.BESTDAT = BESTDAT;
        this.connection = connection;

        String generatedColumns[] = { "BSTNR" };
        Statement stmt;
        ResultSet rs;

        stmt = connection.createStatement();
        rs = stmt.executeQuery(
                "SELECT * FROM KUNDE WHERE " +
                        "KNR =" + KNR);

        if (!rs.next())
            return;

        stmt.close();
        rs.close();

        stmt = connection.prepareStatement(
                "INSERT INTO BESTELLUNG (KNR, BESTDAT, STATUS) "
                        + "VALUES (" +KNR + ", '" + BESTDAT + "', "
                        + "1)", generatedColumns);

        ((PreparedStatement) stmt).executeUpdate();
        rs = stmt.getGeneratedKeys();

        if (rs.next())
            BSTNR = rs.getString(1);

        rs.close();
        stmt.close();

    }

    public void addPOS(String ARTNR, String MENGE) throws SQLException{

        String stmtstring = "INSERT INTO BPOS " +
                "(BSTNR, POSNR, ARTNR, MENGE) VALUES " +
                "(" + BSTNR + "," + POSNR++ + "," + ARTNR + "," + MENGE+")";

        Statement stmt = connection.createStatement();
        stmt.executeUpdate(stmtstring);


    }

    public void finalize() throws SQLException{
        Calculate calculate = new Calculate(BSTNR, connection);
        calculate.start();
    }
}
