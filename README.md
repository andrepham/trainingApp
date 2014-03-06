trainingApp
===========

Eclipse external tool configuration:
  MAVEN_OPTS: -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,address=4000,suspend=n

Pour tester le client angular:
> se placer à la racine du module parent "trainingapp", lancer la commande 'mvn clean install'
> se placer à la racine du module enfant "trainingapp-angular-client", lancer la commande 'mvn jetty:run'
> url: http://localhost:9090/trainingapp-angular-client
