package lsg.characters;

public class Monster extends Character {

    private static int INSTANCES_COUNT=0;
    private float skinThickness;

    //constructeurs
    public Monster(){
        super(10,10,"Monster_"+ INSTANCES_COUNT);
        INSTANCES_COUNT ++;
        skinThickness = 20f;
    }

    public Monster(String name){
        super(10,10,name);
        INSTANCES_COUNT ++;
        skinThickness = 20f;
    }

    public float getSkinThickness() {
        return skinThickness;
    }

    protected void setSkinThickness(float skinThickness) {
        this.skinThickness = skinThickness;
    }

    @Override
    protected float computeProtection() {
        return (skinThickness);
    }
}
