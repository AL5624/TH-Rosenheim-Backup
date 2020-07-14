package de.thro.inf.pvs.bsp;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDate;

@Converter
public class DateConverter implements AttributeConverter<LocalDate, String> {

    @Override
    public String convertToDatabaseColumn(LocalDate datum) {
        return datum.toString();
    }

    @Override
    public LocalDate convertToEntityAttribute(String datum) {
        return LocalDate.parse(datum);
    }

}
