package recep.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Okuyucu implements IOkuI, IOkuII {

	/**
	 * Bu fonksiyon bir text dosyasýndan okuma iþlemi yaparak. Baþka bir text
	 * dosyadýna yazýyor.
	 * 
	 * @param okunacakDosyaYolu
	 * @param yazilacakDosyaYolu
	 */
	public void dosyaAcOku(String okunacakDosyaYolu, String yazilacakDosyaYolu) {

		IYazmaII yazici = new Yazici();

		File file = new File(okunacakDosyaYolu);

		Scanner oku = null;

		try {
			oku = new Scanner(file);

			while (oku.hasNext()) {

				yazici.dosyaAc(yazilacakDosyaYolu);
				yazici.veriYaz(oku.nextLine());
				yazici.dosyaKapt();
				// System.out.println(oku.nextLine());
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		oku.close();

	}

	/**
	 * 
	 * Bu fonksiyon bir text dosyasýndan okuma iþlemi yaparak. Konsola yazýyor.
	 * 
	 * @param okunacakDosyaYolu
	 */
	public String dosyaAcOku(String okunacakDosyaYolu) {

		File file = new File(okunacakDosyaYolu);
		String text=null;
		Scanner oku = null;

		try {
			oku = new Scanner(file);

			while (oku.hasNext()) {
				text=oku.nextLine();
				//System.out.println(text);
				
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		oku.close();
		return text;
	}

}