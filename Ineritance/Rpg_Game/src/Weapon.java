public abstract class Weapon {
    int damage;
    abstract void doDamage(Character target);
    //Constructor
    Weapon(int damage) {
        this.damage = damage;
    }

}
