import FilterPills from './FilterPills.js'
import NoFilterPill from './NoFilterPill.js'

const Filter = ({ itemSelected, filterSelected, filters, itemTypes }) => {
  return `<div>
    <h3>Filter by: </h3>
    <span class="btn-group btn-group-toggle" data-toggle="buttons">
      ${NoFilterPill(filterSelected)}
      ${FilterPills(filters[itemSelected], filterSelected)}
      <input type='text' id='filterInput' />
      <button type='button' class='filter btn btn-primary'>Filter</button>
    </span>
  </div>`
}

export default Filter
