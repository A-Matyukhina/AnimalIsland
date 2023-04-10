package Model;

import Model.Animals.Animal;
import Model.Enums.Gender;
import Model.Enums.Kind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Cell {

    private HashMap<Kind, ArrayList<Animal>> animals = new HashMap<>();
    private Grass grass = new Grass(200);

    public Cell() {
    }

    public HashMap<Kind, ArrayList<Animal>> getAnimals() {
        return new HashMap<>(animals);
    }

    public Grass getGrass() {
        return grass;
    }

    public void growGrass() {
        grass.grow();
    }

    /**
     * Проверка содержания животного в ячейке
     *
     * @param animal Животное, наличие которого проверяется
     * @return true - если животное в ячейке, false - в противном случае
     */
    public boolean isAnimalContains(Animal animal) {
        Kind kind = animal.getKind();
        if (animals.containsKey(kind)) {                         //Если такой вид есть в клетке
            ArrayList<Animal> animalList = animals.get(kind);
            return animalList.contains(animal);                 //Если данное животное есть в клетке
        }
        return false;
    }

    /**
     * Добавление животного в список
     *
     * @param animal Добавляемое животное
     */
    public void addAnimal(Animal animal) {
        Kind kindAnimal = animal.getKind();
        if (animals.containsKey(kindAnimal)) {
            ArrayList<Animal> list = animals.get(kindAnimal);

            if (list.size() >= animal.getKind().getMaxQuantityByCell()) return;

            list.add(animal);
            animals.put(kindAnimal, list);
        } else {
            ArrayList<Animal> list = new ArrayList<>();
            list.add(animal);
            animals.put(kindAnimal, list);
        }
    }

    /**
     * Функция определяет, можно ли животному переместиться в ячейку
     *
     * @param animal перемещаемое животное
     * @return true, если перемещение возможно, false - если невозможно
     */
    public boolean canMove(Animal animal) {
        if (animal.isMoved()) return false;

        Kind kindAnimal = animal.getKind();

        return isFreePlace(kindAnimal);
    }

    public boolean isFreePlace(Kind kindAnimal) {
        if (!animals.containsKey(kindAnimal)) return true;

        ArrayList<Animal> animalsKindInCell = animals.get(kindAnimal);

        return animalsKindInCell.size() < kindAnimal.getMaxQuantityByCell();
    }

    public int getSizeAllAnimals() {
        int result = 0;
        for (ArrayList<Animal> animalList : animals.values()) {
            result += animalList.size();
        }
        return result;
    }


    /**
     * Размножение в клетке
     *
     * @return Возвращает список рождённых животных
     */
    public ArrayList<Animal> multiply() {
        ArrayList<Animal> borningAnimals = new ArrayList<>();

        for (Kind kind : animals.keySet()) {
            if (!isFreePlace(kind)) continue;

            ArrayList<Animal> animalMaleList = getListAnimal(kind, Gender.MALE);
            ArrayList<Animal> animalFemaleList = getListAnimal(kind, Gender.FEMALE);


            for (Animal male : animalMaleList) {
                if (animalFemaleList.size() == 0) break;
                Animal female = animalFemaleList.get(0);
                animalFemaleList.remove(0);

                Animal child = male.multiply(female);
                addAnimal(child);

                borningAnimals.add(child);
            }
        }

        return borningAnimals;
    }

    /**
     * Получение случайного животного из ячейки.
     *
     * @return Model.Animals.Animal, если в ячейке есть подходящее животное, иначе null
     */
    public Animal getRandomAnimal() {
        if (getSizeAllAnimals() < 2) return null;

        ArrayList<Animal> animalsList = new ArrayList<>();
        while (animalsList == null || animalsList.size() == 0) {
            Kind randomKind = Kind.BEAR.getRandomKind();
            animalsList = animals.get(randomKind);
        }

        int randomAnimal = ThreadLocalRandom.current().nextInt(animalsList.size());
        return animalsList.get(randomAnimal);
    }

    public ArrayList<Animal> getListAnimal(Kind kind, Gender gender) {
        if (!animals.containsKey(kind)) return new ArrayList<>();

        List<Animal> animalList = animals.get(kind);
        return animalList.stream().filter(x -> x.getGender() == gender).collect(Collectors.toCollection(ArrayList::new));
    }

    public void removeAnimal(Animal animal) {
        ArrayList<Animal> animalsList = animals.get(animal.getKind());
        animalsList.remove(animal);
    }

    public void prepare() {
        for (ArrayList<Animal> animalsList : animals.values()) {
            for (Animal animal : animalsList) {
                animal.setMultiply(false);
                animal.setMultiply(false);
            }
        }
    }

    @Override
    public String toString() {
        String result = "[";
        result += grass.toString() + " ";
        for (Kind kind : animals.keySet()) {
            result += kind.getIcon() + "x" + animals.get(kind).size() + " ";
        }

        return result.trim() + "]";
    }

    public void hungry() {
        for (ArrayList<Animal> animalsList : animals.values()) {
            for (Animal animal : animalsList) {
                animal.setHungry();
            }
        }
    }
}
