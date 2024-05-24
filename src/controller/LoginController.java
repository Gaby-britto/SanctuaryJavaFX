package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	private TextField usuario;

	@FXML
	private TextField email;

	@FXML
	private TextField senha;

	public Stage primaryStage;

	public void entrar() {
		System.out.println("Login realizado com sucesso!");
		if (usuario.getText().equals("admin") && senha.getText().equals("admin")) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/dashboardOnca.fxml"));
				StackPane root = loader.load();

				Scene scene = new Scene(root, 800, 500);
				Stage currentStage = (Stage) usuario.getScene().getWindow();

				currentStage.setScene(scene);
				currentStage.setTitle("Dashboard");
				currentStage.show();

			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println("Realizando login");
		} else {
			mensagemDeErro();
		}
	}

	public void mensagemDeErro() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Sua senha está errada!");
		alert.setContentText("Senha Incorreta");
		alert.setHeaderText(null);
		alert.showAndWait();
	}
}
