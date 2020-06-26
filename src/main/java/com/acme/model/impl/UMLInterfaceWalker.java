package com.acme.model.impl;

import com.acme.model.UMLInterface;
import com.acme.model.UMLOperation;

public class UMLInterfaceWalker {

    private final UMLInterface umlInterface;

    public UMLInterfaceWalker(UMLInterface umlInterface) {
        this.umlInterface = umlInterface;
    }

    public void walk(UMLOperationVisitor visitor) {
        UMLOperationWalker operationWalker = new UMLOperationWalker();

        for (UMLOperation operation : umlInterface.operations()) {
            operationWalker.walk(operation, visitor);
        }
    }

}