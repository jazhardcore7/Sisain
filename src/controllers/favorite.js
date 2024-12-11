// Import Firestore
const { Firestore } = require("@google-cloud/firestore");
const firestore = new Firestore();

const fetchMenu = async (req, res) => {
  try {
    // Referensi ke koleksi "menu"
    const menuCollection = firestore.collection("menu");

    // Ambil 6 menu dari koleksi
    const menuSnapshot = await menuCollection.limit(6).get();

    // Cek apakah data ada
    if (menuSnapshot.empty) {
      return res.status(404).json({
        success: false,
        message: "No menu items found",
      });
    }

    // Format data menjadi array
    const menuList = menuSnapshot.docs.map((doc) => ({
      id: doc.id, // ID dokumen Firestore
      ...doc.data(), // Data isi dokumen
    }));

    // Kirim respons dengan data menu
    res.status(200).json({
      success: true,
      message: "Menu items fetched successfully",
      data: menuList,
    });
  } catch (error) {
    // Tangani error
    res.status(500).json({
      success: false,
      message: "Failed to fetch menu items",
      error: error.message,
    });
  }
};

module.exports = fetchMenu;
