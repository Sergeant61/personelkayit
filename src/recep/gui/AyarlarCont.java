package recep.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import recep.io.Config;


public class AyarlarCont implements Initializable {
	
	@FXML
	private PasswordField txtUsername,txtPassword;

	@FXML
	private TextField txtUrl;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		String[] urlTxt = Config.configOku();

		txtUrl.setText(urlTxt[0]);
		txtUsername.setText(urlTxt[1]);
		txtPassword.setText(urlTxt[2]);
	}
	
	@FXML
	public void onClickKaydet(){
		
		Config.configYaz(txtUrl.getText(),txtUsername.getText(),txtPassword.getText());
		
		//DAO.setInstance();
		//DAO.getInstance();
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Bilgilendirme");
		alert.setHeaderText(null);
		alert.setContentText("Yapmýþ olduðunuz ayarlar kaydedildi. Ayarlarýn geçerli olmasý için programý yeniden baþlatmalýsýnýz.");

		alert.showAndWait();
		
		
	}
}
