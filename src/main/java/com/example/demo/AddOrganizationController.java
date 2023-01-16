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

public class AddOrganizationController extends AdminDataBaseHandler {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField CityIdField;

    @FXML
    private TextField NewAddressField;

    @FXML
    private TextField NewBossField;

    @FXML
    private TextField NewOrgIdField;

    @FXML
    private TextField NewPhoneField;

    @FXML
    void clean() {
        CityIdField.setText(null);
        NewAddressField.setText(null);
        NewBossField.setText(null);
        NewOrgIdField.setText(null);
        NewPhoneField.setText(null);
    }

    @FXML
    void save(MouseEvent event) {

        String city_id = CityIdField.getText();
        String address = NewAddressField.getText();
        String fam_boss = NewBossField.getText();
        String org_id = NewOrgIdField.getText();
        String ph_number = NewPhoneField.getText();


        if (city_id.isEmpty() || address.isEmpty() || fam_boss.isEmpty() || org_id.isEmpty() || ph_number.isEmpty()){
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
        query = "INSERT INTO organization (org_id, address, fam_boss, city_id, ph_number) VALUES (" + NewOrgIdField.getText() + ",'" + NewAddressField.getText() + "','"+ NewBossField.getText() + "'," +  CityIdField.getText() + ",'" +  NewPhoneField.getText() + "');";
    }

    String query = null;
    PreparedStatement preparedStatement = null;

    ResultSet resultSet = null;

    OrganizationTable organizationTable = null;

    @FXML
    void initialize() {

    }

}
