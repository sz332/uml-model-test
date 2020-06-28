package com.acme.verification.confroles;

import com.acme.model.UMLModel;

import org.junit.Before;
import org.junit.Test;

public class ConflictingRolesVerification {

    // A, A->B,C, B->D,E

    // A has no role, caller has no role -> SUCCESS
    // A has single role, caller has a single matching role -> SUCCESS
    // A has single role, caller has a multiple roles, one matching role -> SUCCESS
    // A has single role, caller has a single not matching role -> FAIL
    // A has single role, caller has multiple not matching role -> FAIL

    // A has multiple roles, caller single matching role -> SUCCESS
    // A has multiple roles, caller have single, non-matching role -> FAIL
    // A has multiple roles, caller has multiple roles, one matching -> SUCCESS
    // A has multiple roles, caller has multiple roles, multiple matching -> SUCCESS
    // A has multiple roles, caller have single, non-matching role -> FAIL
    // A has multiple roles, caller have multiple, non-matching role -> FAIL

    // A -> B
    // A -> B,C
    // A -> B -> D
    // A -> B -> D, E

    @Test
    public void testNoRole(){
        
    }
    
}