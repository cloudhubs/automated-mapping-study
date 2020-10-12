import React from 'react';
import {useHistory, withRouter} from 'react-router-dom';
// table stuff
import { DataGrid, ColDef, ValueGetterParams, RowsProp, RowParams } from '@material-ui/data-grid';


import { Project } from '../../../model/Project';

const columns: ColDef[] = [
  { field: 'id', headerName: 'ID'},
  { field: 'title', headerName: 'Title', width: 400},
];

const GetRows = (projects: Project[]): RowsProp => {
  return projects.map(p => {return {id: p.id, title: p.title}});
}

const ProjectList: React.FC<{projects: Project[]}> = ({projects}) => {
  
  const goToProject = (param: RowParams) => {
    history.push(`/project/${param.data.id}`);
  }
  
  const history = useHistory();

  return (
  <div>
    &nbsp;
    <DataGrid rows={GetRows(projects)} columns={columns} autoHeight={true} onRowClick={goToProject}/>
    &nbsp;
  </div>
)};

export default ProjectList;
