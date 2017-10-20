package lsg.characters;

import lsg.helpers.Dice;
import lsg.weapons.Weapon;

import java.util.Locale;

import static java.lang.String.format;

public abstract class Character {
    private String name;
    private int life, maxLife, stamina, maxStamina;
    private Dice dice;
    private Weapon weapon;

    //accesseurs

    public String getName() {
        return name;
    }

    public int getLife() {
        return life;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public int getStamina() {
        return stamina;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setLife(int life) {
        this.life = life;
    }

    protected void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    protected void setStamina(int stamina) {
        this.stamina = stamina;
    }

    protected void setMaxStamina(int maxStamina) {
        this.maxStamina = maxStamina;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Character(){}

    public Character(int val1, int val2, String name){
        this.name = name;
        life = val1;
        maxLife= val1;
        stamina = val2;
        maxStamina = val2;
        dice = new Dice(101);
    }

    //toString
    @Override
    public String toString() {
        String str1= format("%5d",life);
        String str2= format("%5d",stamina);
        String str3= format(Locale.US,"%6.2f",computeProtection());
        return (format(Locale.US,"%-20s %-20s LIFE:%-10s STAMINA:%-10s PROTECTION:%-10s", "[ "+getClass().getSimpleName()+" ]", name, str1, str2, str3)+ ((this.isAlive())? "(ALIVE)" :"(DEAD)"));
    }

    //methodes
    public void printStats(){
        System.out.println(this.toString());
    }

    public boolean isAlive(){
        return(this.getLife()>0);
    }

    private int attackWith(Weapon weapon){
        if (weapon.isBroken())
            return 0;
        else{
            int pourcent = dice.roll();
            weapon.use();
            int difference = weapon.getMaxDamage()-weapon.getMinDamage();
            int damage = (int)(weapon.getMinDamage() + ((difference*pourcent)/100.0));
            if(stamina<weapon.getStamCost()){
                if(stamina==0)
                    return 0;
                else {
                    double difStamina = stamina/weapon.getStamCost();
                    damage = (int)(damage*difStamina);
                    stamina = 0;
                    return damage;
                }
            }
            else{
                stamina = stamina - weapon.getStamCost();
                return damage;
            }

        }
    }

    public int attack(){
        return (attackWith(weapon));
    }

    public int getHitWith(int val){
        int pvRetire = ((val > life)? life :val);
        if(computeProtection()==100){
            pvRetire = 0;
        }
        else {
            if(computeProtection()!=0){
                pvRetire =Math.round(pvRetire * computeProtection()/100);
            }
        }
        life -= pvRetire;
        return (pvRetire);
    }

    protected abstract float computeProtection();
}
