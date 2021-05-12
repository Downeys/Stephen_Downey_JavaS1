import api from './api/index.js'
import ItemTable from './components/ItemTable.js'
import ItemForm from './components/ItemForm.js'
import Filter from './components/Filter.js'
import ItemSelector from './components/ItemSelector.js'

const state = {
  items: [],
  error: null,
  filterSelected: 'noFilter',
  filters: {
    Games: ['title', 'studio', 'esrbRating'],
    TShirts: ['size', 'color'],
    Consoles: ['manufacturer']
  },
  itemTypes: ['Games', 'TShirts', 'Consoles'],
  itemSelected: 'Games',
  endpoints: {
    Games: 'game',
    TShirts: 'tshirt',
    Consoles: 'console'
  },
  url: 'http://localhost:8080/game'
}

const root = document.querySelector('#root')

function mapRowToItem (itemId, itemProperties) {
  const itemKeys = Object.keys(state.items[0]).filter(key => key !== 'itemId' && key !== 'itemType')
  const mappedItem = {}
  mappedItem.itemId = Number(itemId)
  for (let i = 0; i < itemKeys.length; i++) {
    mappedItem[itemKeys[i]] = itemProperties[i].querySelector('input').value
  }
  return mappedItem
}

function getAllItems () {
  api.index(state.url).then((resp) => {
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
        <p class="text-info">Please refresh the page 📃.</p>`
  } else {
    root.innerHTML = state.items.length
      ? `   ${ItemSelector(state)}
            ${ItemTable(state)}
            ${Filter(state)}
            ${ItemForm(state)}`
      : `<p>There are no items to display at this time.</p>`

    root.querySelector('form')?.addEventListener('submit', (event) => {
      event.preventDefault()
      const newItem = { ...Object.fromEntries(new FormData(event.target)) }

      api.create(newItem, state.url).then((resp) => {
        state.items = [...state.items, resp]
        render()
      }).catch((err) => {
        console.log(err.errorMsg)
      })
    })

    root.querySelectorAll('.delete-btn').forEach((button) => {
      button.addEventListener('click', function () {
        const idToDelete = this.closest('tr').querySelector('td').innerText

        api.delete(idToDelete, state.url).then(() => {
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
          const newGame = mapRowToItem(itemId, itemToUpdate)
          api.update(newGame, state.url)
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
        getAllItems()
      } else if (root.querySelector('#title')?.checked) {
        const title = root.querySelector('#filterInput').value
        api.getByTitle(title).then((resp) => {
          state.items = [...resp]
          render()
        })
      } else if (root.querySelector('#studio')?.checked) {
        const studio = root.querySelector('#filterInput').value
        api.getByStudio(studio).then((resp) => {
          state.items = [...resp]
          render()
        })
      } else if (root.querySelector('#rating')?.checked) {
        const rating = root.querySelector('#filterInput').value
        api.getByRating(rating).then((resp) => {
          state.items = [...resp]
          render()
        })
      } else if (root.querySelector('#color')?.checked) {
        const color = root.querySelector('#filterInput').value
        api.getByColor(color).then((resp) => {
          state.items = [...resp]
          render()
        })
      } else if (root.querySelector('#size')?.checked) {
        const size = root.querySelector('#filterInput').value
        api.getBySize(size).then((resp) => {
          state.items = [...resp]
          render()
        })
      } else if (root.querySelector('#manufacturer')?.checked) {
        const manufacturer = root.querySelector('#filterInput').value
        api.getByManufacturer(manufacturer).then((resp) => {
          state.items = [...resp]
          render()
        })
      }
    })

    root.querySelectorAll('.btn-pill').forEach((btn) => {
      btn.addEventListener('click', function () {
        this.closest('.btn-group').querySelectorAll('.btn').forEach((label) => {
          label.classList.remove('active')
        })
        this.classList.add('active')
        state.filterSelected = this.querySelector('input').value
        render()
      })
    })

    root.querySelector('.form-select')?.addEventListener('change', function () {
      state.itemSelected = this.value
      state.url = `http://localhost:8080/${state.endpoints[state.itemSelected]}`
      state.filterSelected = 'noFilter'
      getAllItems()
    })
  }
}

(async () => {
  getAllItems()
})()

render()
