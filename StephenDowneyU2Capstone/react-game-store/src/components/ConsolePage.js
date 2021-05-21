import { useState } from 'react'
import { Col, Container, Row } from 'react-bootstrap'
import ConsoleFilter from './ConsoleFilter'
import ConsoleModal from './ConsoleModal'
import ConsoleTable from './ConsoleTable'

function ConsolePage({ role, addToCart, consoles, editItem, addItem, deleteItem }){
  const [displayedConsoles, setDisplayedConsoles] = useState([...consoles])

  function handleSubmit(newConsole){
    updateDisplayedConsoles([...displayedConsoles, newConsole])
    newConsole.itemType='console'
    addItem(newConsole)
  }

  function handleDelete(consoleId){
    updateDisplayedConsoles(displayedConsoles.filter(console => console.id !== consoleId))
    deleteItem(consoleId, 'console')
  }

  function  updateDisplayedConsoles(newConsoleList){
    setDisplayedConsoles([...newConsoleList])
  }

  return(
    <Container>
      <Row>
        <h1>Consoles</h1>
      </Row>
      <Row>
        <Col lg='2'>
          {role === 'admin' && <ConsoleModal console={{ id:0 }} onSubmit={handleSubmit}/>}
        </Col>
        <Col>
          <ConsoleFilter consoles={consoles} onFilter={updateDisplayedConsoles}/>
        </Col>
      </Row>
      <Row>
        <Col>
          <ConsoleTable displayedConsoles={displayedConsoles} role={role} onEdit={editItem} onDelete={handleDelete} addToCart={addToCart}/>
        </Col>
      </Row>
    </Container>
  )
}

export default ConsolePage