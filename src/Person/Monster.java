package Person;

import Main.Game;
import Main.Item;

public class Monster extends Character {
     private final int EXPtoGive;
     private Item item;

    public Monster(String name , int stamina, int mana, int HP, int EXPtoGive) {
        super(name,stamina, mana, HP);
        this.EXPtoGive = EXPtoGive;
        this.item = Game.GenI();
    }

    public Item getItem() {
        return item;
    }

    public void ShowMyStats(){
        System.out.println(getName() + " status:");
        System.out.println("Hp = " + getHP() + "/" + getMaxHp());
    }
    public int getEXPtoGive() {
        return EXPtoGive;
    }
    public void Attack(boolean type, Character obj){
        if(!type){
            if (obj.getHP()>1) {
                if(Stamina >0){
                    Stamina--;
                    obj.setHP(obj.getHP()-1);
                }else {
                    Stamina++;
                    System.out.println("Monster is waiting");
                }
            } else {
                obj.setHP(0);
            }
        }else {
            if (obj.getHP()>1) {
                if(Mana>0){
                    Mana-=1;
                    obj.setHP(obj.getHP()-1);
                }else {
                    Mana++;
                    System.out.println("Monster is waiting");
                }
            } else {
                obj.setHP(0);
            }
        }
    }

}
