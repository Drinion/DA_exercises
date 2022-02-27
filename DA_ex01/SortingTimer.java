import da_ex01.Sorting;
import da_ex01.Timer;

import java.util.*;     //for Random and Arraylist
import java.lang.*;     // for Integer object
import java.io.File;
import java.io.PrintWriter;

public class SortingTimer {

  long time_insertionSort(int[] array)    //long used in Timer
  {
    Timer timer = new Timer();
    Sorting.insertionSort(array);
    return timer.timeElapsed();
  }

  long time_mergeSort(int[] array)
  {
    Timer timer = new Timer();
    Sorting.mergeSort(array, 0, array.length-1);
    return timer.timeElapsed();
  }

  void runExperiment() {
    Random rd = new Random(); // creating Random object

    ArrayList<Integer> tested_array_lengths = new ArrayList<Integer>();
    for(int n = 30000; n <= 300000; n += 10000){
      tested_array_lengths.add(n);
    }

    System.out.println("array length, insertion sort time, merge sort time"); //if not "pseudo CSV" output delete this line
    PrintWriter pw_merge_sort = null;
    PrintWriter pw_insertion_sort = null;
    try {
      pw_merge_sort = new PrintWriter(new File("time_merge_sort.csv"));
      pw_insertion_sort = new PrintWriter(new File("time_insertion_sort.csv"));
    } catch (Exception e) {
      System.out.println("File not found");
    }
    StringBuilder builder_merge_sort = new StringBuilder();
    StringBuilder builder_insertion_sort = new StringBuilder();
    builder_merge_sort.append("#Elements,Time" +"\n");
    builder_insertion_sort.append("#Elements,Time" +"\n");

    for (int i : tested_array_lengths) {

      int[] array = new int[i];
      for (int j = 0; j < array.length; j++) {
        array[j] = rd.nextInt(1000); // storing random integers in an array
      }
      int[] array2 = new int[array.length];
      System.arraycopy(array, 0, array2, 0, array.length); //copy array into array2

      // for nicely readable output instead of "pseudo CSV format"
      //System.out.println("array length: " + i);
      //System.out.println("insertion sort time [s]: " + time_insertionSort(array));
      //System.out.println("merge sort time [s]: " + time_mergeSort(array2) + "\n");

      // System.out.println(i + ", " + time_insertionSort(array) + ", " + time_mergeSort(array2));
      //
      builder_merge_sort.append(i+",");
      builder_merge_sort.append(time_mergeSort(array2));
      builder_merge_sort.append("\n");
      builder_insertion_sort.append(i+",");
      builder_insertion_sort.append(time_insertionSort(array));
      builder_insertion_sort.append("\n");
    }
    pw_merge_sort.write(builder_merge_sort.toString());
    pw_merge_sort.close();
    pw_insertion_sort.write(builder_insertion_sort.toString());
    pw_insertion_sort.close();
  }

  public static void main(String[] args)
  {

    SortingTimer st = new SortingTimer();
    st.runExperiment();
  }
}
