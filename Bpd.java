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
        ResultSet rs = stmt.executeQuery(query);

        LinkedList<String> results = new LinkedList<>();
        String Infostring;

        while (rs.next()){
	}
}
