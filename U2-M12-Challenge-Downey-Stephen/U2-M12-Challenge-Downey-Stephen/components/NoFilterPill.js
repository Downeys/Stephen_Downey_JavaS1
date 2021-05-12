const NoFilterPill = (filterSelected) => {
  return `<label class=${filterSelected === 'noFilter' ? `"btn btn-secondary active btn-pill"` : `"btn btn-secondary btn-pill"`}>
                <input type="radio" name="noFilter" id="noFilter" value='noFilter' autocomplete="off" ${filterSelected === 'noFilter' ? `checked` : ``}> No Filter
            </label>`
}

export default NoFilterPill
