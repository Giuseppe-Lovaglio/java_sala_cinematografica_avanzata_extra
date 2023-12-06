package Esercizio_3_sala_cinematografica;

import java.util.ArrayList;
import java.util.Scanner;

public class SalaMappa {
    private int grandezza_sala;
    private int posti_disponibili;
    private int numero_biglietti_acquistati;
    public ArrayList<Integer> sezioneSinistra = new ArrayList<Integer>();
    public ArrayList<Integer> sezioneCentrale = new ArrayList<Integer>();
    public ArrayList<Integer> sezioneDestra = new ArrayList<Integer>();

    Scanner input = new Scanner(System.in);

    public int getGrandezza_sala() {
        return grandezza_sala;
    }

    public int getPosti_disponibili() {
        return posti_disponibili;
    }

    public int getNumero_biglietti_acquistati() {
        return numero_biglietti_acquistati;
    }

    public void setPosti_disponibili(int posti_disponibili) {
        if (posti_disponibili <= (getGrandezza_sala() - getNumero_biglietti_acquistati())) {
            this.posti_disponibili = posti_disponibili;
        }

        else {
            System.out.println("Il numero di posti disponibili non è valido.");
        }
    }

    public void setNumero_biglietti_acquistati(int numero_biglietti_acquistati) {
        if (numero_biglietti_acquistati > 0) {
            this.numero_biglietti_acquistati = numero_biglietti_acquistati;
        }

        else {
            System.out.println("Il valore del numero di biglietti acquistato non è valido.");
        }
    }

    public void setGrandezza_sala() {
        System.out.print("Quanti posti a sedere ha la sala? -> ");
        int grandezza_sala = input.nextInt();

        while(true) {
            if (grandezza_sala < 1 ) {
                System.out.print("La dimensione inserita non può avere un valore negativo o pari a zero, riprova: ");
                grandezza_sala = input.nextInt();
            }

            else {
                this.grandezza_sala = grandezza_sala;
                setPosti_disponibili(grandezza_sala);
                break;
            }
        }
    }

    private void meccanismo_assegnazione_posti(int contatore, int numero_biglietti_acquistati) {
        
        int x = getGrandezza_sala() - getPosti_disponibili();

        for (int i = 1; i<numero_biglietti_acquistati+1; i++) {

            if (contatore > 2) {
                contatore = 0;
            }

            if (contatore == 0) {
                sezioneCentrale.add(i+x);
            }

            else if (contatore == 1) {
                sezioneDestra.add(i+x);
            }

            else {
                sezioneSinistra.add(i+x);
            }

            contatore++;
        }

        setPosti_disponibili(posti_disponibili - numero_biglietti_acquistati);
    }

    public void fineSpettacolo() {
        System.out.println("--- Riepilogo ---");
        System.out.print("Zona sinistra: ");
        for (int e : this.sezioneSinistra) {
            System.out.print(e + " ");
        }
        System.out.println();
        System.out.print("Zona centrale: ");
        for (int e : this.sezioneCentrale) {
            System.out.print(e + " ");
        }
        System.out.println();
        System.out.print("Zona   destra: ");
        for (int e : this.sezioneDestra) {
            System.out.print(e + " ");
        }
        System.out.println();
        
        System.out.println("Sono stati assegnati " + this.sezioneSinistra.size() + " posti a sinistra, " + this.sezioneCentrale.size() + " posti al centro e " + this.sezioneDestra.size() + " posti a destra per un totale di " + getNumero_biglietti_acquistati() + " spettatori.");
    }

