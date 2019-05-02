package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	private StudenteDAO studenteDao;
	private CorsoDAO corsoDao;

	public Model() {

		studenteDao = new StudenteDAO();
		corsoDao = new CorsoDAO();
	}
	public List<Corso> getTuttiICorsi(){
		return corsoDao.getTuttiICorsi();
	}
	
	public Studente ottieniNome(int matricola) {
		return studenteDao.ottieniNome(matricola);
	}
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		return corsoDao.getStudentiIscrittiAlCorso(corso);
	}
	public Corso getCorso(String nome) {
		return corsoDao.getCorso(nome);
	}
	public List<Corso> cercaSuoiCorsi(int matricola){
		return studenteDao.cercaSuoiCorsi(matricola);
	}
}
