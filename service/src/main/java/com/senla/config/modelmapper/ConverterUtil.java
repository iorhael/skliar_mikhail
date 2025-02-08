package com.senla.config.modelmapper;

import com.senla.model.Category;
import com.senla.model.Tag;
import com.senla.model.Vote;
import org.modelmapper.Converter;

import java.util.List;

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

    public static final Converter<List<Vote>, Integer> VOTES_TO_COUNT = context -> context.getSource().size();

    private ConverterUtil() {
    }
}
