package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Sanctuary;
import repository.visitaRepository;

public class SanctuaryController {
	
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
	
	
	
	
	@FXML
	private TextField local;
	
	@FXML
	private TextField horario;
	
	@FXML
	private TextField data;
	
	@FXML
	private TextField valor;
	
	private visitaRepository repoSanctuary;
	
	@FXML
	public void initialize() {
		repoSanctuary = new visitaRepository();
	}
	
	public void adicionar() {
		Sanctuary sanctuary = new Sanctuary();
		sanctuary.setLocalSantuary(local.getText());
		sanctuary.setDataSanctuary(data.getText());
		sanctuary.setHorarioSanctuary(horario.getText());
		sanctuary.setValorSanctuary(Integer.parseInt(valor.getText()));
		repoSanctuary.addSanctuary(sanctuary);
		
	}
	
	public void clearFields(){
		local.clear();
		data.clear();
		horario.clear();
		valor.clear();
	}
	
	public void limpar() {
		clearFields();
	}

}
