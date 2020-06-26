package com.acme.model.impl;

import com.acme.model.UMLActivity;
import com.acme.model.UMLOperation;

public interface UMLVisitor{

    void visit(UMLActivity activity);
    
    void visit(UMLOperation operation);

}