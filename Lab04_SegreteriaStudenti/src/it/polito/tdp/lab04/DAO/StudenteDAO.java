package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public Studente ottieniNome(int matricola) {
		final String sql = "SELECT * " + 
				"FROM studente " + 
				"WHERE matricola=? ";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);

			Studente studente = null;
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				studente = new Studente(matricola, rs.getString("cognome"), rs.getString("nome"), rs.getString("cds"));
			}
			
			conn.close();
			return studente;
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	public List<Corso> cercaSuoiCorsi(int matricola){
		List<Corso> corsi= new LinkedList<Corso>();
		CorsoDAO dao= new CorsoDAO();
		final String sql = "SELECT codins " + 
				"FROM iscrizione " + 
				"WHERE matricola=? ";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);

			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				corsi.add(dao.getCorso(rs.getString("codins")));
				
			}

			
			conn.close();
			return corsi;
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	public boolean iscrittoAlCorso(int matricola, String codins){
		
		final String sql = "SELECT COUNT(*) AS cnt " + 
				"FROM iscrizione  " + 
				"WHERE matricola=? AND codins=? ";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			st.setString(2, codins);

			ResultSet rs = st.executeQuery();
			rs.next(); //unico risultato
			int numero= rs.getInt("cnt");	
			
			conn.close();
			return (numero>0);
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
}
