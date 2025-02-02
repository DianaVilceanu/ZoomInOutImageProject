const express = require('express');
const mysql = require('mysql2');
const mongoose = require('mongoose');
const bodyParser = require('body-parser');
const imageRoutes = require('./routes/imageRoutes');
const snmpRoutes = require('./routes/snmpRoutes');

const app = express();
const PORT = 3000;


app.use(bodyParser.json());


const db = mysql.createConnection({
    host: process.env.MYSQL_HOST || 'mysql',  
    user: process.env.MYSQL_USER || 'root',
    password: process.env.MYSQL_PASSWORD || 'root',
    database: process.env.MYSQL_DATABASE || 'images_db'
});

db.connect(err => {
    if (err) {
        console.error(' MySQL Connection Error:', err);
        process.exit(1);
    }
    console.log('Connected to MySQL Database');
});


mongoose.connect('mongodb://mongo-container:27017/snmp_data', {
    useNewUrlParser: true,
    useUnifiedTopology: true
}).then(() => console.log('Connected to MongoDB'))
  .catch(err => console.error('MongoDB Connection Error:', err));


app.get('/download/:id', (req, res) => {
    db.query('SELECT image FROM processed_images WHERE id = ?', [req.params.id], (err, result) => {
        if (err) {
            res.status(500).send('Error retrieving image');
            return;
        }
        if (!result.length) {
            res.status(404).send('Image not found');
            return;
        }
        res.contentType('image/bmp');
        res.send(result[0].image);
    });
});


app.use('/images', imageRoutes);
app.use('/snmp', snmpRoutes);


app.listen(PORT, () => console.log(`Server running on port ${PORT}`));
