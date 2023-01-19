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

public class UserCityTableController extends AdminDataBaseHandler{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<CityTable, Integer> CityIdColumn;

    @FXML
    private TableColumn<CityTable, String> CityNameColumn;

    @FXML
    private TableView<CityTable> CityTable;

    String query = null;
    PreparedStatement preparedStatement = null;

    ResultSet resultSet = null;

    CityTable cityTable = null;

    ObservableList<CityTable> CityTableList = FXCollections.observableArrayList();

    private void refreshTable() throws SQLException, ClassNotFoundException {
        CityTableList.clear();

        query = "SELECT * FROM cities";
        preparedStatement = getDbConnection().prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            CityTableList.add(new CityTable(
                    resultSet.getString("city_id"),
                    resultSet.getString("name")
            ));
        }

        CityTable.setItems(CityTableList);
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        refreshTable();

        CityIdColumn.setCellValueFactory(new PropertyValueFactory<CityTable,Integer>("city_id"));
        CityNameColumn.setCellValueFactory(new PropertyValueFactory<CityTable,String>("name"));
    }

}
