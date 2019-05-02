
/**
 * Sample Skeleton for 'SegreteriaStudenti.fxml' Controller Class
 */

package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {
	List<Corso> corsi;
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="menuCorso"
    private ComboBox<?> menuCorso; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaIscritti"
    private Button btnCercaIscritti; // Value injected by FXMLLoader

    @FXML // fx:id="txtMatricola"
    private TextField txtMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaNome"
    private Button btnCercaNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader
    
    @FXML // fx:id="txtCognome"
    private TextField txtCognome; // Value injected by FXMLLoader


    @FXML // fx:id="btnCercaCorsi"
    private Button btnCercaCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="btnIscrivi"
    private Button btnIscrivi; // Value injected by FXMLLoader

    @FXML // fx:id="txtArea"
    private TextArea txtArea; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader
    
    public void setModel(Model model) {

		this.model = model;
		setComboItems();
	}

	private void setComboItems() {

		// Ottieni tutti i corsi dal model
		corsi = model.getTuttiICorsi();

		// Aggiungi tutti i corsi alla ComboBox
		Collections.sort(corsi);
		menuCorso.getItems().addAll(corsi);
		menuCorso.getItems().add("");
		menuCorso.getSelectionModel().selectLast();
	}

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	txtArea.clear();
try {
	    	
	    	
	    	if(txtMatricola.equals("")) {
	    		txtArea.appendText("Errore, inserire la matricola");
	    				return;
	    	}
	    	
	    	List<Corso> corsi = model.cercaSuoiCorsi(Integer.parseInt(txtMatricola.getText()));

			StringBuilder sb = new StringBuilder();

			for (Corso c : corsi) {

				/*sb.append(String.format("%-10s ", studente.getMatricola()));
				sb.append(String.format("%-20s ", studente.getCognome()));
				sb.append(String.format("%-20s ", studente.getNome()));
				sb.append(String.format("%-10s ", studente.getCds()));
				*/
				sb.append(c.getCodins()+" "+c.getCrediti()+" "+c.getNome()+" "+c.getPd());
				sb.append("\n");
			}

			txtArea.appendText(sb.toString());
			
			} catch (RuntimeException e) {
				txtArea.setText("ERRORE DI CONNESSIONE AL DATABASE!");
			}

    }

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	txtArea.clear();
		txtNome.clear();
		txtCognome.clear();
		try {
	    	
	    	
	    	if(menuCorso.getValue()=="") {
	    		txtArea.appendText("Errore, inserire il corso");
	    				return;
	    	}
	    	
	    	List<Studente> studenti = model.getStudentiIscrittiAlCorso((Corso)menuCorso.getValue());

			StringBuilder sb = new StringBuilder();

			for (Studente s : studenti) {

				/*sb.append(String.format("%-10s ", studente.getMatricola()));
				sb.append(String.format("%-20s ", studente.getCognome()));
				sb.append(String.format("%-20s ", studente.getNome()));
				sb.append(String.format("%-10s ", studente.getCds()));
				*/
				sb.append(s.getMatricola()+" "+s.getCognome()+" "+s.getNome()+" "+s.getCds());
				sb.append("\n");
			}

			txtArea.appendText(sb.toString());
			
			} catch (RuntimeException e) {
				txtArea.setText("ERRORE DI CONNESSIONE AL DATABASE!");
			}

    }

    @FXML
    void doCercaNome(ActionEvent event) {
    	txtArea.clear();
		txtNome.clear();
		txtCognome.clear();
		
		try {
    	int matricola=Integer.parseInt(txtMatricola.getText());
    	Studente result= model.ottieniNome(matricola);
    	
    	if(result==null) {
    		txtArea.appendText("Matricola inesistente");
    				return;
    	}
    	txtNome.appendText(result.getNome());
    	txtCognome.appendText(result.getCognome());
    	
		}catch (NumberFormatException e) {
			txtArea.setText("Inserire una matricola nel formato corretto.");
		} catch (RuntimeException e) {
			txtArea.setText("ERRORE DI CONNESSIONE AL DATABASE!");
		}

    }

    @FXML
    void doIscrivi(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtArea.clear();
    	menuCorso.getSelectionModel().clearSelection();

    }

    @FXML
    void doSceltaCorso(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert menuCorso != null : "fx:id=\"menuCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaNome != null : "fx:id=\"btnCercaNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtArea != null : "fx:id=\"txtArea\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        
      
    }
}
