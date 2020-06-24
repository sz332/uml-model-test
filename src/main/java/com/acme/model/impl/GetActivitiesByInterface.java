package com.acme.model.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.acme.model.UMLActivity;
import com.acme.model.UMLInterface;
import com.acme.model.UMLOperation;

public class GetActivitiesByInterface {

    public List<UMLActivity> execute(final UMLInterface umlInterface) {
        final Map<String, UMLActivity> activities = new HashMap<>();

        final UMLInterfaceWalker walker = new UMLInterfaceWalker(umlInterface);

        walker.walk(new UMLInterfaceVisitor() {

            @Override
            public void visit(final UMLOperation operation) {
            }

            @Override
            public void visit(final UMLActivity activity) {
                activities.put(activity.id(), activity);
            }
        });

        return activities.values().stream().collect(Collectors.toList());
    }



}