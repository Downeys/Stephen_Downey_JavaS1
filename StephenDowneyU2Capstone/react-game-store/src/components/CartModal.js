import { useEffect, useState } from 'react'
import { Button, Modal } from 'react-bootstrap'
import LineItem from './LineItem'

function CartModal({ cartItems: initialCartItems, removeFromCart, addToCart, checkout, showCheckOutModal }){
  const [show, setShow] = useState(false)
  const [total, setTotal] = useState(0)
  const [cartItems, setCartItems] = useState(initialCartItems)

  useEffect(() => {
    setCartItems(initialCartItems)
    setTotal(calculateTotal())
  }, [initialCartItems, calculateTotal])

  function handleShow(){
    setShow(true)
  }

  function handleClose(){
    setShow(false)
  }

  function handleCancel(){
    cartItems.forEach(item => removeFromCart({...item}))
    setShow(false)
  }

  function handleCheckout(){
    checkout(cartItems)
    showCheckOutModal(total)
    setShow(false)
  }

  function calculateTotal(){
    let newTotal = 0
    cartItems.forEach(item => {
      const subTotal = item.price * item.quantity
      newTotal = newTotal + subTotal
    })
    Intl.NumberFormat('en-US', {style: 'currency', currency: 'USD', minimumFractionDigits:2}).format(newTotal)
    return newTotal
  }

  return (
    <>
      <Button variant="primary" onClick={handleShow}>
        Place Order ({cartItems.length})
      </Button>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header>
          <Modal.Title>Cart</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {cartItems.map(item => <LineItem item={item} removeFromCart={removeFromCart} addToCart={addToCart}/>)}
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant='danger' onClick={handleCancel}>
            Cancel Transaction
          </Button>
          <Button onClick={handleCheckout}>
            Checkout
          </Button>
          <p>Total: ${total}</p>
        </Modal.Footer>
      </Modal>
    </>
  )
}

export default CartModal