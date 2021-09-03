public class MeleeWeapon extends Weapon{
    MeleeWeapon(int damage) {
        super(damage);
    }
    @Override
    void doDamage(Character target) {
        //Sword do a Slash
        System.out.println("Slash " + target.getName() +" : "+"("+ target.getClass()+")"+ " and do " + damage + " Damage!");
    }
}
