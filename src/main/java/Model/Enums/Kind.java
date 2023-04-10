package Model.Enums;

import Model.Animals.*;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public enum Kind {
    BEAR(80, 5, 3, 50, "\uD83D\uDC3B") {
        public HashMap<Kind, Integer> getRation() {
            HashMap<Kind, Integer> result = new HashMap<>();
            result.put(HORSE, 40);
            result.put(DUCK, 10);
            return result;
        }

        @Override
        public Animal born() {
            return new Bear();
        }
    },
    CATERPILLAR(0.005, 1000, 0, 0.01, "\uD83D\uDC1B") {
        public HashMap<Kind, Integer> getRation() {
            HashMap<Kind, Integer> result = new HashMap<>();
//            result.put(GRASS, 100);
            return result;
        }

        @Override
        public Animal born() {
            return new Caterpillar();
        }
    },
    DUCK(0.15, 200, 4, 1, "\uD83E\uDD86") {
        public HashMap<Kind, Integer> getRation() {
            HashMap<Kind, Integer> result = new HashMap<>();
            result.put(CATERPILLAR, 90);
//            result.put(GRASS, 100);
            return result;
        }

        @Override
        public Animal born() {
            return new Duck();
        }
    },
    //    GRASS(0, 200, 0, 1){
//        public HashMap<Model.Enums.Kind, Integer> getRation() {
//            return new HashMap<>();
//        }
//
//        @Override
//        public Model.Animals.Animal born() {
//            return new Model.Grass();
//        }
//    },
    HORSE(60, 20, 4, 400, "\uD83D\uDC0E") {
        public HashMap<Kind, Integer> getRation() {
            HashMap<Kind, Integer> result = new HashMap<>();
//            result.put(GRASS, 100);
            return result;
        }

        @Override
        public Animal born() {
            return new Horse();
        }
    },
    BUFFALO(100, 10, 3, 700, "\uD83D\uDC02") {
        public HashMap<Kind, Integer> getRation() {
            HashMap<Kind, Integer> result = new HashMap<>();
//            result.put(GRASS, 100);
            return result;
        }

        @Override
        public Animal born() {
            return new Buffalo();
        }
    },
    WOLF(8, 30, 3, 50, "\uD83D\uDC3A") {
        public HashMap<Kind, Integer> getRation() {
            HashMap<Kind, Integer> result = new HashMap<>();
            result.put(HORSE, 10);
            result.put(DUCK, 40);
            return result;
        }
        @Override
        public Animal born() {
            return new Wolf();
        }
    },
    FOX(2, 30, 2, 8, "\uD83E\uDD8A") {
        public HashMap<Kind, Integer> getRation() {
            HashMap<Kind, Integer> result = new HashMap<>();
            result.put(RABBIT, 70);
            result.put(DUCK, 60);
            result.put(MOUSE, 90);
            result.put(CATERPILLAR, 40);
            return result;
        }
        @Override
        public Animal born() {
            return new Fox();
        }
    },
    EAGLE(1, 20, 3, 6, "\uD83E\uDD85") {
        public HashMap<Kind, Integer> getRation() {
            HashMap<Kind, Integer> result = new HashMap<>();
            result.put(FOX, 10);
            result.put(DUCK, 80);
            result.put(MOUSE, 90);
            result.put(RABBIT, 90);
            return result;
        }
        @Override
        public Animal born() {
            return new Eagle();
        }
    },
    DEER(50, 20, 4, 300, "\uD83E\uDD8C") {
        public HashMap<Kind, Integer> getRation() {
            HashMap<Kind, Integer> result = new HashMap<>();
            return result;
        }
        @Override
        public Animal born() {
            return new Deer();
        }
    },
    BOAR(50, 50, 2, 400, "\uD83D\uDC16") {
        public HashMap<Kind, Integer> getRation() {
            HashMap<Kind, Integer> result = new HashMap<>();
            result.put(CATERPILLAR, 90);
            result.put(MOUSE, 50);
//            result.put(GRASS, 100);
            return result;
        }

        @Override
        public Animal born() {
            return new Boar();
        }
    },
    RABBIT(0.45, 150, 2, 2, "\uD83D\uDC07") {
        public HashMap<Kind, Integer> getRation() {
            HashMap<Kind, Integer> result = new HashMap<>();
            return result;
        }
        @Override
        public Animal born() {
            return new Rabbit();
        }
    },
    GOAT(10, 140, 3, 60, "\uD83D\uDC10") {
        public HashMap<Kind, Integer> getRation() {
            HashMap<Kind, Integer> result = new HashMap<>();
            return result;
        }
        @Override
        public Animal born() {
            return new Goat();
        }
    },
    SHEEP(15, 140, 3, 70, "\uD83D\uDC0F") {
        public HashMap<Kind, Integer> getRation() {
            HashMap<Kind, Integer> result = new HashMap<>();
            return result;
        }
        @Override
        public Animal born() {
            return new Sheep();
        }
    },
    MOUSE(0.01, 500, 1, 0.05, "\uD83D\uDC00") {
        public HashMap<Kind, Integer> getRation() {
            HashMap<Kind, Integer> result = new HashMap<>();
            result.put(CATERPILLAR, 90);
//            result.put(GRASS, 100);
            return result;
        }

        @Override
        public Animal born() {
            return new Mouse();
        }
    },

    BOA(3, 30, 1, 15, "\uD83D\uDC0D") {
        public HashMap<Kind, Integer> getRation() {
            HashMap<Kind, Integer> result = new HashMap<>();
            result.put(FOX, 15);
            result.put(DUCK, 10);
            result.put(RABBIT, 20);
            result.put(MOUSE, 40);
            return result;
        }
        @Override
        public Animal born() {
            return new Boa();
        }
    };

    private final double maxSatiety;
    private final int maxQuantityByCell;
    private final int speed;
    private final double weight;
    private final String icon;

    public Kind getRandomKind() {
        int random = ThreadLocalRandom.current().nextInt(Kind.values().length);
        return Kind.values()[random];
    }

    Kind(double maxSatiety, int maxQuantityByCell, int speed, double weight, String icon) {
        this.maxSatiety = maxSatiety;
        this.maxQuantityByCell = maxQuantityByCell;
        this.speed = speed;
        this.weight = weight;
        this.icon = icon;
    }

    public abstract HashMap<Kind, Integer> getRation();

    public abstract Animal born();

    public double getMaxSatiety() {
        return maxSatiety;
    }

    public int getMaxQuantityByCell() {
        return maxQuantityByCell;
    }

    public int getSpeed() {
        return speed;
    }

    public double getWeight() {
        return weight;
    }

    public String getIcon() {
        return icon;
    }
}
