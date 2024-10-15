package ua.pima.petSaver.entity.user;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class CountryConverter implements AttributeConverter<Country, String> {

    @Override
    public String convertToDatabaseColumn(Country country) {
        if (country == null) {
            return null;
        }
        return country.getCode();
    }

    @Override
    public Country convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }
        return Stream.of(Country.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}

