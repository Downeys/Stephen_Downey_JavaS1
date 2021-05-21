import { useState } from 'react';
import { Button, Form, Modal } from 'react-bootstrap';

function TshirtModal({ onSubmit, tshirt: initialTshirt }){
  const [show, setShow] = useState(false);
  const [tshirt, setTshirt] = useState(initialTshirt)
  const isAdd = initialTshirt.id === 0

  const handleClose = () => setShow(false);
  const handleShow = () => {
    setTshirt(initialTshirt)
    setShow(true);
  }
  function handleChange(evt){
    let value = evt.target.value;
    const clone = { ...tshirt }
    if (evt.target.type === "number") {
        value = parseInt(value, 10);
    } else {
        clone[evt.target.name] = value
    }
    setTshirt(clone);
  }

  function handleSubmit(evt){
    evt.preventDefault()

    const url = "http://localhost:8080/tshirt"
    const method = isAdd ? "POST" : "PUT";
    const expectedStatus = isAdd ? 201 : 204;

    const init = {
        method,
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        body: JSON.stringify(tshirt)
    };

    setShow(false);

    fetch(url, init)
        .then(response => {
            if (response.status === expectedStatus) {
                if (isAdd) {
                    return response.json();
                } else {
                    return tshirt;
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
        ? 'Add new T-Shirt'
        : 'Edit'}
      </Button>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header>
          <Modal.Title>T-Shirt</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form onSubmit={handleSubmit}>
            <Form.Group controlId="formDescription">
              <Form.Label>Description</Form.Label>
              <Form.Control onChange={handleChange} type="text" placeholder="description" name='description' value={tshirt.description} />
            </Form.Group>
            <Form.Group controlId="formSize">
              <Form.Label>Size</Form.Label>
              <Form.Control onChange={handleChange} type="text" placeholder="size" name='size' value={tshirt.size} />
            </Form.Group>
            <Form.Group controlId="formColor">
              <Form.Label>Color</Form.Label>
              <Form.Control onChange={handleChange} type="text" placeholder="color" name='color' value={tshirt.color}/>
            </Form.Group>
            <Form.Group controlId="formQuantity">
              <Form.Label>Quantity</Form.Label>
              <Form.Control onChange={handleChange} type="text" placeholder="quantity" name='quantity' value={tshirt.quantity}/>
            </Form.Group>
            <Form.Group controlId="formPrice">
              <Form.Label>Price</Form.Label>
              <Form.Control onChange={handleChange} type="text" placeholder="price" name='price' value={tshirt.price}/>
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

export default TshirtModal