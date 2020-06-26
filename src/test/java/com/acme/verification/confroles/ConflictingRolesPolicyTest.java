package com.acme.verification.confroles;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
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
import com.acme.verification.confroles.ConflictingRolesPolicy.GRANT;

import org.junit.Test;

public class ConflictingRolesPolicyTest{

    ConflictingRolesPolicy policy = new ConflictingRolesPolicy();

    @Test
    public void testNoRole(){

        List<UMLRole> expectedRoles = Collections.emptyList();
        
        UMLOperation operation = mock(UMLOperation.class);        
        when(operation.roles()).thenReturn(Collections.emptyList());

        assertThat(policy.compare(expectedRoles, operation, GRANT.GRANT_ALL), is(empty()));
        assertThat(policy.compare(expectedRoles, operation, GRANT.DENY_ALL), is(empty()));
    }

    @Test
    public void testSingleMatchingRole(){

        List<UMLRole> expectedRoles = Arrays.asList(new UMLRoleImpl("admin"));
        
        UMLOperation operation = mock(UMLOperation.class);        
        when(operation.roles()).thenReturn(Arrays.asList(new UMLRoleImpl("admin")));

        assertThat(policy.compare(expectedRoles, operation, GRANT.GRANT_ALL), is(empty()));
        assertThat(policy.compare(expectedRoles, operation, GRANT.DENY_ALL), is(empty()));
    }

    @Test
    public void testRolelessOperationWithSingleRole(){
        List<UMLRole> expectedRoles = Arrays.asList(new UMLRoleImpl("admin"));
        
        UMLOperation operation = mock(UMLOperation.class);        
        when(operation.roles()).thenReturn(Collections.emptyList());

        assertThat(policy.compare(expectedRoles, operation, GRANT.GRANT_ALL), is(empty()));
        assertThat(policy.compare(expectedRoles, operation, GRANT.DENY_ALL), is(not(empty())));
    }

    @Test
    public void testRolelessOperationWithMultipleRoles(){
        List<UMLRole> expectedRoles = Arrays.asList(new UMLRoleImpl("admin"), new UMLRoleImpl("user"));
        
        UMLOperation operation = mock(UMLOperation.class);        
        when(operation.roles()).thenReturn(Collections.emptyList());

        assertThat(policy.compare(expectedRoles, operation, GRANT.GRANT_ALL), is(empty()));
        assertThat(policy.compare(expectedRoles, operation, GRANT.DENY_ALL), is(not(empty())));
    }

}