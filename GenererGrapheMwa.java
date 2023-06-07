import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GenererGrapheMwa
{
	public static void main(String[] args)
	{
		// Création du graphe
		Graph graph                 = new SingleGraph("Graphe stylax");

		try
		{
			// Lecture du fichier texte
			File file = new File("Graphe.txt");
			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine())
			{
				String line = scanner.nextLine();
				String[] data = line.split("\t");
			
				String nodeName = data[0];

				int x = Integer.parseInt(data[1]);
				int y = Integer.parseInt(data[2]);

				String[] links = data[3].split(":"); // Séparateur entre les liens
			
				// Vérifie si le sommet existe déjà dans le graphe
				Node node = graph.getNode(nodeName);

				if (node == null) {
					// Ajouter le sommet avec les positions
					node = graph.addNode(nodeName);
					node.setAttribute("xy", x, y);
				}
			
				

				// Ajoute les liens
				for (String link : links)
				{
					// Vérifie si le lien existe déjà dans le graphe
					boolean edgeExists = false;

					for (org.graphstream.graph.Edge edge : node.getEachEdge())
					{
						if (edge.getNode0().getId().equals(nodeName) && edge.getNode1().getId().equals(link) ||
							edge.getNode0().getId().equals(link) && edge.getNode1().getId().equals(nodeName))
								{
									edgeExists = true;
									break;
								}
					}
			
					// Ajouter le lien si cela n'existe pas déjà
					if (!edgeExists) { graph.addEdge(nodeName + link, nodeName, link); }
				}
			}

			// Ferme le scanner
			scanner.close();

			// Affiche le graphe
			graph.display();
		} catch (FileNotFoundException e) { e.printStackTrace(); }
	}
}