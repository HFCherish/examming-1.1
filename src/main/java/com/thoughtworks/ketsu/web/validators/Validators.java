package com.thoughtworks.ketsu.web.validators;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

/**
 * Created by pzzheng on 5/21/17.
 */
public class Validators {


    public static Validator fieldNotEmpty(String field) {
        return info -> info.getOrDefault(field, "").toString().isEmpty() ? Optional.of(fieldErrorMessage(field, field + " cannot be empty.").toString()) : Optional.empty();
    }

    public static Validator all(Validator... validators) {
        return info -> {
            List<String> errors = asList(validators).stream().map(validator -> validator.validate(info))
                    .filter(error -> error.isPresent())
                    .map(error -> error.get())
                    .collect(Collectors.toList());
            return errors.size() > 0 ? Optional.of(String.join("\n", errors)) : Optional.empty();
        };
    }

    private static Map<String, String> fieldErrorMessage(String field, String message) {
        return new HashMap() {{
            put("field", field);
            put("message", message);
        }};
    }
}
