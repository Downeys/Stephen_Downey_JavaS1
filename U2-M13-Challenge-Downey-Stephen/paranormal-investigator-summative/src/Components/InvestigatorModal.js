import { useState } from 'react'
import { Modal, Button, Form, Col, Row } from 'react-bootstrap'

function InvestigatorModal({onSubmit, investigator: initialInvestigator}) {
    const [show, setShow] = useState(false);
    const [investigator, setInvestigator] = useState(initialInvestigator)
    const isAdd = initialInvestigator.id === 0
    console.log(isAdd)
  
    const handleClose = () => setShow(false);
    const handleShow = () => {
      setInvestigator(initialInvestigator)
      setShow(true);
    }
    function handleChange(evt){
      let value = evt.target.value;
      const clone = { ...investigator }
      clone[evt.target.name] = value
      setInvestigator(clone);
    }

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

      setShow(false);

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
  
    return (
      <>
        <Button variant="primary" onClick={handleShow}>
          Add New Investigator
        </Button>
  
        <Modal show={show} onHide={handleClose}>
          <Modal.Header>
            <Modal.Title>Investigator</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <Form onSubmit={handleSubmit}>
              <Row>
                  <Col md={6}>
                      <Form.Group controlId='firstNameInput'>
                          <Form.Label>First Name</Form.Label>
                          <Form.Control onChange={handleChange} name='firstName' value={investigator.firstName} placeholder="First name" />
                      </Form.Group>
                  </Col>
                  <Col md={6}>
                      <Form.Group controlId='lastNameInput'>
                        <Form.Label>Last Name</Form.Label>
                          <Form.Control onChange={handleChange} name='lastName' value={investigator.lastName} placeholder="Last name" />
                      </Form.Group>
                  </Col>
              </Row>
            </Form >
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={handleClose}>
              Close
            </Button>
            <Button variant="primary" onClick={handleSubmit}>
              Save Changes
            </Button>
          </Modal.Footer>
        </Modal>
      </>
    );
}

export default InvestigatorModal