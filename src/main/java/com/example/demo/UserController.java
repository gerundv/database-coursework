package com.example.demo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UserController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button CityTableButton;

    @FXML
    private Button OrganizationTableButton;

    @FXML
    private Button VacancyTableButton;

    @FXML
    void initialize() {
        CityTableButton.setOnAction(actionEvent -> {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("UserCityTable-view.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

        OrganizationTableButton.setOnAction(actionEvent -> {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("UserOrgTable-view.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

        VacancyTableButton.setOnAction(actionEvent -> {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("UserVacancyTable-view.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

        });
    }

}
