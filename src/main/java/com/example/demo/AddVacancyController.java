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

public class AddVacancyController extends AdminDataBaseHandler {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField NewSalaryField;

    @FXML
    private TextField NewVacIdField;

    @FXML
    private TextField NewVacTitleField;

    @FXML
    private TextField OrgIdField;

    @FXML
    void clean() {
        NewSalaryField.setText(null);
        NewVacIdField.setText(null);
        NewVacTitleField.setText(null);
        OrgIdField.setText(null);
    }

    @FXML
    void save(MouseEvent event) {

        String vac_id = NewVacIdField.getText();
        String start_salary = NewSalaryField.getText();
        String title = NewVacTitleField.getText();
        String org_id = OrgIdField.getText();

        if (vac_id.isEmpty() || start_salary.isEmpty() || title.isEmpty() || org_id.isEmpty()){
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
        query = "INSERT INTO vacancy (vac_id, start_salary, title, org_id) VALUES (" + NewVacIdField.getText() + "," + NewSalaryField.getText() + ",'"+ NewVacTitleField.getText() + "'," +  OrgIdField.getText() + ");";
    }

    String query = null;
    PreparedStatement preparedStatement = null;

    ResultSet resultSet = null;

    VacancyTable vacancyTable = null;

    @FXML
    void initialize() {

    }

}
