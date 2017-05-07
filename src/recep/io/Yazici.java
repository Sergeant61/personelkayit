package recep.io;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Yazici implements IYazmaI,IYazmaII{

	PrintWriter pw;

	public void dosyaAc(String dosyaYolu) {
		try {
			this.pw = new PrintWriter(dosyaYolu);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void dosyaKapt() {
		this.pw.close();
	}

	public void veriYaz(DateBaseConfig insan) {
		this.pw.println(insan.getUrl() + " " + insan.getUserName() + " " + insan.getPassword());
	}
	
	
	public void veriYaz(String okunan) {
		this.pw.println(okunan);
	}
	
	
	

}
