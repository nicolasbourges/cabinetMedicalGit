package com.iut.cabinet.presentation;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.apache.log4j.Logger;

/**
 * Classe correspondant au JFrame (fenêtre pour lier les différents Panel) dans le MVC du cabinet médical
 * @author Nicolas BOURGES
*/
public class CabMedMainFrame extends JFrame{
	private static Logger logger = Logger.getLogger(GererPatientIHM.class.getName());
	private static final long serialVersionUID = 1L;
	
	PanelCreerPatient creerPatient = new PanelCreerPatient();
	PanelListerPatients listerPatients = new PanelListerPatients();
	CardLayout c = new CardLayout();
	
	/**
	 * Constructeur par défaut de CabMedMainFrame
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
	 * Méthode permettant de créer la barre de Menu du CabMedMainFrame
	 * Correspond au constructeur par défaut de CabMedMainFrame
	 */
	private void initialiserMenus(){
		if(logger.isDebugEnabled()){
			logger.debug("Entrée dans la méthode initaliserMenus de CabMedMainFrame");
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
		JMenuItem menuCreer = new JMenuItem("Créer un patient (sans ascendant)");
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
				// (1) (2) (3): permet de rafraîchir la liste de Patient; au prochain affichage, le patient ajouté sera lui aussi présent
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
				JOptionPane.showOptionDialog(null, "Cabinet Médical PIC'OUZ © IUT du Limousin", "A propos...",
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
			logger.debug("Sortie de la méthode initaliserMenus de CabMedMainFrame");
		}
	}
}
