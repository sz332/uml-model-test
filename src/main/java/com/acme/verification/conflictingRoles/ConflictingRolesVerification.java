package com.acme.verification.conflictingRoles;

import java.util.ArrayList;
import java.util.List;

import com.acme.model.*;

public class ConflictingRolesVerification {

    private final ConflictingRolesPolicy policy;
    private final UMLModel model;

    public ConflictingRolesVerification(UMLModel model) {
        this.model = model;
        this.policy = new ConflictingRolesPolicy();
    }

    public ConflictingRolesVerificationResult verify() {

        ConflictingRolesVerificationResult result = new ConflictingRolesVerificationResult();

        for (UMLInterface umlInterface : model.interfaces()) {
            processInterface(umlInterface, result);
        }

        return null;
    }

    private void processInterface(UMLInterface umlInterface, ConflictingRolesVerificationResult result) {
        for (UMLOperation operation : umlInterface.operations()) {
            processRootOperation(operation, result);
        }
    }

    private void processRootOperation(UMLOperation rootOperation, ConflictingRolesVerificationResult result) {
        processOperationRecursively(rootOperation.roles(), rootOperation, new ArrayList<UMLOperation>(), result);
    }

    private void processOperationRecursively(List<UMLRole> roles, UMLOperation currentOperation, List<UMLOperation> path,
        ConflictingRolesVerificationResult result) {
 
            // FIXME USE a walker instead of hand written code!

            path.add(currentOperation);

            List<UMLRole> missingRoles = policy.compare(roles, currentOperation);

            if (!missingRoles.isEmpty()){
                result.addConflict(missingRoles, path);               
            }

    }

}