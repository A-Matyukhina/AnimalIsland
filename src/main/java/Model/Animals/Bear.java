package Model.Animals;

import Model.Enums.Kind;

public class Bear extends Animal{

    public Bear() {
        super(Kind.BEAR);
    }

    @Override
    public String toString() {
        return "\uD83D\uDC3B";
    }

}