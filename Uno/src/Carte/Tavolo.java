package Carte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Tavolo {
	private Stack<Carta> tavolo;					//STACK	
	
	public Tavolo(){
		tavolo = new Stack<Carta>();
	}
	
	public void pushToTavolo(Carta c){
		tavolo.push(c);
	}
	
	public Carta popTavolo(){
		return tavolo.peek();					//legge da testa
	}
	
	public boolean pushToTavoloControl(Carta c){
		if(tavolo.isEmpty()){
			return true;
		}
		Carta pop = tavolo.peek();
		if(c.getTipoCarta()==TipoCarta.Piu4 || c.getTipoCarta()==TipoCarta.CambioColore){ 			//carte speciali
			return true;
		}
		if(c.getColoreCarta() == pop.getColoreCarta() || c.getNumeroCarta() == pop.getNumeroCarta()){		//controllo colore e numero
			return true; 
		}
		return false;
	}
	
	public ArrayList<Carta> changeToMazzo(){
		
		Carta first = tavolo.pop();							//salvo il primo elemento
		ArrayList mazzo = new ArrayList(tavolo); 			//creo l'arraylist da ritornare
		tavolo.clear();										//pulisco il tavolo
		tavolo.push(first);									//carico la prima carta
		Collections.shuffle(mazzo);							//mescola
		return mazzo;										//ritorno mazzo
	}
}