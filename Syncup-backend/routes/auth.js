const router = require('express').Router();
const authController = require('../controllers/auth');

router.get('/leaderboard', authController.getLeaderBoard);
router.post('/signin', authController.signIn);


module.exports = router;
