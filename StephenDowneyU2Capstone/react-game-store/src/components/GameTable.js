import { useEffect, useState } from 'react'
import { Table } from 'react-bootstrap'
import GameRow from './GameRow'

function GameTable({ displayedGames: initialDisplayedGames, role, onEdit, onDelete, addToCart }){

  const [displayedGames, setDisplayedGames] = useState(initialDisplayedGames)

  useEffect(() => {
    setDisplayedGames(initialDisplayedGames)
  }, [initialDisplayedGames])

  return (
    <Table responsive>
      <thead>
        <tr>
          <th>Title</th>
          <th>Description</th>
          <th>ESRB Rating</th>
          <th>Studio</th>
          <th>Quantity</th>
          <th>Price</th>
        </tr>
      </thead>
      <tbody>
        {displayedGames.map(game => <GameRow game={game} role={role} onEdit={onEdit} onDelete={onDelete} addToCart={addToCart}/>)}
      </tbody>
    </Table>
  )
}

export default GameTable