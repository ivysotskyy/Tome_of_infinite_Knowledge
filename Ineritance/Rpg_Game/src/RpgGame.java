
public class RpgGame {
    public static void main(String[] args) {
        /* Instantiating our RPG Characters */
        Character barbarian = new Warrior("Conan");         //Java knows that Warrior *is a* child of Character
        Character harry = new Wizard("Harry Potter");       //and can cast upcast implicitly
        /* Instantiating new Weapons for them */
        MeleeWeapon axe = new MeleeWeapon(15);
        MagicWeapon magicStick = new MagicWeapon(10);
        /* Deposit weapons in to storage */
        WeaponStorage storage = new WeaponStorage();
        storage.storeWeapon(axe);
        storage.storeWeapon(magicStick);
        //Add some more weapons to give Harry a fair chance
        storage.storeWeapon(new MagicWeapon(5));
        storage.storeWeapon(new MagicWeapon(999));
        /* Equip our characters with weapons */
        barbarian.equip(storage.getMeleeWeapon());
        harry.equip(storage.getMagicWeapon());
        /* Make them fight */
        barbarian.attack(harry);
        harry.attack(barbarian);
    }
}
