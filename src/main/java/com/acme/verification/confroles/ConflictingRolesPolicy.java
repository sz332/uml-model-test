package com.acme.verification.confroles;

import java.util.ArrayList;
import java.util.List;

import com.acme.model.UMLOperation;
import com.acme.model.UMLRole;

public class ConflictingRolesPolicy {

    public static enum GRANT {GRANT_ALL, DENY_ALL};

    public List<UMLRole> compare(List<UMLRole> expectedRoles, UMLOperation operation, GRANT policy){
        List<UMLRole> operationRoles = operation.roles();
        List<UMLRole> missingRoles = new ArrayList<>();
        
        if (operationRoles.isEmpty() && policy == GRANT.GRANT_ALL){
            return missingRoles;
        }

        for (UMLRole expectedRole : expectedRoles){
            if (!operationRoles.contains(expectedRole)){
                missingRoles.add(expectedRole);
            }
        }

        return missingRoles;
    }

    
}