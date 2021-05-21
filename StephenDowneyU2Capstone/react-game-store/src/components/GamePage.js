import { useState } from 'react'
import { Col, Container, Row } from 'react-bootstrap'
import GameFilter from './GameFilter'
import GameModal from './GameModal'
import GameTable from './GameTable'

function GamePage({ role, addToCart, games, editItem, addItem, deleteItem }) {

  const [displayedGames, setDisplayedGames] = useState([...games])

  function handleSubmit(newGame){
    updateDisplayedGames([...displayedGames, newGame])
    newGame.itemType='game'
    addItem(newGame)
  }

  function handleDelete(gameId){
    updateDisplayedGames(displayedGames.filter(game => game.id !== gameId))
    deleteItem(gameId, 'game')
  }

  function  updateDisplayedGames(newGameList){
    setDisplayedGames([...newGameList])
  }

  return(
    <Container>
      <Row>
        <h1>Games</h1>
      </Row>
      <Row>
        <Col lg='2'>
          {role === 'admin' && <GameModal game={{ id:0 }} onSubmit={handleSubmit}/>}
        </Col>
        <Col>
          <GameFilter games={games} onFilter={updateDisplayedGames}/>
        </Col>
      </Row>
      <Row>
        <Col>
          <GameTable displayedGames={displayedGames} role={role} onEdit={editItem} onDelete={handleDelete} addToCart={addToCart} />
        </Col>
      </Row>
    </Container>
  )
}

export default GamePage