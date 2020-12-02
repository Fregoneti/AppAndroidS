package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Question {

    Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    private String name;
    private String mail;
    private int day;
    private int month;
    private int year;
    private String date;
    private String tittle;
    boolean valid;
    private String colour;
    private String question;

    public Question() {
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

    public int getMontht() {
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

    public void setDate(int day, int month, int year){

        date=day+"/"+month+"/"+year;
        boolean res;
     /*  res=validarFecha(date);
        if(res==true){
            System.out.println("La fecha es valida");
            this.date=date;
        }else
            System.out.println("La fecha no es valida");


      */
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
            Matcher mather = pattern.matcher(mail);
            valid = false;
            if (mather.find() == true) {
                System.out.println("El email ingresado es válido.");
                valid = true;
                this.mail = mail;
            } else {
                System.out.println("El email ingresado es inválido.");
            }

            return valid;
        }


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

}
