package com.iut.cabinet.essai;

import com.iut.cabinet.metier.CabinetTechniqueException;
import com.iut.cabinet.presentation.GererPatientIHM;

/**
 * Essai de la version console de l'application pour le cabinet médical
 * @author Nicolas BOURGES
*/
public class EssaiCabMed_console {

	public static void main(String[] args) throws CabinetTechniqueException {
		new GererPatientIHM();

	}

}
