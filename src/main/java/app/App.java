package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import player.manager.DatabaseManager;

import java.io.IOException;
import java.sql.SQLException;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try(DatabaseManager dbm = new DatabaseManager()) {
            dbm.createDatabase();
        } catch(SQLException e) {
            System.out.println("Database  was already created");
        }

        try(DatabaseManager dbm = new DatabaseManager()) {
            dbm.createUser();
        } catch(SQLException e) {
            System.out.println("Table player was already created");
        }

        FXMLLoader loader = new FXMLLoader();
        stage.getIcons().add(new Image("/images/icon.png"));
        stage.setResizable(false);
        loader.setLocation(this.getClass().getResource("/fxml/mainWindow.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);

        stage.setScene(scene);
        stage.setTitle("Chess game");
        stage.show();
    }
    /**
     * Method for changing scene
     * @param old   current pane
     * @param name  name of new window from /fxml/
     */
    public static void changeScene(Pane old, String name) {
        Parent root;
        try {
            root = FXMLLoader.load(App.class.getResource("/fxml/" + name + ".fxml"));
            Stage stage = (Stage) old.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
