import { Container, Row, Button, Col } from 'react-bootstrap'
import { useState } from 'react'
import InvestigatorCard from './InvestigatorCard'
import InvestigatorForm from './InvestigatorForm'
import InvestigatorModal from './InvestigatorModal'

function InvestigatorPage({ investigatorList, notify}) {

    const emptyInvestigator = {
        id: 0,
        firstName: '',
        lastName: ''
    }

    const [showForm, setShowForm] = useState(false)
    const [scopedInvestigator, setScopedInvestigator] = useState(emptyInvestigator)

    function handleAddButton(){
        setScopedInvestigator(emptyInvestigator)
        setShowForm(true)
    }

    function handleSubmit(newInvestigator){
        setShowForm(false)
        notify({action: 'add-investigator', investigator: newInvestigator})
    }

    function handleCancel(){
        setShowForm(false)
        notify({action: 'cancel'})
    }

    return (
        <Container>
            <Row>
                <Col md={3}>
                    {/* <Button onClick={handleAddButton}>Add New Investigator</Button> */}
                    <InvestigatorModal investigator={emptyInvestigator} onSubmit={handleSubmit}/>
                </Col>
                <Col md={9}>
                    {showForm && <InvestigatorForm onSubmit={handleSubmit} onCancel={handleCancel} investigator={scopedInvestigator}/>}
                </Col>
            </Row>
            <Row md={3} lg={4}>
                {investigatorList.map(inv => <InvestigatorCard key={inv.id} investigator={inv} />)}
            </Row>
        </Container>
    )
}

export default InvestigatorPage