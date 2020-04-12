package com.justamonad.tutorials.spring.validators.impl;

import java.util.List;
import java.util.function.Function;

import com.justamonad.tutorials.spring.validators.api.Transaction;

@FunctionalInterface
public interface ValidatorFunction extends Function<Transaction, List<ErrorData>> {

}
