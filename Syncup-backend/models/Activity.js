const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const activitySchema = new Schema({
  name: {
    type: Schema.Types.String,
    required: true
  },
  description: {
    type: Schema.Types.String
  },
  points: {
    type: Schema.Types.Number,
    required: true
  },

});


const Activity = mongoose.model('Activity', activitySchema);

Activity.seedFirst = async () => {
  try {
    let activities = await Activity.find();
    if (activities.length > 0) return;
    Activity.create({
      name: 'Animal lover',
      description: 'Take a photo of you petting your pet. If you do not own one, you will have to get creative',
      points: 15,
    });
    Activity.create({
      name: 'Castlemania',
      description: 'Build a castle out of kitchen glasses.',
      points: 25,
    });
    Activity.create({
      name: 'Extinct specimen',
      description: 'Reach a score of 1000 in the offline browser dinosaur game.',
      points: 18,
    });
    Activity.create({
      name: 'Mom\'s spaghetti',
      description: 'Cook a delicious meal with anything you have in the kitchen.',
      points: 30,
    });
    Activity.create({
      name: 'Jokester',
      description: 'Write the best joke you know on a piece of paper and hold it over your head.',
      points: 12,
    });
    return Activity.create({
      name: 'Master cleaner',
      description: 'Make sure your room is nice and tidy and take a picture.',
      points: 12,
    });
  } catch (e) {
    console.log(e);
  }
};


module.exports = Activity;
