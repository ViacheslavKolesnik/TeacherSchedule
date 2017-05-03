package controller.mainWindow;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import controller.addSubject.addSub;
import controller.fromDateWindow.fromDate;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Subject;
import model.SubjectListCell;
import model.SubjectRow;
import model.SubjectTime;


import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class MainController {
    @FXML
    TableColumn<SubjectRow, String> ColumnWeek1;
    @FXML
    TableColumn<SubjectRow, String> ColumnWeek2;
    @FXML
    TableColumn<SubjectRow, String> ColumnWeek3;
    @FXML
    TableColumn<SubjectRow, String> ColumnWeek4;
    @FXML
    TableColumn<SubjectRow, String> ColumnWeek5;
    @FXML
    TableColumn<SubjectRow, String> ColumnWeek6;
    @FXML
    TableColumn<SubjectRow, String> ColumnWeek7;
    @FXML
    TableColumn<SubjectRow, String> ColumnWeek8;
    @FXML
    TableColumn<SubjectRow, String> ColumnWeek9;
    @FXML
    TableColumn<SubjectRow, String> ColumnWeek10;
    @FXML
    TableColumn<SubjectRow, String> ColumnWeek11;
    @FXML
    TableColumn<SubjectRow, String> ColumnWeek12;
    @FXML
    TableColumn<SubjectRow, String> ColumnWeek13;
    @FXML
    TableColumn<SubjectRow, String> ColumnWeek14;
    @FXML
    TableColumn<SubjectRow, String> ColumnWeek15;
    @FXML
    TableColumn<SubjectRow, String> ColumnWeek16;
    @FXML
    MenuItem loadMenuItem;
    @FXML
    MenuItem saveMenuItem;
    @FXML
    MenuItem exportMenuItem;
    @FXML
    MenuItem clearMenuItem;
    @FXML
    MenuItem changeDateMenuItem;
    @FXML
    TableView mainTable;
    @FXML
    ContextMenu contextMenu;
    @FXML
    MenuItem menuItemAdd;
    @FXML
    MenuItem menuItemEdit;
    @FXML
    MenuItem menuItemDelete;
    @FXML
    ListView<Subject> subjectsListView;

    public ObservableList<Subject> subsObsList;
    public ObservableList<SubjectRow> subjTable;

    public MainController mainController;
    private static Integer clicked=null;



    public MainController(){
        subsObsList=FXCollections.observableArrayList();
        try {
            ObjectMapper obj=new ObjectMapper();
            Main.fromDate=LocalDate.parse(obj.readValue(new File("fromDate.json"),String.class));
            ArrayList<Subject> sus=obj.readValue(new File("subjects.json"),
                    new TypeReference<ArrayList<Subject>>(){});
            System.out.println("ArrayList subjects size"+sus.size());
            subsObsList =FXCollections.observableArrayList(sus);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        subjTable=FXCollections.observableArrayList();

    }

    public LocalDate calcDate(LocalDate startDate1, String day){

        for (int i = 0; i < 6; i++) {
            System.out.println(startDate1.getDayOfWeek());
            switch (startDate1.getDayOfWeek()){
                case MONDAY:if(day.contains("Понедельник"))return startDate1;
                    break;
                case TUESDAY:if(day.contains("Вторник"))return startDate1;
                    break;
                case WEDNESDAY:if(day.contains("Среда"))return startDate1;
                    break;
                case THURSDAY:if(day.contains("Четверг"))return startDate1;
                    break;
                case FRIDAY:if(day.contains("Пятница"))return startDate1;
                    break;
                case SATURDAY:if(day.contains("Суббота"))return startDate1;
                    break;
            }
            startDate1=startDate1.plusDays(1);
        }
        return LocalDate.parse("2000-01-01");
    }

    public void updateTable(){
        subjTable.clear();
        String[][] tableStr=new String[subsObsList.size()][16];
        System.out.println("subsObsList.size()= "+subsObsList.size());
        for(Subject sub: subsObsList){
            Period period = Period.between(Main.fromDate,sub.getFinalDate());
            System.out.println("days : "+ period.getDays());
            System.out.println("month : "+ period.getMonths());
            System.out.println("years : "+ period.getYears());
            int countOfWeeks= period.getDays()/7 + (period.getDays()%7==0?0:1) + period.getMonths() * 4 +period.getYears() * 52;
            System.out.println(Main.fromDate+" - "+sub.getFinalDate()+"="+Period.between(Main.fromDate,sub.getFinalDate()).getDays());
            System.out.println("countOfWeeks= "+countOfWeeks);
            for (int i = 0; i < countOfWeeks; i++) {
                tableStr[subsObsList.indexOf(sub)][i]="";

                for(SubjectTime subTime: sub.getSubDays()){
                    if(((i+1)%2==0 && subTime.getNumOfWeek().contains("Вторая"))||
                            ((i+1)%2!=0 && subTime.getNumOfWeek().contains("Числитель"))||
                            subTime.getNumOfWeek().contains("Каждая")){
                        tableStr[subsObsList.indexOf(sub)][i]+=calcDate(Main.fromDate.plusWeeks(i),subTime.getWeekDay())+"\n";
                    }
                }
            }
        }
        for (int i = 0; i < tableStr.length; i++) {
            System.out.println(i);
            subjTable.add(new SubjectRow(tableStr[i]));
        }
        System.out.println(subjTable.size()+"---");
        mainTable.setItems(subjTable);

        try {
            writeToJSON();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Subject> ArrayListFromObservableList(ObservableList<Subject> t){
        ArrayList<Subject> s=new ArrayList<>();
        for(Subject q: t)s.add(q);
        return s;
    }

    public void writeToJSON() throws IOException {
        ObjectMapper s = new ObjectMapper();
        s.writeValue(new File("subjects.json"), ArrayListFromObservableList(subsObsList));
        String fromD=Main.fromDate.toString();
        s.writeValue(new File("fromDate.json"), fromD);
    }



    @FXML
    void initialize(){
        mainController=this;
        subjectsListView.setItems(subsObsList);
        subjectsListView.setCellFactory(listView->new SubjectListCell());

        mainTable.setFixedCellSize(157.0);
        ColumnWeek1.setCellValueFactory(new PropertyValueFactory<>("week1"));
        ColumnWeek2.setCellValueFactory(new PropertyValueFactory<SubjectRow, String>("week2"));
        ColumnWeek3.setCellValueFactory(new PropertyValueFactory<SubjectRow, String>("week3"));
        ColumnWeek4.setCellValueFactory(new PropertyValueFactory<SubjectRow, String>("week4"));
        ColumnWeek5.setCellValueFactory(new PropertyValueFactory<SubjectRow, String>("week5"));
        ColumnWeek6.setCellValueFactory(new PropertyValueFactory<SubjectRow, String>("week6"));
        ColumnWeek7.setCellValueFactory(new PropertyValueFactory<SubjectRow, String>("week7"));
        ColumnWeek8.setCellValueFactory(new PropertyValueFactory<SubjectRow, String>("week8"));
        ColumnWeek9.setCellValueFactory(new PropertyValueFactory<SubjectRow, String>("week9"));
        ColumnWeek10.setCellValueFactory(new PropertyValueFactory<SubjectRow, String>("week10"));
        ColumnWeek11.setCellValueFactory(new PropertyValueFactory<SubjectRow, String>("week11"));
        ColumnWeek12.setCellValueFactory(new PropertyValueFactory<SubjectRow, String>("week12"));
        ColumnWeek13.setCellValueFactory(new PropertyValueFactory<SubjectRow, String>("week13"));
        ColumnWeek14.setCellValueFactory(new PropertyValueFactory<SubjectRow, String>("week14"));
        ColumnWeek15.setCellValueFactory(new PropertyValueFactory<SubjectRow, String>("week15"));
        ColumnWeek16.setCellValueFactory(new PropertyValueFactory<SubjectRow, String>("week16"));
        updateTable();
    }

    @FXML
    public void addClick(){
        if(Main.fromDate==null){
            try {
                new fromDate(mainController,false).start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else {
            try {
                new addSub(mainController,new Subject(),false, 0).start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    @FXML
    public void editClick(){
        int i=subjectsListView.getSelectionModel().getSelectedIndex();
        if(Main.fromDate==null){
            try {
                new fromDate(mainController,false).start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else {
            if(subjectsListView.getItems().size()!=0) {
                try {
                    new addSub(mainController, subsObsList.get(i),true, i).start(new Stage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    @FXML
    public void deleteClick(){
        if(subsObsList.size()!=0){
            subsObsList.remove(subjectsListView.getSelectionModel().getSelectedIndex());
        }
    }
    @FXML
    public void clearClick(){
        subsObsList.clear();
    }
    @FXML
    public void changeDateClick(){
        try {
            new fromDate(this,true).start(new Stage());
        }catch(Exception e){}
    }
}




