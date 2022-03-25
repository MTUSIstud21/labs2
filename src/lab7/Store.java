package lab7;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Store {
    HashMap<String, String> data = new HashMap<>();
    ArrayList<Product> products = new ArrayList<>();
    ArrayList<String> category = new ArrayList<>();
    ArrayList<Product> cart = new ArrayList<>();
    ArrayList<Product> buff = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public User auth() {
        System.out.print("login: ");
        String login = sc.next();
        System.out.print("password: ");
        String password = sc.next();
        if (data.containsKey(login) && data.containsValue(password))
            return new User(login, password);
        System.out.println("User not found");
        return new User();
    }

    public void showCatalog() {
        System.out.println("\n    KATEGORIA");
        int i = 0;
        for (String c : category)
            System.out.println(++i + ") " + c);
        System.out.print("Select a KATEGORIA or exit 0: ");
        if (sc.hasNextInt()) {
            i = sc.nextInt();
            if (i == 0) {
                getMenu();
                return;
            }
            showProduct(category.get(i - 1));
        } else
            showProduct(sc.next());
    }

    public void showProduct(String category) {
        System.out.println("\n    PRODUKT");
        int i = 0;
        buff.clear();
        for (Product p : products)
            if (p.category.toLowerCase().contains(category.toLowerCase())) {
                System.out.println(++i + ") " + p.name + " " + p.price + "rub");
                buff.add(p);
            }
        pushCart(buff);
    }

    public void showProduct() {
        System.out.println("\n    PRODUKT");
        int i = 0;
        for (Product p : products)
            System.out.println(++i + ") " + p.name + " " + p.price + "rub");
        pushCart(products);
    }

    public void showCart() {
        System.out.println("\n    KORZINA");
        int i = 0;
        for (Product p : cart)
            System.out.println(++i + ") " + p.name + " " + p.price + "rub");
        System.out.print("\n    Enter 0 to get to the menu or 4 go to OPLATA: ");
        i = sc.hasNextInt() ? sc.nextInt() : 0;
        if (i == 0)
            getMenu();
        else if (i == 4)
            payment();
    }

    public void pushCart(ArrayList<Product> pp) {
        System.out.print("\n    Add to KORZINA or exit 0: ");
        if (sc.hasNextInt()) {
            int i = sc.nextInt();
            if (i == 0) {
                getMenu();
                return;
            }
            cart.add(pp.get(i - 1));
            System.out.println("    " + pp.get(i - 1).name + " added");
        } else
            pushCart(sc.next());
        pushCart(pp);
    }

    public void pushCart(String a) {
        int i = -1;
        while (++i < products.size()) {
            if (products.get(i).name.equals(a)) {
                cart.add(products.get(i));
                break;
            }
        }
    }

    public void payment() {
        int cost = 0;
        for (Product p : cart)
            cost += p.price;
        System.out.println("The cost of your KORZINA: " + cost);
        System.out.print("Enter 1 to pay or 0 to exit: ");
        cost = sc.hasNextInt() ? sc.nextInt() : 0;
        if (cost == 1) {
            write();
            System.out.println("success");
        } else if (cost == 0)
            getMenu();
    }

    public void write() {
        int i = 0;
        try {
            PrintWriter out = new PrintWriter(new FileOutputStream(new File("src\\lab7\\KORZINA")));
            for (Product p : cart)
                out.println(++i + ") " + p.name + " " + p.price + " rub");
            out.flush();
            out.close();
            System.out.println("File has been written");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void serialized() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(new File("src\\lab7\\ser")));
            out.writeObject(cart.toString());
            out.close();
            System.out.println("File has been serialized");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String deserialized() {
        String ser = "";
        try {
            ObjectInputStream input = new ObjectInputStream(
                    new FileInputStream("src\\lab7\\ser"));
            ser = (String) input.readObject();
            input.close();
            System.out.println("File has been deserialized");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ser;
    }

    public void getMenu() {
        System.out.println("\n    Menu\n1 KATEGORIA\n2 PRODUKT\n3 KORZINA\n4 OPLATA\n0 Exit");
        System.out.print("Select: ");
        String choice = sc.next().toLowerCase(); // scan.nextline doesn`t work, why?
        if (choice.contains("1") || choice.contains("KATEGORIA"))
            showCatalog();
        else if (choice.contains("2") || choice.contains("PRODUKT"))
            showProduct();
        else if (choice.contains("3") || choice.contains("KORZINA"))
            showCart();
        else if (choice.contains("4") || choice.contains("OPLATA"))
            payment();
        else if (choice.contains("0") || choice.contains("exit"))
            return;
        else
            getMenu();
    }

    public void fill() {
        data.put("admin", "admin");
        data.put("1111", "1111");

        products.add(new Product("Сappuccino", 100, "Coffy"));
        products.add(new Product("Latte", 110, "Coffy"));
        products.add(new Product("Espresso", 90, "Coffy"));
        products.add(new Product("Black tea", 70, "Tea"));
        products.add(new Product("Green tea", 80, "Tea"));

        for (Product p : products)
            if (!category.contains(p.category))
                category.add(p.category);
    }

    public static void main(String[] msi) {
        Store s = new Store();
        System.out.println("Welcome\nLog in to your account");
        s.fill();

        User user = s.auth();
        if (user.login != "")
            s.getMenu();

        s.serialized();
        System.out.println(s.deserialized());
        s.sc.close();
    }
}