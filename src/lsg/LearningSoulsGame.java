package lsg;
import lsg.armor.BlackWitchVeil;
import lsg.armor.DragonSlayerLeggings;
import lsg.armor.RingedKnightArmor;
import lsg.buffs.rings.DragonSlayerRing;
import lsg.buffs.rings.RingOfDeath;
import lsg.buffs.talismans.MoonStone;
import lsg.characters.*;
import lsg.characters.Character;
import lsg.weapons.Claw;
import lsg.weapons.Sword;

import java.util.Scanner;

public class LearningSoulsGame {

    private Scanner scanner = new Scanner(System.in);
    private Hero hero;
    private  Monster monster;

    private void refresh(){
        hero.printStats();
        monster.printStats();
    }

    private void fight1v1(){
        Character attaquant = hero;
        Character defenseur = monster;
        Character temp;

        refresh();
        while(hero.isAlive() && monster.isAlive()){
            System.out.println("Hit enter key for next move >");
            scanner.nextLine();

            int hitValue = attaquant.attack();
            int damage = defenseur.getHitWith(hitValue);

            System.out.println(attaquant.getName() + " attacks " + defenseur.getName() + " with " + attaquant.getWeapon().getName() + "(ATTACK:" + hitValue + "| DMG:" + damage + ")");

            temp = attaquant;
            attaquant = defenseur;
            defenseur = temp;
            refresh();
        }

        System.out.println("!!! "+(hero.isAlive() ? hero.getName() : monster.getName()) + " WINS !!!");
    }

    private void init(){
        hero = new Hero();
        hero.setWeapon(new Sword());

        monster = new Monster();
        monster.setWeapon(new Claw());
    }

    private void play_v1(){
        init();
        fight1v1();
    }

    private void play_v2(){
        init();
        hero.setArmorItem(new BlackWitchVeil(),1);
        hero.setArmorItem(new DragonSlayerLeggings(),2);
        hero.setArmorItem(new RingedKnightArmor(),3);
        fight1v1();
    }

    private void play_v3(){
        init();
        monster = new Lycanthrope();
        hero.setArmorItem(new DragonSlayerLeggings(),2);
        hero.setRing(new RingOfDeath(),1);
        hero.setRing(new DragonSlayerRing(),2);
        fight1v1();
    }

    public static void main(String[] args){
        new LearningSoulsGame().play_v3();
    }
}