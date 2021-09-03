public class MagicWeapon extends Weapon {

    MagicWeapon(int damage) {
        super(damage);
    }

    @Override
    void doDamage(Character target) {
        //Stuff do Magic
        System.out.println("Cast spell on " + target.getName() +" : "+"("+ target.getClass()+")"+ " and do " + damage + " Damage!");
    }
}
