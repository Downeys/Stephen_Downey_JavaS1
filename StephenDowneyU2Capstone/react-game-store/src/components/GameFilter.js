import { useEffect, useState } from 'react'
import { Button, Col, Container, Dropdown, DropdownButton, Form, Row, ToggleButtonGroup } from 'react-bootstrap'
import FilterOption from './FilterOption'

function GameFilter({ games, onFilter }) {
  const [titles, setTitles] = useState([])
  const [studios, setStudios] = useState([])
  const [ratings, setRatings] = useState([])
  const [filter, setFilter] = useState('noFilter')
  const [titleForSearch, setTitleForSearch] = useState('')

  useEffect(() => {
    const titleSet = new Set()
    const studioSet = new Set()
    const ratingSet = new Set()

    games.forEach(game => {
      titleSet.add(game.title)
      studioSet.add(game.studio)
      ratingSet.add(game.esrbRating)
    })

    setTitles([...titleSet])
    setStudios([...studioSet])
    setRatings([...ratingSet])
  }, [games])

  function handleDropdownClicks(evt){
    setFilter(evt.target.name)
    if(evt.target.name === 'noFilter'){
      onFilter(games)
    }
  }

  function handleFilterSelection(target){
    let newGameList = [...games]
    newGameList = newGameList.filter(game => game[filter] === target)
    onFilter(newGameList)
  }

  function searchTitle(evt){
    evt.preventDefault()
    handleFilterSelection(titleForSearch)
  }

  function handleChange(evt){
    setTitleForSearch(evt.target.value)
  }

  return (
    <Container className='filterPanel'>
      <Row>
        <Col lg='2'>
          <DropdownButton id="dropdown-item-button" title="filter by">
          <Dropdown.Item as="button" onClick={handleDropdownClicks} name='noFilter'>No filter</Dropdown.Item>
          <Dropdown.Item as="button" onClick={handleDropdownClicks} name='title'>Title</Dropdown.Item>
          <Dropdown.Item as="button" onClick={handleDropdownClicks} name='studio'>Studio</Dropdown.Item>
          <Dropdown.Item as="button" onClick={handleDropdownClicks} name='esrbRating'>ESRB Rating</Dropdown.Item>
          </DropdownButton>
        </Col>
        <Col>
          {filter === 'title' &&
            <Form onSubmit={searchTitle}>
              <Row>
                <Col lg='6'>
                  <Form.Control type='text' placeholder='title' name='title' onChange={handleChange}></Form.Control>
                </Col>
                <Col>
                  <Button type='submit'>Search</Button>
                </Col>
              </Row>
            </Form>
          }
          {filter === 'studio' &&
            <ToggleButtonGroup name='studioOptions'>
              {studios.map(studio => <FilterOption option={studio} selectFilter={handleFilterSelection} />)}
            </ToggleButtonGroup>
          }
          {filter === 'esrbRating' &&
            <ToggleButtonGroup name='ratingOptions'>
              {ratings.map(rating => <FilterOption option={rating} selectFilter={handleFilterSelection} />)}
            </ToggleButtonGroup>
          }
        </Col>
      </Row>
    </Container>
  )
}

export default GameFilter