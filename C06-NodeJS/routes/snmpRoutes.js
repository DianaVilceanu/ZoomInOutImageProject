const express = require("express");
const router = express.Router();
const SnmpData = require("../models/SnmpModel");

router.get("/", async (req, res) => {
    try {
        const data = await SnmpData.find();
        res.json(data);
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

router.post("/", async (req, res) => {
    const { os_name, cpu_usage, ram_usage } = req.body;
    const snmpEntry = new SnmpData({ os_name, cpu_usage, ram_usage });

    try {
        const savedData = await snmpEntry.save();
        res.status(201).json(savedData);
    } catch (err) {
        res.status(400).json({ error: err.message });
    }
});

module.exports = router;
