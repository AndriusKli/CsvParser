package org.andriusk.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.util.List;

public class CsvService {
    private final CsvMapper csvMapper;

    public CsvService() {
        this.csvMapper = new CsvMapper();
    }

    public <T> List<T> parseCsv(String file, Class clazz) throws IOException {
        ObjectReader objectReader = csvMapper
                .registerModule(new JavaTimeModule())
                .readerFor(clazz)
                .with(CsvSchema.emptySchema().withHeader());

        try (MappingIterator<T> objects = objectReader.readValues(file)) {
            return objects.readAll();
        }
    }
}
