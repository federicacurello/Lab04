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
		final String sql = "SELECT nome, cognome " + 
				"FROM studente " + 
				"WHERE matricola=? ";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);

			ResultSet rs = st.executeQuery();
			Studente s= new Studente(matricola, rs.getString("cognome"), rs.getString("nome"));
			
			conn.close();
			return s;
			
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
}
