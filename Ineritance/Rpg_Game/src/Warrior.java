import com.sun.jdi.InvalidTypeException;

public class Warrior extends Character{
    Warrior(String name) {
        super(name);
    }

    @Override
    void equip(Weapon w) {
        setWeapon(w);
    }
}
