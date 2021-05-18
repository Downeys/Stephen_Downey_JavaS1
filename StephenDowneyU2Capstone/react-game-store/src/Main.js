import { useState } from 'react';
import { Container, Nav } from 'react-bootstrap';
import {
  BrowserRouter as Router,
  NavLink,
  Route,
  Switch
} from "react-router-dom";
import GamePage from './components/GamePage';
import UserSelector from './components/UserSelector';

function Main(){

  const [userRole, setUserRole] = useState('')

  function  handleUserSelect(role){
    setUserRole(role)
  }

  return (
    <Container fluid>
        <Router>
            <Nav variant="pills" className='justify-content-end'>
                <Nav.Item>
                    <NavLink exact to='/' className="nav-link" activeClassName="active">Home</NavLink>
                </Nav.Item>
                <Nav.Item>
                    <NavLink to='/games' className="nav-link" activeClassName="active">Games</NavLink>
                </Nav.Item>
                <Nav.Item>
                    <NavLink to='/consoles' className="nav-link" activeClassName="active">Consoles</NavLink>
                </Nav.Item>
                <Nav.Item>
                    <NavLink to='/tshirts' className="nav-link" activeClassName="active">T-Shirts</NavLink>
                </Nav.Item>
            </Nav>
            <Switch>
                <Route path='/games'>
                    <GamePage role={userRole}/>
                </Route>
                <Route path='/consoles'>
                    <h1>Consoles</h1>
                </Route>
                <Route path='/tshirts'>
                    <h1>T-Shirts</h1>
                </Route>
                <Route path='/'>
                    <UserSelector onSelectUser={handleUserSelect} />
                </Route>
            </Switch>
        </Router>
    </Container>
    )
}

export default Main