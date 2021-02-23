package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Question extends RealmObject {
    // Debe existir una clave primaria para poder actualizar objetos
    @PrimaryKey
    private String id;

   // Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    private String name;
    private String mail;
    //private int day;
    // int month;
   // private int year;
    private String date;
    private String tittle;
    boolean valid;
    private String colour;
    private String question;
    private String image;
    private String mode;

    public Question(

    ) {
        this.image = "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";
    }
    public void setMode (String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public String getTittle() {
        return tittle;
    }

    public String getColour() {
        return colour;
    }
    public String getMail() {
        return mail;
    }



    public boolean setDate(String value){
        String format="dd/MM/yyyy";
        boolean result = false;
        Date date = null;
        try {
            //Parseo String-> SimpleDateFormat
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);
            if (value.equals(sdf.format(date))) {
                result = true;
                this.date = value;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
            result = false;
        }
        return result;
    }


    public boolean setTittle(String tittle) {
         valid=false;
        if(tittle!=""){
            this.tittle=tittle;

            valid=true;

        }else{
            valid=false;
            System.out.println("Error al introducir titulo");
        }
        return valid;
    }



    public boolean setColour(String colour) {
        valid = false;
        if (colour != "") {
            this.colour = colour;

            valid = true;

        } else {
            valid = false;
            System.out.println("Error al introducir color");
        }
        return valid;
    }


        public boolean setMail (String mail){
            //Matcher mather = pattern.matcher(mail);
            valid = false;
            if (mail.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$") == true) {
                System.out.println("El email ingresado es válido.");
                valid = true;
                this.mail = mail;
            } else {
                System.out.println("El email ingresado es inválido.");
            }



            return valid;
        }

    public String getImage() {
        return image;
    }


    public void setImage(String image) {
        this.image = image;
    }
/*
    public static boolean validateDate(String date){
            try {
                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
                formatoFecha.setLenient(false);
                formatoFecha.parse(date);
            } catch (ParseException e) {
                return false;
            }
            return true;
        }


 */

        public boolean setName (String name){
             valid = false;
             String s="";
            if (!(name.equals(s))) {
                this.name = name;
                valid = true;
            } else {
                valid = false;
                System.out.println("Error al introducir nombre");
            }
            return valid;
        }

    public String getQuestion() {
        return question;
    }

    /*
        public boolean setDay(int day) {
            valid=false;
            if(day<31 && day>=1){
                this.day = day;
                valid=true;
            }
            return  valid;

        }
        public boolean setMonth(int month) {
            valid=false;
            if(month<=12 && month>=1){
                this.month = month;
                valid=true;
            }
            return  valid;

        }

     */
    public boolean setQuestion(String question) {
        valid = false;
        if (question != "") {
            this.question = question;

            valid = true;

        } else {
            valid = false;
            System.out.println("Error al introducir la pregunta");
        }
        return valid;
    }

    public String getDate() {

        return date;
    }
}
