package com.example.films;

import javafx.collections.FXCollections;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.net.URL;
import java.nio.file.Files;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    @FXML
    private TableView<Film> tabView;

    @FXML
    private TableColumn<Film, Integer> rangCol;

    @FXML
    private TableColumn<Film, String> idCol;

    @FXML
    private TableColumn<Film, String> nomCol;

    @FXML
    private TableColumn<Film, Integer> anneeCol;

    @FXML
    private Button ajoutBtn;

    @FXML
    private Button suppBtn;
    @FXML
    private Button viderBtn;
    @FXML
    private Label LabRang;
    @FXML
    private Label LabId;
    @FXML
    private Label LabNom;
    @FXML
    private Label LabAnn;
    @FXML
    private TextField rangField;
    @FXML
    private TextField idField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField annField;
    @FXML
    private TableColumn<Film, String> acteurCol;
    @FXML
    private TextField acteurTxt;

    @FXML
    protected void onHelloButtonClick() {
    }
    @FXML
    private Button importerBtn;

    private ObservableList<Film> films;

    @Override
    public void initialize(URL url, ResourceBundle resourcesBundle) {
        rangCol.setCellValueFactory(new PropertyValueFactory<Film,Integer>("rang"));
        idCol.setCellValueFactory(new PropertyValueFactory<Film,String>("id"));
        nomCol.setCellValueFactory(new PropertyValueFactory<Film,String>("nom"));
        anneeCol.setCellValueFactory(new PropertyValueFactory<Film,Integer>("annee"));
        acteurCol.setCellValueFactory(new PropertyValueFactory<Film, String>("acteur"));


    }

    @FXML
    private void importer() {

        List<List<String>> csv = getCsv();
        for (int i = 1; i < csv.size(); i++) {
            Film films = new Film();
            films.setRang(Integer.parseInt(csv.get(i).get(0)));
            films.setId(csv.get(i).get(1) + "");
            films.setNom(csv.get(i).get(2) + "");
            films.setAnnee(
                    Integer.parseInt(csv.get(i).get(3))
            );
            films.setActeur(csv.get(i).get(10));
            tabView.getItems().add(films);

        }
    }

    public List<List<String>> getCsv() {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("/home/aloui7u/IdeaProjects/Films/movies.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                    records.add(Arrays.asList(values));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return records;

    }

    public void ajouterFilmDansTableau(Integer rang, String id, String nom, Integer annee , String acteur) {
        Film film = new Film();
        film.setRang(rang);
        film.setId(id);
        film.setNom(nom);
        film.setAnnee(annee);
        film.setActeur(acteur);

        ObservableList<Film> films = tabView.getItems();
        films.add(film);
        tabView.setItems(films);
    }

    @FXML
    private void ajouterFilm() {
        if (rangField.getText().isEmpty() || idField.getText().isEmpty() || nomField.getText().isEmpty() || annField.getText().isEmpty() || Integer.parseInt(rangField.getText()) < 0 || Integer.parseInt(annField.getText()) < 0) {
            return;
        }

        int rang = Integer.parseInt(rangField.getText());
        String id = idField.getText();
        String nom = nomField.getText();
        int annee = Integer.parseInt(annField.getText());
        String acteur = acteurTxt.getText();
        ;
        ajouterFilmDansTableau(rang, id, nom, annee, acteur);
        rangField.setText("");
        idField.setText("");
        nomField.setText("");
        annField.setText("");
        acteurTxt.setText("");
    }
    @FXML
    private void viderTableau() {
        tabView.getItems().clear();
    }
    @FXML
    private void supprimerFilm() {
        ObservableList<Film> films = tabView.getItems();
        films.remove(tabView.getSelectionModel().getSelectedItem());
    }


}

