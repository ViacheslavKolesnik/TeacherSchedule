package controller.addSubject;

import controller.mainWindow.Main;
import controller.mainWindow.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Subject;
import model.SubjectTime;
import model.addSubListCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Slavian on 4/15/2017.
 */
public class addSubController implements Initializable{
    @FXML
    TextField addSubNameText;
    @FXML
    ListView<SubjectTime> addDaysList;
    @FXML
    DatePicker addFinalDate;
    @FXML
    Button addSubButton;
    @FXML
    ContextMenu dayListView;
    @FXML
    MenuItem addDayListItem;
    @FXML
    MenuItem delDayListItem;

    private ObservableList<SubjectTime> subsObservableList;

    private MainController asCmainController;

    public addSubController(){
        subsObservableList = FXCollections.observableArrayList();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        asCmainController=addSub.aSmainController;
        Subject selSub = addSub.selectedSub;
        try {
            addSubNameText.setText(selSub.getName());
            addFinalDate.setValue(selSub.getFinalDate());
            for (SubjectTime s : selSub.getSubDays()) {
                subsObservableList.add(s);
            }
        }catch(Exception e){}

        addDaysList.setItems(subsObservableList);
        addDaysList.setCellFactory(listView->new addSubListCell());
        /*addDaysList.setCellFactory(new Callback<ListView<SubjectTime>, ListCell<SubjectTime>>()
        {
            @Override
            public ListCell<SubjectTime> call(ListView<SubjectTime> listView)
            {
                return new addSubListCell();
            }
        });*/
    }

    @FXML
    private void addDay(){
        /*ObservableList<SubjectTime> newDaysList = FXCollections.observableArrayList();
        for(int i=0;i< subsObservableList.size();i++){
            newDaysList.add(subsObservableList.get(i));
        }
        subsObservableList.clear();
        newDaysList.add(new SubjectTime("Понедельник", "", "Числитель"));
        for(int i=0;i< newDaysList.size();i++){
            subsObservableList.add(newDaysList.get(i));
        }
        addDaysList.setItems(subsObservableList);*/
        subsObservableList.add(new SubjectTime("Понедельник","","Числитель"));

    }
    @FXML
    private void delDay(){

    }
    @FXML
    private void saveSub(){
        /*System.out.println("From: "+ Main.fromDate);
        ObservableList<SubjectTime> newDaysList = FXCollections.observableArrayList();

        System.out.println(addSubNameText.getText());

        for (int i = 0; i < addDaysList.getItems().size(); i++) {
            newDaysList.add((SubjectTime)addDaysList.getItems().get(i));
            System.out.println(newDaysList.get(i).getWeekDay()+
                    " "+newDaysList.get(i).getTimePlace()+
                    " "+newDaysList.get(i).getNumOfWeek());
        }
        System.out.println(addFinalDate.getValue());*/
        Subject newSub = new Subject();
        newSub.setName(addSubNameText.getText());
        newSub.setFinalDate(addFinalDate.getValue());
        ArrayList<SubjectTime> subs= new ArrayList<>();
        for (int i = 0; i < subsObservableList.size(); i++) {
            subs.add(new SubjectTime(subsObservableList.get(i).getWeekDay(),
                    subsObservableList.get(i).getTimePlace(),
                    subsObservableList.get(i).getNumOfWeek()));

        }
        newSub.setSubDays(subs);
        if(!addSub.isEdit)
            asCmainController.subsObsList.add(newSub);
        else {
            asCmainController.subsObsList.remove(addSub.index);
            asCmainController.subsObsList.add(addSub.index,newSub);
        }
        addSub.closeAddSubWindow();
    }
}
