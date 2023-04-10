package Model.Animals;

import Model.Enums.Kind;

public class Boa extends Animal{
    public Boa() {
        super(Kind.BOA);
    }
    @Override
    public String toString() {
        return "\uD83D\uDC0D";
    }
}
