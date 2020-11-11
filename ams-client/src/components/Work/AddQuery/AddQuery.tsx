import { Button, TextField } from '@material-ui/core';
import Box from '@material-ui/core/Box/Box';
import React, { useState } from 'react';

const AddQuery: React.FC<{projectId: number, addQuery: (query: string) => void}> = ({projectId, addQuery}) => {
  const [query, setQuery] = useState("");

  return (
      <div>
        <Box m="8"><TextField label="Query" variant="filled" value={query} onChange={(e) => setQuery(e.target.value)}/></Box>

        <Button onClick={() => addQuery(query)} variant="contained" color="primary">
            Add Query to Project
        </Button>      
      </div>
  );
};

export default AddQuery;
