# AI Custom Loader - Android App

An innovative Android application that generates custom loading bars using AI (Gemini 3.5 Flash). Users can describe what kind of loading bar they want, and the app generates it in real-time.

## Features

✨ **AI-Powered Generation**
- Uses Google Gemini 3.5 Flash API to generate custom loading bars
- Natural language prompts (e.g., "Neon futuristic spinner with purple gradient")

🎨 **Customization**
- Control animation speed (500ms - 3000ms)
- Set primary colors
- Real-time preview

💾 **Save & Export**
- Save favorite loading bars to Firestore
- Export animations as JSON
- View all saved loaders

⚡ **Performance**
- Fast, responsive UI with Jetpack Compose
- Lottie animations for smooth playback
- Efficient API calls with Retrofit

## Tech Stack

### Frontend (Android)
- Kotlin with Jetpack Compose
- Retrofit for API calls
- Lottie for animations
- Firebase Firestore
- Coroutines

### Backend
- Node.js + Express
- Gemini 3.5 Flash API
- Firebase Admin SDK

## Quick Start

### Android Setup
1. Clone: `git clone https://github.com/sravan19970104/AI-Custom-loader.git`
2. Update backend URL in `ApiClient.kt`
3. Build: `./gradlew build`

### Backend Setup
1. Install: `cd backend && npm install`
2. Configure: `cp .env.example .env`
3. Add credentials (Gemini API key, Firebase key)
4. Start: `npm start`

## API Endpoints

- `POST /api/generate-loader` - Generate loading bar
- `POST /api/save-loader` - Save loader
- `GET /api/loaders/:uid` - Get saved loaders
- `DELETE /api/loaders/:uid/:loaderId` - Delete loader
- `GET /api/health` - Health check

## Project Structure

```
├── android/
│   ├── build.gradle.kts
│   └── src/main/kotlin/com/loadingbar/generator/
│       ├── MainActivity.kt
│       ├── ui/screens/ (4 screens)
│       ├── ui/theme/
│       └── data/api/
├── backend/
│   ├── server.js
│   ├── package.json
│   └── .env.example
└── README.md
```

## Features Implemented

✅ Home Screen with navigation
✅ Editor Screen with AI prompt
✅ Real-time preview with Lottie
✅ Backend API integration
✅ Gemini 3.5 Flash integration
✅ Firebase Firestore setup
✅ Color customization
✅ Animation speed control

## Future Enhancements

- User authentication
- Loading bar templates
- Visual animation editor
- Community sharing
- Multiple animation styles
- Offline mode

## License

MIT License

---

**Made with ❤️ using Kotlin, Jetpack Compose, and Gemini 3.5 Flash**