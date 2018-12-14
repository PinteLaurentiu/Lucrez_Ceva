package lucrez.ceva.service;

import lucrez.ceva.exceptions.ValidationException;

import java.util.function.Consumer;

public class Validator<T> {
    private Consumer<T>[] validations;

    @SafeVarargs
    public Validator(Consumer<T>... validations) {
        this.validations = validations;
    }

    public void validate(T element) {
        for (Consumer<T> validation :
                validations) {
            try {
                validation.accept(element);
            } catch (ValidationException ex) {
                throw ex;
            } catch (Exception ex) {
                throw new ValidationException(ex);
            }
        }
    }
}
