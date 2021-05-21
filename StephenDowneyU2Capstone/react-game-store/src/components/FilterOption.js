import { ToggleButton } from 'react-bootstrap'

function FilterOption({ option, selectFilter }) {

  function handleClick(evt){
    evt.preventDefault()
    // console.log(evt.target.name)
    selectFilter(evt.target.id)
  }

  return(
    <ToggleButton type='radio' id={option} onClick={handleClick}>{option}</ToggleButton>
  )
}

export default FilterOption