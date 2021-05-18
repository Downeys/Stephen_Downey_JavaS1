import { useState } from 'react';
import { Button, Form, Modal } from 'react-bootstrap';

function GameModal({ onSubmit, game: initialGame }){
  const [show, setShow] = useState(false);
  const [game, setGame] = useState(initialGame)
  const isAdd = initialGame.id === 0

  const handleClose = () => setShow(false);
  const handleShow = () => {
    setGame(initialGame)
    setShow(true);
  }
  function handleChange(evt){
    let value = evt.target.value;
    const clone = { ...game }
    if (evt.target.type === "number") {
        value = parseInt(value, 10);
    } else {
        clone[evt.target.name] = value
    }
    setGame(clone);
  }

  function handleSubmit(evt){
    evt.preventDefault()

    const url = "http://localhost:8080/game"
    const method = isAdd ? "POST" : "PUT";
    const expectedStatus = isAdd ? 201 : 204;

    const init = {
        method,
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        body: JSON.stringify(game)
    };

    setShow(false);

    fetch(url, init)
        .then(response => {
            if (response.status === expectedStatus) {
                if (isAdd) {
                    return response.json();
                } else {
                    return game;
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
        ? 'Add new Game'
        : 'Edit'}
      </Button>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header>
          <Modal.Title>Game</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form onSubmit={handleSubmit}>
            <Form.Group controlId="formTitle">
              <Form.Label>Title</Form.Label>
              <Form.Control onChange={handleChange} type="text" placeholder="title" name='title' value={game.title} />
            </Form.Group>
            <Form.Group controlId="formDescription">
              <Form.Label>Description</Form.Label>
              <Form.Control onChange={handleChange} type="text" placeholder="description" name='description' value={game.description} />
            </Form.Group>
            <Form.Group controlId="formEsrbRating">
              <Form.Label>ESRB Rating</Form.Label>
              <Form.Control onChange={handleChange} type="text" placeholder="esrb rating" name='esrbRating' value={game.esrbRating}/>
            </Form.Group>
            <Form.Group controlId="formStudio">
              <Form.Label>Studio</Form.Label>
              <Form.Control onChange={handleChange} type="text" placeholder="studio" name='studio' value={game.studio}/>
            </Form.Group>
            <Form.Group controlId="formQuantity">
              <Form.Label>Quantity</Form.Label>
              <Form.Control onChange={handleChange} type="text" placeholder="quantity" name='quantity' value={game.quantity}/>
            </Form.Group>
            <Form.Group controlId="formPrice">
              <Form.Label>Price</Form.Label>
              <Form.Control onChange={handleChange} type="text" placeholder="price" name='price' value={game.price}/>
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

export default GameModal