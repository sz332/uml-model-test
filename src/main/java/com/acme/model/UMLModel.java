package com.acme.model;

import java.util.List;

public interface UMLModel {

    List<UMLInterface> interfaces();
    
    List<UMLRole> getRolesByInterface(UMLInterface umlInterface);
    List<UMLActivity> getActivititesByInterface(UMLInterface umlInterface);
    
}