package com.acme.verification.confroles;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.acme.model.UMLOperation;
import com.acme.model.UMLRole;
import com.acme.model.impl.UMLRoleImpl;

import org.junit.Test;

public class ConflictingRolesPolicyTest{

    ConflictingRolesPolicy policy = new ConflictingRolesPolicy();

    @Test
    public void testNoRole(){

        List<UMLRole> expectedRoles = Collections.emptyList();
        
        UMLOperation operation = mock(UMLOperation.class);        
        when(operation.roles()).thenReturn(Collections.emptyList());

        List<UMLRole> missingRoles = policy.compare(expectedRoles, operation);
        assertThat(missingRoles, is(empty()));
    }


    @Test
    public void testSingleMatchingRole(){

        List<UMLRole> expectedRoles = Arrays.asList(new UMLRoleImpl("admin"));
        
        UMLOperation operation = mock(UMLOperation.class);        
        when(operation.roles()).thenReturn(Arrays.asList(new UMLRoleImpl("admin")));

        List<UMLRole> missingRoles = policy.compare(expectedRoles, operation);
        assertThat(missingRoles, is(empty()));
    }

}