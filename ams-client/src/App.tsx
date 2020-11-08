import React from 'react';
import './App.css';
import ProjectPage from './components/Project/ProjectPage/ProjectPage';
import WorkPage from './components/Work/WorkPage/WorkPage';

Array.prototype.getUnique = function() {
  var u: any = {}, a = [];
  for (var i = 0, l = this.length; i < l; ++i) {
      if (u.hasOwnProperty(this[i])) {
          continue;
      }
      a.push(this[i]);
      u[this[i]] = 1;
  }
  return a;
}

function App() {
  return (
    <div>
      {/* <ProjectPage /> */}
      <ProjectPage />
    </div>
  );
}

export default App;
