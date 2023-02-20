const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const submissionSchema = new Schema({
  evidenceUrl: {
    type: Schema.Types.String,
    required: true
  },
  completedBy: {
    type:mongoose.Schema.Types.ObjectId,
	required: true,
    ref:'User'
  },
  activity: {
    type:mongoose.Schema.Types.ObjectId,
    ref:'Activity',
    required: true
  },
});



const Submission = mongoose.model('Submission', submissionSchema);

module.exports = Submission;
