import { render, screen } from '@testing-library/react'
import EncounterCard from './EncounterCard'

test('should render an Encounter Card', () => {
    const now = new Date();
    const year = now.getFullYear();
    const month = new Intl.DateTimeFormat("en-US", { month: "short" }).format(now);
    const day = new Intl.DateTimeFormat("en-US", { day: "2-digit" }).format(now);
    const time = new Intl.DateTimeFormat("en-US", { hour: "2-digit", minute: "2-digit" })
        .format(now).replace(" ", "");

    const testEncounter = {
        brief: 'A bump in the night',
        details: 'It was loud and scary',
        dateTime: `${day}-${month}-${year} ${time}`,
        imageUrl: '',
        investigators: [{id: 1, firstName: 'Harry', lastName: 'Woodward'}]
    }

    const notify = jest.fn()

    render(<EncounterCard encounter={testEncounter} notify={notify} />)
    const cardElement = screen.getByText(/It was loud and scary/i)

    expect(cardElement).toBeInTheDocument()
})