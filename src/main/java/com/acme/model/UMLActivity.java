package com.acme.model;

import java.util.List;

public interface UMLActivity extends UMLBase {

    UMLOperation assignedOperation();
    List<UMLActivity> calledActivities();
    
}