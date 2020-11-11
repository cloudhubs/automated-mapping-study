import React from 'react';
// table stuff
import { DataGrid, ColDef, ValueGetterParams, RowsProp, RowParams, buildRowParams } from '@material-ui/data-grid';


import { Work } from '../../../model/Work';
import { kMaxLength } from 'buffer';
import AddQuery from '../AddQuery/AddQuery';
import { Modal } from '@material-ui/core';
import Grid from '@material-ui/core/Grid/Grid';
import Typography from '@material-ui/core/Typography/Typography';
import TableContainer from '@material-ui/core/TableContainer/TableContainer';
import Paper from '@material-ui/core/Paper/Paper';
import Table from '@material-ui/core/Table/Table';
import TableBody from '@material-ui/core/TableBody/TableBody';
import TableRow from '@material-ui/core/TableRow/TableRow';
import TableCell from '@material-ui/core/TableCell/TableCell';

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



const WorkList: React.FC<{works: Work[]}> = ({works}) => {
  // modal stuff
  const [open, setOpen] = React.useState(false);
  const [currentRow, setCurrentRow] = React.useState<RowParams>();
  const openWork = (param: RowParams) => {
    setCurrentRow(param);
    setOpen(true);
  };
  const closeWork = () => {
    setOpen(false);
  };

  const getModalContent = () => {
    const final = [];
    for (const elem in currentRow?.data) {
      final.push(
        <TableRow key={elem}>
          <TableCell component="th" scope="row">
            <b>{elem}</b>
          </TableCell>
          <TableCell>{currentRow?.data[elem]}</TableCell>
        </TableRow>
      )
    }
    return final;
  }

  return (
    <div>
    &nbsp;
    <DataGrid rows={GetRows(works)} columns={columns} autoHeight={true} onRowClick={openWork}/>
    &nbsp;
    <Modal
      open={open}
      onClose={closeWork}
      aria-labelledby="work description"
      aria-describedby="work description"
    >
      <TableContainer component={Paper}>
        <Table>
          <TableBody>
            {getModalContent()}
          </TableBody>
        </Table>
      </TableContainer>
    </Modal>
  </div>
  )
  };

export default WorkList;
