package mediaapp.com.just4you.Entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoMedia {

    FILME("movie"),
    SERIE("tv");


    private final String value;

    TipoMedia(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }


    @JsonCreator
    public static TipoMedia fromValue(String value) {
        for (TipoMedia tipo : TipoMedia.values()) {
            if (tipo.value.equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de mídia inválido: " + value);
    }
}
