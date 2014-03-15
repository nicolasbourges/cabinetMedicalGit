package com.iut.cabinet.essai;

import javax.swing.JFrame;

import com.iut.cabinet.presentation.CabMedMainFrame;

/**
 * Essai de la version fenêtrée de l'application pour le cabinet médical
 * @author Nicolas BOURGES
*/
public class CabMed_appliGraphique {

	public static void main(String[] args) {
		CabMedMainFrame fenetre = new CabMedMainFrame();
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setTitle("Cabinet Médical PIC'OUZ");
		fenetre.setSize(500,520);
		fenetre.setVisible(true);
	}

}
