/**
 * File: NameVornameComparator.java
 *
 * Klasse zum Vergleichen zweier Objekte (Records) vom Typ StudentIn
 * bezueglich des Namens/Vornames.
 *
 */

public class NameVornameComparator implements java.util.Comparator<StudentIn> {
  private String nameA;
  private String nameB;

  public int compare(StudentIn a, StudentIn b) {
    nameA = a.getName() + a.getVorname();
    nameB = b.getName() + b.getVorname();

    return lexico(nameA, nameB);
  }

  private static final int lexico(String nameA, String nameB) {
    for(int i = 0; i < nameA.length() && i < nameB.length(); i++) {
      if(nameA.charAt(i) == nameB.charAt(i)) {
        continue;
      }
      else {
        return (int)nameA.charAt(i) - (int)nameB.charAt(i);
      }
    }
    if(nameA.length() < nameB.length()) {
      return (nameB.length() - nameA.length());
    }
    else if(nameA.length() > nameB.length()) {
      return (nameA.length() - nameB.length());
    }
    return 0;
  }
}
