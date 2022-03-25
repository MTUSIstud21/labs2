package lab1;

import java.util.Scanner;

public class task7 {
    public static void main(String[] args){
        Scanner in =new Scanner(System.in);
        System.out.print("Введите код города: ");
        int num = in.nextInt();
        int time = 10;
        if (num==905){
            double price = time * 4.15;
            System.out.print("Москва. Стоимость разговора: "+price);
        }if (num==194){
            double price = time * 1.98;
            System.out.print("Ростов. Стоимость разговора: "+price);
        }if (num==491){
            double price = time * 2.69;
            System.out.print("Краснодар. Стоимость разговора: "+price);
        }if (num==800){
            double price = time * 5.00;
            System.out.print("Киров. Стоимость разговора: "+ price);
        }
    }
}
