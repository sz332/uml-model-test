package com.acme.verification.confroles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.acme.model.UMLOperation;
import com.acme.model.UMLRole;

public class ConflictingRolesPolicy {

    public static enum GRANT {GRANT_ALL, DENY_ALL};

    public List<UMLRole> apply(List<UMLRole> userRoles, UMLOperation operation, GRANT policy){
        List<UMLRole> operationRoles = operation.roles();
        List<UMLRole> missingRoles = new ArrayList<>();
        
        if (operationRoles.isEmpty() && policy == GRANT.GRANT_ALL){
            return missingRoles;
        }

        if (!operationRoles.isEmpty() && userRoles.isEmpty()){
            return operationRoles;
        }

        // if i have a USER role but the operation is protected with ADMIN role then i have a problem
        // the roles associated to an operation are in OR connection

        boolean success = false;

        for (UMLRole operationRole : operationRoles){
            if (userRoles.contains(operationRole)){
                success = true;
                break;
            } else {
                missingRoles.add(operationRole);
            }
        }

        return success == true ? Collections.emptyList() : missingRoles;
    }

    
}