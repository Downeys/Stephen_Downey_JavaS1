import DropdownItem from './DropdownItem.js'

const ItemSelector = ({ itemTypes }) => {
  return `<select class="form-select" aria-label="Default select example">
        <option selected>Select an item category</option>
        ${itemTypes.map(type => DropdownItem(type))}
    </select>`
}

export default ItemSelector
