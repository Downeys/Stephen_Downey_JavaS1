import { render, screen } from '@testing-library/react'
import InvestigatorCard from './InvestigatorCard'

test('should render InvestigatorCard', () => {
    const emptyInvestigator = {
        id: 0,
        firstName: '',
        lastName: ''
    }

    render(<InvestigatorCard investigator={emptyInvestigator} />)
    const cardElement = screen.getByText(/Name/i)

    expect(cardElement).toBeInTheDocument()
})