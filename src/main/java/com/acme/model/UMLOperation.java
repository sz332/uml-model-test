package com.acme.model;

import java.util.List;
import java.util.Optional;

public interface UMLOperation {

    String id();
    String name();

    Optional<UMLActivity> associatedActivity();

    List<UMLRole> roles();

}
