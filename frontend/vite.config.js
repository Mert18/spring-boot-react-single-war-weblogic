import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  base: '/dbmanager-0.0.1-SNAPSHOT/', // Match your app's context path
  build: {
    outDir: 'dist',
  },
  plugins: [react()],
})
