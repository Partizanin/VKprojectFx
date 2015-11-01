package partizanin.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    public Button button1;
    public Button button2;
    public Button button3;
    public TextField field1;
    public TextField field2;
    public TextField field3;



    @FXML
    private Button button;


    @FXML
    private void handleButtonAction(ActionEvent event) {
        // Button was clicked, do something...
        System.out.println(event);
        java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        button = (Button) event.getSource();
        System.out.println(button.getId());
        String text = "";
        if (button.getId().equals("button1")) {
            text = field1.getText();
        }else if (button.getId().equals("button2")) {

            text = field2.getText();
        }else{
            text = field3.getText();

        }
        System.out.println(text);
        StringSelection selection = new StringSelection(text);

        clipboard.setContents(selection, selection);
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        field1.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent arg0) {
                // TODO Auto-generated method stub
                System.out.println("field1: " + arg0.getText());

            }
        });
        field2.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent arg0) {
                // TODO Auto-generated method stub
                System.out.println("field2: " + arg0.getText());

            }
        });
        field3.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent arg0) {
                // TODO Auto-generated method stub
                System.out.println("field3: " + arg0.getText());

            }
        });
    }
}
