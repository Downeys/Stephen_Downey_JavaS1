import { render, screen } from '@testing-library/react'
import InvestigatorModal from './InvestigatorModal'

test('should render an investigator modal', () => {
    const emptyInvestigator = {
        id: 0,
        firstName: '',
        lastName: ''
    }

    const onSubmit = jest.fn()

    render(<InvestigatorModal onSubmit={onSubmit} investigator={emptyInvestigator} />)
    const modalElement = screen.getByText(/Investigator/i)
    
    expect(modalElement).toBeInTheDocument()
})