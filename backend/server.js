const express = require('express');
const cors = require('cors');
const axios = require('axios');
const dotenv = require('dotenv');
const admin = require('firebase-admin');

dotenv.config();
const app = express();

app.use(cors());
app.use(express.json({ limit: '50mb' }));

const serviceAccount = require('./firebase-key.json');
admin.initializeApp({
    credential: admin.credential.cert(serviceAccount)
});

const db = admin.firestore();
const GEMINI_API_KEY = process.env.GEMINI_API_KEY;
const GEMINI_API_URL = 'https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash:generateContent';

app.get('/api/health', (req, res) => {
    res.json({ status: 'ok', timestamp: Date.now() });
});

app.post('/api/generate-loader', async (req, res) => {
    try {
        const { prompt, speed, color } = req.body;
        if (!prompt) return res.status(400).json({ success: false, message: 'Prompt required' });

        const structuredPrompt = `Generate a Lottie animation JSON for a loading bar:
- Description: ${prompt}
- Duration: ${speed}ms
- Color: ${color}

Return ONLY valid Lottie JSON.`;

        const geminiResponse = await axios.post(
            `${GEMINI_API_URL}?key=${GEMINI_API_KEY}`,
            { contents: [{ parts: [{ text: structuredPrompt }] }] },
            { headers: { 'Content-Type': 'application/json' } }
        );

        const geminiText = geminiResponse.data.candidates[0].content.parts[0].text;
        let animationJson;
        try {
            const jsonMatch = geminiText.match(/\{[\s\S]*\}/);
            animationJson = JSON.parse(jsonMatch ? jsonMatch[0] : geminiText);
        } catch (e) {
            animationJson = createFallbackLoader(color, speed);
        }

        res.json({ success: true, animationJson: JSON.stringify(animationJson), message: 'Generated' });
    } catch (error) {
        res.status(500).json({ success: false, message: `Error: ${error.message}` });
    }
});

app.post('/api/save-loader', async (req, res) => {
    try {
        const { uid, name, description, animationJson } = req.body;
        if (!uid || !name || !animationJson) {
            return res.status(400).json({ success: false, message: 'Missing fields' });
        }

        const loaderRef = db.collection('users').doc(uid).collection('loaders').doc();
        await loaderRef.set({ name, description, animationJson, createdAt: new Date() });
        res.json({ success: true, loaderId: loaderRef.id });
    } catch (error) {
        res.status(500).json({ success: false, message: `Error: ${error.message}` });
    }
});

app.get('/api/loaders/:uid', async (req, res) => {
    try {
        const snapshot = await db.collection('users').doc(req.params.uid).collection('loaders').orderBy('createdAt', 'desc').get();
        const loaders = [];
        snapshot.forEach(doc => loaders.push({ id: doc.id, ...doc.data() }));
        res.json({ success: true, loaders });
    } catch (error) {
        res.status(500).json({ success: false, message: `Error: ${error.message}` });
    }
});

app.delete('/api/loaders/:uid/:loaderId', async (req, res) => {
    try {
        await db.collection('users').doc(req.params.uid).collection('loaders').doc(req.params.loaderId).delete();
        res.json({ success: true, message: 'Deleted' });
    } catch (error) {
        res.status(500).json({ success: false, message: `Error: ${error.message}` });
    }
});

function createFallbackLoader(color, speed) {
    return {
        v: '5.7.13', fr: 30, ip: 0, op: 60, w: 200, h: 200, nm: 'Loading Bar',
        layers: [{
            ddd: 0, ind: 1, ty: 4, nm: 'Spinner', sr: 1,
            ks: { o: { a: 0, k: 100 }, r: { a: 1, k: [{ t: 0, s: [0] }, { t: 60, s: [360] }] }, p: { a: 0, k: [100, 100, 0] } },
            shapes: [{ ty: 'gr', it: [{ ty: 'el', s: { a: 0, k: [40, 40] } }, { ty: 'st', c: { a: 0, k: hexToRgb(color) }, w: { a: 0, k: 4 } }] }]
        }]
    };
}

function hexToRgb(hex) {
    const result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex);
    return result ? [parseInt(result[1], 16) / 255, parseInt(result[2], 16) / 255, parseInt(result[3], 16) / 255, 1] : [1, 1, 1, 1];
}

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => console.log(`Server running on port ${PORT}`));