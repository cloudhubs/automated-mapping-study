package edu.baylor.ecs.ams.model;

import edu.baylor.ecs.ams.dto.MetadataDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseModel {

    protected String pdfLink;

    public abstract MetadataDto toDto();

}
