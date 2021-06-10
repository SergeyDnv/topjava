package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MealsRepositoryList implements MealsRepository {
    private final static MealsRepositoryList INSTANCE = new MealsRepositoryList();
    private final int caloriesPerDay = 2000;
    private final CopyOnWriteArrayList<Meal> meals;
    private final AtomicInteger count;

    private MealsRepositoryList() {
        meals = new CopyOnWriteArrayList<>(new Meal[]{
                new Meal(1, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new Meal(2, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new Meal(3, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new Meal(4, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new Meal(5, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new Meal(6, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new Meal(7, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        });

        count = new AtomicInteger(meals.size() + 1);
    }

    @Override
    public List<Meal> getList() {
        return meals;
    }

    @Override
    public void save(Meal meal) {
        meals.add(meal);
        count.addAndGet(1);
    }

    @Override
    public void update(Meal meal) {
        for (int i = 0; i < meals.size(); i++) {
            if (meal.getId() == meals.get(i).getId()) {
                meals.set(i, meal);
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        for (int i = 0; i < meals.size(); i++) {
            if (id == meals.get(i).getId()) {
                meals.remove(i);
                break;
            }
        }
    }

    public static MealsRepositoryList getInstance() {
        return INSTANCE;
    }

    public int getCaloriesPerDay() {
        return caloriesPerDay;
    }

    public AtomicInteger getCOUNT() {
        return count;
    }
}
