package Model.Animals;

import Model.Enums.Kind;
import Model.Grass;

public class Buffalo extends Animal implements Herbivore {
    public Buffalo() {
        super(Kind.BUFFALO);
    }


    @Override
    public String toString() {
        return "\uD83D\uDC02";
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
