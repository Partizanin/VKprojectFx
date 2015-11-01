package partizanin;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import partizanin.controller.AccountEditDialogController;
import partizanin.controller.AccountOverviewController;
import partizanin.model.Account;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    public Stage getPrimaryStage() {
        return primaryStage;
    }

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

    public boolean showAccountEditDialog(Account account) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("sample/EditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Account");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            AccountEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            if (account.getId() == null) {
                account.setId(accountsData.size()  + 1);
            }
            controller.setAccount(account);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Account getNextAccount(Integer id,String secondLogin) {

        Account result = new Account();
        boolean update = false;

        if (id == null) {
            update = true;
        }
        for (Account account : accountsData) {
            if (!update && Objects.equals(account.getId().getValue(), id)) {
                account.setSecondLogin(secondLogin);
                update = true;
            }
            if (account.getSecondLogin().getValue().length() < 2) {

                result = account;
                if (update) {
                    break;
                }
            }
        }
        return result;
    }
}
