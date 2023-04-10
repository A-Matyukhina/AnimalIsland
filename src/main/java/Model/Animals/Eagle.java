package Model.Animals;

import Model.Enums.Kind;

public class Eagle extends Animal{
    public Eagle() {
        super(Kind.EAGLE);
    }

    @Override
    public String toString() {
        return "\uD83E\uDD85";
    }
}
