package com.iut.cabinet.presentation;


import javax.swing.*;

import org.apache.log4j.Logger;

import com.iut.cabinet.application.GererPatientCtrl;
import com.iut.cabinet.application.HelperException;
import com.iut.cabinet.metier.CabinetMedicalException;
import com.iut.cabinet.metier.CabinetTechniqueException;
import com.iut.cabinet.user.AdresseDTO;
import com.iut.cabinet.user.PatientDTO;
import com.iut.cabinet.util.DateUtil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Classe correspondant au panel pour cr�er un Patient dans le MVC du cabinet m�dical
 * @author Nicolas BOURGES
 */
public class PanelCreerPatient extends JPanel {
	private static Logger logger = Logger.getLogger(GererPatientIHM.class.getName());
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructeur par d�faut de PanelCrerPatient
	 */
	public PanelCreerPatient() {
		PremierPanel();
	}

	GererPatientCtrl ctrlUseCase= new GererPatientCtrl();
	
	/**
	 * M�thode permettant de r�initialiser le formulaire
	 * @param o le champ � remttre � "z�ro"
	 */
	@SuppressWarnings("unchecked")
	private void reinitialiserFormulaire(Object o){
		if(logger.isDebugEnabled()){
			logger.debug("Entr�e dans la m�thode reinitialiserFormulaire de PanelCreerpatient");
		}
		if(o instanceof JTextField){
			((JTextField) o).setText("");
		}
		if(o instanceof JComboBox){
			((JComboBox<String>) o).setSelectedIndex(0);
		}
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la m�thode reinitialiserFormulaire de PanelCreerpatient");
		}
	}
	
	/**
	 * Constructeur par d�faut de PremierPanel
	 */
	public void PremierPanel(){
		if(logger.isDebugEnabled()){
			logger.debug("Entr�e dans le constructeur de PanelCreerpatient");
		}
		
		final JTextField nirField = new JTextField(15);
		nirField.setToolTipText("Saisir ici les 15 caract�res du NIR sans espace");
		JLabel nirLabel = new JLabel("NIR (cl� incluse): ");
		
		
		final JComboBox<String> listeGroupe = new JComboBox<String>();
		listeGroupe.addItem("M.");
		listeGroupe.addItem("Mme");
		listeGroupe.addItem("Melle");
		listeGroupe.setPreferredSize(new Dimension(333,20));
		JLabel mLabel = new JLabel("Titre:                                ");
		
		
		int textWidth=30;
		int textWidthBis=35;
		int medFieldWidth=44;
		
		final JTextField nomField = new JTextField(textWidth);
		JLabel nomLabel = new JLabel("Nom: ");
		nomField.setToolTipText("Le nom doit �tre en majuscules, exemple: DUPONT");
		final JTextField prenomField = new JTextField(textWidth);
		JLabel prenomLabel = new JLabel("Pr�nom: ");
		prenomField.setToolTipText("Le pr�nom doit commencer par une majuscule, puis en minuscule. Pas d'accent. Ex: Kevin");
		final JTextField dateField = new JTextField(textWidth);
		JLabel dateLabel = new JLabel("Date de naissance: ");
		dateField.setToolTipText("La date doit �tre au format Am�ricain - AAAA-MM-JJ");
		final JTextField telField = new JTextField(textWidth);
		JLabel telLabel = new JLabel("T�l�phone personnel: ");
		telLabel.setToolTipText("Le t�l�phone est au format 0x.xx.xx.xx.xx avec x un chiffre");
		final JTextField portableField = new JTextField(textWidth);
		JLabel portableLabel = new JLabel("T�l�phone portable: ");
		portableField.setToolTipText("Le t�l�phone est au format 0x.xx.xx.xx.xx avec x un chiffre");
		final JTextField mailField = new JTextField(textWidth);
		JLabel mailLabel = new JLabel("Mail personnel: ");
		mailField.setToolTipText("Le mail est au format blabla@voicivoila.fr");
		
		
		final JLabel adr = new JLabel("Adresse");
			adr.setHorizontalAlignment(JLabel.CENTER);
		
		
		final JTextField numField = new JTextField(textWidthBis);
		JLabel numLabel = new JLabel("Num�ro: ");
		final JTextField rueField = new JTextField(textWidthBis);
		JLabel rueLabel = new JLabel("Rue: ");
		final JTextField voieField = new JTextField(textWidthBis);
		JLabel voieLabel = new JLabel("Voie: ");
		final JTextField batField = new JTextField(textWidthBis);
		JLabel batLabel = new JLabel("B�timent: ");
		final JTextField cpField = new JTextField(textWidthBis);
		JLabel cpLabel = new JLabel("Code postal: ");
		cpField.setToolTipText("Le code postal comporte 5 chiffres - ex: 87220");
		final JTextField villeField = new JTextField(textWidthBis);
		JLabel villeLabel = new JLabel("Ville: ");
		final JTextField paysField = new JTextField(textWidthBis);
		JLabel paysLabel = new JLabel("Pays: ");
		
		
		final JTextField medField = new JTextField(medFieldWidth);
		JLabel medLabel = new JLabel("M�decin traitant (nom et pr�nom � saisir): ");
		
		
		JButton valid = new JButton("Valider");
		JButton quit = new JButton("Quitter");
		
		//Listener bouton Valider
		valid.addActionListener(new ActionListener() {
			
		int erreurs=0;
			
			public void actionPerformed(ActionEvent ap) {
				if(logger.isDebugEnabled()){
					logger.debug("Entr�e dans la m�thode actionPerformed du bouton valider de PanelCreerpatient");
				}
				int idPersonne = 0;
				boolean isMale;
				if(listeGroupe.getSelectedItem()=="M."){
					isMale=true;
				}
				else{
					isMale=false;
				}
				AdresseDTO adresse = new AdresseDTO(batField.getText(), cpField.getText(), numField.getText(), paysField.getText(), rueField.getText(), villeField.getText(), voieField.getText());
				PatientDTO pat = new PatientDTO(idPersonne, nomField.getText(), prenomField.getText(), DateUtil.toDate(dateField.getText()), isMale, telField.getText(), portableField.getText(), mailField.getText(), adresse, null, nirField.getText(), medField.getText());
				try {
					ctrlUseCase.creerPatient(pat);
				} catch (ClassNotFoundException e) {
					e.getMessage();
					erreurs++;
				} catch (CabinetMedicalException e) {
					Object[] options = { "OK" };
					JOptionPane.showOptionDialog(null, "Probl�me lors de la cr�ation du patient: "+e.getMessage(), "ERREUR DE SAISIE",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,
					null, options, options[0]);
					logger.error("D�clenchement d'une exception m�tier dans la m�thode actionPerformed du bouton valider de PanelCreerpatient");
					erreurs++;
				} catch (HelperException e) {
					e.getMessage();
					erreurs++;
				} catch (CabinetTechniqueException e) {
					e.getMessage();
					logger.fatal("D�clenchement d'une exception technique dans la m�thode actionPerformed du bouton valider de PanelCreerpatient");
					erreurs++;
				} catch (NullPointerException e) {
					e.getMessage();
					erreurs++;
				} catch (SQLException e) {
					e.getMessage();
					erreurs++;
				}
				if(erreurs==0){
					Object[] options = { "OK" };
					JOptionPane.showOptionDialog(null, pat.getNom()+pat.getPrenom()+" a bien �t� enregistr�(e) comme Patient", "Information",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
					null, options, options[0]);
				}
				
				//r�initialiser...
				reinitialiserFormulaire(voieField);
				reinitialiserFormulaire(nirField);
				reinitialiserFormulaire(numField);
				reinitialiserFormulaire(nomField);
				reinitialiserFormulaire(prenomField);
				reinitialiserFormulaire(mailField);
				reinitialiserFormulaire(villeField);
				reinitialiserFormulaire(rueField);
				reinitialiserFormulaire(batField);
				reinitialiserFormulaire(cpField);
				reinitialiserFormulaire(medField);
				reinitialiserFormulaire(paysField);
				reinitialiserFormulaire(telField);
				reinitialiserFormulaire(portableField);
				reinitialiserFormulaire(listeGroupe);
				reinitialiserFormulaire(dateField);
				if(logger.isDebugEnabled()){
					logger.debug("Sortie de la m�thode actionPerformed du bouton valider de PanelCreerpatient");
				}
			}
				
		});
		//Listener bouton Valider
		quit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				//r�initialiser...
				reinitialiserFormulaire(voieField);
				reinitialiserFormulaire(nirField);
				reinitialiserFormulaire(numField);
				reinitialiserFormulaire(nomField);
				reinitialiserFormulaire(prenomField);
				reinitialiserFormulaire(mailField);
				reinitialiserFormulaire(villeField);
				reinitialiserFormulaire(rueField);
				reinitialiserFormulaire(batField);
				reinitialiserFormulaire(cpField);
				reinitialiserFormulaire(medField);
				reinitialiserFormulaire(paysField);
				reinitialiserFormulaire(telField);
				reinitialiserFormulaire(portableField);
				reinitialiserFormulaire(listeGroupe);
				reinitialiserFormulaire(dateField);
				
				//Rendre invisible
				setVisible(false);
			}
			
		});
		
		JPanel panelNir = new JPanel(new FlowLayout());
			panelNir.add(nirLabel);
			panelNir.add(nirField);
		
		JPanel panelComboBox = new JPanel(new FlowLayout());
			panelComboBox.add(mLabel);
			panelComboBox.add(listeGroupe);
			
		JPanel panelNom = new JPanel(new FlowLayout());
			JPanel GridLabels = new JPanel(new GridLayout(6,1,0,4));
			JPanel gridFields = new JPanel(new GridLayout(6,1));
				panelNom.add(GridLabels);
				panelNom.add(gridFields);
			
		JPanel panelNom2 = new JPanel(new FlowLayout());
			JPanel gridLabelsBis = new JPanel(new GridLayout(7,1,0,4));
			JPanel gridFieldsBis = new JPanel(new GridLayout(7,1));
				panelNom2.add(gridLabelsBis);
				panelNom2.add(gridFieldsBis);
			
		JPanel panelCentral = new JPanel(new FlowLayout());
			panelCentral.add(panelComboBox);
			panelCentral.add(panelNom);
			panelCentral.add(adr);
			panelCentral.add(panelNom2);
			panelCentral.add(medLabel);
			panelCentral.add(medField);
		

		GridLabels.add(nomLabel);
		gridFields.add(nomField);
		GridLabels.add(prenomLabel);
		gridFields.add(prenomField);
		GridLabels.add(dateLabel);
		gridFields.add(dateField);
		GridLabels.add(telLabel);
		gridFields.add(telField);
		GridLabels.add(portableLabel);
		gridFields.add(portableField);
		GridLabels.add(mailLabel);
		gridFields.add(mailField);
		

		gridLabelsBis.add(numLabel);
		gridFieldsBis.add(numField);
		gridLabelsBis.add(rueLabel);
		gridFieldsBis.add(rueField);
		gridLabelsBis.add(voieLabel);
		gridFieldsBis.add(voieField);
		gridLabelsBis.add(batLabel);
		gridFieldsBis.add(batField);
		gridLabelsBis.add(cpLabel);
		gridFieldsBis.add(cpField);
		gridLabelsBis.add(villeLabel);
		gridFieldsBis.add(villeField);
		gridLabelsBis.add(paysLabel);
		gridFieldsBis.add(paysField);
		
		
		JPanel panelBoutons = new JPanel(new FlowLayout());
			panelBoutons.add(valid);
			panelBoutons.add(quit);
		
		this.setLayout(new BorderLayout());
			this.add(panelNir, BorderLayout.NORTH);
			this.add(panelCentral, BorderLayout.CENTER);
			this.add(panelBoutons, BorderLayout.SOUTH);
		if(logger.isDebugEnabled()){
			logger.debug("Sortie du constructeur de PanelCreerpatient");
		}
	}
}
