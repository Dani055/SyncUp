const router = require('express').Router();
const feedController = require('../controllers/feed');
const isAuth = require('../middleware/is-auth');
const isAuthAndAdmin = require('../middleware/is-auth-and-admin');


// router.get('/cars', feedController.getCars);
// router.post('/car/create',isAuthAndAdmin, feedController.createCar);

module.exports = router;