package paket;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class KontrolcuUygulama1 implements Initializable {

    @FXML
    private TextField txtAd?;

    @FXML
    private TextField txtSoyad?;

    @FXML
    private DatePicker dpTarih;

    @FXML
    private Spinner<Double> spnMaa?;

    @FXML
    private ComboBox<String> cmbCinsiyet;

    @FXML
    private ComboBox<String> cmbDepartman;

    @FXML
    private TableView<Personel> tblPersonel;

    @FXML
    private TableColumn<Personel, String> s?tunAd?;

    @FXML
    private TableColumn<Personel, String> s?tunSoyad?;

    @FXML
    private TableColumn<Personel, LocalDate> s?tunTarih;

    @FXML
    private TableColumn<Personel, Double> s?tunMaa?;

    @FXML
    private TableColumn<Personel, String> s?tunCinsiyet;

    @FXML
    private TableColumn<Personel, String> s?tunDeparman;
    
    private ObservableList<Personel> personeller = FXCollections.observableArrayList();
    private Connection ba?lant?;
    private PreparedStatement sorgu;
    
    private Alert hatamesaji = new Alert(AlertType.ERROR);
    private Alert bilgimesaji = new Alert(AlertType.INFORMATION);
    private LocalDate todaydate = LocalDate.now(); 

    @FXML
    void personelEkle(ActionEvent event) {
    	
    	LocalDate tarih = dpTarih.getValue();
    for (int i = 0; i < personeller.size(); i++) {
		if (todaydate.getYear() - tarih.getYear() < 19) {
			hatamesaji.setTitle("Hata");
		    hatamesaji.setHeaderText("Personel 18 ya??ndan k???k oldu?u i?in kay?t olamaz!");
    	    hatamesaji.show();
    	    return;
		}
	}
    	String ad = txtAd?.getText();
    	String soyad = txtSoyad?.getText();
    	double maa? = spnMaa?.getValue();
    	String cinsiyet = cmbCinsiyet.getValue();
    	String departman = cmbDepartman.getValue();

    	Personel personel = new Personel(ad, soyad, cinsiyet, departman, maa?, tarih);
    	personeller.add(personel);
    	
    	// Buradan sonras?nda veritaban? kay?t i?lemi ger?ekle?tirece?iz ! 
    	try {
			sorgu = ba?lant?.prepareStatement("insert into Tablo (Ad?,Soyad?,Tarih,Maa?,Cinsiyet,Departman) values (?,?,?,?,?,?)");
			sorgu.setString(1, ad);
			sorgu.setString(2, soyad);
			sorgu.setDate(3, Date.valueOf(tarih));
			sorgu.setDouble(4, maa?);
			sorgu.setString(5, cinsiyet);
			sorgu.setString(6, departman);
			sorgu.execute(); // sorguyu ba?latmaya yarayan kod dizimi ! 
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	
    	
    }

    @FXML
    void personelG?ncelle(ActionEvent event) {
    	
    	int index = tblPersonel.getSelectionModel().getSelectedIndex();
    	if (index != -1) {
    		LocalDate tarih = dpTarih.getValue();
        	for (int i = 0; i < personeller.size(); i++) {
    			if (todaydate.getYear() - tarih.getYear() < 19) {
    				hatamesaji.setTitle("Hata");
    			    hatamesaji.setHeaderText("Personel 18 ya??ndan k???k oldu?u i?in kay?t olamaz!");
    	    	    hatamesaji.show();
    	    	    return;
    			}
    		}
        	String ad = txtAd?.getText();
        	String soyad = txtSoyad?.getText();
        	double maa? = spnMaa?.getValue();
        	String cinsiyet = cmbCinsiyet.getValue();
        	String departman = cmbDepartman.getValue();
        	
        	Personel personel = new Personel(ad, soyad, cinsiyet, departman, maa?, tarih);
        	personeller.set(index, personel); // Buraya kadar yaz?lan kodlar tableview i?erisindeki kodlar?n g?ncellenmesini denetler !
        	
      
        	try {
    			sorgu = ba?lant?.prepareStatement("update Tablo set ADI=?,SOYADI=?,TAR?H=?,MAA?=?,C?NS?YET=?,DEPARTMAN=? where TAR?H ="+"'"+tarih+"'");
    		    sorgu.setString(1, ad);
    		    sorgu.setString(2, soyad);
    		    sorgu.setDate(3, Date.valueOf(tarih));
    		    sorgu.setDouble(4, maa?);
    		    sorgu.setString(5, cinsiyet);
    	     	sorgu.setString(6, departman);
    		    sorgu.executeUpdate();
        	} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}         
        	bilgimesaji.setTitle("Bilgi");
        	bilgimesaji.setHeaderText("Ba?ar? ile g?ncellendi...");
        	bilgimesaji.show();
		}
    	else {
			hatamesaji.setTitle("Hata");
			hatamesaji.setHeaderText("G?ncellenecek kay?t se?ilmedi...");
			hatamesaji.show();
		}
    	
    	
    	
    	
    }

    @FXML
    void personelSil(ActionEvent event) {

    int index = tblPersonel.getSelectionModel().getSelectedIndex();
    if (index != -1) {
		try {
			sorgu = ba?lant?.prepareStatement("delete from Tablo where Soyad?=" + "'" + personeller.get(index).getSoyad() + "'");
			personeller.remove(index);
			sorgu.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		bilgimesaji.setTitle("Bilgi");
		bilgimesaji.setHeaderText("Kay?t ba?ar? ile silindi...");
		bilgimesaji.show();
    	
	}
    	else {
			hatamesaji.setTitle("Hata");
			hatamesaji.setHeaderText("Silinecek kay?t se?ilmedi...");
			hatamesaji.show();
			return;
		}
    	
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Ba?lang??ta ?al??an methodtur! 
		
		spnMaa?.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(2200, 15000, 2850.99, 100.0));		
		// Cinsiyet Atama ??lemleri
		cmbCinsiyet.getItems().add("Erkek");
		cmbCinsiyet.getItems().add("Kad?n");
		// Departman Atama ??lemleri ! 
		cmbDepartman.getItems().add("Pazarlama");
		cmbDepartman.getItems().add("Sat??");
		cmbDepartman.getItems().add("?nsan Kaynaklar?");
		cmbDepartman.getItems().add("Bilgi ??lem");
		
		dpTarih.setValue(LocalDate.of(2001, 01, 01)); // important ! 
		
		s?tunAd?.setCellValueFactory(new PropertyValueFactory<>("ad"));
		s?tunSoyad?.setCellValueFactory(new PropertyValueFactory<>("soyad"));
		s?tunTarih.setCellValueFactory(new PropertyValueFactory<>("tarih"));
		s?tunMaa?.setCellValueFactory(new PropertyValueFactory<>("maa?"));
		s?tunCinsiyet.setCellValueFactory(new PropertyValueFactory<>("cinsiyet"));
		s?tunDeparman.setCellValueFactory(new PropertyValueFactory<>("departman"));
		
		tblPersonel.setItems(personeller);
		
		// Tablodan se?ilen ki?ilerin s?ras?yla bilgilerinin sol ekranda yans?mas?n? sa?layan kod sat?rlar? a?a??dad?r ! 
		tblPersonel.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				
				int index = newValue.intValue();
				if (index >-1) {
					Personel se?ilenpersonel = personeller.get(index);
					spnMaa?.getValueFactory().setValue(se?ilenpersonel.getMaa?());
					txtAd?.setText(se?ilenpersonel.getAd());
					txtSoyad?.setText(se?ilenpersonel.getSoyad());
					cmbCinsiyet.setValue(se?ilenpersonel.getCinsiyet());
					cmbDepartman.setValue(se?ilenpersonel.getDepartman());
					dpTarih.setValue(se?ilenpersonel.getTarih());
					
				}
				
				
				
			}
		});
		
		// Buradan sonra veri taban?na ba?lamak i?in driver tan?ml?yoruz ! 
	    try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			ba?lant? = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-NGB8CF4\\SQLEXPRESS;database=PersonelBilgileri;integratedSecurity=true");
		    System.out.println(ba?lant?.isValid(0)); // true d?nd?r?rse e?er ba?land? anlam?na gelmektedir !!! 
	    } catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
