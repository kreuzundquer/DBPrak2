import java.sql.Connection;
import java.util.LinkedList;
import java.io.*;

public class Main {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void main (String[] args) throws Exception {


        State state = null;

        Connect.init();
        Connection y = Connect.getInstance();

        while (state != State.END){
            state = nextState(state);

            switch (state) {
                case bulkInsert:
                    Parse parser = new Parse("x.csv");
                    LinkedList<Artikel> olist = parser.returnList();
                    Insert<Artikel> insert = new Insert<>(y);
                    insert.fromList(olist, Type.ARTIKEL);
                    break;

                case shARTs:case shBESs:case shKUNs:

                    Type temp = Type.ARTIKEL;

                    if (state == State.shARTs)
                        temp = Type.ARTIKEL;
                    else if (state == State.shBESs)
                        temp = Type.BESTELLUNG;
                    else if (state == State.shKUNs)
                        temp = Type.KUNDE;

                    Show show = new Show(temp, y);
                    LinkedList<String> results = show.returnResults();

                    for (String x : results) {
                        System.out.println(x);
                        System.out.println("\n\n");
                    }

                    break;



                case caBES:

                    int x = 0;

                        try {
                            x = Integer.parseInt(in.readLine());
                        }
                        catch (Exception e){
                            continue;
                        }


                    Calculate calculate = new Calculate(Integer.toString(x), y);
                    calculate.start();

                    break;

                case invART: case invBST:

                    int o;

                    System.out.println("Bitte geben Sie einen Parameter ein.");

                    try {
                        o = Integer.parseInt(in.readLine());
                    }
                    catch (Exception e){
                        continue;
                    }

                    Type type = Type.BESTELLUNG;

                    if (state == State.invART)
                        type = Type.ARTIKEL;


                    Investigate inv = new Investigate(type, y, Integer.toString(o));
                    LinkedList<String> result = inv.start();

                    for (String i : result)
                        System.out.println(i);

                    break;

                case plBES:

                    String BESTDAT;
                    String KNR;
                    String ARTNR;
                    String MENGE;

                    System.out.println("Bitte geben Sie ein BESTDAT ein.");
                    BESTDAT = in.readLine();

                    System.out.println("Bitte geben Sie eine KNR ein.");
                    KNR = in.readLine();

                    Place place = new Place(y,KNR , BESTDAT);

                    Boolean done = false;

                    while (!done) {
                        System.out.println("Artikel hinzufügen? ja | nein");

                        String decisison = in.readLine();

                        if (!decisison.equals("ja"))
                            break;

                        System.out.println("ARTNR Eingeben.");
                        ARTNR = in.readLine();

                        System.out.println("MENGE Eingeben.");
                        MENGE = in.readLine();

                        place.addPOS(ARTNR,MENGE);

                    }

                    place.finalize();
                    break;

                case clSHIP:
                	LinkedList<String> alleBestellungen = new LinkedList<String>();
                	System.out.println("Bitte wählen Sie eine Bestellnummer aus:");
                	Bpd initial = new Bpd(y);
                	alleBestellungen = Bpd.showBestellungen(1);
                	for (String bestellung: alleBestellungen) {
                		System.out.println(bestellung);
                	}
                	int bstnr = Integer.parseInt(in.readLine());
                	break;
                    
                    
                default:
                    break;
            }


        }
    }

    public static State nextState(State state){

        System.out.println("\n\nPlease chose mode:");
        System.out.println("1 => 4. JDBC-Insert");
        System.out.println("2 => 5 A) Anzeigen aller Artikel");
        System.out.println("3 => 5 B) Anzeigen aller Bestellungen");
        System.out.println("4 => 5 C) Anzeigen aller Kunden");
        System.out.println("5 => 5 D) Kalkulieren");
        System.out.println("6 => 5 E) Stammdaten ARTNR");
        System.out.println("7 => 5 A) Stammdaten BSTNR");
        System.out.println("8 => 6    Neue Bestellung");
        System.out.println("9 => 8 G) Versandplanung");
        System.out.println("17 => END");



        int st = 0;

        while (st <= 0 || st > 89){
            try {
                st = Integer.parseInt(in.readLine());
            }
            catch (Exception e){
                continue;
            }
        }

        switch (st){
            case 1 :
                state = State.bulkInsert;
                break;

            case 2:
                state = State.shARTs;
                break;

            case 3:
                state = State.shBESs;
                break;

            case 4:
                state = State.shKUNs;
                break;

            case 5:
                state = State.caBES;
                break;

            case 6:
                state = State.invART;
                break;

            case 7:
                state = State.invBST;
                break;

            case 8:
                state = State.plBES;
                break;
                
            case 9:
            	state = State.clSHIP;
            	break;

            case 17:
                state = State.END;
                break;

            default:
                state = State.END;
                break;
        }

        return state;
    }
}
