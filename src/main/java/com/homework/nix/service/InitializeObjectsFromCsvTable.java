package com.homework.nix.service;

import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.List;

public interface InitializeObjectsFromCsvTable {

    <T> List<T> initialize(Class<T> clazz, Path pathToCSVFile);

    Object getConvertedVariable(String cell, Field field);
}
