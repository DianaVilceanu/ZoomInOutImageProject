const mongoose = require("mongoose");

const ImageSchema = new mongoose.Schema({
    filename: String,
    data: Buffer,
    contentType: String
});

module.exports = mongoose.model("Image", ImageSchema);
