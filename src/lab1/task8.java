package lab1;

public class task8 {
    public static void main(String[] args) {
        int[] num = new int[]{1, -10, 5, 6, 45, 23, -45, -34, 0, 32, 56, -1, 2, -2};
        int a = num.length;
        int max = num[1];
        int sum_p = 0;
        int sum = 0;
        int kol = 0;
        int summa = 0;
        int koll = 0;
        double sr =0;
        for (int i = 0; i < a; i++) {
            if (num[i]>max){
                max=num[i];
            }if (num[i]>=0){
                sum_p=sum_p+num[i];
            }if (num[i]<0 & num[i]%2==0){
                sum= sum+num[i];
            }if (num[i]>=0){
                kol=kol+1;
            }if (num[i]<0){
                summa=summa+num[i];
                koll=koll+1;
            }
        }sr=summa/koll;
        System.out.println("Максимальное значение: "+max);
        System.out.println("Сумма положительных элементов: "+sum_p);
        System.out.println("Сумма четных отрицательных элементов: "+sum);
        System.out.println("Количество положительных элементов: "+ kol);
        System.out.println("Среднее арифметическое отрицательных элементов: "+ sr);

    }
}
