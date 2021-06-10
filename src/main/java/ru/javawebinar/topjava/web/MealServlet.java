package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.MealsRepositoryList;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String idForParse = request.getParameter("id");
        String date = request.getParameter("date");
        String description = request.getParameter("description");
        String calories = request.getParameter("calories");

        Meal meal = new Meal(
                idForParse.equals("") ? MealsRepositoryList.getInstance().getCOUNT().get() : Integer.parseInt(idForParse),
                LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")),
                description,
                Integer.parseInt(calories)
        );

        if ("".equals(idForParse)) {
            MealsRepositoryList.getInstance().save(meal);
        } else {
            MealsRepositoryList.getInstance().update(meal);
        }
        response.sendRedirect("meals");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String forward = null;

        List<MealTo> mealToList = MealsUtil.filteredByStreams(
                MealsRepositoryList.getInstance().getList(),
                LocalTime.MIN,
                LocalTime.MAX,
                MealsRepositoryList.getInstance().getCaloriesPerDay()
        );

        if (action == null) {
            request.setAttribute("list", mealToList);

            log.debug("forward to meals");
            forward = "meals.jsp";
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));

            log.debug("delete meal");
            MealsRepositoryList.getInstance().delete(id);
            response.sendRedirect("meals");
            return;
        } else if ("add".equals(action)) {

            log.debug("save meal");
            forward = "edit.jsp";
        } else if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));

            for (MealTo mealTo : mealToList) {
                if (mealTo.getId() == id) {
                    request.setAttribute("mealTo", mealTo);
                    break;
                }
            }

            log.debug("update meal");
            forward = "edit.jsp";
        }

        request.getRequestDispatcher(forward).forward(request, response);
    }
}
