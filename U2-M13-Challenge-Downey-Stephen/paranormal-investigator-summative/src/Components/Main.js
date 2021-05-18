import 'bootstrap/dist/css/bootstrap.css'
import { useState, useEffect } from 'react'
import { Container, Nav, Alert} from 'react-bootstrap'
import {
    BrowserRouter as Router,
    Switch,
    Route,
    NavLink,
} from "react-router-dom";
import EncounterPage from './EncounterPage'
import EncounterForm from './EncounterForm'
import InvestigatorPage from './InvestigatorPage'

function Main() {

    const emptyInvestigator = {
        id: 0,
        firstName: '',
        lastName: ''
    }

    const now = new Date();
    const year = now.getFullYear();
    const month = new Intl.DateTimeFormat("en-US", { month: "short" }).format(now);
    const day = new Intl.DateTimeFormat("en-US", { day: "2-digit" }).format(now);
    const time = new Intl.DateTimeFormat("en-US", { hour: "2-digit", minute: "2-digit" })
        .format(now).replace(" ", "");

    const emptyEncounter = {
        id: 0,
        brief: '',
        details: '',
        dateTime: `${day}-${month}-${year} ${time}`,
        imageUrl: '',
        investigators: [emptyInvestigator]
    }

    const [allEncounters, setAllEncounters] = useState([])
    const [allInvestigators, setAllInvestigators] = useState([])
    const [scopedEncounter, setScopedEncounter] = useState(emptyEncounter)
    const [showForm, setShowForm] = useState(false)
    const [error, setError] = useState()

    useEffect(() => {
        fetch('http://localhost:8080/api/encounter')
        .then(resp => resp.json())
        .then(result => setAllEncounters(result))
        .catch(console.log)

        fetch('http://localhost:8080/api/investigator')
        .then(response => response.json())
        .then(result => setAllInvestigators(result))
        .catch(console.log)

    }, [])

    function handleAddEncounter(){
        setScopedEncounter(emptyEncounter)
        setShowForm(true)
    }

    function notify({ action, encounter, error, investigator }) {

        if (error) {
            setError(error);
            setShowForm(false);
            return;
        }

        switch (action) {
            case "add":
                setAllEncounters([...allEncounters, encounter]);
                setShowForm(false);
                break;
            case "add-investigator":
                setAllInvestigators([...allInvestigators, investigator])
                console.log(allInvestigators)
                break;
            case "edit":
                setShowForm(false);
                setAllEncounters(allEncounters.map(e => {
                    if (e.id === encounter.id) {
                        return encounter;
                    }
                    return e;
                }));
                break;
            case "edit-form":
                setScopedEncounter(encounter);
                setShowForm(true);
                return;
            case "delete":
                setShowForm(false);
                setAllEncounters(allEncounters.filter(e => e.id !== encounter.id));
                break;
            case "cancel":
                setShowForm(false);
                break;
            default:
                alert("This service is not available at this time.")
        }
    }

    return (
    <Container fluid>
        <Router>
            <Nav variant="pills" className='justify-content-end'>
                <Nav.Item>
                    <NavLink to='/' className="nav-link" activeClassName="active">Encounters</NavLink>
                </Nav.Item>
                <Nav.Item>
                    <NavLink to='/investigators' className="nav-link" activeClassName="active">Investigators</NavLink>
                </Nav.Item>
            </Nav>
            {error
            ? <Alert variant='danger'>{error}</Alert>
            : <Switch>
                <Route path='/investigators'>
                    <InvestigatorPage investigatorList={allInvestigators} notify={notify} />
                </Route>
                <Route path='/'>
                    {showForm
                    ?   <EncounterForm encounter={scopedEncounter} investigatorList={allInvestigators} notify={notify} />
                    :   <EncounterPage encounterList ={allEncounters} addfunction={handleAddEncounter} notify={notify} />
                    }
                </Route>
            </Switch>
        }
        </Router>
    </Container>
    )
}

export default Main