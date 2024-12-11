const express = require("express");
const cors = require("cors");
const bodyParser = require("body-parser");
const router = require("./src/routes/router");

const app = express();
const port = 5000;

// Konfigurasi CORS
const corsOptions = {
  origin: "*",
  credentials: true,
  optionsSuccessStatus: 200,
};

app.use(cors(corsOptions));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

// Rute utama aplikasi
app.use("/sisain", router);

app.get("/", (req, res) => {
  res.send("Welcome to sisain backend!");
});

// Middleware untuk menangani rute yang tidak ditemukan
app.use((req, res, next) => {
  res.status(404).send("Route not found");
});

// Middleware untuk menangani error
app.use((err, req, res, next) => {
  console.error(err.stack);
  res.status(500).send("Internal Server Error");
});

app.listen(port, () => {
  console.log("sisain app listening on port ${port}");
});