import java.sql.*;
import java.util.*;

public class Bpd {
	
	private static Connection connection;

	int posnr, artnr, anzbo, menge, algrad;
	String kuehl, artbez;
	boolean verpackt;


	static String query = "SELECT * FROM BESTELLUNG WHERE STATUS=";


	public Bpd(Connection connection) {
		this.connection = connection;
	}

	public static LinkedList<String> show(int status) throws SQLException {
		Statement stmt = connection.createStatement();


        ResultSet rs = stmt.executeQuery(query + status);


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
}
