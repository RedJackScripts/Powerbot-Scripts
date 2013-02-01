package source;

import org.powerbot.game.api.wrappers.Tile;

public enum location {
    Edgeville("Edgeville", new Tile(3097, 3496, 0), new Tile(3109, 3501, 0), 26814);

    String location;
    Tile bankTile, furnaceTile;
    int fid;

    location(String name, Tile bank, Tile furnace, int furnaceid) {
        this.location = name;
        this.bankTile = bank;
        this.furnaceTile = furnace;
        this.fid = furnaceid;
    }

    public String getName (){

        return location;
    }

    public Tile getBankTile(){
        return  bankTile;
    }

    public Tile getFurnaceTile (){

        return furnaceTile;
    }

    public int getFurnaceID() {
        return fid;
    }
}
