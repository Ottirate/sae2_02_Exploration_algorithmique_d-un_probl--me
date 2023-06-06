import java.util.ArrayList;


public class Region
{
	private String            nom;
	private ArrayList<Sommet> lstSommet;

	public Region(String nom)
	{
		this.nom       = nom;
		this.lstSommet = new ArrayList<>();
	}

	public void ajouterSommet(Sommet s)
	{
		this.lstSommet.add(s);

		s.setRegion(this);
	}
}