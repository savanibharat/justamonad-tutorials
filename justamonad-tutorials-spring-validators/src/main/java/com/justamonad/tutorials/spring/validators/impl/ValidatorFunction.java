package com.justamonad.tutorials.spring.validators.impl;

import java.util.List;
import java.util.function.Function;

import com.justamonad.tutorials.spring.validators.api.Transaction;

/**
 * ValidatorFunction is a {@link Function} that accepts type {@link Transaction}
 * and returns List&lt;ErrorData&gt;.
 * 
 * Every API Validator must implement this interface. This interface is used as
 * injection target for all the implementations of Injection will be done as :
 * 
 * <pre>
 * &#064;Inject
 * List&#060;ValidatorFunction&#062; validatorFunctions;
 * </pre>
 */
@FunctionalInterface
public interface ValidatorFunction extends Function<Transaction, List<ErrorData>> {

}
