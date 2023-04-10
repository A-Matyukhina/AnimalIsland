package Model.Animals;

import Model.Enums.Kind;
import Model.Enums.Gender;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal {

    protected Kind kind;

    private Gender gender;
    /**
     * Сытость
     */
    protected double satiety;
    /**
     * Флаг перемещения
     */
    private boolean isMoved = false;
    /**
     * Флаг размножения
     * */
    private boolean isMultiply = false;

    public Animal multiply(Animal animal){
        if (this.kind != animal.getKind()) return null;
        if (this.gender == animal.gender) return null;
        this.isMultiply = true;
        animal.isMultiply = true;
        return this.kind.born();
    };

//    public abstract Model.Enums.Side move();

    public Animal(Kind kind) {
        this.kind = kind;
        this.satiety = kind.getMaxSatiety() / 2;
        this.gender = Gender.getRandomGender();
    }

    public double getSatiety() {
        return satiety;
    }

    public boolean isMoved() {
        return isMoved;
    }

    public void setMoved(boolean moved) {
        isMoved = moved;
    }

    public boolean isMultiply() { return isMultiply; }
    public void setMultiply(boolean multiply) { isMultiply = multiply; }

    public Gender getGender() { return gender; }

    /**
     * Метод поедания другого животного
     * @param victim животное, которое будут есть
     * @return true, если удалось съесть животное, false - в другом случае
     * */
    public boolean eat(Animal victim) {
        if (!canEat(victim)) return false;
        if (satiety >= kind.getMaxSatiety()) return false;

        satiety += victim.kind.getWeight();
        if (satiety > kind.getMaxSatiety()) satiety = kind.getMaxSatiety();
        return true;
    }

    public boolean canEat(Animal victim) {
        HashMap<Kind, Integer> ration = kind.getRation();

        Kind kindVictim = victim.kind;
        for (Map.Entry<Kind, Integer> entry : ration.entrySet()) {
            if (entry.getKey() == kindVictim) {
                int probability = entry.getValue();
                int random = ThreadLocalRandom.current().nextInt(100);
                return random < probability;
            }
        }
        return false;
    }


    public Kind getKind() {
        return kind;
    }


    public void setHungry() {
        this.satiety = Math.max(this.satiety - kind.getMaxSatiety() / 2, 0);
    }
}
