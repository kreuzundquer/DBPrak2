import java.sql.*;

public class Bpd {
	
	private Connection connection;

	int posnr, artnr, anzbo, menge, algrad;
	String kuehl, artbez;
	boolean connection;

	static String stmt = "SELECT * FROM BESTELLUNG WHERE STATUS= ";

	public Bpd(Connection connection) {
		this.connection = connection;
	}

	static public LinkedLiest<String> show(int status){
		Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(stmt + status);

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
