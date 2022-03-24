package com.springapp.app.indoorturfbooking.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {
    
    private String resourceName;
    private String  resourceId;
    

    public ResourceNotFoundException(String resourceName, String resourceId) {
        super("Resource '"+resourceName+"' with id="+resourceId+" was not Found!!! :(");
        this.resourceName = resourceName;
        this.resourceId = resourceId;
    }


    public String getResourceName() {
        return resourceName;
    }
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
    public String getResourceId() {
        return resourceId;
    }
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
    
}
