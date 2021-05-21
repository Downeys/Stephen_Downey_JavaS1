import { useEffect, useState } from 'react';
import { Button, FormControl, Modal } from 'react-bootstrap';
import TshirtModal from './TshirtModal';

function TshirtRow({ tshirt: initialTshirt, role, onEdit, onDelete, addToCart }){
  const [tshirt, setTshirt] = useState({...initialTshirt})
  const [desiredQuantity, setDesiredQuantity] = useState(0)
  const [showAlert, setShowAlert] = useState(false)

  useEffect(() => {
    setTshirt(initialTshirt)
  }, [initialTshirt])


  function handleDelete(){
    fetch(`http://localhost:8080/tshirt/${tshirt.id}`, { method: "DELETE" })
    .then(() => onDelete(tshirt.id))
    .catch(console.log);
  }

  function handleEdit(updatedTshirt){
    setTshirt(updatedTshirt)
    onEdit(updatedTshirt)
  }

  function handleAddToCart(){
    if(!Number(desiredQuantity)){
      return
    }
    if(desiredQuantity <= tshirt.quantity){
      const tshirtOrder = {...tshirt}
      tshirtOrder.inStock = tshirt.quantity
      tshirtOrder.quantity = desiredQuantity
      addToCart(tshirtOrder)
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
        <Modal.Body>There are only {tshirt.quantity} of the item you requested, still in stock. Please revise your desired quantity, and try your order again.</Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShowAlert(false)}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>
      )
    : (
    <tr>
        <td>{tshirt.description}</td>
        <td>{tshirt.size}</td>
        <td>{tshirt.color}</td>
        <td>{tshirt.quantity}</td>
        <td>{tshirt.price}</td>
        {role === 'admin'
        ? <td><TshirtModal tshirt={tshirt} onSubmit={handleEdit}/></td>
        : <td><FormControl name='quantity' placeholder={0} onChange={handleChange}></FormControl></td>
        }
        {role === 'admin'
        ? <td><Button onClick={handleDelete}>Delete</Button></td>
        : <td><Button onClick={handleAddToCart}>Add to Cart</Button></td>
        }
    </tr>
  ))
}

export default TshirtRow