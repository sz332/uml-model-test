package com.acme.model.impl;

import java.util.List;

import com.acme.model.UMLOperation;

public interface UMLOperationVisitor {
    
    void visit(UMLOperation operation, List<UMLOperation> path);

}