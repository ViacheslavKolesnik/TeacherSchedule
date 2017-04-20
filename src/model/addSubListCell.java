package model;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by Slavian on 4/15/2017.
 */
public class addSubListCell extends ListCell<SubjectTime> {
    @FXML
    private ChoiceBox daysChoice;
    @FXML
    private TextField dayPlaceText;
    @FXML
    private ChoiceBox weekChoice;
    @FXML
    AnchorPane anPaneAddSub;

    private ChangeListener<Boolean> editCommitHandlerDays;
    private ChangeListener<Boolean> editCommitHandlerDayPlace;
    private ChangeListener<Boolean> editCommitHandlerWeek;


    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/addSubListCell.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }


        daysChoice.getItems().add("Понедельник");
        daysChoice.getItems().add("Вторник");
        daysChoice.getItems().add("Среда");
        daysChoice.getItems().add("Четверг");
        daysChoice.getItems().add("Пятница");
        daysChoice.getItems().add("Суббота");
        daysChoice.getItems().add("Воскресенье");

        weekChoice.getItems().add("Первая");
        weekChoice.getItems().add("Вторая");
        weekChoice.getItems().add("Каждая");
    }

    public addSubListCell(){
        daysChoice.setValue("Понедельник");
        weekChoice.setValue("Первая");
    }


    @Override
    protected void updateItem(SubjectTime subTime, boolean empty) {
        super.updateItem(subTime, empty);
        if (daysChoice != null) {
            try {
                daysChoice.focusedProperty().removeListener(editCommitHandlerDays);
            }catch(Exception e){}
        }
        if (dayPlaceText != null) {
            try{
                dayPlaceText.focusedProperty().removeListener(editCommitHandlerDayPlace);
            }catch(Exception e){}
        }
        if (weekChoice != null) {
            try{
                weekChoice.focusedProperty().removeListener(editCommitHandlerWeek);
            }catch(Exception e){}
        }
        if (subTime == null) {
            setText(null);
            setGraphic(null);
            return;
        }


        if (daysChoice == null) {
            daysChoice = new ChoiceBox();
        }
        editCommitHandlerDays = (observable, oldValue, newValue) -> {
            try {
                subTime.setWeekDay(daysChoice.getValue().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        daysChoice.focusedProperty().addListener(editCommitHandlerDays);

        if (dayPlaceText == null) {
            dayPlaceText = new TextField();
        }
        editCommitHandlerDayPlace = (observable, oldValue, newValue) -> {
            try {
                subTime.setTimePlace(dayPlaceText.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        dayPlaceText.focusedProperty().addListener(editCommitHandlerDayPlace);

        if (weekChoice == null) {
            weekChoice = new ChoiceBox();
        }
        editCommitHandlerWeek = (observable, oldValue, newValue) -> {
            try {
                subTime.setNumOfWeek(weekChoice.getValue().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        weekChoice.focusedProperty().addListener(editCommitHandlerWeek);

        daysChoice.setValue(subTime.getWeekDay());
        dayPlaceText.setText(subTime.getTimePlace());
        weekChoice.setValue(subTime.getNumOfWeek());


        setText(null);
        setGraphic(anPaneAddSub);
    }
}