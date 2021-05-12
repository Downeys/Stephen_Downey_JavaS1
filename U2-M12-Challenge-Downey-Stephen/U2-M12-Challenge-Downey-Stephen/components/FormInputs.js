const FormInputs = (item) => {
  const itemProperties = Object.keys(item)
  const filteredProperties = itemProperties.filter(prop => prop !== 'itemId' && prop !== 'itemType')
  return `${filteredProperties.map(property => {
      return `<div class="mb-3">
      <label for="${property}" class="form-label">${property}</label>
      <input type="text" class="form-control" id="${property}" name="${property}">
    </div>`
  })}`
}

export default FormInputs
