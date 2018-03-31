package org.springframework.samples.petclinic.migration;

import java.io.IOException;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.*;
import java.util.Set;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public class JSONConverter {

    public void convert(ArrayList<HashMap> list) throws IOException {
        JSONObject obj = new JSONObject();
        JSONArray company = new JSONArray();

        for (HashMap map: list) {

            /*for (Set<Entry> e : map.entrySet()) {
                return;
            }*/
        }
    }

}
