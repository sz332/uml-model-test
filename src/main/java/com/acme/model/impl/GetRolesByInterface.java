package com.acme.model.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.acme.model.UMLActivity;
import com.acme.model.UMLInterface;
import com.acme.model.UMLOperation;
import com.acme.model.UMLRole;

public class GetRolesByInterface {

    public List<UMLRole> execute(final UMLInterface umlInterface) {
        final Map<String, UMLRole> roles = new HashMap<>();

        final UMLInterfaceWalker walker = new UMLInterfaceWalker(umlInterface);

        walker.walk(new UMLInterfaceVisitor() {

            @Override
            public void visit(final UMLOperation operation) {
                for (UMLRole role : operation.roles()){
                    roles.put(role.name(), role);
                }
            }

            @Override
            public void visit(final UMLActivity activity) {
                // do nothing
            }
        });

        return roles.values().stream().collect(Collectors.toList());
    }



}