    public void acquistoNuoviBiglietti() {

        while(true) {

            if (getNumero_biglietti_acquistati() == getGrandezza_sala()) {
                System.out.println();
                System.out.println("Non è possibile acquistare nuovi biglietti, la sala è al completo.");
                break;
            }

            else {
                System.out.print("Quanti biglietti vuoi acquistare? Inserisci 0 se vuoi terminare: ");
                int nuovi_biglietti_acquistati = input.nextInt();
                int posti_disponibili = getGrandezza_sala() - getNumero_biglietti_acquistati();
                System.out.println("Hai acquistato correttamente " + nuovi_biglietti_acquistati + " biglietti.");

                if(nuovi_biglietti_acquistati < 0) {
                    while(true) {
                        if(nuovi_biglietti_acquistati < 0) {
                            System.out.print("Il numero di biglietti acquistati non può essere negativo, riprova o inserisci 0 per terminare: ");
                            nuovi_biglietti_acquistati = input.nextInt();
                        }

                        else {
                            System.out.println("Hai acquistato correttamente " + nuovi_biglietti_acquistati + " biglietti.");
                            break;
                        }
                    }
                }
            
                if (nuovi_biglietti_acquistati > posti_disponibili) {
                    System.out.println("Il numero massimo di biglietti acquistabili è " + posti_disponibili + ".");
                    System.out.print("Inserisci il numero di biglietti che vuoi acquistare o 0 per uscire: ");
                    nuovi_biglietti_acquistati = input.nextInt();
                    while(true) {
                        if (nuovi_biglietti_acquistati < 0 || nuovi_biglietti_acquistati > posti_disponibili) {
                            System.out.print("Il numero di biglietti non può essere negativo o superiore alla capienza massima della sala, ricorda che il numero massimo di biglietti acquistabili è " + posti_disponibili + ". Riprova o inserisci 0 per uscire: ");
                            nuovi_biglietti_acquistati = input.nextInt();
                        }

                        else {
                            break;
                        }
                    }
                }

                if (nuovi_biglietti_acquistati > 0) {
                    int posti_occupati_sezione_ce = this.sezioneCentrale.size();
                    int posti_occupati_sezione_dx = this.sezioneDestra.size();
                    int posti_occupati_sezione_sx = this.sezioneSinistra.size();
                    int contatore;
                    if (posti_occupati_sezione_dx < posti_occupati_sezione_sx || posti_occupati_sezione_dx < posti_occupati_sezione_ce) {
                        contatore = 1;
                    }
                    else if (posti_occupati_sezione_sx < posti_occupati_sezione_dx || posti_occupati_sezione_sx < posti_occupati_sezione_ce) {
                        contatore = 2;
                    }
                    else {
                        contatore = 0;
                    }

                    this.meccanismo_assegnazione_posti(contatore, nuovi_biglietti_acquistati);

                    setNumero_biglietti_acquistati(getNumero_biglietti_acquistati() + nuovi_biglietti_acquistati);
                }

                else {
                    break;
                }
            }
        }
    }

