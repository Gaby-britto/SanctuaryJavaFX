package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Sanctuary;
import repository.visitaRepository;

public class SanctuaryController {

    @FXML
    private ChoiceBox<String> local;
    @FXML
    private ChoiceBox<String> horario;
    @FXML
    private ChoiceBox<String> data;
    @FXML
    private ChoiceBox<String> valor;

    @FXML
    private TableView<Sanctuary> tableView;

    @FXML
    private TableColumn<Sanctuary, String> cLocal;

    @FXML
    private TableColumn<Sanctuary, String> cHorario;

    @FXML
    private TableColumn<Sanctuary, String> cData;

    @FXML
    private TableColumn<Sanctuary, Integer> cValor;

    private String[] locais = {"São Paulo", "Rio de Janeiro"};
    private String[] horarios = {"12:00", "10:00", "15:00", "17:00", "08:00"};
    private String[] datas = {"12/06/2024", "25/09/2024", "05/06/2024", "29/07/2024", "17/09/2024"};
    private String[] valores = {"150"};

    private ObservableList<Sanctuary> sanctuaryData;

    private visitaRepository repoSanctuary;

    @FXML
    public void initialize() {
        cLocal.setCellValueFactory(new PropertyValueFactory<>("localSanctuary"));
        cHorario.setCellValueFactory(new PropertyValueFactory<>("horarioSanctuary"));
        cData.setCellValueFactory(new PropertyValueFactory<>("dataSanctuary"));
        cValor.setCellValueFactory(new PropertyValueFactory<>("valorSanctuary"));

        local.getItems().addAll(locais);
        horario.getItems().addAll(horarios);
        data.getItems().addAll(datas);
        valor.getItems().addAll(valores);

        sanctuaryData = FXCollections.observableArrayList();
        tableView.setItems(sanctuaryData);

        repoSanctuary = new visitaRepository();
        carregarDadosSalvos();
    }

    public void carregarDadosSalvos() {
        try (BufferedReader br = new BufferedReader(new FileReader("database-files.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 5) {
                    Sanctuary sanctuary = new Sanctuary();
                    sanctuary.setId(Integer.parseInt(parts[0]));
                    sanctuary.setLocalSanctuary(parts[1]);
                    sanctuary.setDataSanctuary(parts[2]);
                    sanctuary.setHorarioSanctuary(parts[3]);
                    sanctuary.setValorSanctuary(Integer.parseInt(parts[4]));

                    sanctuaryData.add(sanctuary);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void adicionar() {
        Sanctuary sanctuary = new Sanctuary();
        sanctuary.setLocalSanctuary(local.getValue());
        sanctuary.setDataSanctuary(data.getValue());
        sanctuary.setHorarioSanctuary(horario.getValue());
        sanctuary.setValorSanctuary(Integer.parseInt(valor.getValue()));

        repoSanctuary.addSanctuary(sanctuary);
        sanctuaryData.add(sanctuary);
        clearFields();
    }

    public void clearFields() {
        local.setValue(null);
        data.setValue(null);
        horario.setValue(null);
        valor.setValue(null);
    }

    public void limpar() {
        clearFields();
    }
}
