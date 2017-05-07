package recep.io;

public class Config {
	private static String URL=("URL.txt");

	public static String[] configOku(){
		String[] txtDizi;
		
		String  NEWURL,NEWUSERNAME,NEWPASSWORD;
		
		IOkuI okuyucuKonsol = new Okuyucu();
		
		
		String text = okuyucuKonsol.dosyaAcOku(URL);
		
		NEWURL = text.substring(0, text.indexOf(" "));
			
		text = text.substring(text.indexOf(" ")+1);
			
		NEWUSERNAME = text.substring(0, text.indexOf(" "));
		
		NEWPASSWORD = text.substring(text.indexOf(" ")+1);
		
		txtDizi = new String[]{NEWURL, NEWUSERNAME, NEWPASSWORD};
		return txtDizi;
	}
	
	public static void configYaz(String url,String username, String password){
		IYazmaI yazma = new Yazici();
		yazma.dosyaAc(URL);
		yazma.veriYaz(new DateBaseConfig(url, username, password));
		yazma.dosyaKapt();
	}
	
	
	
	
	
	
}
