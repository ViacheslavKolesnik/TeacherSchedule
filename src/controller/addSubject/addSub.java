package controller.addSubject;

import controller.mainWindow.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Subject;
import model.SubjectTime;

import java.util.ArrayList;

/**
 * Created by Slavian on 4/16/2017.
 */
public class addSub extends Application{
    private static Stage thisStage;
    public static MainController aSmainController;
    public static Subject selectedSub;
    public static boolean isEdit;
    public static int index;

    public addSub(MainController mainController, Subject sub, boolean isEd, int id){
        aSmainController=mainController;
        selectedSub=sub;
        isEdit=isEd;
        index=id;
    }

    @Override
    public void start(Stage newStage) throws Exception{
        thisStage=newStage;
        Parent root = FXMLLoader.load(getClass().getResource("../../view/addSubject.fxml"));
        newStage.setTitle("Новый предмет");
        newStage.setScene(new Scene(root, 495, 519));
        newStage.setResizable(false);
        newStage.show();
    }
    public static void closeAddSubWindow(){
        try {
            thisStage.close();
        }catch(Exception e){}
        aSmainController.updateTable();
    }
}
