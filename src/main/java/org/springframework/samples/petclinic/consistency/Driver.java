package org.springframework.samples.petclinic.consistency;

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

                if(!check.equalsIgnoreCase(val)) {
                    add = true;
                }
            }

            if (add) {
                errors.add(i);
                add = false;
            }

            //Check the errors to know which index of the array list to add or update to postgresql
            //TODO: Add the push to postgreSQL
        }

    }
}
