import { render, screen } from '@testing-library/react'
import  EncounterPage from './EncounterPage'

test('renders "EncounterPage" with a button and no encounter cards', () => {
    const encounterList = []
    const notify = jest.fn()
    const addFunction = jest.fn()

    render(<EncounterPage encounterList={encounterList} notify={notify} addFunction={addFunction}/>)
    const button = screen.getByText(/Log new encounter/i)

    expect(button).toBeInTheDocument()
})