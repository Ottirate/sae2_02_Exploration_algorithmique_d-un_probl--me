import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.Node;

import java.util.ArrayList;

import org.graphstream.graph.Edge;

public class Sommet extends Node
{
	private Region            region;
	private String            nom;
	private Integer           x;
	private Integer           y;
	private ArrayList<Sommet> lstVoisines;

	public Sommet(String nom, int x, int y)
	{
		this.nom         = nom;
		this.x           = x;
		this.y           = y;
		this.lstVoisines = new ArrayList<>();
	}

	public void   setRegion(Region r) {this.region = r;}

	public String getNom   ()         {return this.nom;}
}