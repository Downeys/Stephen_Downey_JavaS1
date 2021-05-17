import { Card } from 'react-bootstrap'
import { useState, useEffect } from 'react'

function InvestigatorCard({ investigator }) {

    const [investigatorsEncounters, setInvestigatorsEncounters] = useState([])

    useEffect(() => {
        console.log(investigator)
        fetch('http://localhost:8080/api/encounter')
        .then(resp => resp.json())
        .then(result => result.filter(encounter => encounter.investigators.some(inv => inv.id === investigator.id)))
        .then(encounters => encounters.map(e => e.brief))
        .then(encounterBriefs => setInvestigatorsEncounters(encounterBriefs))
    }, [investigator])

    return (
        <Card>
            <Card.Title className='text-center'>Name: {investigator.firstName} {investigator.lastName}</Card.Title>
            <Card.Body>
                <Card.Text>
                    Encounters: {investigatorsEncounters.map(encounter => <Card.Link key={encounter} href='#'>{encounter}</Card.Link>)}
                </Card.Text>
            </Card.Body>
        </Card>
    )
}

export default InvestigatorCard