import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/*
Чтобы точно проснуться с утра,
Алексей каждый вечер заводит N будильников на своём телефоне.
Каждый будильник устроен таким образом, что он звонит каждые X
 минут с того момента времени, на который его завели. Например,
 если будильник был заведён на момент времени ti, то он будет звонить
  в моменты времени ti, ti+X, ti+2⋅X и так далее.
При этом если какие-то два будильника начинают звонить в один момент времени,
  то отображается только один из них.
Известно, что прежде чем проснуться, Алексей каждое утро слушает ровно K
 будильников, после чего просыпается. Определите момент времени, когда Алексей проснётся.
Входные данные
Первая строка содержит три целых числа N, X и K — количество будильников, периодичность звонков и
количество будильников, которое нужно отключить, чтобы Алексей проснулся.
Вторая строка содержит N целых чисел t1, t2, …, tN — моменты времени на которые были заведены будильники.

Выведите одно число — момент времени, когда Алексей проснётся.
 */

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<int[]> arr=new ArrayList<>();
        Scanner scanner=new Scanner(new File("input.txt"));
        String firstLine=scanner.nextLine();
        String[] firstNumbers=firstLine.split(" ");
        int n=Integer.parseInt(firstNumbers[0]); //количество будильников
        int x=Integer.parseInt(firstNumbers[1]); //периодичность звонков
        int k=Integer.parseInt(firstNumbers[2]); //количество будильников, которое нужно отключить, чтобы Алексей проснулся
        String secondLine=scanner.nextLine();
        String[] mass=secondLine.split(" ");
        int[] moments=new int[mass.length]; //моменты времени на которые были заведены будильники
        for (int i = 0; i < mass.length; i++) {
          moments[i]=Integer.parseInt(mass[i]);
        }
        findListOfAlarms(n,x,k,moments,arr);
        findTime(arr,k);
    }
    public static void findListOfAlarms(double n, int x, double k, int[] moments, List<int[]>arr){
        int count= (int) Math.ceil(k/n);
        arr.add(moments);
        int total=1; //тут счетчик начинаем с 1, а не 0, тк мы выше добавили туда изначальный массив
        while (total <= count){
            int[] newArray=new int[moments.length];
            for (int i = 0; i < moments.length; i++) {
                newArray[i]=moments[i]+x;
            }
            arr.add(newArray);
            total++;
            moments=newArray;
        }

      //  тут мы выводим на печать для проверки
//        for (int[] a:arr) {
//            for (int value:a) {
//                System.out.println(value+" ");
//            }
//            System.out.println();
//        }

    }
    public static void findTime(List<int[]> arr, double k){
        Set<Integer> mergeSet=new TreeSet<>();
        for (int[] a:arr) {
            for (int value:a) {
                mergeSet.add(value);
            }
        }
        //   System.out.println(mergeSet); // тут мы выводим на печать для проверки
        double current=1; //начинаем счетчик с 1 а не 0, тк тут мы считаем с первого будильника, а не нулевого
        for (int value:mergeSet) {
            if (current == k){
                System.out.println(value);
                break;
            }
            current++;
        }
    }
}
