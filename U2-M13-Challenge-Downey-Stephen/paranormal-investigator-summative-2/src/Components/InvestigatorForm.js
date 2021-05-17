import { Form, Col, Button, Row } from 'react-bootstrap'
import {useState} from 'react'

function InvestigatorForm({onSubmit, onCancel, investigator: initialInvestigator}){

    const [investigator, setInvestigator] = useState(initialInvestigator)
    const isAdd = initialInvestigator.id === 0

    function handleSubmit(evt){
        evt.preventDefault()

        const url = isAdd ? "http://localhost:8080/api/investigator" : `http://localhost:8080/api/investigator/${investigator.id}`;
        const method = isAdd ? "POST" : "PUT";
        const expectedStatus = isAdd ? 201 : 204;

        const init = {
            method,
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
            },
            body: JSON.stringify(investigator)
        };

        fetch(url, init)
            .then(response => {
                if (response.status === expectedStatus) {
                    if (isAdd) {
                        return response.json();
                    } else {
                        return investigator;
                    }
                }
                return Promise.reject(`Didn't receive expected status: ${expectedStatus}`);
            })
            .then(result => onSubmit(result))
            // .catch(error => notify({ error }));
        }

    function handleChange(evt) {
        let value = evt.target.value;
        const clone = { ...investigator }
        clone[evt.target.name] = value
        setInvestigator(clone);
    }

    return (
        <Form onSubmit={handleSubmit}>
            <Row>
                <Col md={3}>
                    <Form.Group controlId='firstNameInput'>
                        <Form.Control onChange={handleChange} name='firstName' value={investigator.firstName} placeholder="First name" />
                    </Form.Group>
                </Col>
                <Col md={3}>
                    <Form.Group controlId='lastNameInput'>
                        <Form.Control onChange={handleChange} name='lastName' value={investigator.lastName} placeholder="Last name" />
                    </Form.Group>
                </Col>
                <Col md={1}>
                    <Button onClick={onCancel}>Cancel</Button>
                </Col>
                <Col md={1}>
                    <Button type='submit'>Add</Button>                    
                </Col>
            </Row>
        </Form>
    )
}

export default InvestigatorForm