import "./App.css";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import { Form } from "./components/Form";
import { Tabla } from "./components/Tabla";

function App() {
  return (
    <Router>
      <Switch>
        <Route  path="/listar">
          <Tabla />
        </Route>
        <Route path="/">
          <Form />
        </Route>
      </Switch>
    </Router>
  );
}

export default App;
