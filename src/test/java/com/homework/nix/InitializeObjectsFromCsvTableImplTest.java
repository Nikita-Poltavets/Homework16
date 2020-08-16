package com.homework.nix;

import com.homework.nix.data.AppProperties;
import com.homework.nix.data.CsvTable;
import com.homework.nix.service.impl.InitializeObjectsFromCsvTableImpl;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InitializeObjectsFromCsvTableImplTest {

    @Test
    void initializeObjects() throws IOException, NoSuchFieldException, IllegalAccessException {
        InitializeObjectsFromCsvTableImpl initializeObjectsFromCSVTableImpl = new InitializeObjectsFromCsvTableImpl();
        Path path = Paths.get("table.csv");
        List<AppProperties> list = initializeObjectsFromCSVTableImpl.initialize(AppProperties.class, path);
        Optional<CsvTable> optionalCsvTable = CsvTable.fromFile(path);

        Object firstRow = list.get(0);

        Class<?> classOfInstance = firstRow.getClass();

        Field fieldName = classOfInstance.getDeclaredField("name");

        fieldName.setAccessible(true);

        String nameValueFromInstance = ((String) fieldName.get(list.get(0)));

        String nameFromCsvTable = optionalCsvTable.get().get(0, "name");

        assertEquals(nameFromCsvTable, nameValueFromInstance);
    }
}