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
A	20	30	B:20:red D:40
B	30	20	A:20:red 
C	30	30	
D	10	30	A:40
Region 1A
E	50	50	F:5:blue
F	60	70	E:5:blue
```

## Explication lecture du fichier :

Ici, on a deux regions : Region 1B et Region 1A 