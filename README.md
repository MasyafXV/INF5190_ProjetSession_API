# INF5190_ProjetSession_API - Partie 2

Par Yassine Hasnaoui (HASY04089702)
Et Thien My Elsa Tran (TRAT24569808)

Ceci est le web API de notre projet de session utilisant Jersey RESTful Web Services, un framework open source pour le développement de services Web RESTful en Java. Il prend en charge les API JAX-RS et sert d'implémentation de référence JAX-RS.

## Utilisation
Lancer cette application sur Tomcat v.9 dans localhost.

Pour vous assurer que l'API fonctionne, vous devriez voir l'image ci-dessous à l'adresse http://localhost:8080/services/

![jersey](/preview.png)


## À ne pas oublier
Ne pas oublier d'inclure toutes les librairies dans le build path d’éclipse. Tous les fichiers jars nécessaires sont dans le folder `lib` de WEB-INF.

# Précision pour lancer l'API
Pour run l'API, il faut lancer le dossier `services` et non le dossier INF5190_ProjetSession_API.

Il se peut que le context root du dossier service ne soit pas le bon. Dans ce cas, il faut aller dans `Properties`--> `Web Project Settings` et changer le context root pour `services`dans le champs prévu à cet effet.

Si après cela, une erreur 500 se produit lors du lancement de l'API, il faut aller dans `Projects Faucets`--> `Java` et changer pour la version 9.
