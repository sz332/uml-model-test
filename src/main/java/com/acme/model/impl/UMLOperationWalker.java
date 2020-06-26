package com.acme.model.impl;

import com.acme.model.UMLActivity;
import com.acme.model.UMLOperation;

public class UMLOperationWalker {
    
    public void walk(UMLOperation operation, UMLVisitor visitor) {
        visitor.visit(operation);

        if (operation.assignedActivity().isPresent()) {

            UMLActivity activity = operation.assignedActivity().get();

            visitor.visit(activity);

            for (UMLActivity calledActivity : activity.calledActivities()) {
                walk(calledActivity.assignedOperation(), visitor);
            }
        }
    }

}