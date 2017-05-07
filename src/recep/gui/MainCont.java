package recep.gui;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import recep.dao.DAO;
import recep.entity.HangiKurs;
 
public class MainCont  implements Initializable {
	
	
	private DAO dao;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			dao = new DAO();
		} catch (Exception e) {
			
		}
		
		
	}
 
	@FXML
	public void onClickKayit(){
		Stage primaryStage = new Stage(); 
		try {
			primaryStage.setTitle("Personel Kay�t Program�");
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/recep/view/kayitgir.fxml"));
			Scene scene = new Scene(root,370,520);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void onClickSorgula(){
		Stage primaryStage = new Stage(); 
		try {
			primaryStage.setTitle("Personel Kay�t Program�");
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/recep/view/sorgula.fxml"));
			Scene scene = new Scene(root,753,518);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void onClickKursEkle(){
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Personel Kay�t Program�");
		dialog.setHeaderText("Eklenecek Kursu Yaz�n�z");
		dialog.setContentText("Kurs ad�:");
		
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		    HangiKurs hangiKurs = new HangiKurs(result.get());
		    dao.addValue(hangiKurs);
		}
	}
	
	@FXML
	public void onClickKursSil(){
		
		List<HangiKurs> myHangiKursList = null;
		List<HangiKurs> myList = dao.getHangiKursList();
		String[] kursAd = new String[myList.size()];
		for (int i = 0; i < myList.size(); i++) {
			kursAd[i] = dao.getHangiKursList().get(i).getKursAdi();
		}

		ChoiceDialog<String> dialog = new ChoiceDialog<>("Kurs Ad�...", kursAd);
		dialog.setTitle("Personel Kay�t Program�");
		dialog.setHeaderText("Silinecek Kursu Se�iniz");
		dialog.setContentText("Kurs ad�:");
		
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
			myHangiKursList = dao.sorgulaHangiKurs(result.get());
		}
		
		if(myHangiKursList != null){
		
		HangiKurs hangiKurs = new HangiKurs();
		hangiKurs.setId(myHangiKursList.get(0).getId());
		dao.deleteValue(hangiKurs);
		
		}
		
		/*TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Personel Kay�t Program�");
		dialog.setHeaderText("Silinecek Kursu Yaz�n�z");
		dialog.setContentText("Kurs ad�:");
		
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		    HangiKurs hangiKurs = new HangiKurs(result.get());
		    
		    dao.deleteValue(hangiKurs);
		}*/
	}
       
}