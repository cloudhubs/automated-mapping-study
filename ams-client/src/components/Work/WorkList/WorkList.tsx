import React from 'react';
// table stuff
import { DataGrid, ColDef, ValueGetterParams, RowsProp } from '@material-ui/data-grid';


import { Work } from '../../../model/Work';
import { kMaxLength } from 'buffer';
import AddQuery from '../AddQuery/AddQuery';

const columns: ColDef[] = [
  { field: 'id', headerName: 'ID'},
  { field: 'doi', headerName: 'DOI', width: 100},
  { field: 'documentTitle', headerName: 'Title', width: 400},
  { field: 'authors', headerName: 'Authors', width: 200},
  { field: 'publicationTitle', headerName: 'Publication', width: 200},
  { field: 'date', headerName: 'Published', width: 100},
  { field: 'abstractKeywords', headerName: 'Abstract Keywords', width: 400, sortable: false},
  { field: 'issn', headerName: 'ISSN', width: 100},
  { field: 'isbns', headerName: 'ISBNs', width: 100},
];

const GetRows = (works: Work[]): RowsProp => {
  return works.map(w => {
      return {
          id: w.id, doi: w.doi, documentTitle: w.documentTitle, authors: w.authors, publicationTitle: w.publicationTitle, date: w.date,
          abstractKeywords: w.abstractKeywords.map(k => k.keyword).join(", "), isbns: w.isbns, issn: w.issn
        }
    });
}

const WorkList: React.FC<{works: Work[]}> = ({works}) => (
  <div>
    &nbsp;
    <DataGrid rows={GetRows(works)} columns={columns} autoHeight={true}/>
    &nbsp;
  </div>
);

export default WorkList;
