# Antworten Übungsblatt 5 – Aufgabe 2

## 2a) Bewertung der User Story nach INVEST

**Ursprüngliche Story:** „Als Student möchte ich mich auf Projektausschreibungen bewerben und nach diesen suchen.“

| Merkmal | Erfüllt? | Begründung |
|---|---|---|
| **I**ndependent | ja | Die Story ist unabhängig von anderen Stories umsetzbar. |
| **N**egotiable | ja | Sie ist offen formuliert und schreibt keine technische Lösung vor. |
| **V**aluable | nein | Es fehlt der „damit“-Teil. Der Nutzen für den Studenten wird nicht genannt. |
| **E**stimable | nein | Da zwei verschiedene Funktionen in einer Story stecken, ist der Aufwand schwer zu schätzen. |
| **S**mall | nein | Die Story enthält zwei Funktionen (Suchen und Bewerben) und sollte in zwei Stories aufgeteilt werden. |
| **T**estable | nein | Es gibt keine Akzeptanzkriterien, anhand derer geprüft werden kann, ob die Story umgesetzt ist. |

**Fazit:** Die Story erfüllt die INVEST-Merkmale nicht vollständig. Sie wird in zwei Stories aufgeteilt, jeweils mit einem „damit“-Teil und Akzeptanztests.

### Neue User Story A – Projektausschreibungen suchen

| Teil | Inhalt |
|---|---|
| Story | Als Student möchte ich nach Projektausschreibungen suchen, damit ich Projekte finde, die zu meinen Skills und Interessen passen. |
| Akzeptanztest 1 | Wenn ich nach einem Skill filtere, werden nur Ausschreibungen angezeigt, die diesen Skill verlangen. |
| Akzeptanztest 2 | Wenn keine Ausschreibung passt, erhalte ich einen Hinweis, dass nichts gefunden wurde. |
| Akzeptanztest 3 | Bereits abgelaufene Ausschreibungen werden in der Suche nicht angezeigt. |

### Neue User Story B – Auf Projektausschreibung bewerben

| Teil | Inhalt |
|---|---|
| Story | Als Student möchte ich mich auf eine Projektausschreibung bewerben, damit ich an einem Projekt bei einem Unternehmen mitarbeiten kann. |
| Akzeptanztest 1 | Wenn ich auf einer Ausschreibung „Bewerben“ wähle und meine Bewerbung absende, erhalte ich eine Bestätigung. |
| Akzeptanztest 2 | Wenn meine Stammdaten unvollständig oder fehlerhaft sind, wird die Bewerbung abgelehnt und mir angezeigt, was fehlt. |
| Akzeptanztest 3 | Ich kann mich auf dieselbe Ausschreibung nicht zweimal bewerben. |

## 2b) Einfluss auf das Use-Case-Modell

### Neue Stories A und B (Suchen und Bewerben)

- Die beiden Stories beschreiben eigenständige Funktionen des Studenten, die im bisherigen Modell fehlen. Das Modell wird daher um zwei Use Cases erweitert, die mit dem Akteur Student assoziiert sind:
  - **Search for project listings**
  - **Apply for project listing**
- Zwischen „Apply for project listing“ und „Manage applications“ wird keine Beziehung modelliert. Dass die Bewerbung anschließend beim Unternehmen landet, ist eine zeitliche Abfolge, und mit Use Cases sollen keine Prozesse modelliert werden.
- Das erweiterte Diagramm befindet sich in `UseCase_Uebung5_2b.pdf`.

### Story „Inhaltsangabe hinzufügen“ (Unternehmer)

- Das Hinzufügen einer Inhaltsangabe ist kein eigener Use Case, sondern ein zusätzlicher Schritt innerhalb des bestehenden Use Case **Post project listing** („bei der Aufgabe einer Projektausschreibung“).
- Das Use-Case-Diagramm ändert sich dadurch nicht. Die Erweiterung betrifft nur die textuelle Beschreibung (den Ereignisfluss) von „Post project listing“.

## 2c) Textueller Use Case und Test Case

**Annahme:** Die Formulierung „zu dieser User Story“ wird auf die Story zur Bewerbung auf Projektausschreibungen (Story B aus 2a) bezogen, da der Use Case und der Test Case laut Aufgabe die Bewerbung abdecken sollen.

### Textueller Use Case

| Element | Inhalt |
|---|---|
| Name des Systems | Coll@HBRS |
| Name des Use Case | Apply for project listing |
| Teilnehmende Akteure | Student |
| Ereignisfluss | 1. Student öffnet eine Projektausschreibung und klickt auf den Button „Bewerben“.<br>&emsp;&emsp;2. SYSTEM zeigt das Bewerbungsformular an. Die Stammdaten (Name, Geburtsdatum, Studiengang) sind aus dem Profil des Studenten vorausgefüllt.<br>3. Student prüft bzw. ergänzt seine Stammdaten, lädt seinen Lebenslauf hoch, schreibt ein kurzes Anschreiben in das Textfeld und klickt auf den Button „Bewerbung absenden“.<br>&emsp;&emsp;4. SYSTEM prüft die Stammdaten auf Vollständigkeit und Gültigkeit.<br>&emsp;&emsp;5. SYSTEM speichert die Bewerbung und ordnet sie der Projektausschreibung zu.<br>&emsp;&emsp;6. SYSTEM zeigt dem Studenten eine Bestätigung an. |
| Alternativer Ereignisfluss | 4a. Die Stammdaten sind fehlerhaft oder unvollständig (z. B. fehlendes Geburtsdatum oder ein Geburtsdatum in der Zukunft).<br>&emsp;&emsp;4a.1 SYSTEM markiert das fehlerhafte Feld und zeigt eine Fehlermeldung an.<br>&emsp;&emsp;4a.2 Der Ablauf wird bei Schritt 3 fortgesetzt. |
| Vorbedingung | Student ist registriert und eingeloggt.<br>Die Projektausschreibung existiert und ist nicht abgelaufen.<br>Student hat sich noch nicht auf diese Projektausschreibung beworben. |
| Nachbedingung | Die Bewerbung ist gespeichert und in der Bewerbungsliste des Unternehmens sichtbar.<br>Student hat eine Bestätigung über die erfolgreiche Bewerbung erhalten. |

### Textueller Test Case

Siehe `TestCase_Uebung5.xlsx` (Reiter „Complex Test Case“).
