package Model.Animals;

import Model.Enums.Kind;
import Model.Grass;

public class Mouse extends Animal implements Herbivore {
    public Mouse() {
        super(Kind.MOUSE);
    }


    @Override
    public String toString() {
        return "\uD83D\uDC00";
    }


    @Override
    public void eatGrass(Grass grass) {
        //Вычисление необходимого количества пищи
        double hunger = kind.getMaxSatiety() - this.satiety;
        if (hunger <= 0) return;

        //Вычисление доступного количества пищи и уменьшение травы
        double eaten = grass.minusGrass(hunger);

        this.satiety += eaten;
    }
}
