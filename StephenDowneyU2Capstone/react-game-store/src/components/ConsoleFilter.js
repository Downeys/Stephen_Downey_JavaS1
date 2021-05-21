import { useEffect, useState } from 'react'
import { Col, Container, Dropdown, DropdownButton, Row, ToggleButtonGroup } from 'react-bootstrap'
import FilterOption from './FilterOption'

function ConsoleFilter({ consoles, onFilter }){
  const [manufacturers, setManufacturers] = useState([])
  const [filter, setFilter] = useState('noFilter')

  useEffect(() => {
    const manufacturerSet = new Set()

    consoles.forEach(console => {
      manufacturerSet.add(console.manufacturer)
    })

    setManufacturers([...manufacturerSet])
  }, [consoles])

  function handleDropdownClicks(evt){
    setFilter(evt.target.name)
    if(evt.target.name === 'noFilter'){
      onFilter(consoles)
    }
  }

  function handleFilterSelection(target){
    let newConsoles = [...consoles]
    newConsoles = newConsoles.filter(console => console[filter] === target)
    onFilter(newConsoles)
  }

  return (
    <Container className='filterPanel'>
      <Row>
        <Col lg='2'>
          <DropdownButton id="dropdown-item-button" title="filter by">
          <Dropdown.Item as="button" onClick={handleDropdownClicks} name='noFilter'>No filter</Dropdown.Item>
          <Dropdown.Item as="button" onClick={handleDropdownClicks} name='manufacturer'>Manufacturer</Dropdown.Item>
          </DropdownButton>
        </Col>
        <Col>
          {filter === 'manufacturer' &&
            <ToggleButtonGroup name='manufacturer'>
              {manufacturers.map(manufacturer => <FilterOption option={manufacturer} selectFilter={handleFilterSelection} />)}
            </ToggleButtonGroup>
          }
        </Col>
      </Row>
    </Container>
  )
}

export default ConsoleFilter