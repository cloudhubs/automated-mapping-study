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
import Box from '@material-ui/core/Box/Box';
import Link from '@material-ui/core/Link/Link';

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
  const [worksLoading, setWorksLoading] = useState<boolean>(false);

  // functions
  const loadData = async (projectId: number) => {
    setKeywordsLoading(true); // keywords not yet loaded
    setWorksLoading(true);
    const res = await ProjectService.getProjectWorks(projectId);
    setWorks(res);
    setFilteredWorks(res);
    extractKeywords(res);
    setWorksLoading(false);
  };
  const addQuery = async (query: string, sites: Map<String,boolean>) => {
    // let sitesToUse = 
    await ProjectService.addQueryToProject(id, query);
    loadData(id);
  }
  const filter = async (keywordsToFind: Set<string>) => {
    setFilteredWorks(works.filter(w => {
      const workKeys = new Set(w.abstractKeywords.map(k => k.keyword));
      for (const elem of Array.from(keywordsToFind)) {
        if (!workKeys.has(elem)) {
            return false; // one of the keywords not found
        }
      }
      return true;
    }));
  }
  const resetFilter = async () => {
    setFilteredWorks(works);
  }
  const extractKeywords = async (works: Work[]) => {
    setKeywords(works.flatMap(w => w.abstractKeywords.map(k => k.keyword)).sort().getUnique());
    // setKeywords(works.flatMap(w => w.extractedKeywords.map(k => k.keyword)).sort().getUnique());
    setKeywordsLoading(false); // done loading keywords
  }

  const exportKeywords = async () => {
    ProjectService.exportKeywords(id);
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
          <FilterWorks projectId={id} keywords={keywords} keywordsLoading={keywordsLoading} filter={filter} resetFilter={resetFilter} />
        </AccordionDetails>
      </Accordion>
      <Accordion>
        <AccordionSummary
          expandIcon={<ExpandMoreIcon />}
          aria-controls="filter-panel-content"
          id="filter-panel-header"
        >
          <Typography>Export</Typography>
        </AccordionSummary>
        <AccordionDetails className={classes.details}>
          <Typography>
            Export this project's keywords as a CSV.
          </Typography>
          <Link rel="noopener noreferrer" href={"localhost:8080/project/" + id + "/exportkeywords"} target="_blank">Export</Link>
        </AccordionDetails>
      </Accordion>
      

      <WorkList works={filteredWorks} worksLoading={worksLoading}/>
    </div>
  );
};

export default WorkPage;
