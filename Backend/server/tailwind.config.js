/** @type {import('tailwindcss').Config} */
module.exports = {
  theme: {
    extend: {
      fontFamily: {
        roboto: ['roboto', 'sans-serif'],
      },colors: {
        heading1: '#575757',
        heading2: '#005D90'
      }
    },
  },
  content: ["./src/main/resources/**/*.{html,js}"],
  theme: {
    extend: {},
  },
  plugins: [],
}


