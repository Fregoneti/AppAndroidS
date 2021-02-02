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

    public Question(
    ) {
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

    /*public int getMontht() {
        return month;
    }

    public int getDay() {
        return day;
    }
    public int getYear() {
        return year;
    }

    public String getDate() {
        return date;
    }

     */

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
                this.date = String.valueOf(date);
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
