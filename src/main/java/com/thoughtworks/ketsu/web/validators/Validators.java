package com.thoughtworks.ketsu.web.validators;

import org.apache.commons.lang3.EnumUtils;

import javax.ws.rs.BadRequestException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

/**
 * Created by pzzheng on 5/21/17.
 */
public class Validators {
    public static void validate(Map<String, Object> info, Validator validator) {
        Optional<String> errors = validator.validate(info);
        if(errors.isPresent()) {
            throw new BadRequestException(errors.get());
        }
    }

    public static Validator fieldNotEmpty(String field) {
        return info -> info.getOrDefault(field, "").toString().isEmpty() ? Optional.of(fieldErrorMessage(field, field + " cannot be empty.").toString()) : Optional.empty();
    }

    public static Validator fieldIsEnum(String field, Class enumType) {
        return info -> !EnumUtils.isValidEnum(enumType, info.get(field).toString()) ? Optional.of(fieldErrorMessage(field, field + " is not valid values. Valid values are: " + EnumUtils.getEnumList(enumType)).toString()) : Optional.empty();
    }

    public static Validator collectionNotEmpty(String field) {
        return info -> ((Collection)info.getOrDefault(field, Collections.EMPTY_LIST)).isEmpty() ? Optional.of(fieldErrorMessage(field, field + " cannot be an empty list").toString()) : Optional.empty();
    }

    public static Validator all(Validator... validators) {
        return info -> {
            List<String> errors = asList(validators).stream().map(validator -> validator.validate(info))
                    .filter(error -> error.isPresent())
                    .map(error -> error.get())
                    .collect(Collectors.toList());
            return errors.size() > 0 ? Optional.of(String.join("\\n", errors)) : Optional.empty();
        };
    }

    private static Map<String, String> fieldErrorMessage(String field, String message) {
        return new HashMap() {{
            put("field", field);
            put("message", message);
        }};
    }
}
