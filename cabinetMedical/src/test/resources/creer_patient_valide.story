Creation d'un patient valide

Narrative:
In order to manipuler des patients valides
As a utilisateurr
I want to creer un patient
					 
Scenario:  Creer un patient valide
Given un controleur
When je cree un patient avec le NIR 194046938324249 et qu'il est valide
Then le patient avec le NIR 194046938324249 est bien cree


