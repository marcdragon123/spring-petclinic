package org.springframework.samples.petclinic.migration;

import java.io.FileReader;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.postgresql.jdbc4.*;

import net.minidev.json.parser.JSONParser;

public class JSONPut {

    private String[][] myArr;

    public void setMyArr(String[][] myArr) {
        this.myArr = myArr;
    }

    public String[][] getMyArr(){
        return myArr;
    }

    public void getJSON(){
        //read json file
        JSONParser reader = new JSONParser();
        try {
            String path ="/home/george/IdeaProjects/spring-petclinic/src/main/java/org/springframework/samples/petclinic/migration/json.json";
            Object obj = reader.parse(new FileReader(path));
            JSONArray jobj = (JSONArray) obj;
            //first index represents an entry in the db
            //second index represents the following:
            //i=0 --> address
            //i=1 --> city
            //i=2 -->last_name
            //i=3 -->telephone
            //i=4 -->id
            //i=5 -->first_name
            String[][] strings = new String[jobj.size()][6];
            for (int i =0; i<jobj.size();i++){
                String[] sad = jobj.get(i).toString().split(":");
                strings[i][0] =sad[1].replace("\",\"city\"","").replace("\"","");
                strings[i][1] =sad[2].replace("\",\""," ").replace(" last_name\"", "").replace("\"","");
                strings[i][2] =sad[3].replace("\",\""," ").replace("\"","").replace("telephone","");
                strings[i][3] =sad[4].replace("\",\""," ").replace("\"","").replace("id","");
                strings[i][4] =sad[5].replace("\",\""," ").replace("\"","").replace("first_name","");
                strings[i][5] =sad[6].replace("}","").replace("\"","");
            }

            for (int i =0; i<jobj.size();i++){
                for (int j=0;j<6;j++){
                    System.out.println(strings[i][j]);
                }
            }
            this.setMyArr(strings);

        }
        catch(Exception e){
            System.out.println("error : ");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {


        //manipulate
    }


}
