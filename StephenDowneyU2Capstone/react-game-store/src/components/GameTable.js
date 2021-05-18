import { Table } from 'react-bootstrap'
import GameRow from './GameRow'

function GameTable({ allGames, role, onEdit, onDelete }){
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
        {allGames.map(game => <GameRow game={game} role={role} onEdit={onEdit} onDelete={onDelete} />)}
      </tbody>
    </Table>
  )
}

export default GameTable