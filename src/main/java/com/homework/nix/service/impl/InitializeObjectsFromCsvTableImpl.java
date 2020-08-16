package com.homework.nix.service.impl;

import com.homework.nix.annotation.PropertyKey;
import com.homework.nix.data.CsvTable;
import com.homework.nix.service.InitializeObjectsFromCsvTable;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InitializeObjectsFromCsvTableImpl implements InitializeObjectsFromCsvTable {

    @Override
    public <T> List<T> initialize(Class<T> clazz, Path pathToCSVFile){
        try {
            Optional<CsvTable> optionalCsvTable = CsvTable.fromFile(pathToCSVFile);

            List<T> list = new ArrayList<>(optionalCsvTable.get().getHeaders().size());

            for (int i = 0; i < optionalCsvTable.get().height(); i++) {

                Constructor<T> constructor = clazz.getConstructor();

                T tObject = constructor.newInstance();

                for (Field field : clazz.getDeclaredFields()) {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(PropertyKey.class)) {

                        PropertyKey propertyKey = field.getAnnotation(PropertyKey.class);

                        for (int j = 0; j < optionalCsvTable.get().width(); j++) {
                            if(propertyKey.value().equals(optionalCsvTable.get().getHeaders().get(j))){

                                String cell = optionalCsvTable.get().get(i, optionalCsvTable.get().getHeaders().get(j));

                                field.set(tObject, getConvertedVariable(cell));
                            }
                        }
                    }
                }
                list.add(tObject);
            }
            return list;
        } catch (IOException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Object getConvertedVariable(String field) {
        try {
            Integer integerNumber = Integer.parseInt(field);
            return integerNumber;
        } catch (NumberFormatException ignored) {}
        try {
            Double doubleNumber = Double.parseDouble(field);
            return doubleNumber;
        } catch (NumberFormatException ignored) {}
        return field;
    }
}