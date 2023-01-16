package com.example.demo;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class UserOrganizationTableController extends AdminDataBaseHandler {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<OrganizationTable, String> FamBossColumn;

    @FXML
    private TableColumn<OrganizationTable, Integer> OrgIdColumn;

    @FXML
    private TableColumn<OrganizationTable, String> PhNumberColumn;
    @FXML
    private TableColumn<OrganizationTable, String> AddressColumn;

    @FXML
    private TableColumn<OrganizationTable, Integer> CityIdColumn;

    @FXML
    private TableView<OrganizationTable> OrganizationTable;

    String query = null;
    PreparedStatement preparedStatement = null;

    ResultSet resultSet = null;

    OrganizationTable organizationTable = null;

    ObservableList<OrganizationTable> OrganizationTableList = FXCollections.observableArrayList();

    private void refreshTable() throws SQLException, ClassNotFoundException {
        OrganizationTableList.clear();

        query = "SELECT * FROM organization";
        preparedStatement = getDbConnection().prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            OrganizationTableList.add(new OrganizationTable(
                    resultSet.getString("org_id"),
                    resultSet.getString("address"),
                    resultSet.getString("fam_boss"),
                    resultSet.getString("city_id"),
                    resultSet.getString("ph_number")
            ));
        }

        OrganizationTable.setItems(OrganizationTableList);
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        refreshTable();

        CityIdColumn.setCellValueFactory(new PropertyValueFactory<OrganizationTable, Integer>("city_id"));
        AddressColumn.setCellValueFactory(new PropertyValueFactory<OrganizationTable, String>("address"));
        PhNumberColumn.setCellValueFactory(new PropertyValueFactory<OrganizationTable, String>("ph_number"));
        FamBossColumn.setCellValueFactory(new PropertyValueFactory<OrganizationTable, String>("fam_boss"));
        OrgIdColumn.setCellValueFactory(new PropertyValueFactory<OrganizationTable, Integer>("org_id"));
    }

}
