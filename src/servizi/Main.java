package servizi;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

import dati.Auto;
import dati.Infrazione;
import servizi.dao.AntoDAO;
import servizi.dao.AutoDAOImpl;
import servizi.dao.InfrazioneDAO;
import servizi.dao.InfrazioneDAOImpl;

public class Main {

	public static void main(String[] args) {
		AntoDAO autoDao = new AutoDAOImpl();
		InfrazioneDAO infrazioneDao = new InfrazioneDAOImpl();
		Scanner scanner = new Scanner(System.in);
		
		int scelta = 0;
		
		do {
			System.out.println("-----------------------------------------------\n"
					+ "1 - Inserisci Dati auto\n"
					+ "2 - Inserisci Dati Infrazione\n"
					+ "3 - Visualizza tutte le auto\n"
					+ "4 - Cerca Auto da Targa\n"
					+ "5 - Visualizza dati Infrazioni e Auto da Targa\n"
					+ "6 - Elimina Infrazione\n\n"
					+ "0 - Termina il programma\n"
					+ "-----------------------------------------------\n");
			scelta = Integer.parseInt(scanner.nextLine());
			switch (scelta) {
			case 1:
				System.out.println("Inserisci targa:");
				String targa = scanner.nextLine();
				System.out.println("Inserisci marca:");
				String marca = scanner.nextLine();
				System.out.println("Inserisci modello:");
				String modello = scanner.nextLine();
				Auto auto = new Auto(targa, marca, modello);
				autoDao.inserisciAuto(auto);
				break;
			case 2:
				System.out.println("Inserisci la data dell'infrazione:");
				System.out.print("	Anno dell'infrazione: ");
				int annoInfrazione = Integer.parseInt(scanner.nextLine());
				System.out.print("	Mese dell'infrazione (numerico): ");
				int meseInfrazione = Integer.parseInt(scanner.nextLine());
				System.out.print("	Giorno dell'infrazione (numerico): ");
				int giornoInfrazione = Integer.parseInt(scanner.nextLine());
				System.out.println("Inserisci il tipo dell'infrazione");
				String tipoInfrazione = scanner.nextLine();
				System.out.println("Inserisci l'importo dell'infrazione");
				double importoInfrazione = Double.parseDouble(scanner.nextLine());
				System.out.println("Inserisci la targa dell'infrazione");
				int idAuto = autoDao.cercaAuto(scanner.nextLine()).getId();
				Infrazione infrazione = new Infrazione(new Date(annoInfrazione-1900, meseInfrazione, giornoInfrazione), tipoInfrazione, importoInfrazione, idAuto);
				infrazioneDao.inserisciInfrazione(infrazione);
				break;
			case 3:
				System.out.println("Lista di tutte le auto:\n");
				ArrayList<Auto> listaAuto = autoDao.getAllAuto();
				listaAuto.forEach(a -> System.out.println(a));
				break;
			case 4:
				System.out.println("Inserisci la targa dell'auto da ricercare");
				String targaRicerca = scanner.nextLine();
				Auto autoRicerca = autoDao.cercaAuto(targaRicerca);
				System.out.println(autoRicerca);
				break;
			case 5: 
				System.out.println("Inserisci la targa dell'auto");
				String targaInfrazioni = scanner.nextLine();
				infrazioneDao.stampaDatiInfrazioniAutoDaTarga(targaInfrazioni);
				break;
			case 6:
				System.out.println("Inserisci l'id dell'infrazione da eliminare");
				int idAutoElimina = Integer.parseInt(scanner.nextLine());
				infrazioneDao.eliminaInfrazione(idAutoElimina);
			}
		} while (scelta != 0);	
	}

}
