import { render, screen } from '@testing-library/react'
import InvestigatorOption from './InvestigatorOption'

test('should render Investigator Option', () => {
    const investigator = {
        id: 1,
        firstName: 'Harry',
        lastName: 'Woodworth'
    }

    render(<InvestigatorOption investigator={investigator} />)
    const optionElement = screen.getByText(/Woodworth/i)

    expect(optionElement).toBeInTheDocument()
})