const express = require('express');
const router = express.Router();

// Endpoint pentru MySQL
router.get('/mysql-data', (req, res) => {
    const query = 'SELECT * FROM tabela_ta'; 
    db.query(query, (err, results) => {
        if (err) {
            res.status(500).json({ error: 'Error fetching data from MySQL' });
            return;
        }
        res.json(results);
    });
});


// Endpoint pentru MongoDB
router.get('/mongodb-data', (req, res) => {
    res.json({ message: 'MongoDB data retrieved successfully!' });
});

module.exports = router;

