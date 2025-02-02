const mongoose = require("mongoose");

const SnmpSchema = new mongoose.Schema({
    os_name: String,
    cpu_usage: Number,
    ram_usage: Number
});

module.exports = mongoose.model("SnmpData", SnmpSchema);
