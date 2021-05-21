import { useEffect, useState } from 'react';
import { Button, FormControl, Modal } from 'react-bootstrap';
import GameModal from './GameModal';

function GameRow({ game: initialGame, role, onEdit, onDelete, addToCart }){

  const [game, setGame] = useState({...initialGame})
  const [desiredQuantity, setDesiredQuantity] = useState(0)
  const [showAlert, setShowAlert] = useState(false)

  useEffect(() => {
    setGame(initialGame)
  }, [initialGame])


  function handleDelete(){
    fetch(`http://localhost:8080/game/${game.id}`, { method: "DELETE" })
    .then(() => onDelete(game.id))
    .catch(console.log);
  }

  function handleEdit(updatedGame){
    setGame(updatedGame)
    onEdit(updatedGame)
  }

  function handleAddToCart(){
    if(!Number(desiredQuantity)){
      return
    }
    if(desiredQuantity <= game.quantity){
      const gameOrder = {...game}
      gameOrder.inStock = game.quantity
      gameOrder.quantity = desiredQuantity
      addToCart(gameOrder)
    } else {
      setShowAlert(true)
    }
  }

  function handleChange(evt){
    let value = evt.target.value;

    if (evt.target.type === "number") {
        value = parseInt(value, 10);
    }

    setDesiredQuantity(value);
  }

  return(
    showAlert
    ? (
      <Modal show={showAlert} onHide={() => setShowAlert(false)}>
        <Modal.Header>
          <Modal.Title>Not enough in stock</Modal.Title>
        </Modal.Header>
        <Modal.Body>There are only {game.quantity} of the item you requested, still in stock. Please revise your desired quantity, and try your order again.</Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShowAlert(false)}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>
      )
    : (
    <tr>
        <td>{game.title}</td>
        <td>{game.description}</td>
        <td>{game.esrbRating}</td>
        <td>{game.studio}</td>
        <td>{game.quantity}</td>
        <td>{game.price}</td>
        {role === 'admin'
        ? <td><GameModal game={game} onSubmit={handleEdit}/></td>
        : <td><FormControl name='quantity' id={game.id} placeholder={0} onChange={handleChange}></FormControl></td>
        }
        {role === 'admin'
        ? <td><Button onClick={handleDelete}>Delete</Button></td>
        : <td><Button onClick={handleAddToCart}>Add to Cart</Button></td>
        }
    </tr>
  ))

}

export default GameRow