package ua.pima.petSaver.entity.user;


import lombok.Getter;

@Getter
public enum Country {
    UKRAINE("UA"), MOLDOVA("MD");

    private String code;

    Country(String code) {
        this.code = code;
    }
}
