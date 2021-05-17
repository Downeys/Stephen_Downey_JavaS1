import { render, screen } from '@testing-library/react'
import Main from './Main'

test('should render main', () => {
    render(<Main />)
    const navElement = screen.getByText(/encounters/i)

    expect(navElement).toBeInTheDocument()
})