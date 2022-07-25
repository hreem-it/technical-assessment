package com.rsakin.vocid22tracker.model.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;

@Converter
public class SymptomListConverter implements AttributeConverter<List<String>, String> {
    // Separator for each symptom to save it on one String
    private static final String SEPARATOR_CHAR = "-";

    @Override
    public String convertToDatabaseColumn(List<String> stringList) {
        return stringList != null ? String.join(SEPARATOR_CHAR, stringList) : "";
    }

    @Override
    public List<String> convertToEntityAttribute(String string) {
        return string != null ? Arrays.asList(string.split(SEPARATOR_CHAR)) : emptyList();
    }
}
