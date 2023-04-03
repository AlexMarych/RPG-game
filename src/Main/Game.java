package Main;

import Person.Character;
import Person.Monster;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    public static Character[] Carr;
    public static ArrayList<Character> FoundCarr = new ArrayList<>();
    public static Character[] ableChar = new Character[2];
    public static Monster[] Marr;


    public static int random() {
        return (int) (Math.random() * (10 - 5) + 1);
    }

    public static int randomExp() {
        return (int) (Math.random() * 5+1);
    }

    public static int randomOther() {
        return (int) (Math.random() * 3 );
    }

    public static boolean Rand() {
        Random b = new Random();
        return b.nextBoolean();
    }



    public static void ShowAllyList() {
        System.out.println("Available Allys:");
        int i = 1;
        if(FoundCarr.size() == 0) {
            System.out.println("no available allys\n");
            return;
        }
            for (Character a : FoundCarr) {

                System.out.println(i + "." + a.getName() + " \nStamina = " + a.getStamina() + " \nMana = " + a.getMana() + " \nHp = " + a.getHP());
                i++;

        }
    }

    public static Item GenI() {
        int value = randomExp();
        String[] Name = {"Flask", "Cheese", "Clarity"};
        int rand = (int)(Math.random()*3);
        return new Item(Name[rand], value);
    }

    public static void GenM() {
        String[] ListOfNames = {"skeleton", "zombie", "golem"};
        String name;
        int stamina, mana, hp, exp;
        int rand1 = (int) (Math.random() * (5)+3);
        Marr = new Monster[rand1];
        for (int i = 0; i < rand1; i++) {
            name = ListOfNames[randomOther()];
            stamina = random();
            mana = random();
            hp = random();
            exp = randomExp();
            Marr[i] = new Monster(name, stamina, mana, hp, exp);
        }
    }

    public static void GenC() {
        String[] ListOfNames = {"David", "Lisa", "Lukas"};
        String name;
        int stamina, mana, hp;
        int rand1 = (int) (Math.random() * (3)+1);
        Carr = new Character[rand1];
        for (int i = 0; i < rand1; i++) {
            name = ListOfNames[i];
            stamina = random();
            mana = randomExp();
            hp = random();
            Carr[i] = new Character(name, stamina, mana, hp);
        }
    }

    public static void fill() {
        Character main = new Character("Main Hero", 3, 0, 5);
        int stats = 15;
        int add;
        String[] stat = {"Stamina", "Mana", "Hp"};
        Scanner s = new Scanner(System.in);
        System.out.println("Please select stats of main character");
        System.out.println("spread points between stats");
        System.out.println("You have basic stats\nStamina: " + main.getStamina() + " \nMana: " + main.getMana() + " \nHp: " + main.getHP());
        while (stats > 0) {
            System.out.println("You have " + stats + " points");
            System.out.println("add points to stat");
            for (int i = 0; i < stat.length; i++) {
                System.out.println((i + 1) + "." + stat[i]);
            }
            int i = s.nextInt();
            if (i < 1 || i > 3) {
                System.out.println("Wrong number");
                continue;
            }
            System.out.println("How much you want to add");
            add = s.nextInt();
            if (add < 0 || add > 15) {
                System.out.println("Wrong number");
                continue;
            }
            switch (i) {
                case 1:
                    main.setMaxStamina(main.getMaxStamina() + add);
                    main.setStamina(main.getStamina() + add);
                    break;
                case 2:
                    main.setMaxMana(main.getMaxMana() + add);
                    main.setMana(main.getMana() + add);
                    break;
                case 3:
                    main.setMaxHp(main.getMaxHp() + add);
                    main.setHP(main.getHP() + add);
                    break;
            }
            stats -= add;
        }
        ableChar[0] = main;
    }
}
