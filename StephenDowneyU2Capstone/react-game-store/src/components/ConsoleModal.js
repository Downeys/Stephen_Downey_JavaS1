import { useState } from 'react';
import { Button, Form, Modal } from 'react-bootstrap';

function ConsoleModal({ onSubmit, console: initialConsole }){
  const [show, setShow] = useState(false);
  const [console, setConsole] = useState(initialConsole)
  const isAdd = initialConsole.id === 0

  const handleClose = () => setShow(false);
  const handleShow = () => {
    setConsole(initialConsole)
    setShow(true);
  }

  function handleChange(evt){
    let value = evt.target.value;
    const clone = { ...console }
    if (evt.target.type === "number") {
        value = parseInt(value, 10);
    } else {
        clone[evt.target.name] = value
    }
    setConsole(clone);
  }

  function handleSubmit(evt){
    evt.preventDefault()

    const url = "http://localhost:8080/console"
    const method = isAdd ? "POST" : "PUT";
    const expectedStatus = isAdd ? 201 : 204;

    const init = {
        method,
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        body: JSON.stringify(console)
    };

    setShow(false);

    fetch(url, init)
        .then(response => {
            if (response.status === expectedStatus) {
                if (isAdd) {
                    return response.json();
                } else {
                    return console;
                }
            }
            return Promise.reject(`Didn't receive expected status: ${expectedStatus}`);
        })
        .then(result => onSubmit(result))
        // .catch(error => notify({ error }));
    }

  return (
    <>
      <Button variant="primary" onClick={handleShow}>
        {isAdd
        ? 'Add new Console'
        : 'Edit'}
      </Button>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header>
          <Modal.Title>Console</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form onSubmit={handleSubmit}>
            <Form.Group controlId="formModel">
              <Form.Label>Model</Form.Label>
              <Form.Control onChange={handleChange} type="text" placeholder="model" name='model' value={console.model} />
            </Form.Group>
            <Form.Group controlId="formManufacturer">
              <Form.Label>Manufacturer</Form.Label>
              <Form.Control onChange={handleChange} type="text" placeholder="manufacturer" name='manufacturer' value={console.manufacturer} />
            </Form.Group>
            <Form.Group controlId="formMemoryAmount">
              <Form.Label>Memory Amount</Form.Label>
              <Form.Control onChange={handleChange} type="text" placeholder="memoryAmount" name='memoryAmount' value={console.memoryAmount}/>
            </Form.Group>
            <Form.Group controlId="formProcessor">
              <Form.Label>Processor</Form.Label>
              <Form.Control onChange={handleChange} type="text" placeholder="processor" name='processor' value={console.processor}/>
            </Form.Group>
            <Form.Group controlId="formQuantity">
              <Form.Label>Quantity</Form.Label>
              <Form.Control onChange={handleChange} type="text" placeholder="quantity" name='quantity' value={console.quantity}/>
            </Form.Group>
            <Form.Group controlId="formPrice">
              <Form.Label>Price</Form.Label>
              <Form.Control onChange={handleChange} type="text" placeholder="price" name='price' value={console.price}/>
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={handleSubmit}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default ConsoleModal