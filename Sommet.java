import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.Node;

import java.util.ArrayList;

import org.graphstream.graph.Edge;

public class Sommet // PAS UTILISE -- ABANDONNEE -- DECHETTERIE -- POUBELLE
{
	private Node              node;
	private Region            region;
	private String            nom;
	private Integer           x;
	private Integer           y;
	private ArrayList<Sommet> lstVoisines;

	public Sommet(MultiGraph graph, String nom, int x, int y)
	{
		this.nom         = nom;
		this.x           = x;
		this.y           = y;
		this.lstVoisines = new ArrayList<>();

		this.node = graph.addNode(this.nom);
		this.node.setAttribute("xy", this.x, this.y);
		
	}

	public void   setRegion(Region r) {this.region = r;}

	public String getNom   ()         {return this.nom;}
}

String derniereRegion;

String   line = sc.nextLine();
String[] ensVal  = line.split("\t");

if (ensVal.length == 1) // Créer régions
{
	derniereRegion = ensVal[0];

	System.out.println("Créer région : " + ensVal[0]);
}