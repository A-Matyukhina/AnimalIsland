package Controller;

import Model.Animals.*;
import Model.Cell;
import Model.Enums.Kind;
import Model.Enums.Side;
import Model.Grass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class IslandController {
    private Cell[][] cells = new Cell[100][20];

    public IslandController() {
        // заполнение каждой клетки случайными объектами животных и растений
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                Cell cell = new Cell(); // каждая клетка содержит одну порцию травы
                fillCell(cell);
                cells[i][j] = cell;
            }
        }
    }

    /**
     * Жизненный цикл на острове в рамках одного "хода"
     */
    public void lifeCycle() {
        moveAnimals();
        mealTime();
        multiply();
    }


    /**
     * Процесс роста травы
     */
    public void growGrassOnCell() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].growGrass();
            }
        }
    }

    /**
     * Заполнение ячейки случайным количеством животных
     *
     * @param cell Ячейка
     */
    private void fillCell(Cell cell) {
        int animalCount = ThreadLocalRandom.current().nextInt(20, 100); // случайное количество животных в клетке
        for (int k = 0; k < animalCount; k++) {
            Animal animal = createRandomAnimal();
            if (animal != null) cell.addAnimal(animal); // добавляем животное в клетку
        }
    }

    private Animal createRandomAnimal() {
        int rand = ThreadLocalRandom.current().nextInt(15);
        switch (rand) {
            case 0 -> {
                return new Wolf();
            }
            case 1 -> {
                return new Bear();
            }
            case 2 -> {
                return new Duck();
            }
            case 3 -> {
                return new Caterpillar();
            }
            case 4 -> {
                return new Horse();
            }
            case 5 -> {
                return new Boa();
            }
            case 6 -> {
                return new Boar();
            }
            case 7 -> {
                return new Buffalo();
            }
            case 8 -> {
                return new Deer();
            }
            case 9 -> {
                return new Eagle();
            }
            case 10 -> {
                return new Fox();
            }
            case 11 -> {
                return new Goat();
            }
            case 12 -> {
                return new Mouse();
            }
            case 13 -> {
                return new Rabbit();
            }
            case 14 -> {
                return new Sheep();
            }
        }
        return null;
    }

    /**
     * Проверка возможности перемещения в ячейку
     *
     * @param row    Номер ячейки
     * @param column Номер столбца
     */
    private boolean canMove(int row, int column) {
        if (row >= cells.length || row < 0) return false;
        if (column >= cells[row].length || column < 0) return false;

        return true;
    }

    /**
     * Перемещение животных
     */
    public void moveAnimals() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                HashMap<Kind, ArrayList<Animal>> animalsInCell = cells[i][j].getAnimals();  //Животные в ячейке
                for (Map.Entry<Kind, ArrayList<Animal>> entry : animalsInCell.entrySet()) {
                    ArrayList<Animal> listAnimal = new ArrayList<>(entry.getValue());
                    for (Animal animal : listAnimal) {
                        int speed = animal.getKind().getSpeed();
                        int rowTo = i, columnTo = j;
                        for (int k = 0; k < speed; k++) {
                            Side side = Side.getRandomSide();
                            if (canMove(rowTo + side.getRowTo(), columnTo + side.getColumnTo())) {
                                if (cells[rowTo + side.getRowTo()][columnTo + side.getColumnTo()].canMove(animal)) {
                                    rowTo += side.getRowTo();
                                    columnTo += side.getColumnTo();
                                }
                            }
                        }

                        if (i != rowTo || j != columnTo) {
                            animal.setMoved(true);
                            cells[i][j].removeAnimal(animal);
                            cells[rowTo][columnTo].addAnimal(animal);
//                            System.out.println(animal + " перемещён в ячейку " + rowTo + " " + columnTo);
                        }
                    }
                }
            }
        }
    }

    /**
     * Функция трапезы всех животных острова
     */
    private void mealTime(){

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                Cell cell = cells[i][j];
                HashMap<Kind, ArrayList<Animal>> animalsInCell = cell.getAnimals();
                for (Map.Entry<Kind, ArrayList<Animal>> entry : animalsInCell.entrySet()) {
                    ArrayList<Animal> listAnimal = new ArrayList<>(entry.getValue());
                    for (Animal animal : listAnimal) {

                        if (animal instanceof Herbivore herbivore) {
                            Grass grass = cell.getGrass();
                            herbivore.eatGrass(grass);
//                            System.out.println(animal + " съел траву");
                        }
                        Animal victim = cell.getRandomAnimal();
                        if (victim != null && animal.eat(victim)) {
                            cell.removeAnimal(victim);
//                            System.out.println(animal + " съел " + victim);
                        } else if (animal.getSatiety() <= 0) {
                            cell.removeAnimal(animal);
//                            System.out.println(animal + " погибает от голода");
                        }
                    }
                }
            }
        }
    }


    /**
     * Размножение животных
     */
    private void multiply(){
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                Cell cell = cells[i][j];
                cell.prepare();
                ArrayList<Animal> bornAnimals = cell.multiply();

                cell.hungry();   //После размножения все устали и проголодались
//                System.out.println("В ячейке " + i + " " + j + " родились " + bornAnimals);
            }
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                result += String.format("%40s", cells[i][j].toString());
            }
            result += "\n";
        }
        return result;
    }
}
