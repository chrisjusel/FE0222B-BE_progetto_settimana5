package servizi.dao;

import java.util.ArrayList;

import dati.Auto;

public interface AntoDAO {

	boolean inserisciAuto(Auto auto);

	ArrayList<Auto> getAllAuto();

	Auto cercaAuto(String targa);

}