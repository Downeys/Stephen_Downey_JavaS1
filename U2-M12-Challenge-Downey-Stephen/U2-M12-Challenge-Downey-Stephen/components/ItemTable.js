import TableHeader from './TableHeader.js'
import TableCell from './TableCell.js'
import UpdateableCells from './UpdateableCells.js'

const renderGameRows = (items, keys) => {
  const rowKeys = keys.filter(key => key !== 'itemId')
  return items.map(item => {
    return `<tr>
    ${TableCell(item.itemId)}
    ${UpdateableCells(item)}
    <td><button type="button" class="btn btn-warning btn-sm update-btn">Edit</button></td>
    <td><button type="button" class="btn btn-danger btn-sm delete-btn">Delete</button></td>
  </tr>`
  })
}

const ItemTable = ({ items, itemSelected }) => {
  const itemKeys = Object.keys(items[0])
  const filteredKeys = itemKeys.filter(key => key !== 'itemType')
  return `<h2>${itemSelected}</h2>
    <table id="table">
        <thead>
            ${filteredKeys.map(key => TableHeader(key))}
        </thead>
        <tbody>
            ${renderGameRows(items, filteredKeys)}
        </tbody>
    </table>`
}

export default ItemTable
