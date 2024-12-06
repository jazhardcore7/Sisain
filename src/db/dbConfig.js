const Firestore = require('@google-cloud/firestore');
const path = require('path');

const pathKey = path.resolve('./keyFile.json');
const idProject = process.env.PROJECT_ID || 'nutricipe-coba';

const db = new Firestore({
    projectId: idProject,
    keyFilename: pathKey,
});

// function add user
async function addUser(data) {
    try {
        const res = await db.collection('users').add(data);
        return res;
    } catch (error) {
        return error;
    }
}

// function read email
async function readUser(data) {
    try {
        const snapshot = await db.collection('users').where('email', '==', data.email).limit(1).get();
        if(snapshot.empty){
            return false;
        }
        return snapshot;
    } catch (error) {
        return error;
    }
}

//function check email
async function checkEmail(email) {
    try {
        let isUsed = false;
        const res = await db.collection('users').where('email', '==', email).get();
        if (!res.empty) {
            isUsed = true;
        }
        return isUsed;
    } catch (error) {
        return error;
    }
}

//function check user id
async function checkUser(id) {
    try {
        const result = await db.collection('users').doc(id).get();
        if (result.empty) {
            return res.status(404).json({
                error: true,
                message: 'User Not Found'
            });
        }
        return result;
    } catch (error) {
        return error;
    }
}

//function save history 
async function saveHistory(data) {
    try {
        const res = await db.collection('history').add(data);
        return res;
    } catch (error) {
        return error;
    }
}

//function update user
async function editName(id, name) {
    try {
        const res = await db.collection('users').doc(id).update(name);
        if (res.empty) {
            return res.status(400).json({
                error: true,
                message: 'Update failed, please try again'
            });
        }
        return res;
    } catch (error) {
        return error;
    }
}

//function read history
async function readHistory(req) {
    try {
        const id = req.userId;
        const page = req.query.page;
        const size = parseInt(req.query.size);
        const start = (page - 1) * size;

        const snapshot = await db.collection("history")
            .where("owner", "==", id)
            .orderBy("createdAt", "desc")
            .get();

        const historyList = [];
        snapshot.forEach((doc) => {
            const historyData = doc.data();
            historyData.id = doc.id;
            historyList.push(historyData);
        });

        const list = historyList.slice(start, start + size);
        return list;
    } catch (error) {
        return {
            error: true,
            message: 'Internal server error',
        };
    }
}

//function save receipe
async function saveRecipe(recipeFirst, recipeSecond) {
    try {
        const res = await db.collection('recipe').add(recipeFirst);
        const res2 = await db.collection('recipe').add(recipeSecond);
        if(!res || !res2){
            return false;
        }
        return true;
    } catch (error) {
        return error;
    }
}

//function get receipe
async function getRecipe(idHistory, userId) {
    try {
        const snapshot = await db.collection('recipe').where('idHistory', '==', idHistory).get();
        if (!snapshot.empty) {
            const recipeList = [];
            snapshot.forEach((doc) => {
                const recipeData = doc.data();
                if (recipeData.owner !== userId) {
                    recipeData.error = true; // Menandai akses tidak sah
                } else {
                    recipeData.error = false;
                }
                recipeData.id = doc.id;
                recipeList.push(recipeData);
            });
            return recipeList;
        }
        return false;
    } catch (error) {
        return false;
    }
}



module.exports = {
    addUser,
    readUser,
    checkEmail,
    checkUser,
    saveHistory,
    editName,
    readHistory,
    saveRecipe,
    getRecipe,
};


