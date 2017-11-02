package Utility;

import Carte.Carta;
import Carte.TipoCarta;

public abstract class CTRLPacket {
	private Packet pacchetto;
	
	public CTRLPacket(Packet p) {
		this.pacchetto = p;
		this.callToAction();
	}	
	
	//Funzione che richiama la funzione astratta dell'evento
	public void callToAction() {
		switch (this.pacchetto.getEvento()) {
		case turno: //E' il turno del client che riceve il pacchetto
			this.myTurn(); 
			break;
		case aspetta: //E' il turno dell'avversario 
			this.otherTurn();
			break;
		case sconfitta: //Il client ha perso
			this.hoPerso();
			break;
		case subisci: //Il client avversario ha giocato una carta
			this.callSubisci(this.pacchetto.getCarte());
			break;
		case vittoria: //Il client ha vinto
			this.hoVinto();
			break;
		
		default:
			System.out.println("EVENTO NON VALIDO!");
			break;
		}
	}
	
	public void callSubisci(Carta[] carte) {
		//Gestisci gli eventi delle tipologie delle carte
		if(carte == null) return;
		TipoCarta tipoSubito = carte[0].getTipoCarta();
		if( tipoSubito == TipoCarta.Normale ) this.subisciNormale(carte[0]);
		else if( tipoSubito == TipoCarta.Piu2 ) this.subisciPIU2(carte[0], carte[1], carte[2]);
		else if( tipoSubito == TipoCarta.Piu4 ) this.subisciPIU4( carte[0], carte[1], carte[2], carte[3], carte[4] );
		else if( tipoSubito == TipoCarta.CambioColore ) this.subisciCambiaColore( carte[0] );
		else if( tipoSubito == TipoCarta.Stop ) this.subisciStop( carte[0] );
		else if( tipoSubito == TipoCarta.CambioGiro ) this.subisciCambioGiro( carte[0] );
		
		return;
	}
	
	public abstract void myTurn();
	public abstract void otherTurn();
	public abstract void hoPerso();
	public abstract void hoVinto();
	
	//Subisci
	public abstract void subisciNormale(Carta c);
	public abstract void subisciPIU2(Carta piu2, Carta c1, Carta c2);
	public abstract void subisciPIU4(Carta piu4, Carta c1, Carta c2, Carta c3, Carta c4);
	public abstract void subisciCambiaColore(Carta c);
	public abstract void subisciStop(Carta c);
	public abstract void subisciCambioGiro(Carta c);
	
}
