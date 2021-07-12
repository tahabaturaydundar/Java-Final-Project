package paket;

import java.time.LocalDate;

public class Personel {

	private String ad, soyad, cinsiyet, departman;
	private double maa�;
	private LocalDate tarih;
	
	// Yap�land�r�c� metod olu�turdum ! 
	public Personel(String ad, String soyad, String cinsiyet, String departman, double maa�, LocalDate tarih) {
		super();
		this.ad = ad;
		this.soyad = soyad;
		this.cinsiyet = cinsiyet;
		this.departman = departman;
		this.maa� = maa�;
		this.tarih = tarih;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getSoyad() {
		return soyad;
	}

	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}

	public String getCinsiyet() {
		return cinsiyet;
	}

	public void setCinsiyet(String cinsiyet) {
		this.cinsiyet = cinsiyet;
	}

	public String getDepartman() {
		return departman;
	}

	public void setDepartman(String departman) {
		this.departman = departman;
	}

	public double getMaa�() {
		return maa�;
	}

	public void setMaa�(double maa�) {
		this.maa� = maa�;
	}

	public LocalDate getTarih() {
		return tarih;
	}

	public void setTarih(LocalDate tarih) {
		this.tarih = tarih;
	}
	
	
	
	
	
	
	
	
	
	
}
