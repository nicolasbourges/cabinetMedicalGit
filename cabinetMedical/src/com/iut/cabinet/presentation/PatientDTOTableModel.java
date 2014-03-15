package com.iut.cabinet.presentation;

import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import com.iut.cabinet.user.PatientDTO;

/**
 * Classe correspondant � la table � cr�er pour lister les Patient dans le MVC du cabinet m�dical
 * @author Nicolas BOURGES
 */
public class PatientDTOTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private Collection<PatientDTO> data;
	private String[] columnNames={"Nom", "Pr�nom", "Date de naissance", "NIR", "Ascendant"};
	
	/**
	 * Constructeur de PatientDTOTableModel � un param�tre 
	 * @param data contient la liste de Patient � mettre dans le tableau
	 */
	public PatientDTOTableModel (Collection<PatientDTO> data){
		this.data=data;
	}
	
	/**
	 * M�thode permettant de r�cup�rer le nombre de colones du tableau
	 */
	public int getColumnCount() {
		return columnNames.length;
	}

	/**
	 * M�thode permettant de r�cup�rer le nombre de lignes du tableau
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
	 * M�thode permettant de r�cup�rer la valeur d'une case du tableau
	 * @param rowIndex le num�ro de ligne de la case
	 * @param columnIndex le num�ro de colonne de la case
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
	 * M�thode permettant de savoir si la case du tableau est �ditable
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	/**
	 * M�thode permettant de r�cup�rer le nom des colonnes du tableau
	 */
	@Override
	public String getColumnName(int arg0) {
		return this.columnNames[arg0];
	}

	/**
	 * M�thode permettant de r�cup�rer la classe d'une colonne du tableau
	 * @param columnIndex le num�ro de la colonne
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
