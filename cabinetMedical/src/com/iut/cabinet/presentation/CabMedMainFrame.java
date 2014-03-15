package com.iut.cabinet.presentation;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.apache.log4j.Logger;

/**
 * Classe correspondant au JFrame (fen�tre pour lier les diff�rents Panel) dans le MVC du cabinet m�dical
 * @author Nicolas BOURGES
*/
public class CabMedMainFrame extends JFrame{
	private static Logger logger = Logger.getLogger(GererPatientIHM.class.getName());
	private static final long serialVersionUID = 1L;
	
	PanelCreerPatient creerPatient = new PanelCreerPatient();
	PanelListerPatients listerPatients = new PanelListerPatients();
	CardLayout c = new CardLayout();
	
	/**
	 * Constructeur par d�faut de CabMedMainFrame
	 */
	public CabMedMainFrame(){
		this.setLayout(c);
		this.add(listerPatients, "1");
		listerPatients.setVisible(false);
		this.add(creerPatient, "2");
		creerPatient.setVisible(false);
		initialiserMenus();
	}
	
	/**
	 * M�thode permettant de cr�er la barre de Menu du CabMedMainFrame
	 * Correspond au constructeur par d�faut de CabMedMainFrame
	 */
	private void initialiserMenus(){
		if(logger.isDebugEnabled()){
			logger.debug("Entr�e dans la m�thode initaliserMenus de CabMedMainFrame");
		}
		
		JMenuBar menu = new JMenuBar();
		
		JMenu menuFichier = new JMenu("Options");
		JMenuItem menuQuitter = new JMenuItem("Quitter");
		menuQuitter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuFichier.add(menuQuitter);
		
		JMenu menuPatients = new JMenu("Patients");
		JMenuItem menuCreer = new JMenuItem("Cr�er un patient (sans ascendant)");
		menuCreer.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				listerPatients.setVisible(false);
				creerPatient.setVisible(true);
				repaint();
			}
		});
		JMenuItem menuLister = new JMenuItem("Lister les patients");
		menuLister.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				creerPatient.setVisible(false);
				remove(listerPatients); 					//(1)
				listerPatients = new PanelListerPatients(); //(2)
				add(listerPatients); 						//(3)
				// (1) (2) (3): permet de rafra�chir la liste de Patient; au prochain affichage, le patient ajout� sera lui aussi pr�sent
				listerPatients.setVisible(true);
				repaint();
			}
		});
		menuPatients.add(menuCreer);
		menuPatients.add(menuLister);
		
		JMenu menuAide = new JMenu("Aide");
		JMenuItem menuAPropos = new JMenuItem("A propos");
		menuAPropos.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Object[] options = {"OK"};
				JOptionPane.showOptionDialog(null, "Cabinet M�dical PIC'OUZ � IUT du Limousin", "A propos...",
											JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
											null, options, options[0]);
			}
		});
		menuAide.add(menuAPropos);
		
		menu.add(menuFichier);
		menu.add(menuPatients);
		menu.add(menuAide);
		
		setJMenuBar(menu);
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la m�thode initaliserMenus de CabMedMainFrame");
		}
	}
}
