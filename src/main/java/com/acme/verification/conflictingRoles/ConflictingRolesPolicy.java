package com.acme.verification.conflictingRoles;

import java.util.ArrayList;
import java.util.List;

import com.acme.model.UMLOperation;
import com.acme.model.UMLRole;

public class ConflictingRolesPolicy {

    public List<UMLRole> compare(List<UMLRole> expectedRoles, UMLOperation operation){
        List<UMLRole> calleeRoles = operation.roles();

        List<UMLRole> missingRoles = new ArrayList<>();
        
        for (UMLRole expectedRole : expectedRoles){
            if (!calleeRoles.contains(expectedRole)){
                missingRoles.add(expectedRole);
            }
        }

        return missingRoles;
    }

    
}