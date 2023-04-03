package Main;

import Person.Character;

import java.util.Scanner;

public class Main {
    public static Character Hero;

    public static void Options() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Available options");
            System.out.println("1.See your Stats \n2.See available Allys \n3.Use items \n4.Continue");
            int press = scan.nextInt();
            switch (press) {
                case 1:
                    Hero.ShowMyStats();
                    break;
                case 2:
                    Game.ShowAllyList();
                    break;
                case 3:
                    Hero.Use(Character.getAbleIt());
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Wrong number");
            }
        }
    }

    public static void main(String[] args) {
        Game g = new Game();
        Scanner scan = new Scanner(System.in);
        Game.GenM();
        Game.GenC();
        System.out.println("Create your Character");
        Game.fill();
        Hero = Game.ableChar[0];
        System.out.println("Now your adventure starts");
        int allyI = 0, MonstI = 0;
        int way;
        boolean meet;
        while (true) {
            if (Hero.getFreeEXP() >= 5) Hero.LevelUp();
            Options();
            meet = Game.Rand();
            if (allyI == Game.Carr.length) meet = true;
            if (meet) {
                System.out.println(Hero.Ally(meet));
                System.out.println("defeat enemy :" + Game.Marr[MonstI].getName());
                while (Hero.getHP() > 0 && Game.Marr[MonstI].getHP() > 0) {
                    Game.Marr[MonstI].ShowMyStats();
                    System.out.println("1.Attack \n2.Call an Ally \n3.Use Item");
                    way = scan.nextInt();
                    switch (way) {
                        case 1:
                            for (Character a : Game.ableChar) {
                                if (a == null || Game.Marr[MonstI].getHP() == 0) break;
                                System.out.println(a.getName() + " attacks");
                                a.Attack(Game.Marr[MonstI]);
                            }
                            if (Game.Marr[MonstI].getHP()>0)Game.Marr[MonstI].ShowMyStats();
                            else System.out.println("Enemy status: \nDead");
                            break;
                        case 2:
                            if (Game.ableChar[1] != null) System.out.println("Your team is full");
                            else Hero.Call();
                            continue;
                        case 3:
                            Hero.Use(Character.getAbleIt());
                            continue;
                    }
                    if (Game.Marr[MonstI].getHP() != 0) {
                        Game.Marr[MonstI].Attack(Game.Rand(), Hero);
                        System.out.println(Game.Marr[MonstI].getName() + " attacks");
                        Hero.ShowMyStats();
                    }else System.out.println("Enemy defeated");
                }
                if (Hero.getHP() < 1) {
                    System.out.println("Game over");
                    return;
                }
                if (Character.getAbleIt().size() != 3) {
                    Character.getAbleIt().add(Game.Marr[MonstI].getItem());
                    System.out.println(Game.Marr[MonstI].getItem().getName() + " was added");
                }
                if (Game.ableChar[1] != null) {
                    System.out.println(Game.ableChar[1].getName() + " leaves team");
                    Game.ableChar[1] = null;
                }
                if (MonstI+1 == Game.Marr.length) {
                    System.out.println("Congratulations, You won!");
                    return;
                } else MonstI++;
            } else {
                if (allyI < Game.Carr.length) {
                    System.out.println(Hero.Ally(meet));
                    System.out.println(Game.Carr[allyI].getName() + ": I will help you, just call me.");
                    Game.FoundCarr.add(Game.Carr[allyI]);
                    allyI++;
                }
            }
        }
    }
}