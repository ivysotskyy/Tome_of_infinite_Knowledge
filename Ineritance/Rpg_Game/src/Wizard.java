import com.sun.jdi.InvalidTypeException;

public class Wizard extends Character{
    Wizard(String name) {
        super(name);
    }

    @Override
    void equip(Weapon w) {
        setWeapon(w);
    }
}
