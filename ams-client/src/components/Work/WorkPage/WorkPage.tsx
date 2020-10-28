import { Button } from '@material-ui/core';
import { workers } from 'cluster';
import React, {useState, useEffect} from 'react';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    useParams, useHistory
  } from "react-router-dom";

import { Project } from '../../../model/Project';
import { Work } from '../../../model/Work';
import ProjectService from '../../../service/ProjectService';
import AddQuery from '../AddQuery/AddQuery';
import WorkList from '../WorkList/WorkList';

const WorkPage: React.FC = () => {
  const [works, setWorks] = useState<Work[]>([]);
  const loadData = async (projectId: number) => {
    const res = await ProjectService.getProjectWorks(projectId);
    setWorks(res);
  };
  const addQuery = async (query: string) => {
    await ProjectService.addQueryToProject(id, query);
    loadData(id);
  }
  const id = (useParams() as any).id;

  useEffect(() => {
    loadData(id);

    return () => {};
  }, []);

  // routing stuff
  const history = useHistory();
  
  return (
    <div>
        <Button color="primary" onClick={() => history.push("/")}>Back to projects</Button>
      <AddQuery projectId={id} addQuery={addQuery} />
      <WorkList works={works} />
    </div>
  );
};

export default WorkPage;
