/** Auteur : Equipe 1
  * Date   : juin 2023
  * Info   : n'appartient pas au package
*/

/*Importations*/
// Graphe
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;
import org.graphstream.ui.view.Viewer;
import iut.algo.Clavier;

// Lecture
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.FileInputStream;

public class GenererGraphe
{
	/*Attributs*/
	private static final MultiGraph        graph          = new MultiGraph("Les aventuriers du rail");
	private static final ArrayList<String> ensLienACreer  = new ArrayList<>();

	/*Main*/
	public static void main(String[] args)
	{
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

		final String FICHIER = "Graphe.data";

		//ArrayList<Region> ensRegion = new ArrayList<>(); // Liste des régions

		try
		{
			Scanner sc = new Scanner ( new FileInputStream ( FICHIER ), StandardCharsets.UTF_8);
			String derniereRegion = "";

			while (sc.hasNextLine())
			{
				String   line = sc.nextLine();
				String[] ensVal  = line.split("\t");

				if (ensVal.length == 1) // Créer régions
				{
					derniereRegion = ensVal[0];
				}
				else // Créer nœuds et liaisons
				{
					if (ensVal.length >= 3 && ensVal[1].matches("^-?\\d+") && ensVal[2].matches("^-?\\d+")) // Voir Sujet DS 2021 IHM pour les matches()
					{
						Node n = graph.addNode(ensVal[0]);

						n.setAttribute("ui.style", "shape: circle; fill-color: #EEE; stroke-mode: plain; stroke-color: #333; size:30px;");

						n.setAttribute("x", Double.parseDouble(ensVal[1]));
						n.setAttribute("y", Double.parseDouble(ensVal[2]));
						n.setAttribute("region", derniereRegion);

						n.setAttribute("label", String.format("%s [%s]", ensVal[0], derniereRegion));

						if (ensVal.length >= 4)
						{
							String[] ensLien = ensVal[3].split(" ");

							GenererGraphe.creerLien(ensLien, n);
						}
					}
				}
			}

			sc.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Il semblerait qu'il y ait un problème de fichier");
		}

		// Affichage du graphe
		Viewer viewer = graph.display();

		viewer.disableAutoLayout();

		changerCouleur();
	}

	/*Methodes*/
	
	public static void creerLien(String[] ensLien, Node n)
	{
		Integer  value   = null;

		for (String lien : ensLien)
		{
			String[] ensInfo = lien.split(":");
			String   sommet  = ensInfo[0];
			String   nom     = String.format("%s:%s", n.getId(), sommet);

			if (ensInfo.length >= 2 && ensInfo[1].matches("^-?\\d+"))
			{
				value = Integer.parseInt(ensInfo[1]);
			}

			if (graph.getNode(sommet) != null) // impossible de créer le lien maintenant
			{
				Edge e = graph.addEdge(nom, n.getId(), sommet);

				if (value != null) e.setAttribute("label", String.format("%s (%s)", nom, value));
			}
			else // Si noeud n°2 existe pas
			{
				ensLienACreer.add(n.getId() + ":" + lien);
			}
		}

		// Mise en place des liens manquants
		for (String lienACreer : ensLienACreer)
		{
			String[] ensInfosLien = lienACreer.split(":");

			if (ensInfosLien[1].equals(n.getId()))
			{
				String nomACreer = String.format("%s:%s", ensInfosLien[1], ensInfosLien[0]);

				if (graph.getEdge(nomACreer) == null)
				{
					Edge e = graph.addEdge(nomACreer, ensInfosLien[1], ensInfosLien[0]);

					e.setAttribute("label", value != null ? String.format("%s (%s)", nomACreer, value) : nomACreer);    // On définit le label de l'arête à son nom + sa valeur si elle en a une
				}
			}
		}
	}

	public static void changerCouleur()
	{
		System.out.print("Sélectionner une arête à colorier : ");
		String arete = Clavier.lireString();

		while (!arete.equals(""))
		{
			Edge e = graph.getEdge(arete);

			if (e != null)
			{
				System.out.print("Couleur                           : ");
				String couleur = Clavier.lireString();

				e.setAttribute("ui.style", String.format("fill-color:%s;", couleur));

			}

			System.out.print("\nSélectionner une arête à colorier : ");
			arete = Clavier.lireString();
		}
	}
}

/*
1
Nieu Waal	0	100	Noordermarkt:2 Haringpakkerstoren:2
Noordermarkt	0	80	Haringpakkerstoren:3 Korenmetershuisje:3
Haringpakkerstoren	20	100	Korenmetershuisje:1
Korenmetershuisje	20	90
 */
