package partizanin.controller;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import partizanin.Main;
import partizanin.model.Account;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

/**
 * Created with Intellij IDEA.
 * Project name: VKprojectFx.
 * Date: 31.10.2015.
 * Time: 20:43.
 * To change this template use File|Setting|Editor|File and Code Templates.
 */
public class AccountOverviewController {
    @FXML
    public Button button1;

    @FXML
    public Button button2;

    @FXML
    public Button button3;

    @FXML
    private TableView<Account> accountTableView;

    @FXML
    private TableColumn<Account, String> LoginColumn;

    @FXML
    private TableColumn<Account, String> PasswordColumn;

    @FXML
    private TableColumn<Account, Integer> IdColumn;

    @FXML
    private TableColumn<Account, String> SecondLoginColumn;

    @FXML
    private TableColumn<Account, Boolean> UsedColumn;

    @FXML
    private TableColumn<Account, Boolean> ActiveColumn;

    @FXML
    private Label LoginLabel;

    @FXML
    private Label PasswordLabel;

    @FXML
    private Label IdLabel;
    @FXML
    private Label SecondLoginCodeLabel;

    @FXML
    private CheckBox UsedCheckBox;

    @FXML
    private CheckBox ActiveCheckBox;

    private Main main;

    @FXML
    private TextField field1;

    @FXML
    private TextField field2;

    @FXML
    private TextField field3;

    public AccountOverviewController() {


    }


    @FXML
    private void handleButtonAction(ActionEvent event) {
        // Button was clicked, do something...
        System.out.println(event);
        java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Button button = (Button) event.getSource();
        System.out.println(button.getId());
        String text = "";
        if (button.getId().equals("button1")) {
            text = field1.getText();
        } else if (button.getId().equals("button2")) {

            text = field2.getText();
        } else {
            text = field3.getText();

        }
        System.out.println(text);
        StringSelection selection = new StringSelection(text);

        clipboard.setContents(selection, selection);
    }


    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        IdColumn.setCellValueFactory(cellData -> cellData.getValue().getId().asObject());
        LoginColumn.setCellValueFactory(cellData -> cellData.getValue().getLogin());
        PasswordColumn.setCellValueFactory(cellData -> cellData.getValue().getPassword());
        SecondLoginColumn.setCellValueFactory(cellData -> cellData.getValue().getSecondLogin());
        UsedColumn.setCellValueFactory(cellData -> cellData.getValue().getUsed());
        ActiveColumn.setCellValueFactory(cellData -> cellData.getValue().getActive());

        accountTableView.getSelectionModel().selectedItemProperty().addListener(this::actionTableListener);

    }

    @FXML
    private void handleDeleteAccount() {
        int selectedIndex = accountTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            accountTableView.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Account Selected");
            alert.setContentText("Please select a account in the table.");

            alert.showAndWait();
        }
    }

    private void actionTableListener(ObservableValue<? extends Account> observable, Account oldValue, Account newValue) {
        System.out.println("\nchangeListener ");
        System.out.println("oldValue: " + oldValue);
        System.out.println("newValue:" + newValue);
    }

    public void setMain(Main main) {
        this.main = main;

        accountTableView.setItems(main.getAccountData());
    }

    @FXML
    private void handleNewAccount() {
        Account tempAccount = new Account();
        boolean okClicked = main.showAccountEditDialog(tempAccount);
        if (okClicked) {
            main.getAccountData().add(tempAccount);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditAccount() {
        Account selectedAccount = accountTableView.getSelectionModel().getSelectedItem();
        if (selectedAccount != null) {
            main.showAccountEditDialog(selectedAccount);


        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
}
