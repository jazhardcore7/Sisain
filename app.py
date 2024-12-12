from tensorflow.keras.preprocessing.sequence import pad_sequences
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity
from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from typing import List
from ast import literal_eval
import tensorflow as tf
import pandas as pd
import numpy as np
import string

# dictionary that contain id for word recognized by the model
# e.g.: {'give': 2, 'recipe': 4, 'me': 5}
word2id = np.load('vocabs.npy', allow_pickle='TRUE').item()

recipe_path = './recipe.csv'
recipes_df = pd.read_csv(recipe_path)

# "cache" recipes list into vector
vectorizer = TfidfVectorizer()
recipes_vector = vectorizer.fit_transform(recipes_df['ingredients'])

# Definisikan skema input menggunakan Pydantic
class IngredientRequest(BaseModel):
    sentence: str

# Buat kelas untuk memuat model dan melakukan prediksi
class RecipeModel:
    def __init__(self, model_path: str):
        self.model = tf.keras.models.load_model(model_path)
        self.maxlen = 137 # panjang maximal sequence

    def sentences2sequences(self, sentences: str):
        sequences = []
        for sentence in sentences:
            trans = str.maketrans('', '', string.punctuation)
            words = sentence.translate(trans).split() # remove punctuation & split
            sequence = [word2id[word] if word in word2id else 0 for word in words]
            sequences.append(sequence)
        return sequences
    
    def predict_recipe(self, sentence: str):
        input_data = self.preprocess(sentence)
        predictions = self.model.predict(input_data)
        predictions = np.argmax(predictions, axis=-1)[0]

        # remove punctuation and split
        trans = str.maketrans('', '', string.punctuation)
        words = sentence.translate(trans).split()

        # List bahan makanan yang ter-extract: ['egg', 'milk']
        ingredients = [words[i] for i, tagid in enumerate(predictions) if tagid > 0] 

        # convert ingredients list ke string: ['egg', 'milk'] -> "egg milk"
        ingredients_str = ''
        if len(ingredients) == 0:
            ingredients_str = sentence
        else:
            ingredients_str = " ".join(ingredients)

        return self.postprocess(ingredients_str)
    
    def preprocess(self, sentence: str):
        # Implementasikan preprocessing yang diperlukan
        # Misalnya, ubah text menjadi vector/sequence of number
        # e.g.: "give me recipe from egg and milk" -> [2, 30, 5, 4, 6, 20, 11]
        sequence = self.sentences2sequences([sentence])
        sequence = pad_sequences(sequence, maxlen=self.maxlen, padding="post")

        return sequence
    
    def postprocess(self, predictions: str):
        # Implementasikan postprocessing untuk mengubah prediksi menjadi resep
        # Cari resep yang paling mirip dengan ingredients, hitung skor
        print(predictions)
        ingredients_vector = vectorizer.transform([predictions])
        score = cosine_similarity(ingredients_vector, recipes_vector).flatten()

         # Cari 3 besar skor terbaik
        top_indices = np.argpartition(score, -3)[-3:]  # Get indices of top 3 scores
        top_recipes = recipes_df.iloc[top_indices]  # Get the corresponding recipes

        results = []
        for index in top_indices:
            results.append({
                'recipe_name': recipes_df.iloc[index]['title'],
                'steps': literal_eval(recipes_df.iloc[index]['directions']),
            })

        return results

# Inisialisasi model
model = RecipeModel('./model.h5')

# Inisialisasi FastAPI
app = FastAPI()

# Definisikan endpoint untuk prediksi
@app.post("/predict")
async def get_recipe(ingredient_request: IngredientRequest):
    try:
        sentence = ingredient_request.sentence
        recipes = model.predict_recipe(sentence)
        return {
            "sentence": sentence,
            "recipes": recipes
        }
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))

# Jalankan server menggunakan uvicorn
if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8000)
