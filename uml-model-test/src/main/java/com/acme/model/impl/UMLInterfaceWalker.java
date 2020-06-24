package com.acme.model.impl;

import com.acme.model.UMLActivity;
import com.acme.model.UMLInterface;
import com.acme.model.UMLOperation;

public class UMLInterfaceWalker {

    private final UMLInterface umlInterface;

    public UMLInterfaceWalker(UMLInterface umlInterface) {
        this.umlInterface = umlInterface;
    }

    public void walk(UMLInterfaceVisitor visitor) {
        for (UMLOperation operation : umlInterface.operations()) {
            walkRecursively(operation, visitor);
        }
    }

    private void walkRecursively(UMLOperation operation, UMLInterfaceVisitor visitor) {
        visitor.visit(operation);

        if (operation.associatedActivity().isPresent()) {

            UMLActivity activity = operation.associatedActivity().get();

            visitor.visit(activity);

            for (UMLActivity calledActivity : activity.calledActivities()) {
                walkRecursively(calledActivity.associatedOperation(), visitor);
            }
        }
    }

}