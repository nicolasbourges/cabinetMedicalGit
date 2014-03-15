package com.iut.cabinet.presentation;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Collection;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import org.apache.log4j.Logger;

import com.iut.cabinet.application.GererPatientCtrl;
import com.iut.cabinet.application.HelperException;
import com.iut.cabinet.metier.CabinetTechniqueException;
import com.iut.cabinet.metier.Patient;
import com.iut.cabinet.user.PatientDTO;

/**
 * Classe correspondant au panel pour lister les Patient dans le MVC du cabinet m�dical
 * @author Nicolas BOURGES
 */
public class PanelListerPatients extends JPanel {
	private static Logger logger = Logger.getLogger(GererPatientIHM.class.getName());

	private static final long serialVersionUID = 1L;
	GererPatientCtrl ctrlUseCase= new GererPatientCtrl();

	/**
	 * Constructeur par d�faut de PanelCrerPatient
	 */
	public PanelListerPatients() {
		if(logger.isDebugEnabled()){
			logger.debug("Entr�e dans le constructeur de PanelListerpatients");
		}
		// Mise en place du Mod�le de donn�es � afficher
		// ///////////////////////////////////////////////
		Collection<PatientDTO> maListe;
		try {
			maListe = ctrlUseCase.listerPatients();
			PatientDTOTableModel maTableModele = new PatientDTOTableModel(maListe);
			// /////////////////////////////////////////////////////////////////////////
			// Affichage la JTable
			// que l'on doit placer au pr�alable dans un conteneur de type
			// JScrollPane
			// /////////////////////////////////////////////////////////////////////////
			JTable maTable = new JTable(maTableModele);
			JScrollPane monScrollPane = new JScrollPane(maTable);
			add(monScrollPane);
			maTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			maTable.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					if(logger.isDebugEnabled()){
						logger.debug("Entr�e dans la m�thode mouseClicked du constructeur de PanelListerpatients");
					}
					//cas du double clic
					if(e.getClickCount()==2 && e.getSource() instanceof JTable){
						JTable maTable = (JTable) e.getSource();
						int indiceLigne = maTable.getSelectedRow();
						Patient pat;
						try {
							pat = (Patient) ctrlUseCase.trouverPatient((String)maTable.getValueAt(indiceLigne, 0), (String)maTable.getValueAt(indiceLigne, 1), (String)maTable.getValueAt(indiceLigne, 3));
							Object[] options = { "OK" };
							JOptionPane.showOptionDialog(null,
									"Num�ro: "+indiceLigne+"\n"+
									"Nom: "+pat.getNom()+"\n"+
									"Pr�nom: "+pat.getPrenom()+"\n"+
									"Date de naissance: "+pat.getDateNaissance()+"\n"+
									"Homme: "+pat.isMale()+"\n"+
									"T�l�phone fixe: "+pat.getTelephone()+"\n"+
									"T�l�phone portable: "+pat.getPortable()+"\n"+
									"Mail: "+pat.getEmail()+"\n"+
									"Adresse: "+"\n"+pat.getAdresse().getNumero()+" "+pat.getAdresse().getRue()+"\n"+
										pat.getAdresse().getCodePostal()+" "+pat.getAdresse().getVille()+"\n"+
										pat.getAdresse().getPays()+"\n"+
									"Ascendant: "+pat.getUnAscendant()+"\n"+
									"Num�ro s�cu: "+pat.getNir()+"\n"+
									"M�decin traitant: "+pat.getMedecinTraitant()+"\n"
									, "Information",
									JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
									null, options, options[0]);
						} catch (ClassNotFoundException e1) {
							e1.getMessage();
						} catch (CabinetTechniqueException e1) {
							e1.getMessage();
							logger.fatal("D�clenchement d'une exception technique dans la m�thode mouseClicked du constructeur de PanelListerpatients");
						} catch (SQLException e1) {
							e1.getMessage();
						}
					}
					if(logger.isDebugEnabled()){
						logger.debug("Sortie de la m�thode mouseClicked du constructeur de PanelListerpatients");
					}
				}
			});
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (CabinetTechniqueException e) {
			e.printStackTrace();
			logger.fatal("D�clenchement d'une exception technique dans le constructeur de PanelListerpatients");
		} catch (HelperException e) {
			e.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		if(logger.isDebugEnabled()){
			logger.debug("Sortie du constructeur de PanelListerpatients");
		}
	}
}