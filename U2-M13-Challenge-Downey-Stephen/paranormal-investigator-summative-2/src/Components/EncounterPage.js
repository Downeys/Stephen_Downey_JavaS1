import { Row, Button, Container } from 'react-bootstrap'
import EncounterCard from './EncounterCard'

function EncounterPage({ encounterList, addfunction, notify }) {

    return (
        <Container>
            <Button onClick={addfunction}>Log New Encounter</Button>
            <Row md={3} lg={4}>
                {encounterList.map(enc => <EncounterCard key={enc.id} encounter={enc} notify={notify}/>)}
            </Row>
        </Container>
    )
}

export default EncounterPage