import java.util.*;

public class WeaponStorage{
    /* Does not care what weapons to store */
    private final List<Weapon> weapons = new LinkedList<>();
    public void storeWeapon(Weapon weapon) {
        weapons.add(weapon);
    }
    /* Our Weapon dispenser methods */
    public MagicWeapon getMagicWeapon() {
        Optional<Weapon> o = weapons.stream().filter((x) -> x instanceof MagicWeapon).findAny();
        if(o.isPresent()) {
            weapons.remove(o.get());        //Don't forget to remove it from the storage
            return (MagicWeapon) o.get();
        }else {
            System.out.println("No magic weapon could be found");
            return null;
        }
    }
    public MeleeWeapon getMeleeWeapon() {
        Optional<Weapon> o = weapons.stream().filter((x) -> x instanceof MeleeWeapon).findAny();
        if(o.isPresent()) {
            weapons.remove(o.get());        //Don't forget to remove it from the storage
            return (MeleeWeapon) o.get();
        }else {
            System.out.println("No magic weapon could be found");
            return null;
        }
    }
}
