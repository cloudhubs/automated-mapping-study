import React, {useState, useEffect} from 'react';

import { Project } from '../../../model/Project';
import ProjectService from '../../../service/ProjectService';
import ProjectList from '../ProjectList/ProjectList';

const ProjectPage: React.FC = () => {
  const [projects, setProjects] = useState<Project[]>([]);
  const loadData = async () => {
    const res = await ProjectService.getAllProjects();
    setProjects(res);
  };

  useEffect(() => {
    loadData();
    return () => {};
  }, []);

  return (
    <div>
      <ProjectList projects={projects}/>
    </div>
  );
};

export default ProjectPage;
