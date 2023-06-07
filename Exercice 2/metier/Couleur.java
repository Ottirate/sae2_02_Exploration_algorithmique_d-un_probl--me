package graphe.metier;

import java.awt.Color;

public enum Couleur
{
	BLEU_PALE  (Color.decode("#3D74FF")),
	ROUGE_PALE (Color.decode("#FF644C")),
	JAUNE_PALE (Color.decode("#F0FF4C")),
	VERT_PALE  (Color.decode("#85FF6D")),
	VIOLET_PALE(Color.decode("#FF42B3")),
	CYAN_PALE  (Color.decode("#6DFFD8")),
	MARRON_PALE(Color.decode("#FF7E42")),
	ORANGE_PALE(Color.decode("#FFCA44")),
	GRIS_PALE  (Color.decode("#ADADAD")),
	BLEU_FONCE (Color.decode("#3854AA"));
	
	private Color color;
	
	private Couleur(Color c) {
		this.color = c;
	}
	
}
