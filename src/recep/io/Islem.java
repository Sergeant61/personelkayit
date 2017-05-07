package recep.io;

public class Islem {
	
	public static void main(String[] args) {
		
		String URL=("D:/Eclipse/Personel Kayit Programi/Workspace/PersonelKayitFx/src/recep/resource/URL.txt");
		
		DateBaseConfig insan = new DateBaseConfig("jdbc:mysql://localhost:3306/fxproje1","roo1","root");
		
		IYazmaI yazici = new Yazici();
		
		yazici.dosyaAc(URL);
		yazici.veriYaz(insan);
		yazici.dosyaKapt();
		
		
		IOkuI okuyucuKonsol = new Okuyucu();
		
		String text = okuyucuKonsol.dosyaAcOku(URL);
		
		
		String  NEWURL,NEWUSERNAME,NEWPASSWORD;
		
		NEWURL = text.substring(0, text.indexOf(" "));
		
		
		text = text.substring(text.indexOf(" ")+1);
		
		
		NEWUSERNAME = text.substring(0, text.indexOf(" "));
		
		NEWPASSWORD = text.substring(text.indexOf(" ")+1);
		
		
		
	}

}
