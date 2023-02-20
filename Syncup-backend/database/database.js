const mongoose = require('mongoose');
const User = require('../models/User');
const Activity = require('../models/Activity');

mongoose.Promise = global.Promise;
module.exports = () => {
    let conString = `mongodb+srv://${process.env.DB_USERNAME}:${process.env.DB_PASSWORD}@cluster0.guudf9c.mongodb.net/Syncup`
    mongoose.set("strictQuery", true);
    mongoose.connect(conString, {
        useNewUrlParser: true,
        useUnifiedTopology: true ,
    });       
    const db = mongoose.connection;
    db.once('open', err => {
        if (err) throw err;
        Activity.seedFirst();
        User.seedAdminUser().then(() => {
            console.log('Database ready');
        }).catch((reason) => {
            console.log('Something went wrong');
            console.log(reason);
        });
    });
    db.on('error', reason => {
        console.log(reason);
    });
};