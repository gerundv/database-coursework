package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserVacancyTableController extends AdminDataBaseHandler{

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

        VacIdColumn.setCellValueFactory(new PropertyValueFactory<VacancyTable, Integer>("vac_id"));
        TitleVacancyColumn.setCellValueFactory(new PropertyValueFactory<VacancyTable, String>("title"));
        SalaryColumn.setCellValueFactory(new PropertyValueFactory<VacancyTable, Integer>("start_salary"));
        OrgIdColumn.setCellValueFactory(new PropertyValueFactory<VacancyTable, Integer>("org_id"));

    }

}
