package org.springframework.samples.petclinic.migration;
import java.sql.Connection;
public class Driver {
    public static void main(String[] args) {

        DataExport d = new DataExport();
        d.getData();

        ImportToPost i = new ImportToPost();
        JSONPut j = new JSONPut();

        j.getJSON();
        Connection c = i.connect();
        for (int y = 0; y<j.getMyArr().length;y++){
            i.putData(c,j.getMyArr()[y]);
        }

    }
}
