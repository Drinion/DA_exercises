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

    ArrayList<Integer> insertion_tested_array_lengths = new ArrayList<Integer>();
    ArrayList<Integer> merge_tested_array_lengths = new ArrayList<Integer>();

    for(int n = 30000; n <= 300000; n += 10000){
      insertion_tested_array_lengths.add(n);
    }

    for(int n = 30000; n <= 300000; n += 10000){
      merge_tested_array_lengths.add(n);
    }

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

    for (int i : insertion_tested_array_lengths) {

      int[] array = new int[i];
      for (int j = 0; j < array.length; j++) {
        array[j] = rd.nextInt(100000); // storing random integers in an array
      }

      builder_insertion_sort.append(i+",");
      builder_insertion_sort.append(time_insertionSort(array));
      builder_insertion_sort.append("\n");
    }
    for (int i: merge_tested_array_lengths) {

      int[] array2 = new int[i];
      for (int p = 0; p < array2.length; p++) {
        array2[p] = rd.nextInt(1000); // Limit set to 1000 to reduce spikes in graph
      }

      builder_merge_sort.append(i+",");
      builder_merge_sort.append(time_mergeSort(array2));
      builder_merge_sort.append("\n");
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
