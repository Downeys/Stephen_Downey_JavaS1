const renderGameRows = (items) => {
  return (items.map(({ itemId, title, description, esrbRating, studio, price, quantity }) => {
    return `<tr>
                <td class="col-lg-1">${itemId}</td>
                <td class="col-lg-2 updatable">${title}</td>
                <td class="col-lg-2 updatable">${description}</td>
                <td class="col-lg-2 updatable">${esrbRating}</td>
                <td class="col-lg-2 updatable">${studio}</td>
                <td class="col-lg-1 updatable">${price}</td>
                <td class="col-lg-1 updatable">${quantity}</td>
                <td><button type="button" class="btn btn-warning btn-sm update-btn">Edit</button></td>
                <td><button type="button" class="btn btn-danger btn-sm delete-btn">Delete</button></td>
            </tr>`
  })
  )
}

const GameTable = ({ items }) => {
  return `<table id="table">
        <thead>
            <th scope ="col-md-2">Id</th>
            <th scope ="col-md-2">Title</th>
            <th scope ="col-md-2">Description</th>
            <th scope ="col-md-2">ESRB Rating</th>
            <th scope ="col-md-2">Studio</th>
            <th scope ="col-md-2">Price</th>
            <th scope ="col-md-2">Quantity</th>
        </thead>
        <tbody>
            ${renderGameRows(items)}
        </tbody>
    </table>`
}

export default GameTable
