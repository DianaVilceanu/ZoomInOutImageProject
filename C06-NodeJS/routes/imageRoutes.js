const express = require("express");
const multer = require("multer");
const mysql = require("mysql2");
const Image = require("../models/ImageModel");
const router = express.Router();

const storage = multer.memoryStorage();
const upload = multer({ storage: storage });


const db = mysql.createConnection({
    host: "mysql",
    user: "root",
    password: "password",
    database: "images_db",
});


router.get("/:id", async (req, res) => {
    try {
        const image = await Image.findById(req.params.id);
        if (!image) return res.status(404).json({ error: "Imaginea nu există" });

        res.set("Content-Type", image.contentType);
        res.send(image.data);
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});



router.post("/", upload.single("image"), async (req, res) => {
    if (!req.file) {
        return res.status(400).json({ error: "No image uploaded" });
    }


    const newImage = new Image({
        filename: req.file.originalname,
        data: req.file.buffer,
        contentType: req.file.mimetype,
    });

    try {
        const savedImage = await newImage.save();

   
        db.query(
            "INSERT INTO processed_images (filename, image) VALUES (?, ?)",
            [req.file.originalname, req.file.buffer],
            (err, result) => {
                if (err) {
                    return res.status(500).json({ error: err.message });
                }
                console.log("Image saved to MySQL");
            }
        );

        res.status(201).json({ message: "Image saved!", id: savedImage._id });
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

module.exports = router;
