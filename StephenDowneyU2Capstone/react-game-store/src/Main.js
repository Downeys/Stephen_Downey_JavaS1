import { useEffect, useState } from 'react';
import { Container, Nav } from 'react-bootstrap';
import {
    BrowserRouter as Router,
    NavLink,
    Route,
    Switch
} from "react-router-dom";
import CartModal from './components/CartModal';
import CheckOutModal from './components/CheckOutModal';
import ConsolePage from './components/ConsolePage';
import GamePage from './components/GamePage';
import TshirtPage from './components/TshirtPage';
import UserSelector from './components/UserSelector';

function Main(){

  const [userRole, setUserRole] = useState('')
  const [cartItems, setCartItems] = useState([])
  const [allGames, setAllGames] = useState([])
  const [allConsoles, setAllConsoles] = useState([])
  const [allTshirts, setAllTshirts] = useState([])
  const [checkedOut, setCheckedOut] = useState(false)
  const [transactionTotal, setTransactionTotal] = useState(0)

  useEffect(() => {
    fetch('http://localhost:8080/game')
    .then(resp => resp.json())
    .then(result => {
        result.forEach(game => game.itemType = 'game')
        setAllGames(result)
    })
    .catch(console.log)

    fetch('http://localhost:8080/console')
    .then(resp => resp.json())
    .then(result => {
        result.forEach(console => console.itemType = 'console')
      setAllConsoles(result)
    })
    .catch(console.log)

    fetch('http://localhost:8080/tshirt')
    .then(resp => resp.json())
    .then(result => {
        result.forEach(tshirt => tshirt.itemType = 'tshirt')
      setAllTshirts(result)
    })
    .catch(console.log)

  }, [])

  function  handleUserSelect(role){
    setUserRole(role)
  }

  function handleAddItem(newItem){
      switch(newItem.itemType){
        case 'game':
            setAllGames([...allGames, newItem])
            break
        case 'console':
            setAllConsoles([...allConsoles, newItem])
            break
        case 'tshirt':
            setAllTshirts([...allTshirts, newItem])
            break
        default:
            console.log('1')
            console.log(newItem.itemType)
            console.log('An error has occurred.')
      }
  }

  function handleEdit(item){
      switch(item.itemType){
            case 'game':
                const gameClone = [...allGames]
                const gameToUpdate = gameClone.filter(game => game.id === item.id)
                gameClone.splice(gameClone.findIndex(game => game.id === gameToUpdate[0].id), 1, item)
                setAllGames([...gameClone])
                break
            case 'console':
                const consoleClone = [...allConsoles]
                const consoleToUpdate = consoleClone.filter(console => console.id === item.id)
                consoleClone.splice(consoleClone.findIndex(console => console.id === consoleToUpdate[0].id), 1, item)
                setAllConsoles([...consoleClone])
                break
            case 'tshirt':
                const tshirtClone = [...allTshirts]
                const tshirtToUpdate = tshirtClone.filter(tshirt => tshirt.id === item.id)
                tshirtClone.splice(tshirtClone.findIndex(tshirt => tshirt.id === tshirtToUpdate[0].id), 1, item)
                setAllTshirts([...tshirtClone])
                break
            default:
                console.log('2')
                console.log(item.itemType)
                console.log('An error has occurred')
      }
  }

  function handleDelete(id, itemType){
      switch(itemType){
        case 'game':
            setAllGames(allGames.filter(game => game.id !== id))
            break
        case 'console':
            setAllConsoles(allConsoles.filter(console => console.id !== id))
            break
        case 'tshirt':
            setAllTshirts(allTshirts.filter(tshirt => tshirt.id !== id))
            break
        default:
            console.log('3')
            console.log(itemType)
            console.log('An error has occurred')
      }
  }

  function handleAddToCart(newItem){
    const cartClone = [...cartItems]
    if(cartClone.some(item => item.id === newItem.id)
    && cartItems.some(item => item.itemType === newItem.itemType)){

        const itemToUpdate = cartClone.filter(item =>  (item.id === newItem.id && item.itemType === newItem.itemType))

        const newQuantity = Number(itemToUpdate[0].quantity) + Number(newItem.quantity)

        const index = cartClone.findIndex(item =>  (item.id === newItem.id && item.itemType === newItem.itemType))

        itemToUpdate[0].quantity = newQuantity
        cartClone.splice(index, 1, itemToUpdate[0])
        setCartItems([...cartClone])
    } else {
        const newCartItemList = [...cartItems, newItem]
        setCartItems([...newCartItemList])
    }
    changeInventory(newItem, 'remove')
  }

  function handleRemoveFromCart(removedItem){
    const cartClone = [...cartItems]
    const itemToUpdate = cartClone.filter(item => (item.id === removedItem.id && item.itemType === removedItem.itemType))

        const newQuantity = Number(itemToUpdate[0].quantity) - Number(removedItem.quantity)

        const index = cartClone.findIndex(item =>  (item.id === removedItem.id && item.itemType === removedItem.itemType))
        itemToUpdate[0].quantity = newQuantity
        if(newQuantity > 0){
            cartClone.splice(index, 1, itemToUpdate[0])
        } else {
            cartClone.splice(index, 1)
        }
        changeInventory(removedItem, 'add')
        setCartItems([...cartClone])
  }

  function handleCheckout(itemsToCheckout){
      itemsToCheckout.forEach(item => {
        const url = `http://localhost:8080/${item.itemType}`
        const method = "PUT";
        const expectedStatus = 204;
        switch(item.itemType){
            case 'game':
                const gameClone = [...allGames]
                const gameToUpdate = gameClone.filter(game => game.id === item.id)

                const gameInit = {
                    method,
                    headers: {
                        "Content-Type": "application/json",
                        "Accept": "application/json"
                    },
                    body: JSON.stringify(gameToUpdate[0])
                };

                fetch(url, gameInit)

                break
            case 'console':
                const consoleClone = [...allConsoles]
                const consoleToUpdate = consoleClone.filter(console => console.id === item.id)

                const consoleInit = {
                    method,
                    headers: {
                        "Content-Type": "application/json",
                        "Accept": "application/json"
                    },
                    body: JSON.stringify(consoleToUpdate[0])
                };

                fetch(url, consoleInit)

                break
            case 'tshirt':
                const tshirtClone = [...allTshirts]
                const tshirtToUpdate = tshirtClone.filter(tshirt => tshirt.id === item.id)

                const tshirtInit = {
                    method,
                    headers: {
                        "Content-Type": "application/json",
                        "Accept": "application/json"
                    },
                    body: JSON.stringify(tshirtToUpdate[0])
                };

                fetch(url, tshirtInit)

                break
            default:
                console.log('2')
                console.log(item.itemType)
                console.log('An error has occurred')
        }
      })
      setCartItems([])
  }

  function showCheckOutModal(total){
      setTransactionTotal(total)
      setCheckedOut(true)
  }

  function completeCheckout(){
      setTransactionTotal(0)
      setCheckedOut(false)
  }

  function changeInventory(item, operation){
    let newQuantity = 0
    switch(item.itemType){
        case 'game':
            const gameClone = [...allGames]
            const gameToUpdate = gameClone.filter(game => game.id === item.id)
            switch(operation){
                case 'add':
                    newQuantity = Number(gameToUpdate[0].quantity) + Number(item.quantity)
                    break
                case 'remove':
                    newQuantity =  Number(gameToUpdate[0].quantity) - Number(item.quantity)
                    break
                default:
                    console.log('4')
                    console.log(operation)
                    console.log('An error has occurred')
            }
            gameToUpdate[0].quantity = newQuantity
            handleEdit(gameToUpdate[0])
            break
        case 'console':
            const consoleClone = [...allConsoles]
            const consoleToUpdate = consoleClone.filter(console => console.id === item.id)
            switch(operation){
                case 'add':
                    newQuantity = Number(consoleToUpdate[0].quantity) + Number(item.quantity)
                    break
                case 'remove':
                    newQuantity = Number(consoleToUpdate[0].quantity) - Number(item.quantity)
                    break
                default:
                    console.log('5')
                    console.log('An error has occurred')
            }
            consoleToUpdate[0].quantity = newQuantity
            handleEdit(consoleToUpdate[0])
            break
        case 'tshirt':
            const tshirtClone = [...allTshirts]
            const tshirtToUpdate = tshirtClone.filter(tshirt => tshirt.id === item.id)
            switch(operation){
                case 'add':
                    newQuantity = Number(tshirtToUpdate[0].quantity) + Number(item.quantity)
                    break
                case 'remove':
                    newQuantity = Number(tshirtToUpdate[0].quantity) - Number(item.quantity)
                    break
                default:
                    console.log('6')
                    console.log('An error has occurred')
            }
            tshirtToUpdate[0].quantity = newQuantity
            handleEdit(tshirtToUpdate[0])
            break
        default:
            console.log('7')
            console.log(item.itemType)
            console.log('An error has occurred')
    }
  }

  let returnVal

  userRole
  ? returnVal = (
    <Container fluid>
        <Router>
            <Nav variant="pills" className='justify-content-end'>
                {userRole &&
                    <Nav.Item>
                        <NavLink exact to='/' className="nav-link" activeClassName="active">Switch User</NavLink>
                    </Nav.Item>
                }
                <Nav.Item>
                    <NavLink to='/games' className="nav-link" activeClassName="active">Games</NavLink>
                </Nav.Item>
                <Nav.Item>
                    <NavLink to='/consoles' className="nav-link" activeClassName="active">Consoles</NavLink>
                </Nav.Item>
                <Nav.Item>
                    <NavLink to='/tshirts' className="nav-link" activeClassName="active">T-Shirts</NavLink>
                </Nav.Item>
                {(cartItems.length > 0 && userRole === 'customer') &&
                    <Nav.Item>
                        <CartModal cartItems={cartItems} removeFromCart={handleRemoveFromCart} addToCart={handleAddToCart} checkout={handleCheckout} showCheckOutModal={showCheckOutModal}/>
                    </Nav.Item>
                }
                {checkedOut && <CheckOutModal checkedOut={checkedOut} total={transactionTotal} completeCheckout={completeCheckout} />}
            </Nav>
            <Switch>
                <Route path='/games'>
                    <GamePage role={userRole} addToCart={handleAddToCart} games={allGames} editItem={handleEdit} addItem={handleAddItem} deleteItem={handleDelete}/>
                </Route>
                <Route path='/consoles'>
                    <ConsolePage role={userRole} addToCart={handleAddToCart} consoles={allConsoles} editItem={handleEdit} addItem={handleAddItem} deleteItem={handleDelete}/>
                </Route>
                <Route path='/tshirts'>
                    <TshirtPage role={userRole} addToCart={handleAddToCart} tshirts={allTshirts} editItem={handleEdit} addItem={handleAddItem} deleteItem={handleDelete}/>
                </Route>
                <Route path='/'>
                    <UserSelector onSelectUser={handleUserSelect} />
                </Route>
            </Switch>
        </Router>
    </Container>
    )
  : returnVal = (<UserSelector onSelectUser={handleUserSelect} />)

  return returnVal
}

export default Main