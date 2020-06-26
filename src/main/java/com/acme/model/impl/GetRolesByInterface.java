package com.acme.model.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.acme.model.UMLInterface;
import com.acme.model.UMLOperation;
import com.acme.model.UMLRole;

public class GetRolesByInterface {

    public List<UMLRole> execute(final UMLInterface umlInterface) {
        final Map<String, UMLRole> roles = new HashMap<>();

        final UMLOperationWalker walker = new UMLOperationWalker();

        for (UMLOperation operation : umlInterface.operations()) {

            walker.walk(operation, new UMLOperationVisitor() {

                @Override
                public void visit(final UMLOperation operation, List<UMLOperation> path) {
                    for (UMLRole role : operation.roles()) {
                        roles.put(role.name(), role);
                    }
                }

            });

        }

        return roles.values().stream().collect(Collectors.toList());
    }

}