package com.acme.verification.confroles;

import java.util.List;

import com.acme.model.UMLInterface;
import com.acme.model.UMLModel;
import com.acme.model.UMLOperation;
import com.acme.model.UMLRole;
import com.acme.model.impl.UMLOperationWalker;

public class ConflictingRolesVerification {

    private final UMLModel model;

    public ConflictingRolesVerification(UMLModel model) {
        this.model = model;
    }

    public ConflictingRolesVerificationResult verify() {

        ConflictingRolesVerificationResult result = new ConflictingRolesVerificationResult();

        for (UMLInterface umlInterface : model.interfaces()) {
            processInterface(umlInterface, result);
        }

        return result;
    }

    private void processInterface(UMLInterface umlInterface, ConflictingRolesVerificationResult result) {
        ConflictingRolesPolicy policy = new ConflictingRolesPolicy();
        UMLOperationWalker walker = new UMLOperationWalker();

        for (final UMLOperation rootOperation : umlInterface.operations()) {
            walker.walk(rootOperation, (currentOperation, path) -> {
                List<UMLRole> missingRoles = policy.compare(rootOperation.roles(), currentOperation, ConflictingRolesPolicy.GRANT.GRANT_ALL);

                if (!missingRoles.isEmpty()) {
                    result.addConflict(missingRoles, path);
                }
            });
        }
    }

}