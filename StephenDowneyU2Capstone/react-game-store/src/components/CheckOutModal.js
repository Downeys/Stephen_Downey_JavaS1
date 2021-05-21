import { useEffect, useState } from 'react';
import { Button, Modal } from 'react-bootstrap';

function CheckOutModal({ checkedOut, total, completeCheckout}){
  const [show, setShow] = useState(checkedOut);

  useEffect(() => {
    setShow(checkedOut)
  }, [checkedOut])

  const handleClose = () => {
    completeCheckout()
    setShow(false);
  }

  return (
    <Modal show={show} onHide={handleClose}>
      <Modal.Header>
        <Modal.Title>Transaction Complete</Modal.Title>
      </Modal.Header>
      <Modal.Body>You paid ${total}.</Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={handleClose}>
          Continue Shopping
        </Button>
      </Modal.Footer>
    </Modal>
  )
}

export default CheckOutModal