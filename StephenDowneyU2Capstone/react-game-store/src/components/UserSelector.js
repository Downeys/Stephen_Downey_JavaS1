import { Button } from 'react-bootstrap'

function UserSelector({ onSelectUser  }){

  function handleSelect(evt){
    const role = evt.target.id
    onSelectUser(role)
  }

  return(
    <>
      <h1>Select a User</h1>
      <Button id='customer' onClick={handleSelect}>Customer</Button>
      <Button id='admin' onClick={handleSelect}>Administrator</Button>
    </>
  )
}

export default UserSelector