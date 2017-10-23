package mergesort;

import java.util.Random;
import edu.princeton.cs.algs4.Stopwatch;


/**
 * Created by Vasiliy Kylik on 23.10.2017.
 */
public class IntersectionOfTwoSets<Item extends Comparable<Item>> {

        public void selection(Item[] data){
            for(int i=0; i<data.length; i++){
                int min = i;
                for(int j=i+1; j<data.length; j++){
                    min = lesser(data, min, j) ? min : j;
                }
                swap(data,i,min);
            }
        }

        public void insertionSort(Item[] data){
            for(int i=1; i< data.length; i++){
                for(int j=i; j>0 && lesser(data,j,j-1); j--){
                    swap(data, j, j-1);
                }
            }
        }



        public void shell(Item[] data){
            int h = 1;
            while(h < data.length){
                h = 3 * h + 1;
            }

            while(h > 0){

                for(int i=h; i<data.length; i++){
                    for(int j=i; j>=h && lesser(data,j,j-h); j-=h){
                        swap(data,j,j-h);
                    }
                }
                h = h/3;
            }
        }


        private void swap(Item[] data,int i, int j){
            Item buff = data[i];
            data[i] = data[j];
            data[j] = buff;
        }

        private boolean lesser(Item[] data, int i, int j){
            return data[i].compareTo(data[j]) <= 0;
        }

        private Integer[] build(int N){
            Random rand = new Random(System.currentTimeMillis());
            Integer[] data = new Integer[N];
            int i=0;
            while(N-- >0){
                data[i++] =rand.nextInt(10000);
            }
            return data;
        }

        private void check(Item[] data){
            for(int i=1; i<data.length; i++){
                if(lesser(data,i-1,i))continue;
                else {
                    System.out.println(i-1+"_"+i);
                    throw new RuntimeException("Not sorted");
                }
            }
        }
        public static void main(String[] args){
            int N = 10000000;

            IntersectionOfTwoSets<Integer> sorter = new IntersectionOfTwoSets<>();
            Integer[] data = sorter.build(N);
            Stopwatch watch = new Stopwatch();
//		sorter.insertionSort(data);
//		System.out.println(watch.getDuration());
//		sorter.check(data);
//
//		data = sorter.build(N);
//		System.out.println("Shell Sort");
//		watch = new StopWatch();
//		sorter.shell(data);
//		System.out.println(watch.getDuration());
//		sorter.check(data);

            System.out.println("merge Sort");
            data = sorter.build(N);
            Merge<Integer> mergeSorter = new Merge<>();
            watch = new Stopwatch();
            mergeSorter.mergeSort(data);
            System.out.println(watch.elapsedTime());
            sorter.check(data);

            System.out.println("Bottom Up Merge");
            data = sorter.build(N);
            watch = new Stopwatch();
            mergeSorter.bottomUp(data);
            System.out.println(watch.elapsedTime());
            sorter.check(data);

            System.out.println("Quick Sort");
            data = sorter.build(N);
            quick.sort.Quick<Integer> quickSorter = new quick.sort.Quick<>();
            watch = new Stopwatch();
            quickSorter.quickSort(data);
            System.out.println(watch.elapsedTime());
            sorter.check(data);

            System.out.println("3 Way Quick Sort");
            data = sorter.build(N);
            watch = new Stopwatch();
            quickSorter.threeWayQuickSort(data);
            System.out.println(watch.elapsedTime());
            sorter.check(data);
        }

    }

