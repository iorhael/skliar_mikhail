package com.senla.config.modelmapper;

import com.senla.model.Category;
import com.senla.model.Role;
import com.senla.model.RoleName;
import com.senla.model.Tag;
import com.senla.model.Vote;
import org.modelmapper.Converter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class ConverterUtil {
    public static final Converter<List<Category>, List<String>> CATEGORIES_TO_NAMES = context ->
            context.getSource()
                    .stream()
                    .map(Category::getName)
                    .toList();

    public static final Converter<List<Tag>, List<String>> TAGS_TO_NAMES = context ->
            context.getSource()
                    .stream()
                    .map(Tag::getName)
                    .toList();

    public static final Converter<Set<Role>, Set<String>> ROLES_TO_NAMES = context ->
            context.getSource()
                    .stream()
                    .map(Role::getName)
                    .map(RoleName::name)
                    .collect(Collectors.toSet());

    public static final Converter<List<Vote>, Integer> VOTES_TO_COUNT = context -> context.getSource().size();

    private ConverterUtil() {
    }
}
