import React, {useState, useEffect} from 'react';
import { Button, TextField } from '@material-ui/core';

import { Project } from '../../../model/Project';
import ProjectService from '../../../service/ProjectService';
import ProjectList from '../ProjectList/ProjectList';

const ProjectPage: React.FC = () => {
  const [projects, setProjects] = useState<Project[]>([]);
  const loadData = async () => {
    const res = await ProjectService.getAllProjects();
    setProjects(res);
  };
  const [title, setTitle] = useState("");
  const createProject = async (title: string) => {
    await ProjectService.createProject(title);
  };

  useEffect(() => {
    loadData();
    return () => {};
  }, []);

  return (
    <div>
      <TextField label="Project Title" variant="filled" value={title} onChange={(e) => setTitle(e.target.value)}/>
      <Button onClick={() => createProject(title)} variant="contained" color="primary">
          Add Query to Project
      </Button>
      <ProjectList projects={projects}/>
    </div>
  );
};

export default ProjectPage;
