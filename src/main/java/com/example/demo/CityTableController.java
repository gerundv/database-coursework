package com.example.demo;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
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

public class CityTableController extends AdminDataBaseHandler {

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

    @FXML
    private Button UpdateTableButton;

    @FXML
    private Button DeleteButton;
    @FXML
    private Button InsertIntoCityTableButton;


    private void refreshTable() throws SQLException, ClassNotFoundException {
        CityTableList.clear();

        query = "SELECT * FROM city";
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

    String query = null;
    PreparedStatement preparedStatement = null;

    ResultSet resultSet = null;

    CityTable cityTable = null;

    ObservableList<CityTable> CityTableList = FXCollections.observableArrayList();

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

        InsertIntoCityTableButton.setOnAction(actionEvent -> {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("AddCity.fxml"));
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
            cityTable = CityTable.getSelectionModel().getSelectedItem();
            query = "DELETE FROM city WHERE city_id=" + cityTable.getCity_id() + ";";
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
        CityIdColumn.setCellValueFactory(new PropertyValueFactory<CityTable,Integer>("city_id"));
        CityNameColumn.setCellValueFactory(new PropertyValueFactory<CityTable,String>("name"));

    }

}
