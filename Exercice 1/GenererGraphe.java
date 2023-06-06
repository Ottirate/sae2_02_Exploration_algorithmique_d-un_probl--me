// Graphe
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.Node;

// Lecture
import java.util.*;
import java.io.FileInputStream;

public class GenererGraphe 
{
    private static final String FICHIER = "Graphe.txt";
	public static void main(String[] args) 
	{
		ArrayList<Region> ensRegion = new ArrayList<>(); // Liste des régions


		try 
		{
			Scanner sc = new Scanner ( new FileInputStream ( GenererGraphe.FICHIER ), "UTF8" );
			Region r   = null;



			while (sc.hasNextLine())
			{
				String   line = sc.nextLine();
				String[] val  = line.split("\t");

				if (val.length == 1) // Créer régions
				{
					r = new Region(val[0]);

					ensRegion.add( r );
				}
				else // Créer noeuds et liaisons
				{
					Sommet s = new Sommet(val[0], Integer.parseInt());

					r.ajouterSommet(s);
				}
			}

			sc.close();
		}
		catch (Exception e)
		{
			System.out.println("Il semblerait qu'il y ait un problème de fichier D:");
		}
	}
}
