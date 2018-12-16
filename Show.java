import java.sql.*;
import java.util.*;

public class Show {

    static String stmtART = "SELECT * FROM ARTIKEL";
    static String stmtBST = "SELECT * FROM BESTELLUNG";
    static String stmtKND  = "SELECT * FROM KUNDE";

    String stmtWILDCARD;
    Type type;

    Connection  connection;


    public Show (Type type, Connection connection){

        this.type = type;
        this.connection = connection;

        switch (type){
            case ARTIKEL:
                stmtWILDCARD = stmtART;
                break;
            case BESTELLUNG:
                stmtWILDCARD = stmtBST;
                break;
            case KUNDE:
                stmtWILDCARD = stmtKND;
                break;
        }


    }

    public LinkedList<String> returnResults() throws SQLException{

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(stmtWILDCARD);

        LinkedList<String> results = new LinkedList<>();
        String Infostring;

        while (rs.next()){

            switch(type){
                case ARTIKEL:
                     Infostring = "ARTNR :" + rs.getInt("ARTNR") +
                            "\nARTBEZ :" + rs.getString("ARTBEZ") +
                            "\nMGE :" + rs.getString("MGE") +
                            "\nPREIS :" + rs.getDouble("PREIS") +
                            "\nKUEHL :" + rs.getString("KUEHL") +
                            "\nANZBO :" + rs.getString("ANZBO");

                    results.add(Infostring);

                    break;
                case BESTELLUNG:
                    Infostring = "BSTNR :" + rs.getInt("BSTNR") +
                            "\nKNR :" + rs.getString("KNR") +
                            "\nSTATUS :" + rs.getString("STATUS") +
                            "\nRSUM :" + rs.getDouble("RSUM");

                    results.add(Infostring);

                    break;
                case KUNDE:
                    Infostring = "KNR :" + rs.getInt("KNR") +
                            "\nKNAME :" + rs.getString("KNAME") +
                            "\nPLZ :" + rs.getString("PLZ") +
                            "\nORT :" + rs.getString("ORT") +
                            "\nSTRASSE :" + rs.getString("STRASSE");

                    results.add(Infostring);

                    break;
            }
        }

        return results;


    }
}
