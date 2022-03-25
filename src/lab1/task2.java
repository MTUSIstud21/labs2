package lab1;

import java.util.Scanner;

public class task2 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.print("Введите число: ");
        int num = in.nextInt();
        int a = num/10;
        a = a/10;
        int b = num/10;
        b = b%10;
        int c = num%10;
        int sum = a+b+c;
        System.out.print(sum);
    }

}
