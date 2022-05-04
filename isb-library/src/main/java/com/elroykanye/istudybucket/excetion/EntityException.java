package com.elroykanye.istudybucket.excetion;

import java.util.Arrays;

public class EntityException {
    public static class EntityNotFoundException extends RuntimeException {
        public EntityNotFoundException(String entityName, Long id) {
            super(String.format("%s with id %d not found.", entityName, id));
        }
        public EntityNotFoundException(String entityName, String identifier) {
            super(String.format("%s with identifier %s not found.", entityName, identifier));
        }
    }
    public static class EntityAlreadyExistsException extends RuntimeException {
        public EntityAlreadyExistsException(String entityName, Long id) {
            super(String.format("%s with id %d already exists.", entityName, id));
        }
        public EntityAlreadyExistsException(String entityName, String... identifiers) {
            super(String.format(("%s with any of following identifiers already exists: " + Arrays.toString(identifiers)), entityName));
        }
    }
}
