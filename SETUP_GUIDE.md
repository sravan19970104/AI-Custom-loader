# AI Custom Loader - Complete Setup Guide

## Prerequisites
- Android Studio (latest)
- Node.js 16+
- Firebase Account
- Google Gemini API Key

## Android App Setup

### Step 1: Configure Backend URL
Edit `android/src/main/kotlin/com/loadingbar/generator/data/api/ApiClient.kt`:
```kotlin
private const val BASE_URL = "http://your-backend-url.com/api/"
```

### Step 2: Add Firebase Configuration
1. Download `google-services.json` from Firebase Console
2. Place in `android/app/` directory
3. Build and run: `./gradlew installDebug`

## Backend Setup

### Step 1: Install Dependencies
```bash
cd backend
npm install
```

### Step 2: Configure Environment
```bash
cp .env.example .env
```

Edit `.env` with your credentials:
- `GEMINI_API_KEY` - From Google AI Studio
- Firebase credentials from Firebase Console

### Step 3: Firebase Service Account
1. Download service account key from Firebase
2. Save as `firebase-key.json` in backend directory

### Step 4: Start Backend
```bash
npm start
```

## API Testing

### Generate Loader
```bash
curl -X POST http://localhost:3000/api/generate-loader \
  -H "Content-Type: application/json" \
  -d '{
    "prompt": "Neon futuristic spinner",
    "speed": 1000,
    "color": "#6200EE"
  }'
```

### Health Check
```bash
curl http://localhost:3000/api/health
```

## Troubleshooting

**Android: API Connection Error**
- Check backend is running
- Verify BASE_URL is correct
- Check firewall settings

**Backend: Gemini API Error**
- Verify API key is valid
- Check API quotas
- Ensure correct model name

**Firebase Connection Error**
- Verify service account credentials
- Check Firebase project ID
- Ensure Firestore is enabled

## Next Steps

1. Test locally first
2. Deploy backend to cloud (Firebase, Heroku, etc.)
3. Update BASE_URL in Android app
4. Build APK and test on device
5. Publish to Google Play Store
