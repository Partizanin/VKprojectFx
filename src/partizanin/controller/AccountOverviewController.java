package partizanin.controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import partizanin.Main;
import partizanin.model.Account;

/**
 * Created with Intellij IDEA.
 * Project name: VKprojectFx.
 * Date: 31.10.2015.
 * Time: 20:43.
 * To change this template use File|Setting|Editor|File and Code Templates.
 */
public class AccountOverviewController {
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

    public AccountOverviewController() {


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

    }

    public void setMain(Main main) {
        this.main = main;

        accountTableView.setItems(main.getAccountData());
    }
}
