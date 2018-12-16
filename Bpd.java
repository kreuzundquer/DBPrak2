import java.sql.*;
import java.util.*;

public class Bpd {
	
	private static Connection connection;

	int posnr, artnr, anzbo, menge, algrad;
	String kuehl, artbez;
	boolean verpackt;


	public Bpd(Connection connection) {
		this.connection = connection;
	}

	public static LinkedList<String> showBestellungen(int status) throws SQLException {
		Statement stmt = connection.createStatement();
		String queryBestellungen = "SELECT * FROM BESTELLUNG WHERE STATUS=";
        ResultSet rs = stmt.executeQuery(queryBestellungen + status);

        LinkedList<String> results = new LinkedList<>();
        String Infostring;

        while (rs.next()){
			Infostring = "BSTNR :" + rs.getInt("BSTNR") +
            			"\nKNR :" + rs.getString("KNR") +
            			"\nSTATUS :" + rs.getString("STATUS") +
                        "\nRSUM :" + rs.getDouble("RSUM");
						
			results.add(Infostring);
		}

		return results;
	}
	
	public static LinkedList<Bpd> showBpos(int bstnr) throws SQLException {
		LinkedList<Bpd> lbpos = new LinkedList<Bpd>();
		Statement stmt = connection.createStatement();
		String queryBpos = "SELECT * FROM BPOS WHERE BSTNR = " + bstnr +
				"GROUP BY "
		
		return lbpos;
	}
}