    public void rappresentazione_grafica() {
        ArrayList<Integer> dimensioni = new ArrayList<Integer>();
        dimensioni.add(1);

        int sala_diviso_tre = (getGrandezza_sala()-3) / 3;
        int resto_divisione_sala = (getGrandezza_sala()-3) % 3;

        for (int x=0; x<sala_diviso_tre; x++) {  // creo un array la cui dimensione rappresenta le righe che devo stampare e il valore di ogni elemento il numero di colonne di quella riga
            for (int i=dimensioni.size(); i-- > 0;) { // faccio in modo che il ciclo parta dall'ultimo elemento
                int elemento = dimensioni.get(i);
                if (elemento < dimensioni.size()) {
                    dimensioni.set(dimensioni.indexOf(elemento), (elemento+1));
                    break;
                }

                else {
                    dimensioni.add(1);
                    break;
                }
            }
        }

        int indice_del_piu_piccolo = 0;
        int valore_massimo = dimensioni.get(0);
        
        for (int j=0; j<dimensioni.size(); j++) {  //trovo l'indice con il valore piu piccolo
            int valore = dimensioni.get(j);
            if (valore < valore_massimo) {
                indice_del_piu_piccolo = j;
                break;
            }
        }

        int copia_biglietti_acquistati_sx = sezioneSinistra.size();
        int copia_biglietti_acquistati_ce = sezioneCentrale.size();
        int copia_biglietti_acquistati_dx = sezioneDestra.size();
        int copia_biglietti_acquistati = 0;
        int max_stampati_0 = 0;
        int max_stampati_1 = 0;
        int max_stampati_2 = 0;
        int stampati = 0;
        int differenza = 0;

        System.out.println();
        for (int i=0; i<dimensioni.size(); i++) {  // inizio ciclo righe
            for (int y=0; y<3; y++) {  // ripeto per ogni sezione
                stampati = 0;
                switch (y) {
                    case 0: 
                        copia_biglietti_acquistati = copia_biglietti_acquistati_sx;
                        break;
                    case 1:
                        copia_biglietti_acquistati = copia_biglietti_acquistati_ce;
                        break;
                    case 2:
                        copia_biglietti_acquistati = copia_biglietti_acquistati_dx;
                        break;
                }
                
                for (int n=0; n<dimensioni.get(i); n++) {
                    if (copia_biglietti_acquistati > 0) {
                        System.out.print("[O]");
                        switch (y) {
                            case 0: 
                                copia_biglietti_acquistati_sx -= 1;
                                break;
                            case 1:
                                copia_biglietti_acquistati_ce -= 1;
                                break;
                            case 2:
                                copia_biglietti_acquistati_dx -= 1;
                                break;
                        }
                        copia_biglietti_acquistati -= 1;
                    }

                    else {
                        System.out.print("[ ]");
                    }
                    stampati += 1;  // conto quante sedie vengono stampate
                }
                if (i == indice_del_piu_piccolo) {  // condizione per aggiungere la sedia solo nella riga con meno sedie
                    if (resto_divisione_sala > 0) {
                        boolean aggiungo = true;  // condizione che mi permette di aggiungere il posto di resto solo nella sezione dove sono stati assegnati piu biglietti, perché nel caso di acquisto di tutti i biglietti i posti stampati non coincidevano con il numero di persone sedute
                        switch (y) {
                            case 0:
                                if (this.sezioneSinistra.size() < this.sezioneCentrale.size() ||
                                    this.sezioneSinistra.size() < this.sezioneDestra.size()) {
                                        aggiungo = false;
                                }
                                break;
                            case 1:
                                if (this.sezioneCentrale.size() < this.sezioneSinistra.size() ||
                                    this.sezioneCentrale.size() < this.sezioneDestra.size()) {
                                        aggiungo = false;
                                }
                                break;
                            case 2:
                                if (this.sezioneDestra.size() < this.sezioneSinistra.size() ||
                                    this.sezioneDestra.size() < this.sezioneCentrale.size()) {
                                        aggiungo = false;
                                }
                                break;
                        }

                        if (aggiungo) {
                            if (copia_biglietti_acquistati > 0) {
                                System.out.print("[O]");
                                switch (y) {
                                    case 0: 
                                        copia_biglietti_acquistati_sx -= 1;
                                        break;
                                    case 1:
                                        copia_biglietti_acquistati_ce -= 1;
                                        break;
                                    case 2:
                                        copia_biglietti_acquistati_dx -= 1;
                                        break;
                                }
                                copia_biglietti_acquistati -= 1;
                            }

                            else {
                                System.out.print("[ ]");
                            }
                            stampati += 1;
                            resto_divisione_sala -= 1;
                        }
                    }
                }

                switch(y) {  // per capire quante volte deve stampare lo spazio tra le righe delle sezioni
                    case 0:
                        if (stampati > max_stampati_0) {
                            max_stampati_0 = stampati;
                        }
                        differenza = max_stampati_0 - stampati;
                        break;
                    case 1:
                        if (stampati > max_stampati_1) {
                            max_stampati_1 = stampati;
                        }
                        differenza = max_stampati_1 - stampati;
                        break;
                    case 2:
                        if (stampati > max_stampati_2) {
                            max_stampati_2 = stampati;
                        }
                        differenza = max_stampati_2 - stampati;
                        break;
                }

                for (int xx=0; xx <= differenza; xx++) {
                    System.out.print("   ");
                }
            }
            System.out.println();    
        }
        System.out.println();
    }
}
