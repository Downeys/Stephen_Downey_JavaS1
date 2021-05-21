import { useEffect, useState } from 'react'
import { Col, Container, Dropdown, DropdownButton, Row, ToggleButtonGroup } from 'react-bootstrap'
import FilterOption from './FilterOption'

function TshirtFilter({ tshirts, onFilter }){
  const [sizes, setSizes] = useState([])
  const [colors, setColors] = useState([])
  const [filter, setFilter] = useState('noFilter')

  useEffect(() => {
    const sizeSet = new Set()
    const colorSet = new Set()

    tshirts.forEach(tshirt => {
      sizeSet.add(tshirt.size)
      colorSet.add(tshirt.color)
    })

    setSizes([...sizeSet])
    setColors([...colorSet])
  }, [tshirts])

  function handleDropdownClicks(evt){
    setFilter(evt.target.name)
    if(evt.target.name === 'noFilter'){
      onFilter(tshirts)
    }
  }

  function handleFilterSelection(target){
    let newTshirtList = [...tshirts]
    newTshirtList = newTshirtList.filter(tshirt => tshirt[filter] === target)
    onFilter(newTshirtList)
  }

  return (
    <Container className='filterPanel'>
      <Row>
        <Col lg='2'>
          <DropdownButton id="dropdown-item-button" title="filter by">
          <Dropdown.Item as="button" onClick={handleDropdownClicks} name='noFilter'>No filter</Dropdown.Item>
          <Dropdown.Item as="button" onClick={handleDropdownClicks} name='size'>Size</Dropdown.Item>
          <Dropdown.Item as="button" onClick={handleDropdownClicks} name='color'>Color</Dropdown.Item>
          </DropdownButton>
        </Col>
        <Col>
          {filter === 'size' &&
            <ToggleButtonGroup name='sizeOptions'>
              {sizes.map(size => <FilterOption option={size} selectFilter={handleFilterSelection} />)}
            </ToggleButtonGroup>
          }
          {filter === 'color' &&
            <ToggleButtonGroup name='colorOptions'>
              {colors.map(color => <FilterOption option={color} selectFilter={handleFilterSelection} />)}
            </ToggleButtonGroup>
          }
        </Col>
      </Row>
    </Container>
  )
}

export default TshirtFilter