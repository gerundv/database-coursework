package com.example.demo;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class VacancyTableController extends AdminDataBaseHandler {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button DeleteButton;

    @FXML
    private Button UpdateTableButton;
    @FXML
    private Button InsertIntoVacancyTableButton;

    @FXML
    private TableColumn<VacancyTable, Integer> OrgIdColumn;

    @FXML
    private TableColumn<VacancyTable, Integer> SalaryColumn;

    @FXML
    private TableColumn<VacancyTable, String> TitleVacancyColumn;

    @FXML
    private TableColumn<VacancyTable, Integer> VacIdColumn;

    @FXML
    private TableView<VacancyTable> VacancyTable;
    private void refreshTable() throws SQLException, ClassNotFoundException {
        VacancyTableList.clear();

        query = "SELECT * FROM vacancies";
        preparedStatement = getDbConnection().prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            VacancyTableList.add(new VacancyTable(
                    resultSet.getString("vac_id"),
                    resultSet.getString("start_salary"),
                    resultSet.getString("title"),
                    resultSet.getString("org_id")
            ));
        }

        VacancyTable.setItems(VacancyTableList);
    }

    String query = null;
    PreparedStatement preparedStatement = null;

    ResultSet resultSet = null;

    VacancyTable vacancyTable = null;

    ObservableList<VacancyTable> VacancyTableList = FXCollections.observableArrayList();

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        refreshTable();

        UpdateTableButton.setOnAction(actionEvent -> {
            try {
                refreshTable();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        InsertIntoVacancyTableButton.setOnAction(actionEvent -> {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("AddVacancy.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        DeleteButton.setOnAction(actionEvent -> {
            vacancyTable = VacancyTable.getSelectionModel().getSelectedItem();
            query = "DELETE FROM vacancies WHERE vac_id=" + vacancyTable.getVac_id() + ";";
            try {
                preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.execute();
                refreshTable();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        VacIdColumn.setCellValueFactory(new PropertyValueFactory<VacancyTable, Integer>("vac_id"));
        TitleVacancyColumn.setCellValueFactory(new PropertyValueFactory<VacancyTable, String>("title"));
        SalaryColumn.setCellValueFactory(new PropertyValueFactory<VacancyTable, Integer>("start_salary"));
        OrgIdColumn.setCellValueFactory(new PropertyValueFactory<VacancyTable, Integer>("org_id"));

    }

}


