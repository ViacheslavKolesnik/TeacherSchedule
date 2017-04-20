package controller.fromDateWindow;

import controller.addSubject.addSub;
import controller.mainWindow.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import model.Subject;
import model.SubjectTime;

import java.util.ArrayList;

/**
 * Created by Slavian on 4/17/2017.
 */
public class fromDateController {
    @FXML
    DatePicker fromSubDate;
    @FXML
    Button setFDateButton;
    @FXML
    public void addClick(){
        if(fromSubDate.getValue()!=null) {
            Main.fromDate = fromSubDate.getValue();
            if(!fromDate.isChange) {
                try {
                    new addSub(fromDate.fDmainController, new Subject(), false, 0).start(new Stage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            fromDate.closeFromWindow();
        }
    }
}
