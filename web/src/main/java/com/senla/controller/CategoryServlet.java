package com.senla.controller;

import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;
import com.senla.dto.category.CategoryCreateDto;
import com.senla.dto.category.CategoryGetDto;
import com.senla.model.Category;
import com.senla.service.CategoryService;
import com.senla.util.ValidationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
public class CategoryServlet extends HttpServlet {

    @Autowired
    private CategoryService categoryService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            listCategories(request, response);
        } else {
            switch (pathInfo) {
                case "/insert":
                    createCategory(request, response);
                    break;
                case "/update":
                    updateCategory(request, response);
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
            listCategories(request, response);
        } else {
            switch (pathInfo) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/delete":
                    deleteCategory(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    break;
            }
        }
    }

    private void listCategories(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<CategoryGetDto> listCategories = categoryService.getAllCategories();

        request.setAttribute("listCategories", listCategories);
        request.getRequestDispatcher("/WEB-INF/views/category/list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/category/create.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UUID id = UUID.fromString(request.getParameter("id"));

        CategoryGetDto category = categoryService.getCategoryById(id);

        request.setAttribute("category", category);
        request.getRequestDispatcher("/WEB-INF/views/category/edit.jsp").forward(request, response);
    }

    private void createCategory(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        String parentIdParam = request.getParameter("parentId");
        UUID parentId = (parentIdParam == null || parentIdParam.isEmpty()) ? null : UUID.fromString(parentIdParam);
        Category parentCategory = new Category();
        parentCategory.setId(parentId);

        CategoryCreateDto category = ValidationUtil.validate(new CategoryCreateDto(name, description, parentCategory));

        categoryService.createCategory(category);
        response.sendRedirect(request.getContextPath() + "/category");
    }

    private void updateCategory(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        UUID id = UUID.fromString(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        String parentIdParam = request.getParameter("parentId");
        UUID parentId = (parentIdParam == null || parentIdParam.isEmpty()) ? null : UUID.fromString(parentIdParam);
        Category parentCategory = new Category();
        parentCategory.setId(parentId);

        CategoryCreateDto category = ValidationUtil.validate(new CategoryCreateDto(name, description, parentCategory));

        categoryService.updateCategory(category, id);
        response.sendRedirect(request.getContextPath() + "/category");
    }

    private void deleteCategory(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        UUID id = UUID.fromString(request.getParameter("id"));

        categoryService.deleteCategory(id);
        response.sendRedirect(request.getContextPath() + "/category");
    }
}
