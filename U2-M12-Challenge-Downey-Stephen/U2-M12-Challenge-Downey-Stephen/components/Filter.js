const Filter = ({ filterSelected }) => `
<div>
  <h3>Filter by: </h3>
  <span class="btn-group btn-group-toggle" data-toggle="buttons">
    <label class=${filterSelected === 'No Filter' ? `"btn btn-secondary active btn-pill"` : `"btn btn-secondary btn-pill"`}>
      <input type="radio" name="noFilter" id="noFilter" autocomplete="off" ${filterSelected === 'No Filter' ? `checked` : ``}> No Filter
    </label>
    <label class=${filterSelected === 'Title' ? `"btn btn-secondary active btn-pill"` : `"btn btn-secondary btn-pill"`}>
      <input type="radio" name="titleFilter" id="titleFilter" autocomplete="off" ${filterSelected === 'Title' ? `checked` : ``}> Title
    </label>
    <label class=${filterSelected === 'Studio' ? `"btn btn-secondary active btn-pill"` : `"btn btn-secondary btn-pill"`}>
      <input type="radio" name="studioFilter" id="studioFilter" autocomplete="off" ${filterSelected === 'Studio' ? `checked` : ``}> Studio
    </label>
    <label class=${filterSelected === 'Rating' ? `"btn btn-secondary active btn-pill"` : `"btn btn-secondary btn-pill"`}>
      <input type="radio" name="esrbRatingFilter" id="esrbRatingFilter" autocomplete="off" ${filterSelected === 'Rating' ? `checked` : ``}> ESRB Rating
    </label>
  </span>
  <input type='text' id='filterInput' />
  <button type='button' class='filter btn btn-primary'>Filter</button>
</div>
`
export default Filter

// <form>
//     <div class="mb-3">
//       <label for="name" class="form-label">${name}</label>
//       <input type="text" class="form-control" id="name" name="name">
//     </div>
//   <button type="button" class="filter btn btn-primary" id="${name}-filter-button">Filter</button>
// </form>
