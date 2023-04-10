package Model.Enums;

import java.util.concurrent.ThreadLocalRandom;

public enum Side {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1);

    int rowTo, columnTo;
    Side(int rowTo, int columnTo) {
        this.rowTo = rowTo;
        this.columnTo = columnTo;
    }

    public static Side getRandomSide(){
        int random = ThreadLocalRandom.current().nextInt(4);
        switch (random){
            case 0: return UP;
            case 1: return DOWN;
            case 2: return LEFT;
            case 3: return RIGHT;
        }
        return null;
    }
    public int getRowTo() { return rowTo; }
    public int getColumnTo() { return columnTo; }
}
