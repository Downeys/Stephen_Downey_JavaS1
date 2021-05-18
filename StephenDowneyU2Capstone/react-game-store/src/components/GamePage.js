import { useEffect, useState } from 'react'
import GameModal from './GameModal'
import GameTable from './GameTable'

function GamePage({ role }) {

  const [allGames, setAllGames] = useState([])

  useEffect(() => {
    fetch('http://localhost:8080/game')
    .then(resp => resp.json())
    .then(result => setAllGames(result))
    .catch(console.log)
  }, [])

  function handleSubmit(game){
    console.log(game)
    setAllGames([...allGames, game])
    console.log(allGames)
  }

  function handleDelete(gameId){
    setAllGames(allGames.filter(game => game.id !== gameId))
  }

  return(
    <>
      <h1>Games</h1>
      {role === 'admin' && <GameModal game={{ id:0 }} onSubmit={handleSubmit}/>}
      <GameTable allGames={allGames} role={role} onEdit={handleSubmit} onDelete={handleDelete}/>
    </>
  )
}

export default GamePage