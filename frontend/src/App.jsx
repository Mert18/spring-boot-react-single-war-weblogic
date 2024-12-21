import { Link } from "react-router-dom";
import "./App.css";
import { useEffect, useState } from "react";

function App() {
  const [paging, setPaging] = useState({
    page: 0,
    size: 10,
  });
  const [databases, setDatabases] = useState([]);

  useEffect(() => {
    fetch("/api/database")
      .then((response) => response.json())
      .then((json) =>
        setResult("Received From Spring: " + JSON.stringify(json))
      );
  }, []);
  return (
    <div>
      <nav>
        <ul>
          <li>
            <Link to="/">Home</Link>
          </li>
          <li>
            <Link to="/add-database">About</Link>
          </li>
        </ul>
      </nav>

      <div>
        <p>List of databases</p>
      </div>
    </div>
  );
}

export default App;
