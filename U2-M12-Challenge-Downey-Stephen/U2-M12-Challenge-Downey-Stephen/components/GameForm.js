const Form = () => `<h3>Add a new game to inventory:</h3>
<form>
    <div class="mb-3">
      <label for="title" class="form-label">Title</label>
      <input type="text" class="form-control" id="title" name="title">
    </div>
  <div class="mb-3">
    <label for="description" class="form-label">Description</label>
    <input type="text" class="form-control" id="description" name="description">
  </div>
  <div class="mb-3">
    <label for="studio" class="form-label">Studio</label>
    <input type="text" class="form-control" id="studio" name="studio">
  </div>
  <div class="mb-3">
      <label for="esrbRating" class="form-label">ESRB Rating</label>
      <input type="text" class="form-control" id="esrbRating" name="esrbRating">
    </div>
  <div class="mb-3">
    <label for="price" class="form-label">Price</label>
    <input type="text" class="form-control" id="price" name="price">
  </div>
  <div class="mb-3">
    <label for="quantity" class="form-label">Quantity</label>
    <input type="text" class="form-control" id="quantity" name="quantity">
  </div>
  <button type="submit" class="btn btn-primary">Add Game</button>
</form>`

export default Form
