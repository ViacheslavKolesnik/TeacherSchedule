package controller.fromDateWindow;

import controller.mainWindow.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Slavian on 4/17/2017.
 */
public class fromDate extends Application {
    static private Stage thisStage;
    public static MainController fDmainController;
    public static boolean isChange;

    public fromDate(MainController mainController, boolean isChange){
        fDmainController=mainController;
        this.isChange=isChange;
    }

    @Override
    public void start(Stage newStage) throws Exception{
        thisStage=newStage;
        Parent root = FXMLLoader.load(getClass().getResource("../../view/fromDate.fxml"));
        newStage.setTitle("Начало семестра");
        newStage.setScene(new Scene(root, 439, 333));
        newStage.setResizable(false);
        newStage.show();
    }

    public static void closeFromWindow(){
        try {
            thisStage.close();
        }catch(Exception e){}
    }
}