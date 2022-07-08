package servizi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.LoggerFactory;

import dati.Auto;
import servizi.ConnectionFactory;

public class AutoDAOImpl implements AntoDAO {
	private static final String CERCA_AUTO = "select * from auto where targa = ?";
	private static final String GET_ALL_AUTO = "select * from auto";
	private static final String INSERISCI_AUTO = "INSERT INTO multe.auto("
			+ "	targa, marca, modello)"
			+ "	VALUES (?, ?, ?);";
	private static org.slf4j.Logger log = LoggerFactory.getLogger(AutoDAOImpl.class);
	
	@Override
	public boolean inserisciAuto(Auto auto) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps = null;
		int i = 0;
		String query = INSERISCI_AUTO;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, auto.getTarga());
			ps.setString(2, auto.getMarca());
			ps.setString(3, auto.getModello());
			i = ps.executeUpdate();
			log.info("Sono state inserite "+i+" righe");
		} catch (SQLException e) {
			log.error("Errore nell'inserimento", e);
		}
		try {conn.close();} catch (Exception e) {}
		
		return i > 0;
	}
	
	@Override
	public ArrayList<Auto> getAllAuto() {
		Connection conn = ConnectionFactory.getConnection();
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Auto> listaAuto = null;
		String query = GET_ALL_AUTO;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			
			listaAuto = new ArrayList<Auto>();
			
			while(rs.next()) {
				listaAuto.add(new Auto(rs.getInt("id"), rs.getString("targa"), rs.getString("marca"), rs.getString("modello")));
			}
			log.info("Recupero auto avvenuto con successo");
		} catch (Exception e) {
			log.error("Errore nel recupero delle auto");
		}
		try {rs.close();} catch (Exception e) {}
		try {conn.close();} catch (Exception e) {}
		
		return listaAuto;
	}
	
	@Override
	public Auto cercaAuto(String targa) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Auto auto = null;
		String query = CERCA_AUTO;
		
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, targa);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				auto = new Auto(rs.getInt("id"), rs.getString("targa"), rs.getString("marca"), rs.getString("modello"));
				log.info("Auto trovata");
			}
		} catch (Exception e) {
			log.error("Errore nella rircerca dell'auto");
		}
		
		try {ps.close();} catch (Exception e) {}
		try {rs.close();} catch (Exception e) {}
		try {conn.close();} catch (Exception e) {}
		return auto;
	}
}
