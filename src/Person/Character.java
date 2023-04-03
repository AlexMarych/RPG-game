package Person;

import java.util.ArrayList;
import java.util.Scanner;

import Main.Game;
import Main.Main;
import Main.Item;

public class Character {
    protected String Name;
    protected int Stamina;
    protected int MaxStamina;
    protected int Mana;
    protected int MaxMana;
    protected int Hp;
    protected int MaxHp;
    private int FreeEXP;
    private static ArrayList<Item> ableIt = new ArrayList<>();


    public Character(String name, int stamina, int mana, int HP) {
        this.Name = name;
        this.MaxStamina = stamina;
        this.MaxMana = mana;
        this.MaxHp = HP;
        this.Hp = MaxHp;
        this.Stamina = MaxStamina;
        this.Mana = MaxMana;
    }

    public Character(int stamina, int mana, int hp) {
        this.MaxStamina = stamina;
        this.MaxMana = mana;
        this.MaxHp = hp;
    }

    public static ArrayList<Item> getAbleIt() {
        return ableIt;
    }

    public String getName() {
        return Name;
    }

    public int getStamina() {
        return Stamina;
    }

    public int getMana() {
        return Mana;
    }

    public int getHP() {
        return Hp;
    }


    public static void ShowItemList() {
        int i = 1;
        System.out.println("Flask adds HP \nCheese adds Stamina \nClarity adds Mana\n");
        if (ableIt == null) System.out.println("no available Items");
        for (Item a : ableIt) {
            System.out.println(i + "." + a.getName() + " " + a.getValue());
            i++;
        }
    }
    public void setStamina(int stamina) {
        if (stamina > MaxStamina) Stamina = MaxStamina;
        else Stamina = stamina;
    }

    public void setMana(int mana) {
        if (mana > MaxMana) Mana = MaxMana;
        else Mana = mana;
    }

    public void setHP(int hp) {
        if (hp > MaxHp) Hp = MaxHp;
        else Hp = hp;
    }

    public int getFreeEXP() {
        return FreeEXP;
    }

    public int getMaxStamina() {
        return MaxStamina;
    }

    public void setMaxStamina(int maxStamina) {
        MaxStamina = maxStamina;
    }

    public int getMaxMana() {
        return MaxMana;
    }

    public void setMaxMana(int maxMana) {
        MaxMana = maxMana;
    }

    public int getMaxHp() {
        return MaxHp;
    }

    public void setMaxHp(int maxHp) {
        MaxHp = maxHp;
    }

    public void ShowMyStats() {
        System.out.println(getName() + " status:");
        System.out.println("Stamina = " + getStamina() + "/" + getMaxStamina() + "\nMana = " + getMana() + "/" + getMaxMana() + "\nHp = " + getHP() + "/" + getMaxHp());
    }

    public void ShowMyStats(int i) {
        System.out.println( "1.Stamina = " + getStamina() + "/" + getMaxStamina() + "  +" + i + "\n2.Mana = " + getMana() + "/" + getMaxMana() + "  +" + i + "\n3.Hp = " + getHP() + "/" + getMaxHp() + "  +" + i);
    }

    private boolean NotEnough() {
        System.out.println("use Item or wait");
        System.out.println("1.Item \n2.Wait");
        Scanner scanner = new Scanner(System.in);
        int choose = scanner.nextInt();
        while (true) {
            if (choose == 1) {
                Use(ableIt);
                return true;
            } else if (choose == 2) {
                setStamina(Stamina + 1);
                return false;
            } else System.out.println("Wrong number");

        }
    }

    public void Attack(Monster obj) {
        System.out.println("Choose type of Attack \n1.Physical \n2.Magic");
        Scanner scanner = new Scanner(System.in);
        int type = scanner.nextInt();
        while (true) {
            if (type == 1) {
                if (obj.getHP() > 1) {
                    if (Stamina > 0) {
                        Stamina--;
                        obj.setHP(obj.getHP() - 1);
                        return;
                    } else {
                        System.out.println("Not enough Stamina");
                        if (NotEnough() == true) continue;
                    }
                } else {
                    obj.setHP(0);
                    Stamina--;
                    FreeEXP += obj.getEXPtoGive();
                }
            } else if (type == 2) {
                if (obj.getHP() > 1) {
                    if (Mana > 0) {
                        Mana -= 1;
                        obj.setHP(obj.getHP() - 1);
                    } else {
                        System.out.println("Not enough Mana");
                        if (NotEnough() == true) continue;
                    }
                } else {
                    obj.setHP(0);
                    Mana--;
                    FreeEXP += obj.getEXPtoGive();
                }
            } else {
                System.out.println("Wrong number");
                continue;
            }
            return;
        }
    }

    public String Ally(boolean flag) {
        if (flag) return "Monster";
        else return "Ally";
    }

    public Character[] Call() {
        System.out.println("Remember ,you can call Ally only ones \nand only one in one time");
        Game.ShowAllyList();
        System.out.println("4.Cancel");
        Scanner scan = new Scanner(System.in);
        while (true) {
            int ally = scan.nextInt();
            if (ally == 4) return Game.ableChar;
            if (Game.FoundCarr.size()==0) return Game.ableChar;
            if (ally < 1 || ally > Game.FoundCarr.size()) {
                System.out.println("Wrong number");
                continue;
            }
            Game.ableChar[1] = Game.FoundCarr.get(ally - 1);
            System.out.println(Game.FoundCarr.get(ally - 1).getName() + " joins the team");
            Game.FoundCarr.remove(ally-1);
            return Game.ableChar;
        }
    }

    public void Use(ArrayList<Item> subj) {
        while (true) {
            System.out.println("Please Choose Item you want to use:");
            ShowItemList();
            System.out.println("4.Cancel");
            Scanner scan = new Scanner(System.in);
            int i = scan.nextInt()-1;
            if (i == 3) return;
            if (i<0 || i>2) System.out.println("Wrong number");
            else {
                switch (ableIt.get(i).getName()){
                    case "Flask":
                        setHP(getHP()+ableIt.get(i).getValue());
                        ableIt.remove(i);
                        return;
                    case "Cheese":
                        setStamina(getStamina()+ableIt.get(i).getValue());
                        ableIt.remove(i);
                        return;
                    case "Clarity":
                        setMana(getMana()+ableIt.get(i).getValue());
                        ableIt.remove(i);
                        return;
                }
            }
        }
    }

    public void LevelUp() {
        System.out.println("Congratulations, you have new level");
        System.out.println("Choose stat to increase:");
        ShowMyStats(5);
        Scanner scan = new Scanner(System.in);
        int statName = scan.nextInt();
        while (true) {
            switch (statName) {
                case 1:
                    MaxStamina += 5;
                    Stamina += 5;
                    FreeEXP = 0;
                    return;
                case 2:
                    MaxMana += 5;
                    Mana += 5;
                    FreeEXP = 0;
                    return;
                case 3:
                    MaxHp += 5;
                    Hp += 5;
                    FreeEXP = 0;
                    return;
                default:
                    System.out.println("Wrong number");
            }
        }
    }
}
