# Explications de notre structure

## Explication du fichier texte :

Le fichier texte est structuré de la facon suivante :
- Le fichier commence par le nom de la région
- Ensuite, on trouvera la suite des points appartenant à cette région. Ces points sont composé de la méthode suivante :
    - Un nom
    - Des coordonées X
    - Des coordonées Y
    - La liste des arêtes, définis par le nom du point, une valeur et une couleur, tous séparer par ':'

Prenons l'exemple suivant d'un fichier :

```
Region 1B
A	20	30	B:20:red D:30
B	30	20	
C	30	30	D:15 E:40:purple
D	10	30	
Region 1A
E	50	50	F:5:blue
F	60	70	E:5:blue
```


## Explication lecture du fichier :

### Créations des Sommet et Region
On gardera le fichier précédent pour la suite des explications. Ce fichier sera lu par un simple Scanner :

```
import java.util.Scanner;

public class 
{
    private static final String FICHIER = "Graphe.txt";

	public void main (String[] args)
	{
		try 
		{
			Scanner sc = new Scanner ( new FileInputStream ( GenererGraphe.FICHIER ), "UTF8" );
		}
		catch (Exception e) {}
	}
}
```

On remarquera que le nom des régions sont sur une ligne seule. Ceux-ci n'est pas pour rien, en effet, on compte crée une classe Region qui nous permettra de séparé dans le code les différents sous-graphes. Donc, pour créer une Region, on aura seulement besoin de regarder si une tabulation est présente sur la ligne : 

```
String[] ensVal  = line.split("\t");

if (ensVal.length == 1) // Créer régions
{
	derniereRegion = new Region(ensVal[0]);
}
```

Ces regions seront stockés dans une ArrayList, stocker en tant qu'attribus. Une ArrayList nous semble être le meuilleur choix pour ce que nous souhaitons : un tableau avec une capacité presque infinis.



Une fois la Region crée, on créera des nodes listé en séparant la ligne en 4 :
- Le nom
- Coord X
- Coord Y
- Liaisons

On ne s'occupera pas des liaison tous de suite, car tous les nodes ne sont pas encore crée. Pour l'instant, on ajoutera juste les nodes à la Region mais on l'ajoutera aussi à l'interface Graphique au coordonnées données.

```

```

Note : On aurait pu crée notre propre classe Sommet qui hérite de Node mais...
- Node est une interface, donc pas d'héritage possible
- Cela serait inutile quand on sait que la classe Node est composé de tous les éléments nécessaire pour l'instant.

### Créations des liaisons 
Maintenant tous les Sommets crées, nous pouvons passé au liaison. Nous allons donc devoir relire le fichier une deuxième fois pour seulement traiter la partie liaisons.

Comme la première fois, on prendra 
