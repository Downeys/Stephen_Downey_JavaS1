import { render, screen } from '@testing-library/react'
import InvestigatorForm from './InvestigatorForm'

test('should render Investigator Form', () => {
    const emptyInvestigator = {
        id: 0,
        firstName: '',
        lastName: ''
    }

    const onSubmit = jest.fn()
    const onCancel = jest.fn()

    render(<InvestigatorForm investigator={emptyInvestigator} onSubmit={onSubmit} onCancel={onCancel}/>)
    const formElement = screen.getByText(/First Name/i)

    expect(formElement).toBeInTheDocument()
})