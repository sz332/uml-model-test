package com.acme.model.impl;

import java.util.Objects;

import com.acme.model.UMLRole;

public class UMLRoleImpl implements UMLRole{

    private final String name;

    public UMLRoleImpl(String name){
        this.name = name;
    }

    @Override
    public String id() {
        return this.name;
    }

    @Override
    public String name() {
        return this.name;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UMLRoleImpl)) {
            return false;
        }
        UMLRoleImpl uMLRoleImpl = (UMLRoleImpl) o;
        return Objects.equals(name, uMLRoleImpl.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }


    
}