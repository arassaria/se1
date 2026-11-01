# Review Lösung Julius P. (Aufgabe 4-2)

## F1 - Wiederverwendung & Singleton
- Wiederverwendung: Kopieren statt Generics ist eine unglückliche Lösung, da bei einer Kopie auch Fehler mit kopiert werden, die dann in allen Kopien gefixed werden müssen. Besser wäre die Verwendung von Generics mit `<E extends Member>` (oder wenn man statt Member das Interface in "Identifiable" umbenennen würde, wäre treffender). Hierbei zu beachten: Generics und Singleton beißen sich, weil eine statische Instanz keinen Typparameter haben kann. Lösung über eine typspezifische Unterklasse oder Dependency Injection.
- Singleton: Umsetzung nicht gut. Konstruktor ist package-private nicht private, also alles im Package kann den Konstruktor aufrufen. Das fehlende final bei instance sorgt dafür, dass es doch mehrere Instanzen geben kann, weil mit instance = new Container() die alte Instanz überschrieben werden kann.
- Thread-Safety / Speicher:

## F2 - Alles in einer Klasse
## F3 - store()-Exception und Scanner
## F4 - Sortierung & Attribute UserStory
## F5 - Filter-Map-Reduce
## F6 - Package-Struktur & Muster