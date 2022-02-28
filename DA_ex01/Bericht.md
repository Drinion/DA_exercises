# Bericht

### Computerspezifikationen

MacBook Pro

2GHz Quad-Core Intel Core i5

32 GB 3733 MHz-LPDDR4X

### Messwerte

Die erreichten Messwerte ensprechen beim Insertion Sort und dem Merge Sort den Erwartungen. Der Graph der vom Insertion Sort generiert wurde, enspricht ungefähr einer quadratischen Funktion. Beim Merge Sort entspricht der Graph ebenfalls ungefähr der Funktion n*log(n), wobei hier noch grössere Messwerte hätten verwendet werden müssen, damit das besser sehbar ist. Grössere Messwerte dauern auf dem obigen Rechner aber sehr lange um Sie auszurechnen.

### Abschätzung

Aus dem Graphen kann abgelesen werden, dass die benötigte Zeit bei 3'000'000 Elementen etwa 14'000ms entspricht. Um die benötigte Zeit für 10'000'000 abzuschätzen reicht es, den Koeffizienten a von a*x^2 zu berechnen. 

Also a*(3'000'000)^2 = 14'000 ergibt a = 7/4500000000 und somit a*(10'000'000)^2 = 155555.55555.

Es benötigt also etwa 155'555 ms um 10'000'000 Elemente anzuordnen.