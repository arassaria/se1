# Akzeptanztests – UserStory-Tool (Übung 4, Aufgabe 2)

**Testart:** Manueller Black-Box-Test über die Konsole (Eingaben wie durch einen Nutzer)
**Persistenz:** `PersistenceStrategyStream` (Datei `objects.ser`)
**Testdatum:** 07.11.2026

## Äquivalenzklassen

| Eingabe | Gültige Klassen | Ungültige Klassen |
|---|---|---|
| ID | ganze Zahl, noch nicht vergeben | keine Zahl (z. B. `abc`); bereits vergebene ID |
| Mehrwert, Aufwand | ganze Zahl > 0 | ≤ 0 (Grenzwert 0) |
| Strafe, Risiko | ganze Zahl ≥ 0 (Grenzwert 0 ist gültig) | < 0 |
| Befehl | `help`, `enter`, `store`, `load`, `dump [Projekt]`, `exit` | unbekannter Befehl; leere Eingabe |
| Projektfilter bei `dump` | kein Filter; vorhandenes Projekt | nicht vorhandenes Projekt |
| `load` | Datei vorhanden | keine gespeicherte Datei vorhanden |

## Testfälle

| Nr. | Befehl / Eingabe | Äquivalenzklasse | Erwartetes Ergebnis | Tatsächliches Ergebnis | OK? |
|---|---|---|---|---|---|
| 1 | `help` | gültiger Befehl | Übersicht aller 6 Befehle | Alle 6 Befehle mit Beschreibung ausgegeben | ✅ |
| 2 | `enter` mit ID 1, Mehrwert 5, Strafe 3, Aufwand 2, Risiko 2, danach `dump` | gültige Story | Story wird angelegt; Priorität (5+3)/(2+2) = 2,00 | „Eintrag 1 wurde erfolgreich angelegt.“; `dump` zeigt Priorität 2.00 | ✅ |
| 3 | `enter`, bei ID `abc` eingeben, danach `2` | ID keine Zahl (ungültig) | Fehlermeldung, erneute Abfrage der ID | „ID muss eine Zahl sein.“, erneute Abfrage; mit `2` wird die Story angelegt | ✅ |
| 4 | `enter` mit Aufwand 0 | Aufwand ≤ 0 (Grenzwert, ungültig) | Eintrag wird abgelehnt | „Eintrag abgelehnt: … Value and Effort must be > 0 …“ | ✅ |
| 5 | `enter` mit Aufwand 1, Risiko 0 | Aufwand = 1 und Risiko = 0 (Grenzwerte, gültig) | Story wird angelegt | „Eintrag 4 wurde erfolgreich angelegt.“ | ✅ |
| 6 | Zweimal `enter` mit ID 1 | ID bereits vergeben (ungültig) | Zweiter Eintrag wird abgelehnt | „Eintrag abgelehnt: Der Eintrag mit der ID 1 ist bereits vorhanden!“ | ✅ |
| 7 | `dump` bei leerem Container | keine Einträge | Hinweis, dass nichts anzuzeigen ist | „Keine Einträge zum Anzeigen gefunden.“ | ✅ |
| 8 | 3 Stories (App: 2,00 und 0,25; Web: 6,00), dann `dump` | kein Filter | Tabelle mit allen 3 Stories, absteigend nach Priorität | Reihenfolge 6.00 (Web), 2.00 (App), 0.25 (App) | ✅ |
| 9 | wie 8, dann `dump App` | vorhandenes Projekt | Nur die beiden App-Stories, sortiert | 2.00, 0.25 | ✅ |
| 10 | wie 8, dann `dump Mobile` | nicht vorhandenes Projekt | Hinweis, dass nichts anzuzeigen ist | „Keine Einträge zum Anzeigen gefunden.“ | ✅ |
| 11 | `load` ohne vorhandene Datei | keine Datei (ungültig) | Verständliche Fehlermeldung, Programm läuft weiter | „Fehler beim Laden: objects.ser (No such file or directory)“, Programm läuft weiter | ✅ |
| 12 | Story anlegen, `store`, `exit`; Tool neu starten, `load`, `dump` | Datei vorhanden (gültig) | Story ist nach Neustart wieder da | „1 User Stories gespeichert.“ → nach Neustart „1 User Stories geladen.“, `dump` zeigt Story 1 | ✅ |
| 13 | `foo` bzw. leere Eingabe | unbekannter Befehl (ungültig) | Fehlermeldung, Programm läuft weiter | „Dieser Befehl existiert nicht. Bitte versuchen sie es erneut.“ | ✅ |

## Befund

- Alle 13 Testfälle bestanden.
- **Testfall 11:** Im ersten Durchlauf erschien das Präfix „Fehler beim Laden:“ doppelt, weil `ConsoleView` und `PersistenceStrategyStream` es beide setzten (ebenso „Fehler beim Speichern:“). Behoben: `PersistenceStrategyStream` gibt nur noch die technische Meldung weiter, das Präfix setzt allein die View. Der erneute Test war erfolgreich.
