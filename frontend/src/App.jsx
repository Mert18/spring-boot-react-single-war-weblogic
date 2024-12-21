import { Link } from "react-router-dom";
import "./App.css";
import { useEffect, useState } from "react";

function App() {

  const [paging, setPaging] = useState({
    number: 0,
    size: 10,
    numberOfElements: null
  });

  const [filter, setFilter] = useState({
    name: "",
    level: null,
  });
  const [databases, setDatabases] = useState([]);

  useEffect(() => {
    fetch("/api/database/list?number=" + paging.number + "&size=" + paging.size, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(filter),
    }).then(async (response) => {
      const res = await response.json();
      setDatabases(res.data.content)
    });
  }, [paging, filter]);

  useEffect(() => {
    console.log("dbs changed: ", databases)
  }, [databases])

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
