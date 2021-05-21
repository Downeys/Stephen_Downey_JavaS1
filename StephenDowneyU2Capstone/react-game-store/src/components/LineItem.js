import { useState } from 'react'
import { Button, Col, Container, FormControl, Row, Table } from "react-bootstrap"

function LineItem({ item: initialItem, removeFromCart, addToCart }){

  const [item, setItem] = useState(initialItem)

  function handleRemoveAll(){
    const clone = {...item}
    removeFromCart(clone)
  }

  function handleRemoveOne(){
    const clone = {...item}
    clone.quantity = 1
    removeFromCart(clone)
  }

  function handleAddOne(){
    const desiredQuantity = Number(item.quantity) + 1
    if(desiredQuantity <= item.inStock){
      console.log('triggered')
      const clone = {...item}
      clone.quantity = 1
      addToCart(clone)
    }
  }

  return(
    <Container>
      {item.itemType === 'game' && <h5>{`${item.title} ${item.itemType}`}</h5>}
      {item.itemType === 'console' && <h5>{`${item.model} ${item.itemType}`}</h5>}
      {item.itemType === 'tshirt' && <h5>{`${item.description} ${item.itemType}`}</h5>}
      <Row>
        <Col lg='9'>
          <Table size='sm'>
            <thead>
              <th>Price</th>
              <th className='text-center'>Quantity</th>
              <th>Subtotal</th>
            </thead>
            <tbody>
              <td>{item.price}</td>
              <td>
                <Row>
                  <Col sm='3'>
                    <Button onClick={handleRemoveOne}>-</Button>
                  </Col>
                  <Col sm='4'>
                    <FormControl type='text' name='quantity' placeholder={item.quantity} disabled></FormControl>
                  </Col>
                  <Col sm='3'>
                    <Button onClick={handleAddOne}>+</Button>
                  </Col>
                </Row>
              </td>
              <td>{Intl.NumberFormat('en-US', {style: 'currency', currency: 'USD', minimumFractionDigits:2}).format(item.price * item.quantity)}</td>
            </tbody>
          </Table>
        </Col>
        <Col lg='3'>
          <Button onClick={handleRemoveAll}>Remove</Button>
        </Col>
      </Row>
    </Container>
  )
}

export default LineItem