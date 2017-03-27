public interface Partie1 {

	/** initialise un plateau a partire d'un fichier texte
	 * @param fileName le nom du fichier a lire
	 */
	public void setFromFile(String fileName);
	
	/**	sauve la configuration d'un plateau dans un fichier
	 * @param fileName le nom du fichier a sauvegarder
	 * 
	 * Le format doit etre comatipblre avec celui utilise pour la lecture
	 */
	public void saveToFile(String fileName);
	
	/** indique si le coup <move> est valide pour le joueur <player> sur le plateau courant
	 * @param move le coup a jouer sous la forme "A1-B2"
	 * @param player le joueur qui joue (represente par "noir" ou "blanc")
	 */
	public boolean estValide(String move, String player);
	
	/** calculeles coups possibles pour le joueur <player> sur le plateau courant
	 * @param player le joueur qui joue (represente par "noir" ou "blanc")
	 */
	public String[] mouvementPossibles(String player);
	
	/** modifie le plateau en jouant le coup move
	 * @param move le coup a jouer sous la forme "A1-B2"
	 * @param player le joueur qui joue (represente par "noir" ou "blanc")
	 */
	public void play(String move, String player);
	
	/** vrai lorsque le plateau correspond a une fin de partie
	 */
	public boolean finDePartie();
}
