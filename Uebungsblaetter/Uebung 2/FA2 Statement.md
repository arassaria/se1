# FA2-Statement

Wenn wir mit Strings als Rückgabewert für Fehlerfälle arbeiten, könnten diese im Vergleich zu
Exceptions einfach ignoriert werden und müssten keine Behandlung erhalten. Desweiteren
sind Erfolg und Fehler nur über String-Vergleiche unterscheidbar, was fehleranfälliger ist, als
im Fehlerfall direkt eine Exception zu werfen. Weiterhin müssen Fehler auf jeder Ebene manuell
weitergereicht werden, während Exception automatisch den Stack hochwandern, bis sie
jemand fängt.