import { useState } from 'react'
import { Form, Button, Row, Col, Table } from 'react-bootstrap'
import InvestigatorOption from './InvestigatorOption'
import InvestigatorModal from './InvestigatorModal'

function EncounterForm({ encounter: initialEncounter, investigatorList, notify }) {

    const [encounter, setEncounter] = useState(initialEncounter)
    const isAdd = initialEncounter.id === 0

    function handleChange(evt) {
        let value = evt.target.value;
        const clone = { ...encounter }
        if (evt.target.type === "checkbox") {
            value = evt.target.checked;
        } else if (evt.target.type === "number") {
            value = parseInt(value, 10);
        } else if (evt.target.name ==='investigators') {
            if(clone.investigators.length > 0 && clone.investigators[0].id === 0){
                clone.investigators.pop()
            }
            value = JSON.parse(value)
            if(!clone.investigators.some(inv => inv.id === value.id)){
                clone[evt.target.name].push(value)
            }
        } else {
            clone[evt.target.name] = value
        }
        setEncounter(clone);
    }

    function removeInvestigator(evt){
        console.log(evt.target.id)
        encounter.investigators.forEach(inv => console.log(inv.id))
        const index = encounter.investigators.findIndex(inv => inv.id === Number(evt.target.id))
        console.log(index)
        const clone = {...encounter}
        clone.investigators.splice(index, 1)
        setEncounter(clone)
    }

    function handleSubmit(evt) {
        evt.preventDefault();
        console.log('submitting encounter')

        const url = isAdd ? "http://localhost:8080/api/encounter" : `http://localhost:8080/api/encounter/${encounter.id}`;
        const method = isAdd ? "POST" : "PUT";
        const expectedStatus = isAdd ? 201 : 204;

        const init = {
            method,
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
            },
            body: JSON.stringify(encounter)
        };

        fetch(url, init)
            .then(response => {
                if (response.status === expectedStatus) {
                    if (isAdd) {
                        return response.json();
                    } else {
                        return encounter;
                    }
                }
                return Promise.reject(`Didn't receive expected status: ${expectedStatus}`);
            })
            .then(result => notify({
                action: isAdd ? "add" : "edit",
                encounter: result
            }))
            .catch(error => notify({ error }));
    }

    function handleInvestigatorSubmit(newInvestigator){
        notify({action: 'add-investigator', investigator: newInvestigator})
    }

    return (
        <Form onSubmit={handleSubmit}>
            <Form.Group controlId="encounterForm.briefInput">
                <Form.Label>Brief</Form.Label>
                <Form.Control name='brief' type="text" placeholder='title your encounter.' value={encounter.brief} onChange={handleChange}/>
            </Form.Group>
            <Form.Group controlId="encounterForm.dateTime">
                <Form.Label>Date &amp; Time</Form.Label>
                <Form.Control name='dateTime' type="text" placeholder="date and time of encounter" value={encounter.dateTime} onChange={handleChange}/>
                <Form.Text className='text-muted'>Date format: dd/mm/yyyy hh:mma ie: 25/05/2021 11:37AM</Form.Text>
            </Form.Group>
            <Form.Group controlId="encounterForm.investigatorInput">
                <Row>
                    <Col>
                        <Form.Label>Who investigated this encounter?</Form.Label>
                        <Form.Control name='investigators' onChange={handleChange} as="select" multiple>
                            {investigatorList.map(inv => <InvestigatorOption investigator={inv}/>)}
                        </Form.Control>
                    </Col>
                    <Col>
                        <Table hover size='sm'>
                            <thead>Investigators assigned to this encounter: </thead>
                            <tbody>
                                {encounter.investigators.map(inv =><tr><td  id={inv.id} onClick={removeInvestigator}> {inv.firstName + ' ' + inv.lastName} </td></tr>)}
                            </tbody>
                        </Table>
                    </Col>
                </Row> 
            </Form.Group>
            {/* <Row>
                <Col md={3}>
                    <Button onClick={handleAddInvestigator}>Add new investigator</Button>
                </Col>
                <Col md={9}>
                    {showInvestigatorForm && <InvestigatorForm onSubmit={handleInvestigatorSubmit} onCancel={handleCancel} investigator={{ id: 0, firstName: '', lastName: ''}}/>}
                </Col>
            </Row> */}
            <InvestigatorModal onSubmit={handleInvestigatorSubmit} investigator={{ id: 0, firstName:'', lastName:'' }}/>
            <Form.Group controlId="encounterForm.detailInput">
                <Form.Label>Encounter Details</Form.Label>
                <Form.Control name='details' as="textarea" rows={3} placeholder='describe the encounter here' value={encounter.details} onChange={handleChange}/>
            </Form.Group>
            <Form.Group controlId="encounterForm.imageUrlInput">
                <Form.Label>Image Url</Form.Label>
                <Form.Control name='imageUrl' type="text" placeholder="url for your encounter image goes here" value={encounter.imageUrl} onChange={handleChange}/>
            </Form.Group>
            <Button variant='danger' onClick={() => notify({ action: "cancel" })}>Cancel</Button>
            <Button variant='success' type='submit'>Submit</Button>
        </Form>
    )
}

export default EncounterForm