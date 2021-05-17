import { render, screen } from '@testing-library/react'
import InvestigatorPage from './InvestigatorPage'

test('should render investigator page', () => {
    const investigator = {
        id: 1,
        firstName: 'Harry',
        lastName: 'Woodworth'
    }

    const investigatorList = [investigator]

    const notify = jest.fn()

    render(<InvestigatorPage investigatorList={investigatorList} notify={notify} />)
    const pageElement = screen.getByText(/Add new investigator/i)

    expect(pageElement).toBeInTheDocument()
})