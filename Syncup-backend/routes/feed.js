const router = require('express').Router();
const feedController = require('../controllers/feed');
const isAuth = require('../middleware/is-auth');


router.get('/activities',isAuth, feedController.getActivities);
router.get('/activities/:activityId',isAuth, feedController.getActivityById);
router.get('/activities/:activityId/submissions',isAuth, feedController.getSubmissionsForActivity);
router.post('/submission',isAuth, feedController.createSubmission);

module.exports = router;