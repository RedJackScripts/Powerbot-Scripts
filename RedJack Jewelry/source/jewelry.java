package source;

/**
 * Created with IntelliJ IDEA.
 * User: RedJack
 */
public enum jewelry {


    Sapphire_Ring(true, 1607, 18),
    Emerald_Ring(true, 1605, 34),
    Ruby_Ring(true, 1603, 50),
    Diamond_Ring(true, 1601, 66),

    Sapphire_Necklace(true, 1607, 22),
    Emerald_Necklace(true, 1605, 38),
    Ruby_Necklace(true, 1603, 54),
    Diamond_Necklace(true, 1601, 70),

    Sapphire_Bracelet(true, 1607, 26),
    Emerald_Bracelet(true, 1605, 42),
    Ruby_Bracelet(true, 1603, 58),
    Diamond_Bracelet(true, 1601, 74),

    Sapphire_Amulet(true, 1607, 30),
    Emerald_Amulet(true, 1605, 46),
    Ruby_Amulet(true, 1603, 62),
    Diamond_Amulet(true, 1601, 78);


    int saph = 1607;
    int emer = 1605;
    int ruby = 1603;
    int diam = 1601;

    int gem, inter;

    jewelry(boolean needgem, int gemid, int ifid) {
        this.gem = gemid;
        this.inter = ifid;

    }

    public int getGem() {
        return gem;

    }

    public int getInterface() {
        return inter;
    }

    @Override
    public String toString() {
        String name = name().replaceAll("_", " ");
        return name;
    }
}
