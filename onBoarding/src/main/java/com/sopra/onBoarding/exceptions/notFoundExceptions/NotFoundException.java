package com.sopra.onBoarding.exceptions.notFoundExceptions;

import com.sopra.onBoarding.exceptions.GreatMotherException;

public class NotFoundException extends GreatMotherException {

    private static final long serialVersionUID = 1L;

    public NotFoundException(String mensaje) {
        super(mensaje);
        // TODO Auto-generated constructor stub
    }
}