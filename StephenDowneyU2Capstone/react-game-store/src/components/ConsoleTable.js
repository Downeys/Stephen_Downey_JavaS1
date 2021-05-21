import { Table } from 'react-bootstrap'
import ConsoleRow from './ConsoleRow'

function ConsoleTable({ displayedConsoles, role, onEdit, onDelete, addToCart }){
  return (
    <Table responsive>
      <thead>
        <tr>
          <th>Model</th>
          <th>Manufacturer</th>
          <th>Memory Amount</th>
          <th>Processor</th>
          <th>Quantity</th>
          <th>Price</th>
        </tr>
      </thead>
      <tbody>
        {displayedConsoles.map(console => <ConsoleRow console={console} role={role} onEdit={onEdit} onDelete={onDelete} addToCart={addToCart}/>)}
      </tbody>
    </Table>
  )
}

export default ConsoleTable