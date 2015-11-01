package partizanin;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import partizanin.controller.AccountOverviewController;
import partizanin.model.Account;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList<Account> accountsData = FXCollections.observableArrayList();

    public Main() {
        accountsData.add(new Account("vasa", "pupkin", false, true, "pupkin", 1));
        accountsData.add(new Account("igor", "vasechkin", false, true, "vasechkin", 2));
        accountsData.add(new Account("vasa", "pupkin", false, true, "pupkin", 3));
        accountsData.add(new Account("vasa", "pupkin", false, true, "pupkin", 4));
        accountsData.add(new Account("vasa", "pupkin", false, true, "pupkin", 5));
        accountsData.add(new Account("vasa", "pupkin", false, true, "pupkin", 6));
        accountsData.add(new Account("vasa", "pupkin", false, true, "pupkin", 7));
        accountsData.add(new Account("vasa", "pupkin", false, true, "pupkin", 8));
        accountsData.add(new Account("vasa", "pupkin", false, true, "pupkin", 9));
        accountsData.add(new Account("vasa", "pupkin", false, true, "pupkin", 10));
    }

    public ObservableList<Account> getAccountData() {
        return accountsData;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("VkApp");

        initRootLayout();
        showAccountOverview();

    }

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("sample/RootLayout.fxml"));
            rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showAccountOverview() {
        try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("sample/sample.fxml"));
            AnchorPane accountOverview = loader.load();

            rootLayout.setCenter(accountOverview);

            AccountOverviewController controller = loader.getController();

            controller.setMain(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
