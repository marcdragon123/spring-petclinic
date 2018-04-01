package org.springframework.samples.petclinic.consistency;

public class Driver {
    public static void main(String[] args) {

        DataExport dSQL = new DataExport();
        dSQL.getData("jdbc:mysql://localhost:3306/petclinic", "root", "petclinic");

        DataExport dPSQL = new DataExport();
        dPSQL.getData("jdbc:postgresql://localhost/mydb","mydb","potuto");


    }
}
