# Esercizio 3x03 Java – Cinema
#### Traccia base
Una sala cinematografica è dotata di 300 posti suddivisi in 3 zone da 100 posti: sinistra,
destra, centrale.
Realizzare un programma che, chiedendo il numero di spettatori all'utente (valore da
incapsulare nel modo più efficace possibile), assegni loro i posti occupandoli con i rispettivi
numeri dei biglietti d'ingresso, i quali devono essere unici per tutta la sala, distribuendoli
equamente e automaticamente nelle tre zone della sala. Esempi:
* 7 spettatori saranno ripartiti 2 a sinistra, 3 al centro, 2 a destra;
* 20 spettatori saranno ripartiti 7 a sinistra, 7 al centro, 6 a destra.
Stampare a fine "spettacolo" un riepilogo della distribuzione dei posti in ciascuna zona della
sala attraverso i numeri dei biglietti, nonché un totale sull'affluenza; esempio di riepilogo da
mostrare su terminale:
Zona sinistra: 2 5 8 11 14 17 20
Zona centrale: 1 4 7 10 13 16 19
Zona destra: 3 6 9 12 15 18
Assegnati 7 posti a sinistra, 7 posti al centro, 6 posti a destra;
totale 20 spettatori.
Un possibile approccio al problema, implementando gli opportuni metodi necessari:
1. Generare biglietti univoci numerati ricevendo un input dall'utente (incapsulamento)
2. Assegnare i biglietti a rotazione alle tre zone della sala
3. Stampare il numero di posti occupati per ogni zona e il totale.
#### Traccia avanzata
Dopo aver completato la traccia base e aver ottenuto riscontro positivo dal docente,
sviluppare una versione potenziata del medesimo programma che permetta di:
* effettuare acquisti successivi fintanto che la sala ha posti disponibili;
* gestire secondo gli stessi criteri una sala di capienza qualsiasi, sempre suddivisa in
tre zone.
#### Traccia extra
A chi avesse completato la traccia avanzata entro le ore lavorative dedicate, il docente ha proposto di completare il programma con la visualizzazione su terminale della mappatura della sala cinematografica, distinguendo i posti liberi da quelli occupati.