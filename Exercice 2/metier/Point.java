package graphe.metier;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Point
{
	private int              id;
	private int              x;
	private int              y;
	private Region           region;
	private ArrayList<Arete> ensArete;

	public Point(int id, int x, int y)
	{
		this.id       = id;
		this.x        = x;
		this.y        = y;
		this.ensArete = new ArrayList<>();
	}

	public int getId() {return this.id;}
	public int getX () {return this.x; }
	public int getY () {return this.y; }

	public boolean setRegion(Region reg)
	{
		if (this.region != null || reg == null) return false;

		this.region = reg;
		return true;
	}

	public boolean ajouterArete(Arete a)
	{
		if (this.ensArete.contains(a) || a == null) return false;

		this.ensArete.add(a);
		return true;
	}

	public List<Arete> getAretesAdjacentes() {return this.ensArete;}

	public String toString()
	{
		return "x=" + this.x + ", y=" + this.y;
	}
}
