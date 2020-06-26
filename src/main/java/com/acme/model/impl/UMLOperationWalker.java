package com.acme.model.impl;

import java.util.ArrayList;
import java.util.List;

import com.acme.model.UMLActivity;
import com.acme.model.UMLOperation;

public class UMLOperationWalker {
    
    public void walk(UMLOperation operation, UMLOperationVisitor visitor) {
        walkRecursively(operation, visitor, new ArrayList<>());
    }

    public void walkRecursively(UMLOperation operation, UMLOperationVisitor visitor, List<UMLOperation> path){

        List<UMLOperation> newPath = extendList(path, operation);

        visitor.visit(operation, newPath);

        if (operation.assignedActivity().isPresent()) {
            UMLActivity activity = operation.assignedActivity().get();

            for (UMLActivity calledActivity : activity.calledActivities()) {
                walkRecursively(calledActivity.assignedOperation(), visitor, newPath);
            }
        }
    }

    public List<UMLOperation> extendList(List<UMLOperation> path, UMLOperation operation){
        List<UMLOperation> retValue = new ArrayList<>(path);
        retValue.add(operation);
        return retValue;
    }

}