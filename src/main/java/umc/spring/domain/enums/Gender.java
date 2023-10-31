package umc.spring.domain.enums;

public enum Gender {
    MALE(0),
    FEMALE(1);

    int value;

    Gender(int value) {
        this.value = value;
    }
    public int get(){
        return value;
    }
}
