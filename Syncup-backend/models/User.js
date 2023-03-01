const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const userSchema = new Schema({
  email: {
    type: Schema.Types.String,
    required: true
  },
  firstName: {
    type: Schema.Types.String
  },
  lastName: {
    type: Schema.Types.String
  },
  position: {
    type: Schema.Types.String
  },
  profileImageUrl: {
    type: Schema.Types.String
  },
  points: {
    type: Schema.Types.Number,
  },
  roles: [{type: Schema.Types.String, required: true}]
});


const User = mongoose.model('User', userSchema);

User.seedAdminUser = async () => {
  try {
    let users = await User.find();
    if (users.length > 0) return;
    return User.create({
      email: 'admin@admin.com',
      firstName: 'Admin4o',
      lastName: 'Adminov',
      position: 'Team leader',
      profileImageUrl: "",
      points: 123,
      roles: ['Admin']
    });
  } catch (e) {
    console.log(e);
  }
};

module.exports = User;