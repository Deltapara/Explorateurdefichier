Explorateur de fichiers - Readme

Description
-----------
Petit projet Java simulant un explorateur de fichiers en mémoire.
Le point d'entrée est la classe main : com.esiea.pootd2.ExplorerApp
Le contrôleur principal est : com.esiea.pootd2.controllers.ExplorerController

Compilation 
-----------
# depuis la racine du dossier Explorateurdefichier 
javac -d classes $(find | grep -E "./com/.*\.java")


Exécution
--------
# Mode texte (par défaut)
java -cp bin com.esiea.pootd2.ExplorerApp

# Mode HTTP (interface web sur le port 8001)
java -cp bin com.esiea.pootd2.ExplorerApp http

Commandes supportées
--------------------
ls        : lister les éléments du répertoire courant
cd <dir>  : changer de répertoire (.. pour remonter, / pour la racine)
mkdir <name> : créer un dossier
touch <name> : créer un fichier (taille aléatoire)
exit      : quitter l'application (ou arrêter le serveur HTTP)

Notes
-----
- Les fichiers et dossiers sont stockés en mémoire via les classes
  com.esiea.pootd2.models.FileInode et com.esiea.pootd2.models.FolderInode.
- Le parser de commandes est dans com.esiea.pootd2.commands.parsers.UnixLikeCommandParser.
- L'interface web (HttpInterface) sert une page simple.

Fichiers principaux
-------------------
- com/esiea/pootd2/ExplorerApp.java
- com/esiea/pootd2/controllers/ExplorerController.java
- com/esiea/pootd2/interfaces/TextInterface.java
- com/esiea/pootd2/interfaces/HttpInterface.java

