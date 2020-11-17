import { Button, TextField } from '@material-ui/core';
import Box from '@material-ui/core/Box/Box';
import Checkbox from '@material-ui/core/Checkbox/Checkbox';
import FormControl from '@material-ui/core/FormControl/FormControl';
import FormControlLabel from '@material-ui/core/FormControlLabel/FormControlLabel';
import FormGroup from '@material-ui/core/FormGroup/FormGroup';
import FormLabel from '@material-ui/core/FormLabel/FormLabel';
import React, { useState } from 'react';

const AddQuery: React.FC<{projectId: number, addQuery: (query: string, sites: Map<String,boolean>) => void}> = ({projectId, addQuery}) => {
  const [query, setQuery] = useState("");
  const [sites, setSites] = React.useState<Map<String,boolean>>(new Map([["ieee", false], ["sciencedirect", false]]));

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    let newSites = sites;
    newSites.set(event.target.name, event.target.checked);
    setSites(newSites);
  };

  return (
      <div style={{width: "100%"}}>
        <TextField fullWidth={true} label="Query" variant="filled" value={query} onChange={(e) => setQuery(e.target.value)}/>
        <br></br>
        <br></br>
        {/* <FormControl component="fieldset">
          <FormLabel component="legend">Assign responsibility</FormLabel>
          <FormGroup>
            <FormControlLabel
              control={<Checkbox onChange={handleChange} name="ieee" />}
              label="IEEE"
            />
            <FormControlLabel
              control={<Checkbox onChange={handleChange} name="sciencedirect" />}
              label="Science Direct"
            />
          </FormGroup>
        </FormControl> */}
        <Button onClick={() => addQuery(query, sites)} variant="contained" color="primary">
            Add Query to Project
        </Button>
      </div>
  );
};

export default AddQuery;
