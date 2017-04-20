package controller.mainWindow;

import controller.addSubject.addSub;
import controller.fromDateWindow.fromDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import model.Subject;
import model.SubjectListCell;
import model.SubjectTime;

import java.util.ArrayList;

public class MainController {
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
    public MainController mainController;
    private static Integer clicked=null;

    public MainController(){
        subsObsList = FXCollections.observableArrayList();
        ArrayList<SubjectTime> tList = new ArrayList<>();
        tList.add(new SubjectTime("Вторник","У1-300 10:25","Знаменатель"));
        tList.add(new SubjectTime("Понедельник","У2-410 8:30","Числитель"));

    }

    @FXML
    void initialize(){
        mainController=this;
        subjectsListView.setItems(subsObsList);
        subjectsListView.setCellFactory(listView->new SubjectListCell());
        /*menuItemEdit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                subjectsListView.getSelectionModel().getSelectedIndex();
            }
        });*/
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
