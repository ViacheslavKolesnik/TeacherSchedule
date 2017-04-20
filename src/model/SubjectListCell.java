package model;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by Slavian on 4/17/2017.
 */
public class SubjectListCell extends ListCell<Subject> {
    @FXML
    private Label subTextArea;
    @FXML
    private Label subNameTextField;
    @FXML
    AnchorPane subListPane;

    private ChangeListener<Boolean> editCommitHandlerArea;
    private ChangeListener<Boolean> editCommitHandlerField;


    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/subjectListCell.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void updateItem(Subject subject, boolean empty) {
        super.updateItem(subject, empty);
        if (subTextArea != null) {
            try {
                subTextArea.focusedProperty().removeListener(editCommitHandlerArea);
            }catch(Exception e){}
        }
        if (subNameTextField != null) {
            try {
                subNameTextField.focusedProperty().removeListener(editCommitHandlerField);
            }catch(Exception e){}
        }
        if (subject == null) {
            setText(null);
            setGraphic(null);
            return;
        }


        /*if (subTextArea == null) {
            subTextArea = new Label();
        }
        editCommitHandlerArea = (observable, oldValue, newValue) -> {
            try {
                subject.setSubDays(subTextArea.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        subTextArea.focusedProperty().addListener(editCommitHandlerArea);*/

        /*if (subNameTextField == null) {
            subNameTextField = new Label();
        }
        editCommitHandlerField = (observable, oldValue, newValue) -> {
            try {
                subject.setSubDays(subNameTextField.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        subNameTextField.focusedProperty().addListener(editCommitHandlerField);*/


        String s="";
        for (int i = 0; i < subject.getSubDays().size(); i++) {
            s+=subject.getSubDays().get(i).getWeekDay()+" "+
                    subject.getSubDays().get(i).getTimePlace()+" "+
                    subject.getSubDays().get(i).getNumOfWeek()+"\n";
        }
        //s+="\n";
        int size=23;
        subTextArea.setMinHeight(size*subject.getSubDays().size());
        subTextArea.setText(s);
        subNameTextField.setText(subject.getName());


        setText(null);
        setGraphic(subListPane);
    }
}
