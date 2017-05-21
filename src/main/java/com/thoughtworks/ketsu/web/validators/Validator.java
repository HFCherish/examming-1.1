package com.thoughtworks.ketsu.web.validators;

import java.util.Map;
import java.util.Optional;

/**
 * Created by pzzheng on 5/21/17.
 */
public interface Validator {
    Optional<String> validate(Map<String, Object> info);
}
