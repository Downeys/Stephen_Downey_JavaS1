const FilterPills = (filters, filterSelected) => {
  return `${filters.map(filter => {
        return `<label class=${filterSelected === filter ? `"btn btn-secondary active btn-pill"` : `"btn btn-secondary btn-pill"`}>
                <input type="radio" name="${filter}" id="${filter}" value='${filter}' autocomplete="off" ${filterSelected === filter ? `checked` : ``}> ${filter}
            </label>`
    })}`
}

export default FilterPills
