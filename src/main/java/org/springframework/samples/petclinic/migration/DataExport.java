package org.springframework.samples.petclinic.migration;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DataExport {


    private ArrayList<HashMap<String, String>> set = new ArrayList<>();

    public void getData() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/petclinic", "root", "petclinic");

            Statement stmt = con.createStatement();

            stmt.executeQuery("USE petclinic");


            ResultSet res = stmt.executeQuery("SELECT * FROM owners;");

            ResultSetMetaData md = res.getMetaData();

            while (res.next()) {
               HashMap<String, String> results = new HashMap<>();
               System.out.println(res.getInt(1)+"  "+res.getString(2)+"  "+res.getString(3));


               for(int i =0; i<md.getColumnCount(); i++) {
                   if (md.getColumnName(i)!=null && res.getString(i)!=null) {
                       results.put(md.getColumnName(i), res.getString(i));
                   }
               }

               set.add(results);
           }

           con.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }



}
