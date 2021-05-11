import api from './api/index.js'
import GameTable from './components/GameTable.js'
import GameForm from './components/GameForm.js'
import Filter from './components/Filter.js'

const state = {
  items: [],
  error: null,
  filterSelected: 'No Filter'
}

const root = document.querySelector('#root')

function mapRowToGame (itemId, itemProperties) {
  const mappedGame = {}
  mappedGame.itemId = Number(itemId)
  mappedGame.title = itemProperties[0].querySelector('input').value
  mappedGame.description = itemProperties[1].querySelector('input').value
  mappedGame.esrbRating = itemProperties[2].querySelector('input').value
  mappedGame.studio = itemProperties[3].querySelector('input').value
  mappedGame.price = Number(itemProperties[4].querySelector('input').value)
  mappedGame.quantity = Number(itemProperties[5].querySelector('input').value)
  return mappedGame
}

function getAllGames () {
  api.index().then((resp) => {
    if (resp.status >= 400) {
      // Turn `errors` into a string
      const errorMsg = resp.errors.join('\n')
      throw new Error(errorMsg)
    }
    state.items = [...resp]
    render()
  }).catch((error) => {
    state.error = error

    // Error messages will render instead of the table
    render()
  })
}

function render () {
  if (state.error) {
    root.innerHTML = `<p class="text-danger">${state.error} 😞</p>
        <p class="text-info">Please refresh the page 📃.</p>
        `
  } else {
    root.innerHTML = state.items.length
      ? `${GameTable(state)}
            ${Filter(state)}
            ${GameForm()}`
      : `
            <p>There are no items to display at this time.</p>
       `

    root.querySelector('form')?.addEventListener('submit', (event) => {
      event.preventDefault()
      const newItem = { ...Object.fromEntries(new FormData(event.target)) }
      api.create(newItem).then((resp) => {
        console.log(resp)
        state.items = [...state.items, resp]
        render()
      }).catch((err) => {
        console.log(err.errorMsg)
      })
    })

    root.querySelectorAll('.delete-btn').forEach((button) => {
      button.addEventListener('click', function () {
        const idToDelete = this.closest('tr').querySelector('td').innerText

        api.delete(idToDelete).then(() => {
          state.items = state.items.filter(({ itemId }) => itemId !== Number(idToDelete))
          render()
        }).catch((err) => {
          console.log(err.errorMsg)
        })
      })
    })

    root.querySelectorAll('.update-btn').forEach((button) => {
      button.addEventListener('click', function () {
        const itemId = this.closest('tr').querySelector('td').innerText
        const itemToUpdate = this.closest('tr').querySelectorAll('.updatable')

        if (this.innerText === 'Save') {
          const newGame = mapRowToGame(itemId, itemToUpdate)
          // console.log(newGame)
          api.update(newGame).then(() => {
            console.log('updated')
          })
        }
        itemToUpdate.forEach((td) => {
          if (td.innerText) {
            const tdValue = td.innerText
            td.innerText = ''
            td.innerHTML = `<input value="${tdValue}" data-key="${tdValue}" />`
            this.innerText = 'Save'
            this.classList.remove('btn-warning')
            this.classList.add('btn-success')
          } else {
            // const resp = await api.update()
            const tdValue = td.querySelector('input').value
            td.innerHTML = ''
            td.innerText = `${tdValue}`
            this.innerText = 'Edit'
            this.classList.remove('btn-success')
            this.classList.add('btn-warning')
          }
        })
      })
    })

    root.querySelector('.filter')?.addEventListener('click', () => {
      if (root.querySelector('#noFilter').checked) {
        getAllGames()
      } else if (root.querySelector('#titleFilter').checked) {
        const title = root.querySelector('#filterInput').value
        api.getByTitle(title).then((resp) => {
          state.items = [...resp]
          render()
        })
      } else if (root.querySelector('#studioFilter').checked) {
        const studio = root.querySelector('#filterInput').value
        api.getByStudio(studio).then((resp) => {
          state.items = [...resp]
          render()
        })
      } else if (root.querySelector('#esrbRatingFilter').checked) {
        const rating = root.querySelector('#filterInput').value
        api.getByRating(rating).then((resp) => {
          state.items = [...resp]
          render()
        })
      }
    })

    root.querySelector('#noFilter')?.addEventListener('click', function () {
      this.closest('.btn-group').querySelectorAll('.btn').forEach((label) => {
        label.classList.remove('active')
      })
      this.classList.add('active')
      state.filterSelected = 'No Filter'
      render()
    })

    root.querySelector('#titleFilter')?.addEventListener('click', function () {
      this.closest('.btn-group').querySelectorAll('.btn').forEach((label) => {
        label.classList.remove('active')
      })
      this.classList.add('active')
      state.filterSelected = 'Title'
      render()
    })

    root.querySelector('#studioFilter')?.addEventListener('click', function () {
      this.closest('.btn-group').querySelectorAll('.btn').forEach((label) => {
        label.classList.remove('active')
      })
      this.classList.add('active')
      state.filterSelected = 'Studio'
      render()
    })

    root.querySelector('#esrbRatingFilter')?.addEventListener('click', function () {
      this.closest('.btn-group').querySelectorAll('.btn').forEach((label) => {
        label.classList.remove('active')
      })
      this.classList.add('active')
      state.filterSelected = 'Rating'
      render()
    })
  }
}

(async () => {
  getAllGames()
})()

render()
