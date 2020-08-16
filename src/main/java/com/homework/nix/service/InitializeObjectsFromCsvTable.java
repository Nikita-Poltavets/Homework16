package com.homework.nix.service;

import java.nio.file.Path;
import java.util.List;

public interface InitializeObjectsFromCsvTable {

    <T> List<T> initialize(Class<T> clazz, Path pathToCSVFile);

    Object getConvertedVariable(String field);
}
