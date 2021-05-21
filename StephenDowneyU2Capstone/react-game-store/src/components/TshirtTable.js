import { Table } from 'react-bootstrap'
import TshirtRow from './TshirtRow'

function TshirtTable({ displayedTshirts, role, onEdit, onDelete, addToCart }){
  return (
    <Table responsive>
      <thead>
        <tr>
          <th>Description</th>
          <th>Size</th>
          <th>Color</th>
          <th>Quantity</th>
          <th>Price</th>
        </tr>
      </thead>
      <tbody>
        {displayedTshirts.map(tshirt => <TshirtRow tshirt={tshirt} role={role} onEdit={onEdit} onDelete={onDelete} addToCart={addToCart}/>)}
      </tbody>
    </Table>
  )
}

export default TshirtTable