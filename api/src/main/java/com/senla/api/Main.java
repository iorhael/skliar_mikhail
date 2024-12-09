package com.senla.api;

import com.senla.api.service.CategoryService;
import com.senla.di.DiApplication;
import com.senla.di.context.ApplicationContext;


public class Main {
    public static void main(String[] args) {
        ApplicationContext context = DiApplication.run(Main.class);
        // Example of getting initialized object
        CategoryService categoryService = context.getBean(CategoryService.class);
    }
}
