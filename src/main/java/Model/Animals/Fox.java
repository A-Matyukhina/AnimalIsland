package Model.Animals;

import Model.Enums.Kind;

public class Fox extends Animal{
    public Fox() {
        super(Kind.FOX);
    }
    @Override
    public String toString() {
        return "\uD83E\uDD8A";
    }
}
