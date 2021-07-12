package paket;

import java.time.LocalDate;

public class Personel {

	private String ad, soyad, cinsiyet, departman;
	private double maaþ;
	private LocalDate tarih;
	
	// Yapýlandýrýcý metod oluþturdum ! 
	public Personel(String ad, String soyad, String cinsiyet, String departman, double maaþ, LocalDate tarih) {
		super();
		this.ad = ad;
		this.soyad = soyad;
		this.cinsiyet = cinsiyet;
		this.departman = departman;
		this.maaþ = maaþ;
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

	public double getMaaþ() {
		return maaþ;
	}

	public void setMaaþ(double maaþ) {
		this.maaþ = maaþ;
	}

	public LocalDate getTarih() {
		return tarih;
	}

	public void setTarih(LocalDate tarih) {
		this.tarih = tarih;
	}
	
	
	
	
	
	
	
	
	
	
}
