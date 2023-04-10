package Model.Animals;

import Model.Enums.Kind;

public class Wolf extends Animal{
    public Wolf() {
        super(Kind.WOLF);
    }

    @Override
    public String toString() {
        return "\uD83D\uDC3A";
    }
}
