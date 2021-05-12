import FormInputs from './FormInputs.js'

const ItemForm = ({ items }) => {
  return `<h3>Add a new game to inventory:</h3>
  <form>
  ${FormInputs(items[0])}
  <button type="submit" class="btn btn-primary">Add Item</button>
  </form>`
}

export default ItemForm
