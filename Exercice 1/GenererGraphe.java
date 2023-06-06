// Graphe
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;

// Lecture
import java.util.*;
import java.io.FileInputStream;

public class GenererGraphe 
{
	private static MultiGraph graph = new MultiGraph("Les aventuriers du rail");

	public static void main(String[] args) 
	{
		final String FICHIER = "Graphe.txt";
		
		//ArrayList<Region> ensRegion = new ArrayList<>(); // Liste des régions

		try 
		{
			Scanner sc = new Scanner ( new FileInputStream ( FICHIER ), "UTF8" );
			String derniereRegion = "";

			while (sc.hasNextLine())
			{
				String   line = sc.nextLine();
				String[] ensVal  = line.split("\t");

				if (ensVal.length == 1) // Créer régions
				{
					derniereRegion = ensVal[0];

					System.out.println("Créer région : " + ensVal[0]);
				}
				else // Créer noeuds et liaisons
				{
					if (ensVal[1].matches("^-[0-9]+") && ensVal[2].matches("^-[0-9]+") && ensVal.length >= 3) // Voir Sujet DS 2021 IHM pour les matches()
					{
						Node n = graph.addNode(ensVal[0]);

						n.setAttribute("xy", Integer.parseInt(ensVal[1]), Integer.parseInt(ensVal[2]));
						n.setAttribute("label", String.format("%s [%s]", ensVal[0], derniereRegion));

						// derniereRegion.ajouterSommet(n);

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
			System.out.println("Il semblerait qu'il y ait un problème de fichier D:");
		}

		// Affichage du graphe
		graph.display();
	}

	public static void creerLien(String[] ensLien, Node n)
	{
		for (String lien : ensLien)
		{
			String[] ensInfo = lien.split(":");

			String sommet = ensInfo[0];

			String nom = String.format("%s:%s", n.getId(), sommet);

			String  couleur = null;
			Integer value   = null;

			if (ensInfo.length >= 2)
			{
				if (ensInfo[1].matches("[0-9]+"))
				{
					value = Integer.parseInt(ensInfo[1]);

					if (ensInfo.length >= 3) couleur = ensInfo[2];
				}
				else
				{
					couleur = ensInfo[1];

					if (ensInfo.length >= 3 && ensInfo[2].matches("[0-9]+")) value = Integer.parseInt(ensInfo[2]);
				}
			}

			Edge e = graph.addEdge(nom, n.getId(), sommet);

			if (couleur != null) e.setAttribute("ui.style",String.format("fill-color:%s;", couleur));
			if (value   != null) e.setAttribute("label", String.valueOf(value));
		}
	}
}
