package servizi.dao;

import dati.Infrazione;

public interface InfrazioneDAO {

	boolean inserisciInfrazione(Infrazione infrazione);

	void stampaDatiInfrazioniAutoDaTarga(String targa);

	boolean eliminaInfrazione(int id);

}