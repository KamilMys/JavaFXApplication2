package javafxapplication2;

import java.io.IOException;
import javafx.scene.canvas.Canvas;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable {
    
    @FXML private Canvas img = new Canvas();
    @FXML private FlowPane flowPane;
    @FXML private BarChart<?, ?> timeChart;
    @FXML private CategoryAxis algorithm;
    @FXML private NumberAxis time;
    @FXML private BarChart<?, ?> roadChart;
    @FXML private CategoryAxis algorithm2;
    @FXML private NumberAxis road;
    @FXML private CheckBox brutal;
    @FXML private CheckBox greedy;
    @FXML private CheckBox genetic;
    @FXML private TextField wartosc;

   
    public GraphicsContext gc;
        
    private LinkedList<Coordinates> linkedList = new LinkedList<Coordinates> ();
     
    @FXML private void drawCanvas(MouseEvent event) {
        int value = DataValidation.validation(wartosc, DataValidation.minValue, DataValidation.maxValue);
        if(DataValidation.isWarning(value,DataValidation.minValue, DataValidation.maxValue)){
            return;
        }
        Random random = new Random();
        int r, g, b;
        r = random.nextInt(256);
        g = random.nextInt(256);
        b = random.nextInt(256);
        gc.setFill(Color.rgb(r,g,b));
        double x, y;
        x = event.getX()-15;
        y = event.getY()-15;
        gc.fillOval(x,y,30,30);
        linkedList.addLast(new Coordinates(x,y,value,getNastepnyNumer()));
       // System.out.println("Coordinate X -> " + x);
       // System.out.println("Coordinate Y -> " + y); 
        
    }
    
    public long getNastepnyNumer(){
        long numer = -1;
        for(Coordinates miasto: linkedList){
            if(miasto.getNumer() > numer){
                numer = miasto.getNumer();
            }
        }
        return numer+1;
    }
    
    public void drawFromList(LinkedList<Coordinates> lista){
        int i = 0;
        for(Coordinates item: lista){
        Random random = new Random();
        int r, g, b;
        r = random.nextInt(256);
        g = random.nextInt(256);
        b = random.nextInt(256);
        gc.setFill(Color.rgb(r,g,b));
        gc.fillOval(item.getX()-15,item.getY()-15,30,30);
        System.out.println(i+ " Coordinate X -> " + item.getX());
        System.out.println(i +" Coordinate Y -> " + item.getY()); 
        i++;
        }

    }
    
        public void drawLines(ArrayList<Coordinates> linkedList){
        gc.beginPath();
        gc.moveTo(linkedList.get(0).getX()+15, linkedList.get(0).getY()+15);
        for(Coordinates item: linkedList){
        gc.setLineWidth(1);
        gc.setStroke(Color.BLUE);
        gc.lineTo(item.getX()+15,item.getY()+15); 
        }
        gc.lineTo(linkedList.get(0).getX()+15, linkedList.get(0).getY()+15);
        gc.closePath();
        gc.stroke();

    }

    public void updateLinkedList(LinkedList<Coordinates> lista){
    linkedList.addAll(lista);
}
    
    @FXML
    private void handleButtonRun(ActionEvent event) {
        if(linkedList.isEmpty()){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setHeaderText("Nie wprowadzono żadnych miast!");
            alert.setContentText("Naciśnij OK i dodaj miasta klikając w polu oznaczonym ramką lewym przyciskiem myszy!");
            alert.showAndWait().ifPresent(rs -> {
             if (rs == ButtonType.OK) {
            System.out.println("Pressed OK.");
                }
            }
            );
            return;
        }
        System.out.println("It's run button");
        System.out.println("List of coordinates:");
        for(Coordinates item:linkedList){
            System.out.println("X: " + item.getX() + " Y:" + item.getY());
        }
        isChecked();
    }
    
    @FXML
    private void handleButtonClear(ActionEvent event){
        System.out.println("It's clear button");
        gc.clearRect(0, 0, 500, 550);
        linkedList.clear();
        timeChart.getData().clear();
        roadChart.getData().clear();
    }
    

    private void isChecked(){
        boolean isBrutal = brutal.isSelected();
        boolean isGreedy = greedy.isSelected();
        boolean isGenetic = genetic.isSelected();
        if(!isBrutal && !isGreedy && !isGenetic){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setHeaderText("Nie wybrano żadnego algorytmu!");
            alert.setContentText("Wybierz 1 lub więcej algorytmów zaznaczając checkbox!");
            alert.showAndWait().ifPresent(rs -> {
             if (rs == ButtonType.OK) {
            System.out.println("Pressed OK.");
                }
            }
            );
            return;
        }
        if(isBrutal){
            System.out.println("You choose brutal algorithm!");
        }
        if(isGreedy){
        GreedyAlgorithm greedy = new GreedyAlgorithm(linkedList);
        ArrayList <Coordinates> list;
        list = greedy.greedyMethod();
        drawLines(list);
        XYChart.Series set1 = new XYChart.Series<>();
        set1.getData().add(new XYChart.Data<>("Zachłanny", greedy.getTime()));
        timeChart.getData().addAll(set1);
        XYChart.Series set2 = new XYChart.Series<>();
        set2.getData().add(new XYChart.Data<>("Zachłanny", greedy.getRoad()));   
        roadChart.getData().addAll(set2);
        }
        if(isGenetic){
            GenethicAlgorithm genetic = new GenethicAlgorithm(linkedList);
        ArrayList <Coordinates> list;
        list = genetic.uruchomAlgorytm();
        drawLines(list);
        XYChart.Series set1 = new XYChart.Series<>();
        set1.getData().add(new XYChart.Data<>("Genetyczny", genetic.getCzas()));
        timeChart.getData().addAll(set1);
        XYChart.Series set2 = new XYChart.Series<>();
        set2.getData().add(new XYChart.Data<>("Genetyczny", genetic.getNajlepszaTrasa()));   
        roadChart.getData().addAll(set2);
        }
    }
     
    @FXML 
    private void handleButtonRand(ActionEvent event){
 try {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("NewWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 350, 200);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.sizeToScene();
        stage.setTitle("Losuj miasta");
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(flowPane.getScene().getWindow());
        stage.show();
        
    } catch (IOException e) {
      e.printStackTrace();
    }
        System.out.println("Koniec wywołania okna");
        //drawFromList();
    }
    

            
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      //  img = new Canvas();
        

        gc = img.getGraphicsContext2D();
        FlowPane pane = flowPane;
        pane.setBorder(new Border(new BorderStroke(Color.BLACK, 
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
       // linkedList = new LinkedList<Coordinates>();
    }    
    
}
