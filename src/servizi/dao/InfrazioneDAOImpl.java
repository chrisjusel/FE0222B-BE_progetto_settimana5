package servizi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.LoggerFactory;

import dati.Auto;
import dati.Infrazione;
import servizi.ConnectionFactory;

public class InfrazioneDAOImpl implements InfrazioneDAO {
	private static final String ELIMINA_INFRAZIONE = "delete from infrazione where id = ?";
	private static final String STAMPA_DATI_INFRAZIONE_AUTO_DA_TARGA = "select "
			+ "a.id auto_id, a.targa, a.marca, a.modello, i.id infr_id, i.tipo, i.importo, i.data"
			+ " from "
			+ "multe.infrazione as i" + 
			" inner join multe.auto as a" + 
			" on a.id = i.id_auto"
			+ " where a.targa = ?;";
	private static final String INSERISCI_INFRAZIONE = "INSERT INTO multe.infrazione("
			+ "	data, tipo, importo, id_auto)" + "	VALUES (?, ?, ?, ?);";
	private static org.slf4j.Logger log = LoggerFactory.getLogger(InfrazioneDAOImpl.class);

	@Override
	public boolean inserisciInfrazione(Infrazione infrazione) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps = null;
		int i = 0;
		try {
			ps = conn.prepareStatement(INSERISCI_INFRAZIONE);
			ps.setDate(1, infrazione.getData());
			ps.setString(2, infrazione.getTipo());
			ps.setDouble(3, infrazione.getImporto());
			ps.setInt(4, infrazione.getId_auto());
			i = ps.executeUpdate();
			log.info("Sono state inserite " + i + " righe");
		} catch (SQLException e) {
			log.error("Errore nell'inserimento", e);
		}
		try {
			conn.close();
		} catch (Exception e) {
		}

		return i > 0;
	}

	@Override
	public void stampaDatiInfrazioniAutoDaTarga(String targa) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = STAMPA_DATI_INFRAZIONE_AUTO_DA_TARGA;

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, targa);
			rs = ps.executeQuery();

			while (rs.next()) {
				Infrazione i = new Infrazione(rs.getInt("infr_id"),
						rs.getDate("data"), 
						rs.getString("tipo"), 
						rs.getDouble("importo"), 
						rs.getInt("auto_id") );
				Auto a = new Auto(rs.getInt("auto_id"),
						rs.getString("targa"), 
						rs.getString("marca"), 
						rs.getString("modello"));
				log.info("{} -> {}", i, a);
			}

		} catch (Exception e) {
			log.error("Errore nella ricerca dell'auto");
		}

		try {
			ps.close();
		} catch (Exception e) {
		}
		try {
			rs.close();
		} catch (Exception e) {
		}
		try {
			conn.close();
		} catch (Exception e) {
		}
	}

	@Override
	public boolean eliminaInfrazione(int id) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps = null;
		int i = 0;

		String q = ELIMINA_INFRAZIONE;
		try {
			ps = conn.prepareStatement(q);
			ps.setInt(1, id);
			i = ps.executeUpdate();
			log.info("Cancellazione riga avvenuta");
		} catch (SQLException e) {
			log.error("Errore cancellazione");
		}

		try {
			conn.close();
		} catch (Exception e) {
		}
		return i > 0;
	}

}
