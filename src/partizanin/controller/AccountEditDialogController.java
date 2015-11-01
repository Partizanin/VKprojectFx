package partizanin.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import partizanin.model.Account;

/**
 * Dialog to edit details of a account.
 * 
 * @author Marco Jakob
 */
public class AccountEditDialogController {
    @FXML
    public Button CancelButton;
    @FXML
    private TextField idField;
    @FXML
    private TextField loginField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField secondLoginField;
    @FXML
    private CheckBox usedBox;
    @FXML
    private CheckBox activeBox;


    private Stage dialogStage;
    private Account account;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the account to be edited in the dialog.
     * 
     * @param account
     */
    public void setAccount(Account account) {
        this.account = account;

        if (account.getLogin() != null) {
            loginField.setText(account.getLogin().getValue());
            passwordField.setText(account.getPassword().getValue());
            secondLoginField.setText(account.getSecondLogin().getValue());
            idField.setText(String.valueOf(account.getId().getValue()));
            usedBox.setSelected(account.getUsed().getValue());
            activeBox.setSelected(account.getActive().getValue());
        }
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            account.setLogin(loginField.getText());
            account.setSecondLogin(secondLoginField.getText());
            account.setPassword(passwordField.getText());
            account.setActive(activeBox.isSelected());
            account.setUsed(usedBox.isSelected());

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (loginField.getText() == null || loginField.getText().length() == 0) {
            errorMessage += "No valid Login!\n";
        }
        if (passwordField.getText() == null || passwordField.getText().length() == 0) {
            errorMessage += "No valid password!\n";
        }
        if (secondLoginField.getText() == null || secondLoginField.getText().length() == 0) {
            errorMessage += "No valid secondLogin!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}