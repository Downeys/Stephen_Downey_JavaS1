import { useEffect, useState } from 'react';
import { Button, FormControl, Modal } from 'react-bootstrap';
import ConsoleModal from './ConsoleModal';

function ConsoleRow({ console: initialConsole, role, onEdit, onDelete, addToCart }){
  const [console, setConsole] = useState({...initialConsole})
  const [desiredQuantity, setDesiredQuantity] = useState([])
  const [showAlert, setShowAlert] = useState(false)

  useEffect(() => {
    setConsole(initialConsole)
  }, [initialConsole])


  function handleDelete(){
    fetch(`http://localhost:8080/console/${console.id}`, { method: "DELETE" })
    .then(() => onDelete(console.id))
    .catch(console.log);
  }

  function handleEdit(updatedConsole){
    setConsole(updatedConsole)
    onEdit(updatedConsole)
  }

  function handleAddToCart(){
    if(!Number(desiredQuantity)){
      return
    }
    if(desiredQuantity <= console.quantity){
      const consoleOrder = {...console}
      consoleOrder.inStock = console.quantity
      consoleOrder.quantity = desiredQuantity
      addToCart(consoleOrder)
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
        <Modal.Body>There are only {console.quantity} of the item you requested, still in stock. Please revise your desired quantity, and try your order again.</Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShowAlert(false)}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>
      )
    : (
    <tr>
        <td>{console.model}</td>
        <td>{console.manufacturer}</td>
        <td>{console.memoryAmount}</td>
        <td>{console.processor}</td>
        <td>{console.quantity}</td>
        <td>{console.price}</td>
        {role === 'admin'
        ? <td><ConsoleModal console={console} onSubmit={handleEdit}/></td>
        : <td><FormControl name='quantity' placeholder={0} onChange={handleChange}></FormControl></td>
        }
        {role === 'admin'
        ? <td><Button onClick={handleDelete}>Delete</Button></td>
        : <td><Button onClick={handleAddToCart}>Add to Cart</Button></td>
        }
    </tr>
  ))
}

export default ConsoleRow