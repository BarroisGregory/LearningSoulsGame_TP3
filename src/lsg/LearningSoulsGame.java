package lsg;
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

    public static void main(String[] args){
        new LearningSoulsGame().play_v1();
    }
}