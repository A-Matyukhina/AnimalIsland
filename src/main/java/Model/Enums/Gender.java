package Model.Enums;

import java.util.concurrent.ThreadLocalRandom;

public enum Gender {
    MALE, FEMALE;

    public static Gender getRandomGender(){
        return ThreadLocalRandom.current().nextDouble() < 0.5 ? MALE : FEMALE;
    }
}
