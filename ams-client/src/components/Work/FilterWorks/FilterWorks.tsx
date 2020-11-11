import { Button, createStyles, TextField } from '@material-ui/core';
import React, { useState } from 'react';
import Autocomplete from '@material-ui/lab/Autocomplete';
import Typography from '@material-ui/core/Typography';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import Box from '@material-ui/core/Box/Box';
import Grid, { GridSpacing } from '@material-ui/core/Grid';
import makeStyles from '@material-ui/core/styles/makeStyles';
import { Theme } from '@material-ui/core/styles/createMuiTheme';

const useStyles = makeStyles((theme: Theme) =>
  createStyles({
    item: {
      flexGrow: 1,
      flexBasis: 0,
    }
  }),
);

const FilterWorks: React.FC<{projectId: number, keywords: string[], keywordsLoading: boolean, filter: (keywords: Set<string>) => void, resetFilter: () => void}> = ({projectId, keywords, keywordsLoading, filter, resetFilter}) => {
  const classes=useStyles();
  const [currentKeyword, setCurrentKeyword] = useState<string | null>(null);
  const [selectedKeywords, setSelectedKeywords] = useState<Set<string>>(new Set());
  const [keywordText, setKeywordText] = useState<string>("");

  const addKeyword = async (newKeyword: string | null) => {
    if (newKeyword !== null && !selectedKeywords.has(newKeyword)) {
      selectedKeywords.add(newKeyword);
      setSelectedKeywords(selectedKeywords);
      setCurrentKeyword(null);
      updateText();
    }
  }

  const updateText = async () => {
    let newStr = "";
    selectedKeywords.forEach(s => newStr += s + ", ");
    setKeywordText(newStr);
  }


  return (
    <div>
      <Grid container justify="center" spacing={2}>
        <Grid item className={classes.item}>
          <Box my={2}>
            <Autocomplete
              id="keywordsDropdown"
              options={keywords}
              loading={keywordsLoading}
              value={currentKeyword}
              onChange={(event: any, newKeyword: string | null) => {
                setCurrentKeyword(newKeyword);
              }}
              // getOptionLabel={(option) => option}
              style={{ width: "100%" }}
              renderInput={(params) => <TextField {...params} label="All Keywords" variant="filled" />}
            />
          </Box>
          <Button onClick={() => addKeyword(currentKeyword)} variant="contained" color="default">Add keyword</Button>
        </Grid>
        <Grid item className={classes.item}>
          <Box my={2}>
            <Card variant="outlined" style={{ width: "100%" }}>
              <CardContent>
                <Typography variant="h5" component="h5">
                  Current Keywords
                </Typography>
                <Typography variant="body2" component="p">
                  {/* &nbsp;{getKeywordText()} */}
                  &nbsp;{keywordText}
                </Typography>
              </CardContent>
            </Card>
          </Box>
          <Button onClick={() => filter(selectedKeywords)} variant="contained" color="primary">
              Apply Filters
          </Button>
          <br></br>
          <br></br>
          <Button onClick={resetFilter} variant="contained" color="default">
              Reset Filter
          </Button>
        </Grid>
      </Grid>
    </div>
  );
};

export default FilterWorks;
