public class Artikel {

    public String ARTBEZ;
    public String MGE;
    public String PREIS;
    public String KUEHL;
    public String ANZBO;
    public Type type = Type.ARTIKEL;

    public Artikel( String ARTBEZ, String MGE, String PREIS,
                    String KUEHL, String ANZBO ){

        this.ARTBEZ = ARTBEZ;
        this.MGE = MGE;
        this.PREIS = PREIS;
        this.KUEHL = KUEHL;
        this.ANZBO = ANZBO;
    }
}
