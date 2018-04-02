package org.springframework.samples.petclinic.consistency;

import java.sql.*;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.*;
import java.util.Set;

public class Driver {
    public static void main(String[] args) {

        DataExport dSQL = new DataExport();
        dSQL.getData("jdbc:mysql://localhost:3306/petclinic", "root", "petclinic");

        DataExport dPSQL = new DataExport();
        dPSQL.getData("jdbc:postgresql://localhost/mydb","mydb","potuto");

        ArrayList<HashMap<String, String>> list1 = dSQL.getList();

        ArrayList<HashMap<String, String>> list2 = dPSQL.getList();

        ArrayList <Integer> errors = new ArrayList<>();

        boolean add = false;

        for (int i =0; i<list1.size(); i++) {
            HashMap<String, String> map1 = list1.get(i);
            HashMap<String, String> map2 = list2.get(i);

            Set<Entry<String, String>> set1 = map1.entrySet();
            Set<Entry<String, String>> set2 = map2.entrySet();

            Iterator<Entry<String, String>> it1 = set1.iterator();
            Iterator<Entry<String, String>> it2 = set2.iterator();
            while(it1.hasNext() && it2.hasNext()) {

                Entry<String, String> e1 = it1.next();

                String key = e1.getKey();
                String val = e1.getValue();

                String check = map2.get(key);

                System.out.println("sql value: "+val + "    VS     psql value: "+check);

                if(!check.equalsIgnoreCase(val)) {
                    add = true;
                }
            }

            if (add) {
                errors.add(i);
                add = false;
            }
        }

        //Check the errors to know which index of the array list to add or update to postgresql
        //TODO: Add the push to postgreSQL
        if (!errors.isEmpty()){
            //there is something in errors that needs to be put into the psql db from the sql db

            //loop thru errors
            for (int i = 0; i < errors.size(); i++) {
                System.out.println("errors found");
                System.out.println("SQL index of error: "+errors.get(i));
                //get the # that was added to errors arrayList
                Integer index = errors.get(i);

                //get() on sql list with that index
                HashMap<String, String> sqlData = list1.get(index);

                //connect to psql
                Connection c = null;
                try {
                    String url = "jdbc:postgresql://localhost/mydb";
                    String  user = "mydb";
                    String password = "potuto";
                    c = DriverManager.getConnection(url,user,password);
                    System.out.println("Connection success");
                }
                catch(Exception e){
                    e.printStackTrace();
                    System.out.println("connection failure");
                }

                //call a method that will update that entry in the psql side
                //that index is the "id" field in psql
                updatePSQL(c,sqlData,index);


                //boom
            }

        }

    }

    public static void updatePSQL(Connection c, HashMap<String, String> data, int id){

        Statement stm = null;

        Set<Entry<String, String>> set = data.entrySet();
        Iterator<Entry<String, String>> it = set.iterator();

        Entry<String, String> e = it.next();

        String column_name = e.getKey();
        String value = e.getValue();

        String pSQL = "UPDATE owners SET "+column_name+"= '"+value+"' WHERE id = "+id+";";

        try{
            stm = c.createStatement();
            stm.execute(pSQL);
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
    }
}
