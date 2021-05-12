/**
 * Create an API for interacting with our designated endpoint.
 * @param {string} endpoint
 * @returns {Object}
 */

const api = {
  async index (endpoint) {
    const res = await fetch(endpoint)
    return res.json()
  },
  async getById (id, endpoint = 'http://localhost:8080/game') {
    const res = await fetch(`${endpoint}/${id}`)
    return res.json()
  },
  async getByTitle (title, endpoint = 'http://localhost:8080/game') {
    const res = await fetch(`${endpoint}?title=${title}`)
    return res.json()
  },
  async getByStudio (studio, endpoint = 'http://localhost:8080/game') {
    const res = await fetch(`${endpoint}?studio=${studio}`)
    return res.json()
  },
  async getByRating (rating, endpoint = 'http://localhost:8080/game') {
    const res = await fetch(`${endpoint}?rating=${rating}`)
    return res.json()
  },
  async getByColor (color, endpoint = 'http://localhost:8080/tshirt') {
    const res = await fetch(`${endpoint}?color=${color}`)
    return res.json()
  },
  async getBySize (size, endpoint = 'http://localhost:8080/tshirt') {
    const res = await fetch(`${endpoint}?size=${size}`)
    return res.json()
  },
  async getByManufacturer (manufacturer, endpoint = 'http://localhost:8080/console') {
    const res = await fetch(`${endpoint}?manufacturer=${manufacturer}`)
    return res.json()
  },
  async create (newGame, endpoint = 'http://localhost:8080/game') {
    const response = await fetch(endpoint, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(newGame)
    })
    return response.json()
  },
  async delete (id, endpoint = 'http://localhost:8080/game') {
    const response = await fetch(`${endpoint}/${id}`, {
      method: 'DELETE'
    })
    return response
  },
  async update (newGame, endpoint = 'http://localhost:8080/game') {
    const response = await fetch(endpoint, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(newGame)
    })
    return response
  }
}

export default api
