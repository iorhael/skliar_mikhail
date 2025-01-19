package com.senla.controller;

import com.senla.dto.subscription.SubscriptionCreateDto;
import com.senla.dto.subscription.SubscriptionGetDto;
import com.senla.dto.subscription.SubscriptionUpdateDto;
import com.senla.model.SubscriptionPlan;
import com.senla.model.User;
import com.senla.service.SubscriptionService;
import com.senla.util.ValidationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class SubscriptionServlet extends HttpServlet {

    private final SubscriptionService subscriptionService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            listSubscriptions(request, response);
        } else {
            switch (pathInfo) {
                case "/insert":
                    createSubscription(request, response);
                    break;
                case "/update":
                    updateSubscription(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    break;
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            listSubscriptions(request, response);
        } else {
            switch (pathInfo) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/delete":
                    deleteSubscription(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    break;
            }
        }
    }

    private void listSubscriptions(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<SubscriptionGetDto> listSubscriptions = subscriptionService.getAllSubscriptions();

        request.setAttribute("listSubscriptions", listSubscriptions);
        request.getRequestDispatcher("/WEB-INF/views/subscription/list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/subscription/create.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UUID id = UUID.fromString(request.getParameter("id"));

        SubscriptionGetDto subscription = subscriptionService.getSubscriptionById(id);

        request.setAttribute("subscription", subscription);
        request.getRequestDispatcher("/WEB-INF/views/subscription/edit.jsp").forward(request, response);
    }

    private void createSubscription(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        UUID userId = UUID.fromString(request.getParameter("userId"));
        UUID subscriptionPlanId = UUID.fromString(request.getParameter("subscriptionPlanId"));
        Instant expiresDate = validateDate(request.getParameter("expiresDate"));

        User user = new User();
        user.setId(userId);

        SubscriptionPlan subscriptionPlan = new SubscriptionPlan();
        subscriptionPlan.setId(subscriptionPlanId);

        SubscriptionCreateDto subscription = ValidationUtil.validate(new SubscriptionCreateDto(user, subscriptionPlan, expiresDate));

        subscriptionService.createSubscription(subscription);
        response.sendRedirect(request.getContextPath() + "/subscription");
    }

    private void updateSubscription(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        UUID id = UUID.fromString(request.getParameter("id"));
        Instant expiresDate = validateDate(request.getParameter("expiresDate"));

        SubscriptionUpdateDto subscription = ValidationUtil.validate(new SubscriptionUpdateDto(expiresDate));

        subscriptionService.updateSubscription(subscription, id);
        response.sendRedirect(request.getContextPath() + "/subscription");
    }

    private void deleteSubscription(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        UUID id = UUID.fromString(request.getParameter("id"));

        subscriptionService.deleteSubscription(id);
        response.sendRedirect(request.getContextPath() + "/subscription");
    }

    private Instant validateDate(String date) {
        try {
            return Instant.parse(date);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid expiresDate format");
        }
    }
}
