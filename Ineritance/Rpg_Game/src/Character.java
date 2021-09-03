import com.sun.jdi.InvalidTypeException;

public abstract class Character {
    private final String name;
    //Every character has a Weapon and can equip it.
    private Weapon weapon;
    abstract void equip(Weapon w);

    //Implementation of damage type depends on the Type of weapon
    public void attack(Character target) {
        weapon.doDamage(target);
    }

    Character(String name){
        this.name = name;
    }

    /* We can do default getter and setter as well */
    public String getName() {
        return this.name;
    }
    public void setWeapon(Weapon w) {
        weapon = w;
    }
    public Weapon getWeapon() {
        return weapon;
    }
}
