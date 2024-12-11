const { db } = require("../config/firebase");

const recipeDetailController = {
  getRecipeDetail: async (req, res) => {
    try {
      const { id } = req.params;
      const recipeRef = db.collection("recipes").doc(id);
      const recipe = await recipeRef.get();

      if (!recipe.exists) {
        return res.status(404).json({
          success: false,
          message: "Recipe not found",
        });
      }

      const recipeData = {
        id: recipe.id,
        ...recipe.data(),
      };

      res.status(200).json({
        success: true,
        message: "Recipe detail retrieved successfully",
        data: recipeData,
      });
    } catch (error) {
      res.status(500).json({
        success: false,
        message: "Failed to get recipe detail",
        error: error.message,
      });
    }
  },
};

module.exports = recipeDetailController;
