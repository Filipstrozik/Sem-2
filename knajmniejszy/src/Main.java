import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static int sekcja(ArrayList<Integer> arr, int start, int end){
        int V = arr.get(end);
        int index = start;
        for(int i=start; i<=end;i++){
            if(arr.get(i) < V){
                int temp=arr.get(i);
                arr.set(i,arr.get(index));
                arr.set(index,temp);
                index++;
            }
        }
        int temp=arr.get(end);
        arr.set(end,arr.get(index));
        arr.set(index,temp);
        return index;
    }
    public static void kNajmniejszy(ArrayList<Integer> arr, int start, int end, int k){
        int index = sekcja(arr,start,end);
        if(index == k-1){
            System.out.println(arr.get(index));
        }
        else if(index < k-1)
        {
            kNajmniejszy(arr,index+1,end,k);
        }
        else
        {
            kNajmniejszy(arr,start,index-1,k);
        }
    }



    public static void main(String[] args) {
	ArrayList<Integer> list = new ArrayList<>(Arrays.asList(7,10,9,2,3,1,5,8,57,8));
        kNajmniejszy(list,0,list.size()-1,5);
    }
}
