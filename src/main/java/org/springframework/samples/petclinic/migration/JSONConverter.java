package org.springframework.samples.petclinic.migration;

import java.io.File;
import java.io.IOException;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.*;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class JSONConverter {

    public void convert(ArrayList<HashMap<String, String>> list) throws IOException {

        JSONArray owners = new JSONArray();

        for (HashMap map: list) {

            JSONObject obj = new JSONObject();

            Set<Entry> set = map.entrySet();

            for (Entry e: set) {
                obj.put(e.getKey().toString(), e.getValue().toString());
            }

            owners.add(obj);

        }


        // try-with-resources statement based :)
        //This a path for my VM, please change this link for your system!!!!!!!
        File file = new File("/home/marcdragon/Projects/Spring/spring-petclinic/src/main/java/org/springframework/samples/petclinic/migration/json.json");
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(owners.toJSONString());
            System.out.println("Successfully Copied JSON Array to File...");
        }
    }

}
