package com.acme.model;

import java.util.List;

public interface UMLInterface {

    String id();
    String name();

    List<UMLOperation> operations();
    
}