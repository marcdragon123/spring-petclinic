package org.springframework.samples.petclinic.consistency;

import org.springframework.samples.petclinic.migration.JSONConverter;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DataExport {


    private ArrayList<HashMap<String, String>> set = new ArrayList<>();

    public void getData(String url, String user, String password) {

        try {
            Connection con = DriverManager.getConnection(url,user,password);

            Statement stmt = con.createStatement();
            if (url == "jdbc:mysql://localhost:3306/petclinic") {
                stmt.executeQuery("USE petclinic");
            }

            ResultSet res = stmt.executeQuery("SELECT * FROM owners;");

            ResultSetMetaData md = res.getMetaData();

            addToMap(res, md, set);

            con.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static void addToMap(ResultSet res, ResultSetMetaData md, ArrayList<HashMap<String, String>> set) throws SQLException {
        while (res.next()) {
           HashMap<String, String> results = new HashMap<>();

           for(int i = 1; i<=md.getColumnCount(); i++) {
               if (md.getColumnName(i)!=null && res.getString(i)!=null) {
                   results.put(md.getColumnName(i), res.getString(i));
               }
           }

           set.add(results);
       }
    }

    public ArrayList<HashMap<String, String>> getList() {
        return set;
    }

    public void setList(ArrayList<HashMap<String, String>> set) {
        this.set = set;
    }

    public HashMap<String, String> getQuery(String query, String url, String user, String password) {

        HashMap<String, String> results = new HashMap<>();

        try {
            Connection con = DriverManager.getConnection(url,user,password);

            Statement stmt = con.createStatement();
            if (url == "jdbc:mysql://localhost:3306/petclinic") {
                stmt.executeQuery("USE petclinic");
            }

            ResultSet res = stmt.executeQuery(query);

            ResultSetMetaData md = res.getMetaData();

            res.next();

            for(int i = 1; i<=md.getColumnCount(); i++) {
               if (md.getColumnName(i)!=null && res.getString(i)!=null) {
                    results.put(md.getColumnName(i), res.getString(i));
               }
            }

            con.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return results;
    }


}
