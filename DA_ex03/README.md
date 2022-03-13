## Datenstrukturen und Algorithmen: Praktische Aufgaben

### 1.) Output

MatrikelNr Name Vorname
94419832 Briod Jean
99323462 Fischer Hans
96419642 Gans Gustav
92987654 Habegger Pascal
89345675 Moser Käthy
99876532 Müller Anton
90588921 Müller Kurt
98345632 Schneider Anna
98222634 Stucki Daniel
92123456 Wenger Thomas

### 2.) StackOverflowError

Beim Quicksort wird ein Pivot Element ausgewählt und das Array dann bei diesem Pivot Element in zwei Arrays aufgeteilt. Alle Elemente im Array welche grösser sind als das Pivot Element kommen dann in das rechte und alle kleineren in das linke Array. Dieser Prozess wird rekursiv auf den Teil-Arrays wiederholt. Bei einem sortierten Arrays ist der beste Fall, wenn dass Pivot Element genau in der Mitte des Arrays gewählt wird. Der schlechteste Fall bei einem sortierten Array ist, wenn es beim grössten oder kleinsten gewählt wird. Bei dieser Implementierung wird das rechteste Element im Array als erstes Pivot Element gewählt. Da das Array bereits sortiert ist, ist das auch das grösste Element und es tritt der zeitliche Worst-Case des Algorithmus ein. Der StackOverflowError entsteht, da bei jeder Rekursion die benötigten Variablen im Stack gespeichert werden und dieser nach genügend Rekursionen voll ist. Das kann auch eintreten, wenn eine Rekursive Methode keine Abbruchbedingung hat. Hier liegt es an der grossen Input Menge und der wahl des Pivots.