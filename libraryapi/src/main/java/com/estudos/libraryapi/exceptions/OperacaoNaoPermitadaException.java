package com.estudos.libraryapi.exceptions;

public class OperacaoNaoPermitadaException extends RuntimeException {
    public OperacaoNaoPermitadaException(String message) {
        super(message);
    }
}
