package lab1;

import java.util.Scanner;

public class task3 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.print("Введите число: ");
        int num = in.nextInt();
        if (num >= 0){
            System.out.print(num+1);
        }else System.out.print(num);
    }
}
