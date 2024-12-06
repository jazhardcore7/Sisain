const express = require('express');
const router = express.Router();
const register = require('../controllers/register');
const login = require('../controllers/login');
const { updateName } = require('../controllers/updateUser');
const { getProfile } = require('../controllers/profile');
const authMiddleware = require('../middleware/authMiddleware')

router.post('/register', register.registerUser);
router.post('/login', login.loginUser);
router.get('/profile', authMiddleware.authMiddleware, getProfile);
router.put('/updateName', authMiddleware.authMiddleware, updateName);


module.exports = router;

