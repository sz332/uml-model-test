package com.acme.model;

import java.util.List;

public interface UMLActivity {

    String id();
    String name();

    UMLOperation associatedOperation();
    List<UMLActivity> calledActivities();
    
}