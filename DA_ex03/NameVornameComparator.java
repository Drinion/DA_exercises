/**
 * File: NameVornameComparator.java
 *
 * Klasse zum Vergleichen zweier Objekte (Records) vom Typ StudentIn
 * bezueglich des Namens/Vornames.
 *
 */

public class NameVornameComparator implements java.util.Comparator<StudentIn> {

  public int compare(StudentIn a, StudentIn b) {
    if (a.getName().compareTo(b.getName()) == 0) { // if same last Name
      return a.getVorname().compareTo(b.getVorname()); // sort by Vorname
    }
    else{
      return a.getName().compareTo(b.getName());// else sort by Name
    }

  }

}
