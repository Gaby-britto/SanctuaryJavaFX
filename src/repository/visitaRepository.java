package repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Sanctuary;

public class visitaRepository {

	public List<Sanctuary> sanctuarys;
	private File database;
	
	public visitaRepository() {
		this.database = new File("database-files.txt");
		this.sanctuarys = new ArrayList<>();
	}
	
	public void updateSanctuary(Sanctuary updateSanctuary) {
		for(int i = 0; i < sanctuarys.size(); i++) {
			if(sanctuarys.get(i).getId() == updateSanctuary.getId()) {
				sanctuarys.set(i, updateSanctuary);
				saveSanctuarys();
				break;
			}
		}
	}
	
	public Sanctuary getSanctuaryById(int id) {
		for(Sanctuary sanctuary: sanctuarys) {
			if(sanctuary.getId() == id) {
				return sanctuary;
			}
		}
		return null;
	}
	
	// Buscar todos
	public List<Sanctuary> buscarTodos(){
		return new ArrayList<>(sanctuarys);
	}
	
	// Deletar objeto específico
	public void deleteSanctuary(int id) {
		//Percorrer todo o arrray, caso seja igual 
		sanctuarys.removeIf(sanctuary -> sanctuary.getId() == id);
	}
	
	// Criar Sanctuary
	public void addSanctuary(Sanctuary sanctuary) {
		// vai faltar o ID
		sanctuary.setId(getNextId());
		sanctuarys.add(sanctuary);
		saveSanctuarys();
		// Sobrescreve o arquivo database
	}
	
	// Sobrescrever o arquuivo
	private void saveSanctuarys() {
		try(PrintWriter writer = new PrintWriter(new FileOutputStream(database, false))) {
			for(Sanctuary sanctuary: sanctuarys) {
				String data = sanctuary.getId() + ";" + sanctuary.getLocalSantuary() + ";" + sanctuary.getDataSanctuary() + ";" + sanctuary.getHorarioSanctuary() + ";" + sanctuary.getLocalSantuary() + ";" + sanctuary.getValorSanctuary();  
			}
		}catch(FileNotFoundException e) {
			System.out.println("Arquivo database não encontrado! Deu ruim ");
		}
		
	}
	
	// Lógica para pegar o próximo id 
	private int getNextId() {
		int maxId = 0;
		for(Sanctuary sanctuary : sanctuarys) {
			// Verifica se o número é maior que o nosso número máximo
			if(sanctuary.getId() > maxId) {
				maxId = sanctuary.getId();			
			}
		}
		return maxId + 1;
	}
	
	private void loadSanctuarys() {
		try(Scanner sc = new Scanner(database)){
			while(sc.hasNextLine()) {
				String[] data = sc.nextLine().split(";");
				if(data.length >= 5) {
					Sanctuary sanctuary = new Sanctuary();
					sanctuary.setId(Integer.parseInt(data[0]));
					sanctuary.setLocalSantuary(data[1]);
					sanctuary.setDataSanctuary(data[2]);
					sanctuary.setHorarioSanctuary(data[3]);
					sanctuary.setValorSanctuary(Integer.parseInt(data[4]));
					sanctuarys.add(sanctuary);
					
				}
			}
		}catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado, criando um novo!localSantuary");
		}
	}
}
