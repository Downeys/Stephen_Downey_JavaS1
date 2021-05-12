const UpdateableCells = (item) => {
  const properties = []
  for (const property in item) {
    if (property !== 'itemId' && property !== 'itemType') {
      properties.push(property)
    }
  }
  return properties.map(property => `<td class="col-lg-2 updatable ${property}">${item[property]}</td>`)
}

export default UpdateableCells
