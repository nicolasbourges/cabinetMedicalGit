package com.iut.cabinet.presentation;

import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import com.iut.cabinet.user.PatientDTO;

/**
 * Classe correspondant à la table à créer pour lister les Patient dans le MVC du cabinet médical
 * @author Nicolas BOURGES
 */
public class PatientDTOTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private Collection<PatientDTO> data;
	private String[] columnNames={"Nom", "Prénom", "Date de naissance", "NIR", "Ascendant"};
	
	/**
	 * Constructeur de PatientDTOTableModel à un paramètre 
	 * @param data contient la liste de Patient à mettre dans le tableau
	 */
	public PatientDTOTableModel (Collection<PatientDTO> data){
		this.data=data;
	}
	
	/**
	 * Méthode permettant de récupérer le nombre de colones du tableau
	 */
	public int getColumnCount() {
		return columnNames.length;
	}

	/**
	 * Méthode permettant de récupérer le nombre de lignes du tableau
	 */
	public int getRowCount() {
		if(this.data==null){
			return 0;
		}
		else{
			return this.data.size();
		}
	}

	/**
	 * Méthode permettant de récupérer la valeur d'une case du tableau
	 * @param rowIndex le numéro de ligne de la case
	 * @param columnIndex le numéro de colonne de la case
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		PatientDTO pat = (PatientDTO) data.toArray()[rowIndex];
		switch (columnIndex) {
		case 0:
			return pat.getNom();
		case 1:
			return pat.getPrenom();
		case 2:
			return pat.getDateNaissance();
		case 3:
			return pat.getNir();
		case 4:
			if(pat.getUnAscendant()!=null){
				return true;
			}
			else{
				return false;
			}
		default:
			throw new IllegalArgumentException("Colonne inconnue "+columnIndex);
		}
	}

	/**
	 * Méthode permettant de savoir si la case du tableau est éditable
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	/**
	 * Méthode permettant de récupérer le nom des colonnes du tableau
	 */
	@Override
	public String getColumnName(int arg0) {
		return this.columnNames[arg0];
	}

	/**
	 * Méthode permettant de récupérer la classe d'une colonne du tableau
	 * @param columnIndex le numéro de la colonne
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if(data==null){
			return Object.class;
		}
		else{
			Object objetRecupere = getValueAt(0, columnIndex);
			if(objetRecupere==null){
				return Object.class;
			}
			else{
				return objetRecupere.getClass();
			}
		}
	}
	
	
}
