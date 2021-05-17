import {Button, Col, Card} from 'react-bootstrap'


function EncounterCard({ encounter, notify }){

    function handleDelete() {
        fetch(`http://localhost:8080/api/encounter/${encounter.id}`, { method: "DELETE" })
            .then(() => notify({ action: "delete", encounter }))
            .catch(error => notify({ action: "delete", error }));
    }

    return (
        <Col>
            <Card style={{ width: '18rem' }}>
                <Card.Img variant="top" src={encounter.imageUrl} />
                <Card.Body>
                    <Card.Title>{encounter.brief}</Card.Title>
                    <Card.Subtitle>{encounter.dateTime}</Card.Subtitle>
                    Investigated by: {encounter.investigators.map((investigator, index) => <Card.Link key={investigator.id + index} href="#">{investigator.firstName + ' ' + investigator.lastName}</Card.Link>)}
                    <Card.Text>
                        {encounter.details}
                    </Card.Text>
                    <Button variant="primary" onClick={() => notify({ action: "edit-form", encounter })}>Edit</Button>
                    <Button variant="danger" onClick={handleDelete}>Delete</Button>
                </Card.Body>
            </Card>
        </Col>
    )
}

export default EncounterCard