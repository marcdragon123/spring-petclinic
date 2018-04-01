package org.springframework.samples.petclinic.migration;
import java.sql.*;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
public class ImportToPost {

    public Connection connect(){
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
        return c;
    }

    public void putData(Connection c, String[] arr){
        String psql0 = "GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA PUBLIC TO root;";
        System.out.println(psql0);
        String psql = "INSERT INTO owners (telephone, city, address, last_name, first_name, id) VALUES (\'"+arr[3]+"\', \'"+arr[1]+"\', \'"+arr[0]+"\', \'"+arr[2]+"\', \'"+arr[5]+"\', \'"+arr[4]+"\');";
        try {
            Statement stm = null;
            Statement stm0 = null;

            stm0 = c.createStatement();
            stm = c.createStatement();

            stm0.execute(psql0);
            //System.out.println(psql0);

            stm.execute(psql);
            System.out.println(psql);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
