package com.example.demo;

import java.net.URL;
import java.sql.DriverManager;
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

public class AddCityController extends AdminDataBaseHandler {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField NewCityIdField;

    @FXML
    private TextField NewCityNameField;

    @FXML
    void clean() {
        NewCityIdField.setText(null);
        NewCityNameField.setText(null);
    }

    @FXML
    void save(MouseEvent event) {

        String city_id = NewCityIdField.getText();
        String name = NewCityNameField.getText();

        if (city_id.isEmpty() || name.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Заполните все поля");
            alert.showAndWait();
        }
        else {
            getQuery();
            insert();
            clean();
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
        query = "INSERT INTO cities (city_id, name) VALUES (" + NewCityIdField.getText() + ",'" + NewCityNameField.getText() + "');";
    }

    String query = null;
    PreparedStatement preparedStatement = null;

    ResultSet resultSet = null;

    CityTable cityTable = null;

    @FXML
    void initialize() {

    }

}
