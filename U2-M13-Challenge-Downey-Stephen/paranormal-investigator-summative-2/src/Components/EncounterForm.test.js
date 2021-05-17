import { render, screen } from '@testing-library/react'
import EncounterForm from './EncounterForm'

test('should render EncounterForm', () => {
    const emptyInvestigator = {
        id: 0,
        firstName: '',
        lastName: ''
    }

    const now = new Date();
    const year = now.getFullYear();
    const month = new Intl.DateTimeFormat("en-US", { month: "short" }).format(now);
    const day = new Intl.DateTimeFormat("en-US", { day: "2-digit" }).format(now);
    const time = new Intl.DateTimeFormat("en-US", { hour: "2-digit", minute: "2-digit" })
        .format(now).replace(" ", "");

    const emptyEncounter = {
        id: 0,
        brief: '',
        details: '',
        dateTime: `${day}-${month}-${year} ${time}`,
        imageUrl: '',
        investigators: [emptyInvestigator]
    }

    const allInvestigators = [emptyInvestigator]
    const notify = jest.fn()

    render(<EncounterForm encounter={emptyEncounter} investigatorList={allInvestigators} notify={notify} />)
    const formElement = screen.getByText(/brief/i)
    
    expect(formElement).toBeInTheDocument()
})