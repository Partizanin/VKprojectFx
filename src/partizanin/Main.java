package partizanin;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import partizanin.controller.AccountEditDialogController;
import partizanin.controller.AccountOverviewController;
import partizanin.model.Account;
import partizanin.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Main extends Application {
    /*todo add file chooser for load new accounts*/
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private Stage primaryStage;
    private BorderPane rootLayout;
    private FileUtils fileUtils = new FileUtils();
    private AccountOverviewController controller;

    private ObservableList<Account> accountsData = FXCollections.observableArrayList();

    public Main() {
        loadAccountToTable();
    }

    private void loadAccountToTable() {
        accountsData.clear();
        for (Account account : fileUtils.getAccounts()) {

            if (account.getId().getValue() == 0 && accountsData.size() != 0) {
                account.setId(accountsData.size());
            }
            accountsData.add(account);
        }
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

    public void updateFile() {
        fileUtils.updateFile(accountsData);
    }

    public void updateAccounts(Integer id, String secondLogin) {
        accountsData.stream().filter(account -> Objects.equals(account.getId().getValue(), id)).forEach(account -> {
            account.setSecondLogin(secondLogin);
            account.setUsed(true);
            updateFile();
        });
    }

    public void showFileChooser() {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Open Resource File");

        File file = fileChooser.showOpenDialog(primaryStage);
        String validation = null;
        if (file == null) {
            return;
        }
        Object[] validationResult = fileUtils.fileValidation(file);
        validation = (String) validationResult[0];
        if (!validation.equals("wrong File Type") && !validation.equals("all")) {
            System.out.println(file.getPath());


            fileUtils.loadNewNumbers(accountsData, (List<Account>) validationResult[1]);
            loadAccountToTable();
        } else {
            validation = "Chose the correct File Type!!!";
        }

        if (!validation.equals("true")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(getPrimaryStage());
            alert.setTitle("Validation Alert!!!");
            alert.setHeaderText("some lines have not been validated");
            alert.setContentText(validation);

            alert.showAndWait();
        }
    }

    public void showSaveFileDialog() {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(primaryStage);
        fileUtils.saveFile(file);
    }

    public void showAccountOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("sample/sample.fxml"));
            AnchorPane accountOverview = loader.load();

            rootLayout.setCenter(accountOverview);

            controller = loader.getController();

            controller.setMain(this);

            controller.getNextAccount();

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
                account.setId(accountsData.size() + 1);
            }
            controller.setAccount(account);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            this.controller.repaintTableView();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Account getNextAccount(Integer id, String secondLogin) {

        Account result = new Account();
        updateAccounts(id, secondLogin);
        for (Account account : accountsData) {
            if (account.getSecondLogin().getValue().length() < 2) {
                result = account;
                break;
            }
        }

        if (result.getId() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(getPrimaryStage());
            alert.setTitle("No UnUsed Account");
            alert.setHeaderText("No UnUsed Account");
            alert.setContentText("Please load new UnUsed Account.");

            alert.showAndWait();
        }
        return result;
    }

}
