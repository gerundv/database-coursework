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

public class OrganizationTableController extends AdminDataBaseHandler{

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

    @FXML
    private Button DeleteButton;

    @FXML
    private Button InsertIntoOrgTableButton;

    @FXML
    private Button UpdateTableButton;

    private void refreshTable() throws SQLException, ClassNotFoundException {
        OrganizationTableList.clear();

        query = "SELECT * FROM organizations";
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

    String query = null;
    PreparedStatement preparedStatement = null;

    ResultSet resultSet = null;

    OrganizationTable organizationTable = null;

    ObservableList<OrganizationTable> OrganizationTableList = FXCollections.observableArrayList();

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

        InsertIntoOrgTableButton.setOnAction(actionEvent -> {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("AddOrganization.fxml"));
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
            organizationTable = OrganizationTable.getSelectionModel().getSelectedItem();
            query = "DELETE FROM organizations WHERE org_id=" + organizationTable.getOrg_id() + ";";
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
        CityIdColumn.setCellValueFactory(new PropertyValueFactory<OrganizationTable, Integer>("city_id"));
        AddressColumn.setCellValueFactory(new PropertyValueFactory<OrganizationTable, String>("address"));
        PhNumberColumn.setCellValueFactory(new PropertyValueFactory<OrganizationTable, String>("ph_number"));
        FamBossColumn.setCellValueFactory(new PropertyValueFactory<OrganizationTable, String>("fam_boss"));
        OrgIdColumn.setCellValueFactory(new PropertyValueFactory<OrganizationTable, Integer>("org_id"));

    }

}
