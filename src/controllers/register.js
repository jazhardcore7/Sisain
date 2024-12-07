const hash = require('../helper/hash');
const register = require('../db/dbConfig');
const validator = require('validator');

async function registerUser(req, res) {
    const { name, email, password, confirmPassword } = req.body;

    if (!name || !email || !password || !confirmPassword) {
        return res.status(400).send('Bad Request: Name, email, password, and confirm password are required');
    }

    if (!validator.isEmail(email)) {
        return res.status(400).json({ error: true, message: 'Invalid email format' });
    }

    if (password !== confirmPassword) {
        return res.status(400).json({ error: true, message: 'Password and confirm password do not match' });
    }

    if (!validator.isLength(password, { min: 6 })) {
        return res.status(400).json({ error: true, message: 'Password must be at least 6 characters long' });
    }

    try {
        const check = await register.checkEmail(email);
        if (check) {
            return res.status(409).json({ error: true, message: 'Email already in use' });
        }

        const hashed = await hash.hashPw(password);
        const data = {
            name: name,
            email: email,
            password: hashed
        };

        const result = await register.addUser(data);
        if (!result.id) {
            return res.status(500).json({ error: true, message: 'Failed to add user' });
        }

        return res.status(200).json({ error: false, message: 'User added successfully' });
    } catch (error) {
        console.error('Error registering user:', error);
        return res.status(500).json({ error: true, message: 'Internal Server Error' });
    }
}

module.exports = {
    registerUser
};
