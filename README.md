# Localisation des pharmacies
Ce projet est concue pour mettre en place une application qui va permettre au internaute de localiser les pharmacies en precisant la ville et la zone d'appartenance et le type de garde de la pharmacie qui peut etre jour ou nuit.

# Fonctionnalités
1. Affichage des pharmacies de garde de jour et de nuit dans la ville sélectionnée, groupées par zone
2. Sélectionne la ville dans la liste des villes disponibles
3. Possibilité de filtrer les pharmacies affichées par type (garde de jour ou de nuit)
4. Creer un compte utilisateur et s'authentifier
5. Localiser les pharmacies dans la Map
6. Afficher l'itiniraire vers la pharmacie choisi
7. mention de garde pour les pharmacien
8. Un pharmacien peut ajouter sa pharmacie
9. Un pharmacien peut mentionner une garde pour ca pharmacie

# Mise en place du projet
Pour mettre en place ce projet, vous aurez besoin des éléments suivants :
1. Un serveur d'application Java (comme Tomcat)
2. Un SGBD (comme MySQL)
3. Un éditeur de code (comme IntellijIdea)
4. Le driver JDBC de votre SGBD (à télécharger sur le site officiel de votre SGBD)
5. Le framework Hibernate (à télécharger sur le site officiel ou via Maven)
6. Android Studio
7. Editeur de texte (comme VS code)

# Déploiement
Pour déployer ce projet sur votre serveur d'application, suivez les étapes suivantes :

1. Téléchargez le projet sur votre ordinateur
2. importer backend dans IntellijIdea par exemple
3. Configurez la connexion à votre SGBD dans le fichier application.proprties
4. Déployez l'archive sur votre serveur d'application en suivant les instructions de votre serveur
5. Ouvrez votre navigateur et accédez à l'application en entrant l'URL suivante : http://localhost:8080/nom_de_votre_application (remplacez nom_de_votre_application par le nom de votre application déployée)
6. Ouvri le frontend du web dans votre editeur du text (VS code )
7. Executer la commande npm install puis npm start pour avoir les interfaces dans votre navigateur sur l'url : http://localhost:3000/nom_de_votre_application
8. Pour l'application mobile importer la sur android studio
9. changer les adresses ip dans les fichiers java RetrofitClient, RechercheMap , villesViewModel, SlidesHowViewModel, ApiClient sans oublier Network_Security_Config.xml
10. executer l'application sur l'emulateur ou votre telephone portable en connectant le backend et le telephone sur l'application  mobile
