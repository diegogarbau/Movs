package com.sopra.onBoarding.exceptions.wrongInputExceptions;

import com.sopra.onBoarding.exceptions.GreatMotherException;

public abstract class BadRequestMotherException extends GreatMotherException {

    private static final long serialVersionUID = 1L;

    public BadRequestMotherException(String mensaje) {
        super(mensaje);
        // TODO Auto-generated constructor stub
    }
}