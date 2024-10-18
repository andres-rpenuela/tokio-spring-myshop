package com.tokioschool.myshop.exception;

/**
 * Excepción para controlar la falta de stock de un producto
 */
public class NoStockException extends Exception {

    public NoStockException() {
        super();
    }

    public NoStockException(String message) {
        super(message);
    }
}
