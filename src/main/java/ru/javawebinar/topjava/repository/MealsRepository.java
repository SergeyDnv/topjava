package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealsRepository {

    List<Meal> getList();

    void save(Meal meal);

    void update(Meal meal);

    void delete(int id);
}
