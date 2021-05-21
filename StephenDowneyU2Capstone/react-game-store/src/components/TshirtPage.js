import { useState } from 'react'
import { Col, Container, Row } from 'react-bootstrap'
import TshirtFilter from './TshirtFilter'
import TshirtModal from './TshirtModal'
import TshirtTable from './TshirtTable'

function TshirtPage({ role, addToCart, tshirts, editItem, addItem, deleteItem }){
  const [displayedTshirts, setDisplayTshirts] = useState([...tshirts])

  function handleSubmit(newTshirt){
    updateDisplayedTshirts([...displayedTshirts, newTshirt])
    newTshirt.itemType='tshirt'
    addItem(newTshirt)
  }

  function handleDelete(tshirtId){
    updateDisplayedTshirts(displayedTshirts.filter(tshirt => tshirt.id !== tshirtId))
    deleteItem(tshirtId, 'tshirt')
  }

  function  updateDisplayedTshirts(newTshirtList){
    setDisplayTshirts([...newTshirtList])
  }

  return(
    <Container>
      <Row>
        <h1>T-Shirts</h1>
      </Row>
      <Row>
        <Col lg='2'>
          {role === 'admin' && <TshirtModal tshirt={{ id:0 }} onSubmit={handleSubmit}/>}
        </Col>
        <Col>
          <TshirtFilter tshirts={tshirts} onFilter={updateDisplayedTshirts}/>
        </Col>
      </Row>
      <Row>
        <Col>
          <TshirtTable displayedTshirts={displayedTshirts} role={role} onEdit={editItem} onDelete={handleDelete} addToCart={addToCart} />
        </Col>
      </Row>
    </Container>
  )
}

export default TshirtPage