import java.util.Comparator;

public class Character {
    String name;
    String type;
    int rarity;
    int eidolon;

    public Character(String name, String type, int rarity) {
        this.name = name;
        this.type = type;
        this.rarity = rarity;
        this.eidolon = 0;
    }

    public String getName() {
        return name;
    }

    public String getBanner() {
        return name + " [" + type + "] " + Integer.toString(rarity) + "*";
    }

    public boolean addEidolon() {
        if (eidolon < 6) {
            eidolon++;
            return false;
        }
        return true;
    }
}

class CharacterComparator implements Comparator<Character> {
    @Override
    public int compare(Character c1, Character c2) {
        return c1.name.compareTo(c2.name);
    }
}
