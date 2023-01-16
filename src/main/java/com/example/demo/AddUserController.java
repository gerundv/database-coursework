package com.example.demo;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddUserController extends AdminDataBaseHandler{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField NewLoginField;

    @FXML
    private TextField NewPasswordField;

    @FXML
    private TextField NewRoleField;

    @FXML
    void save(MouseEvent event) {
        String login = NewLoginField.getText();
        String password = NewPasswordField.getText();
        String role = NewRoleField.getText();

        if (login.isEmpty() || password.isEmpty() || role.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Заполните все поля");
            alert.showAndWait();
        }
        else {
            getQuery();
            insert();
        }
    }

    private void insert() {
        try {
            preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.execute();

        } catch (SQLException e) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void getQuery() {
        query = "INSERT INTO users (login, pass, check_admin) VALUES ('" + NewLoginField.getText() + "','" + NewPasswordField.getText() + "'," + NewRoleField.getText() + ");";
    }

    String query = null;
    PreparedStatement preparedStatement = null;

    ResultSet resultSet = null;

    @FXML
    void initialize() {

    }

}
