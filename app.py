from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from typing import List
import tensorflow as tf
import numpy as np

# Definisikan skema input menggunakan Pydantic
class IngredientRequest(BaseModel):
    ingredients: List[str]

# Buat kelas untuk memuat model dan melakukan prediksi
class RecipeModel:
    def __init__(self, model_path: str):
        self.model = tf.keras.models.load_model(model_path)
    
    def predict_recipe(self, ingredients: list):
        input_data = self.preprocess(ingredients)
        predictions = self.model.predict(input_data)
        return self.postprocess(predictions)
    
    def preprocess(self, ingredients: list):
        # Implementasikan preprocessing yang diperlukan
        # Misalnya, ubah daftar bahan menjadi fitur numerik
        ingredient_vector = np.zeros((1, 10))  # Asumsikan panjang vektor input adalah 10
        for i, ingredient in enumerate(ingredients):
            if i < 10:
                ingredient_vector[0, i] = len(ingredient)  # Misal mengisi vektor dengan panjang string bahan makanan
        return ingredient_vector
    
    def postprocess(self, predictions):
        # Implementasikan postprocessing untuk mengubah prediksi menjadi resep
        # Ini hanya contoh, Anda perlu menyesuaikan dengan output model Anda
        return {
            "recipe_name": "Example Recipe",
            "steps": ["Step 1", "Step 2", "Step 3"]
        }

# Inisialisasi model
model = RecipeModel("D:\File Faisal\API_Model\model.h5")

# Inisialisasi FastAPI
app = FastAPI()

# Definisikan endpoint untuk prediksi
@app.post("/predict")
async def get_recipe(ingredient_request: IngredientRequest):
    try:
        ingredients = ingredient_request.ingredients
        recipe = model.predict_recipe(ingredients)
        return {
            "ingredients": ingredients,
            "recipe": recipe
        }
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))

# Jalankan server menggunakan uvicorn
if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8000)
