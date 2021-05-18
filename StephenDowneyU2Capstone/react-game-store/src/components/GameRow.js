import { Button, FormControl } from 'react-bootstrap';
import GameModal from './GameModal';

function GameRow({ game, role, onEdit, onDelete }){

  function handleDelete(){
    fetch(`http://localhost:8080/game/${game.id}`, { method: "DELETE" })
    .then(() => onDelete(game.id))
    .catch(console.log);
  }

  return(
    <tr>
        <td>{game.title}</td>
        <td>{game.description}</td>
        <td>{game.esrbRating}</td>
        <td>{game.studio}</td>
        <td>{game.quantity}</td>
        <td>{game.price}</td>
        {role === 'admin'
        ? <td><GameModal game={game} onSubmit={onEdit}/></td>
        : <td><FormControl name='quantity' placeholder={1}></FormControl></td>
        }
        {role === 'admin'
        ? <td><Button onClick={handleDelete}>Delete</Button></td>
        : <td><Button>Add to Cart</Button></td>
        }
    </tr>
  )

}

export default GameRow