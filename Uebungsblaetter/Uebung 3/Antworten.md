## CR1:

Wir benutzen dafür das Singleton-Pattern, hier in der Eager-Variante implementiert. Dabei wird beim Laden der Klasse über `private static final Container container = new Container();` ein Container Objekt erzeugt, welches danach über die `getInstance()` Methode immer die Instanz zurück gibt. Hier durch werden doppelte oder widersprüchliche Datenbestände vermieden, da überall mit demselben Container gearbeitet wird.


## CR 3:

Hier wird das Strategy-Pattern angewandt. Der Container kennt nur das Interface `PesistenceStrategy` und nicht die konkrete Implementierung. Die Strategie wird von außen über `setPersistenceStrategy()` gesetzt und kann so auch zur Laufzeit gewechselt werden, ohne dass am Container selbst etwas geändert werden muss. Neue Persistenz-Strategien können einfach hinzugefügt und genutzt werden: Sobald sie das Interface korrekt implementieren, kann der Container sie verwenden (Open-Closed-Prinzip).
