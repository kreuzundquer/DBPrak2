import java.io.*;
import java.util.*;

public class Parse {


    LinkedList<Artikel> ArtikelList;

    public Parse(String csvPath) throws IOException{

        BufferedReader in = new BufferedReader(new FileReader(csvPath));

        ArtikelList = new LinkedList<Artikel>();

        String x;

       while ((x = in.readLine()) != null){
               String[] value = x.split(";");

               int i = 0;
               ArtikelList.add(new Artikel( value[i++], value[i++], value[i++], value[i++],value[i++]));
        }
       in.close();
    }

    public LinkedList<Artikel> returnList(){

        return ArtikelList;
    }
}
