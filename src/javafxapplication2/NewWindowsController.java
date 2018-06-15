
package javafxapplication2;

import java.net.URL;
import java.util.LinkedList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class NewWindowsController implements Initializable {

    @FXML
    private TextField liczbaMiast;

    @FXML
    private TextField od;

    @FXML
    private TextField dopoki;
    

    
    FXMLDocumentController conn1 =  JavaFXApplication2.okienko1.getController();

    @FXML
    void handleButtonOK(ActionEvent event) {
        Random random = new Random();    
        int iloscMiast = DataValidation.validation(liczbaMiast,DataValidation.minValue,DataValidation.maxValueCities);
        int liczbaOd = DataValidation.validation(od,DataValidation.minValue, DataValidation.maxValue);
        int liczbaDopoki = DataValidation.validation(dopoki,DataValidation.minValue, DataValidation.maxValue);
        if(DataValidation.isWarning(iloscMiast, DataValidation.minValue, DataValidation.maxValueCities) || DataValidation.isWarning(liczbaOd,DataValidation.minValue,DataValidation.maxValue) || DataValidation.isWarning(liczbaDopoki, DataValidation.minValue, DataValidation.maxValue) ){
            return;
        }
        if(liczbaOd>liczbaDopoki){
            DataValidation.showCommunicat("Wpisano błędne dane!", "Wartość minimalna nie może być większa od maksymalnej!");
            return;
        }
        LinkedList<Coordinates> lista = new LinkedList<Coordinates>();
        for(int i = 0; i<iloscMiast ;i++){
            double x = (double)random.nextInt(500);
            double y = (double)random.nextInt(550);
            int wartosc = ThreadLocalRandom.current().nextInt(liczbaOd,liczbaDopoki+1);
            lista.add(new Coordinates(x,y,wartosc,conn1.getNastepnyNumer()));
        }
        conn1.drawFromList(lista);
        conn1.updateLinkedList(lista);
    }
    
    @FXML
    void handleButtonReset(ActionEvent event){
        liczbaMiast.clear();
        od.clear();
        dopoki.clear();      
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
