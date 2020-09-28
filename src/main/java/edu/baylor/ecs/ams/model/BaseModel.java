package edu.baylor.ecs.ams.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseModel {

    protected String pdfLink;
    protected String fullTextPath;

    public abstract MetadataModel toMetadata();

}
