import { Button, createStyles } from '@material-ui/core';
import { workers } from 'cluster';
import React, {useState, useEffect} from 'react';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    useParams, useHistory
  } from "react-router-dom";

import Accordion from '@material-ui/core/Accordion';
import AccordionSummary from '@material-ui/core/AccordionSummary';
import AccordionDetails from '@material-ui/core/AccordionDetails';
import Typography from '@material-ui/core/Typography';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';


import { Work } from '../../../model/Work';
import ProjectService from '../../../service/ProjectService';
import AddQuery from '../AddQuery/AddQuery';
import FilterWorks from '../FilterWorks/FilterWorks';
import WorkList from '../WorkList/WorkList';
import makeStyles from '@material-ui/core/styles/makeStyles';
import { Theme } from '@material-ui/core/styles/createMuiTheme';

const useStyles = makeStyles((theme: Theme) =>
  createStyles({
    details: {
      display: "block",
    }
  }),
);

const WorkPage: React.FC = () => {
  const classes=useStyles();
  const id = (useParams() as any).id; // get proj id passed in

  // state values
  const [works, setWorks] = useState<Work[]>([]);
  const [filteredWorks, setFilteredWorks] = useState<Work[]>([]);
  const [keywords, setKeywords] = useState<string[]>([]);
  const [keywordsLoading, setKeywordsLoading] = useState<boolean>(false);

  // functions
  const loadData = async (projectId: number) => {
    setKeywordsLoading(true); // keywords not yet loaded
    const res = await ProjectService.getProjectWorks(projectId);
    setWorks(res);
    extractKeywords(res);
  };
  const addQuery = async (query: string) => {
    await ProjectService.addQueryToProject(id, query);
    loadData(id);
  }
  const filter = async (keywords: Set<string>) => {
    setFilteredWorks(works.filter(w => w.abstractKeywords.filter))
  }
  const extractKeywords = async (works: Work[]) => {
    setKeywords(works.flatMap(w => w.extractedKeywords.map(k => k.keyword)).sort().getUnique());
    setKeywordsLoading(false); // done loading keywords
  }

  // load data on component load
  useEffect(() => {
    loadData(id);

    return () => {};
  }, []);

  // routing stuff
  const history = useHistory();
  
  // actual component
  return (
    <div>
      <Button color="primary" onClick={() => history.push("/")}>Back to projects</Button>

      {/* add query area */}
      <Accordion>
        <AccordionSummary
          expandIcon={<ExpandMoreIcon />}
          aria-controls="query-panel-content"
          id="query-panel-header"
        >
          <Typography>Add Query to Project</Typography>
        </AccordionSummary>
        <AccordionDetails>
          <Typography>
            Add the results of a new query to this project.
          </Typography>
          <AddQuery projectId={id} addQuery={addQuery} />
        </AccordionDetails>
      </Accordion>
      {/* filter by keyword area */}
      <Accordion>
        <AccordionSummary
          expandIcon={<ExpandMoreIcon />}
          aria-controls="filter-panel-content"
          id="filter-panel-header"
        >
          <Typography>Keyword Filter</Typography>
        </AccordionSummary>
        <AccordionDetails className={classes.details}>
          <Typography>
            Filter results by keywords.
          </Typography>
          <FilterWorks projectId={id} keywords={keywords} keywordsLoading={keywordsLoading} filter={filter} />
        </AccordionDetails>
      </Accordion>

      <WorkList works={works} />
    </div>
  );
};

export default WorkPage;
