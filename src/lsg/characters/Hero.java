package lsg.characters;

import lsg.armor.ArmorItem;
import lsg.armor.BlackWitchVeil;
import lsg.armor.RingedKnightArmor;

import static java.lang.String.format;

public class Hero extends Character {
    private final int MAX_ARMOR_PIECES = 3;
    private ArmorItem armor[];

    //constructeurs
    public Hero(){
        super(100,50,"Gregooninator");
        armor = new ArmorItem[3];
    }

    public Hero(String name){
        super(100,50,name);
        armor = new ArmorItem[3];
    }

    public void setArmorItem(ArmorItem armor, int slot) {
        slot --;
        if(0<=slot && slot<=2) {
            this.armor[slot] = armor;
        }
    }

    public float getTotalArmor(){
        float total = 0f;
        for(int i = 0; i<MAX_ARMOR_PIECES; i++){
            if (armor[i] != null) {
                total += armor[i].getArmorValue();
            }
        }
        return(total);
    }

    public String armorToString(){
        String n = "ARMOR ";
        for(int i=0; i<MAX_ARMOR_PIECES; i++){
            if (armor[i] == null) {
                n += format(" %2d:%-30s",i+1,"empty");
            } else {
                n +=format(" %2d:%-30s",i+1, armor[i].toString());
            }
        }
        return (n +"TOTAL:"+getTotalArmor());
    }

    public ArmorItem[] getArmorItems(){
        int n = 0;
        for (int i=0; i<MAX_ARMOR_PIECES; i++){
            if(armor[i] != null){
                n++;
            }
        }
        ArmorItem temp[] = new ArmorItem[n];
        if(n>0){
            int m = 0;
            for (int i=0; i<MAX_ARMOR_PIECES; i++){
                if(armor[i] != null){
                    temp[m] = armor[i];
                    m++;
                }
            }
        }
        return (temp);
    }

    @Override
    protected float computeProtection() {
        return (this.getTotalArmor());
    }

    public static void main(String[] args) {
        Hero hero = new Hero();
        hero.setArmorItem(new BlackWitchVeil(),1);
        hero.setArmorItem(new RingedKnightArmor(),3);
        System.out.println(hero.armorToString());
    }

}
