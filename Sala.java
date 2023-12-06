package Esercizio_3_sala_cinematografica;

import java.util.ArrayList;
import java.util.Scanner;

public class Sala {
    private int grandezza_sala;
    private int posti_disponibili;
    private int numero_biglietti_acquistati;
    public ArrayList<Integer> sezioneSinistra = new ArrayList<Integer>();
    public ArrayList<Integer> sezioneCentrale = new ArrayList<Integer>();
    public ArrayList<Integer> sezioneDestra = new ArrayList<Integer>();

    Scanner input = new Scanner(System.in);

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
                this.posti_disponibili = grandezza_sala;
                break;
            }
        }
    }

    private void meccanismo_assegnazione_posti(int contatore, int numero_biglietti_acquistati) {
        
        int x = this.grandezza_sala - posti_disponibili;

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

        this.posti_disponibili = posti_disponibili - numero_biglietti_acquistati;
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
        
        System.out.println("Sono stati assegnati " + this.sezioneSinistra.size() + " posti a sinistra, " + this.sezioneCentrale.size() + " posti al centro e " + this.sezioneDestra.size() + " posti a destra per un totale di " + this.numero_biglietti_acquistati + " spettatori.");
    }

    public void acquistoNuoviBiglietti() {

        while(true) {

            if (this.numero_biglietti_acquistati == this.grandezza_sala) {
                System.out.println();
                System.out.println("Non è possibile acquistare nuovi biglietti, la sala è al completo.");
                break;
            }

            else {
                System.out.print("Quanti biglietti vuoi acquistare? Inserisci 0 se vuoi terminare: ");
                int nuovi_biglietti_acquistati = input.nextInt();
                int posti_disponibili = this.grandezza_sala - this.numero_biglietti_acquistati;

                if(nuovi_biglietti_acquistati < 0) {
                    while(true) {
                        if(nuovi_biglietti_acquistati < 0) {
                            System.out.print("Il numero di biglietti acquistati non può essere negativo, riprova: ");
                            nuovi_biglietti_acquistati = input.nextInt();
                        }

                        else {
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
                            System.out.print("Il numero di biglietti non può essere negativo o superiore alla capienza massima della sala, ricorda che il numero massimo di     biglietti acquistabili è " + posti_disponibili + ". Riprova o inserisci 0 per uscire: ");
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

                    this.numero_biglietti_acquistati += nuovi_biglietti_acquistati;
                }

                else {
                    break;
                }
            }
        }
    }
}
