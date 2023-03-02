const Activity = require('../models/Activity');
const Submission = require('../models/Submission');
const User = require('../models/User');

module.exports = {
  getActivities: async (req, res, next) => {
    try {
    let activities = await Activity.find()
    let newActivites = []
    let submission = null;
    for(let activity of activities){
      submission = await Submission.findOne({completedBy: req.userId, activity: activity._id});
      let newActivity = {...activity._doc}
      if(submission != null){
        newActivity["isCompleted"] = true;
      }
      else{
        newActivity["isCompleted"] = false;
      }
      newActivites.push(newActivity)
    }

      res
          .status(200)
          .json({ message: 'Fetched activities', activities: newActivites });
      
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
    let submission = await Submission.findOne({completedBy: req.userId, activity: activity._id}).populate("completedBy").populate("activity")
    
    let isCompleted = false;
    if(submission != null){
      isCompleted = true;
    }
      res
          .status(200)
          .json({ message: 'Fetched activity', activity, isCompleted, mySubmission: submission });
      
    } catch (error) {
      if (!error.statusCode) {
        error.statusCode = 500;
      }
      next(error);
    }
  },
  getSubmissionsForBingo: async (req, res, next) => {
    try {
    let submissions = await Submission.find().populate("completedBy").populate("activity")
      res
          .status(200)
          .json({ message: 'Fetched submissions for bingo', submissions });
      
    } catch (error) {
      if (!error.statusCode) {
        error.statusCode = 500;
      }
      next(error);
    }
  },
  createSubmission: async (req, res, next) => {
    try {
      const submissionObj = {"evidenceUrl": req.body.evidenceUrl, "activity": req.body.activityId}
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