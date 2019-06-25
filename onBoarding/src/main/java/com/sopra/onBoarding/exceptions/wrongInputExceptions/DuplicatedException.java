package com.sopra.onBoarding.exceptions.wrongInputExceptions;

import com.sopra.onBoarding.exceptions.GreatMotherException;

public class DuplicatedException extends BadRequestMotherException {

    private static final long serialVersionUID = 1L;

    public DuplicatedException(String mensaje) {
        super(mensaje);
        // TODO Auto-generated constructor stub
    }
}