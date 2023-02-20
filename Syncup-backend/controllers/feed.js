const Activity = require('../models/Activity');
const Submission = require('../models/Submission');
const User = require('../models/User');

module.exports = {
  getActivities: async (req, res, next) => {
    try {
    let activities = await Activity.find()
      res
          .status(200)
          .json({ message: 'Fetched activities', activities });
      
    } catch (error) {
      if (!error.statusCode) {
        error.statusCode = 500;
      }
      next(error);
    }
  },
  getActivityById: async (req, res, next) => {
    try {
    let activity = await Activity.findById(req.params.activityId)
    let submission = await Submission.findOne({completedBy: req.userId, activity: activity._id})
    const isCompleted = false;
    if(submission != null){
      activity.isCompleted = true;
    }
      res
          .status(200)
          .json({ message: 'Fetched activity', activity,isCompleted, mySybmission: submission });
      
    } catch (error) {
      if (!error.statusCode) {
        error.statusCode = 500;
      }
      next(error);
    }
  },
  getSubmissionsForActivity: async (req, res, next) => {
    try {
    let activity = await Activity.findById(req.params.activityId)
    let submissions = await Submission.find({activity: activity._id})
      res
          .status(200)
          .json({ message: 'Fetched submissions for activity', submissions });
      
    } catch (error) {
      if (!error.statusCode) {
        error.statusCode = 500;
      }
      next(error);
    }
  },
  createSubmission: async (req, res, next) => {
    try {
      const submissionObj = req.body;
      submissionObj.completedBy = req.userId;
      let submission = await Submission.create(submissionObj)
      let user = await User.findById(req.userId)
      let activity = await Activity.findById(submission.activity)
      user.points += activity.points
      await user.save();

      res.status(200)
      .json({
        message: 'Submission posted'
      })

    } catch (error) {
      if (!error.statusCode) {
        error.statusCode = 500;
      }
      next(error);
    }
  },
}