/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 *
 * @author kmysl
 */
public class DataValidation {
    public static final int maxValue = 100;
    
    public static final int minValue = 1;
    
    public static final int maxValueCities = 1000;
    
     public static boolean isWarning(int value, int minValue, int maxValue){
         switch(value){
            case -1: showCommunicat("Nie wpisano wymaganych danych!", "Uzupełnij pola tekstowe danymi!"); 
            return true;
            case -2: showCommunicat("Wprowadzono błędne dane!", "Wpisz dane liczbowe!");
            return true;
            case -3: showCommunicat("Wprowadzono błędne dane!", "Wpisz dane liczbowe! Miasta w zakresie od " + minValue + " do " + maxValue + "!");
            return true;
        }
         return false;
    }
    
    public static void showCommunicat(String komunikat1, String komunikat2){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ostrzeżenie!");
        alert.setHeaderText(komunikat1);
        alert.setContentText(komunikat2);

        alert.showAndWait();
    }
    
    public static int validation(TextField text, int minimum, int maximum){
        String value = text.getText();
        int number;
        if(value.isEmpty() || value == null){
            return -1;
        }
        try{
            number = Integer.parseInt(value);
            
        }
        catch(NumberFormatException e){
            return -2;
        }
        if(number<minimum || number > maximum){
            return -3;
        }
        else{
            return number;
        }
        
    }
}
