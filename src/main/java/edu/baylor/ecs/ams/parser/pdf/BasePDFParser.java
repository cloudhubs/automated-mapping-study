package edu.baylor.ecs.ams.parser.pdf;

import edu.baylor.ecs.ams.model.BaseModel;
import edu.baylor.ecs.ams.model.MetadataModel;

public abstract class BasePDFParser {

    public abstract String parsePDF(BaseModel model);
    public abstract String parsePDF(MetadataModel model);
}
