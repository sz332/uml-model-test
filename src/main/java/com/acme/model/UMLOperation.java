package com.acme.model;

import java.util.List;
import java.util.Optional;

public interface UMLOperation extends UMLBase {

    Optional<UMLActivity> assignedActivity();

    List<UMLRole> roles();

}
